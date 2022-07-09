/*******************************************************************************
 *  Imixs Workflow 
 *  Copyright (C) 2001, 2011 Imixs Software Solutions GmbH,  
 *  http://www.imixs.com
 *  
 *  This program is free software; you can redistribute it and/or 
 *  modify it under the terms of the GNU General Public License 
 *  as published by the Free Software Foundation; either version 2 
 *  of the License, or (at your option) any later version.
 *  
 *  This program is distributed in the hope that it will be useful, 
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of 
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU 
 *  General Public License for more details.
 *  
 *  You can receive a copy of the GNU General Public
 *  License at http://www.gnu.org/licenses/gpl.html
 *  
 *  Project: 
 *      http://www.imixs.org
 *      http://java.net/projects/imixs-workflow
 *  
 *  Contributors:  
 *      Imixs Software Solutions GmbH - initial API and implementation
 *      Ralph Soika - Software Developer
 *******************************************************************************/

package org.imixs.workflow.wopi;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.TimeZone;
import java.util.logging.Logger;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RunAs;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.imixs.workflow.FileData;
import org.imixs.workflow.ItemCollection;
import org.imixs.workflow.WorkflowKernel;
import org.imixs.workflow.engine.DocumentService;

/**
 * The WopiHostService implements a Rest Service for the Wopi protocol.
 * <p>
 * A WOPI host does not need to implement every WOPI operation. WOPI hosts
 * express their capabilities using properties in CheckFileInfo, such as
 * SupportsLocks. In addition, WOPI actions specify the WOPI operations that
 * must be supported in order to use that action, in the form of Action
 * requirements.
 * <p>
 * This service implements a minimum set of operations required in order to
 * support the two major WOPI scenarios - viewing and editing.
 * <p>
 * The files are contained in a workflow instance (workitem) or in a snapshot
 * ItemCollection.
 * <p>
 * The /id/ in the Rest API URLs is a combination a workitem $uniqueID followed
 * by the filename
 * <p>
 * <code> /wopi/xxxxxxx-0000-0000-0000-yyyy_{FILENAME}</code>
 * <p>
 * If a snapshot exits than the API automatically loads the file content from
 * the snapshot.
 * 
 * 
 * @see https://wopi.readthedocs.io/projects/wopirest/en/latest/
 * @see https://sdk.collaboraonline.com/docs/installation/CODE_Docker_image.html
 * @see https://sdk.collaboraonline.com/docs/How_to_integrate.html
 * @see https://sdk.collaboraonline.com/docs/Step_by_step_tutorial.html
 * @author rsoika
 * @version 1.0
 * 
 */
@Stateless
@LocalBean
@DeclareRoles({ "org.imixs.ACCESSLEVEL.MANAGERACCESS" })
@RunAs("org.imixs.ACCESSLEVEL.MANAGERACCESS")
@Path("/wopi")
@Produces({ MediaType.APPLICATION_JSON })
public class WopiHostService {

    @javax.ws.rs.core.Context
    private HttpServletRequest servletRequest;

    private static Logger logger = Logger.getLogger(WopiHostService.class.getName());

    @Inject
    WopiAccessHandler wopiAccessHandler;

    @Inject
    DocumentService documentService;
    
    @Inject
    @ConfigProperty(name = "wopi.postmessageorigin")
    Optional<String> postMessageOrigin;

    /**
     * GET Method just to test the service readiness
     * 
     * @param requestBodyStream
     * @return
     */
    @GET
    @Path("/ping")
    public String ping() {
        String message = "wopi service ping: " + System.currentTimeMillis();
        logger.info(message);
        return message;
    }

    /**
     * Returns the file details.
     * <p>
     * The method expects an id consisting of a $uniqueid followed by the filename
     * <p>
     * <code> /wopi/xxxxxxx-0000-0000-0000-yyyy_{FILENAME}</code>
     * <p>
     * 
     * @param name     - A string that specifies a file
     * @param response - The response to a CheckFileInfo call is JSON (as specified
     *                 in RFC 4627) containing a number of properties, most of which
     *                 are optional.
     * @see: https://wopi.readthedocs.io/projects/wopirest/en/latest/files/CheckFileInfo.html#checkfileinfo
     * @return
     * 
     */
    @GET
    @Path("/{uniqueid : ([0-9a-f]{8}-.*|[0-9a-f]{11}-.*)}/files/{file}")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response getFileInfo(@PathParam("uniqueid") String uniqueid, @PathParam("file") String file,
            @QueryParam("access_token") String accessToken) {

        // clean unexpected query params
        accessToken = wopiAccessHandler.purgeAccessToken(accessToken);

        // validate access_token
        JsonObject acessTokenPayload = wopiAccessHandler.validateAccessToken(accessToken);
        if (acessTokenPayload == null) {
            logger.warning("...invalid access_token!");
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        ItemCollection workitem = null;
        workitem = documentService.load(uniqueid);
        if (workitem == null) {
            logger.warning("wokitem '" + uniqueid + "' not found!");
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        FileData fileData = null;
        fileData = loadFileData(uniqueid, file, accessToken);
        if (fileData == null) {
            logger.warning("wokitem '" + uniqueid + "' no fileData object not found!");
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        
        logger.info("......GET getFileInfo: " + uniqueid + "/" + file);


        // create the json object
        JsonObjectBuilder builder = null;
        try {
            builder = buildJsonFileInfo(fileData, workitem.getItemValueDate(WorkflowKernel.MODIFIED),
                    acessTokenPayload);
        } catch (NoSuchAlgorithmException e) {
            logger.warning("unable to compute Sha256 from content: " + e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        JsonObject result = builder.build();
        return Response.ok(result.toString(), MediaType.APPLICATION_JSON).build();

    }

    /**
     * Opens a file and returns the file content as an octet-stream
     * <p>
     * The method expects an id consisting of a $uniqueid followed by the filename
     * <p>
     * <code> /wopi/xxxxxxx-0000-0000-0000-yyyy_{FILENAME}</code>
     * <p>
     * If the file content was already modified before, the content is fetched from the 
     * local wopi file cache
     * 
     * @param name - the file name to be opened
     * @return
     */
    @GET
    @Path("/{uniqueid : ([0-9a-f]{8}-.*|[0-9a-f]{11}-.*)}/files/{file}/contents")
    public Response getFileContents(@PathParam("uniqueid") String uniqueid, @PathParam("file") String file,
            @QueryParam("access_token") String accessToken) {

        // clean unexpected query params
        accessToken = wopiAccessHandler.purgeAccessToken(accessToken);

        // validate access_token
        JsonObject acessTokenPayload = wopiAccessHandler.validateAccessToken(accessToken);
        if (acessTokenPayload == null) {
            logger.warning("...invalid access_token!");
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        // load the FileData
        logger.info("......GET getFileContents: " + uniqueid + "/" + file);
        FileData fileData = loadFileData(uniqueid, file, accessToken);
        if (fileData == null) {
            logger.warning("no file data found '" + uniqueid + "'!");
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        try {
            logger.info("......sending " + fileData.getContent().length + " bytes...");
            // load file
            Response.ResponseBuilder builder = Response.ok(fileData.getContent(), MediaType.APPLICATION_OCTET_STREAM)
                    .header("Content-Disposition",
                            "attachment;filename=" + new String(file.getBytes("UTF-8"), "ISO-8859-1"))
                    .header("Content-Length", fileData.getContent());

            return builder.status(Response.Status.OK).build();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    /**
     * This method stores a modified file content form the wopi client into the local wopi file cache.
     * <p>
     * The method expects a $uniqueID and filename
     * <p>
     * <code> /wopi/xxxxxxx-0000-0000-0000-yyyy/files/{FILENAME}/contents</code>
     * <p>
     * The method returns a json file info object
     * <p>
     * 
     * @param name
     * @param content
     */
    @POST
    @PUT
    @Path("/{uniqueid : ([0-9a-f]{8}-.*|[0-9a-f]{11}-.*)}/files/{file}/contents")
    public Response postFileContents(@PathParam("uniqueid") String uniqueid, @PathParam("file") String file,
            InputStream contentStream, @QueryParam("access_token") String accessToken, @Context UriInfo info) {
        
        logger.info("......POST postFileContents: " + uniqueid + "/" + file);
        // analyze header X-LOOL-WOPI-Timestamp, X-LOOL-WOPI-IsAutosave, X-LOOL-WOPI-IsExitSave
        // We do ignroe the X-LOOL-WOPI-IsExitSave event
        String wopiHeader=servletRequest.getHeader("X-LOOL-WOPI-IsExitSave");
        if (wopiHeader!=null && "true".equalsIgnoreCase(wopiHeader)) {
            logger.info("...ignore X-LOOL-WOPI-IsExitSave = " + wopiHeader);
            return  Response.ok().build();
        }
        
        // clean unexpected query params
        accessToken = wopiAccessHandler.purgeAccessToken(accessToken);
        

        // validate access_token
        JsonObject acessTokenPayload = wopiAccessHandler.validateAccessToken(accessToken);
        if (acessTokenPayload == null) {
            logger.warning("...invalid access_token!");
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        FileData fileData = null;
        byte[] content;
        try {
            content = readAllBytes(contentStream);
            logger.finest("...receifed " + content.length + " bytes");
            // cache the file data temporary 
            fileData = new FileData(file, content, null, null);
            wopiAccessHandler.cacheFileData(accessToken, fileData);

        } catch (IOException e) {
            logger.warning("failed to cache document data: " + e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        // build a new JSON file info object
        JsonObjectBuilder builder = null;
        try {
            builder = buildJsonFileInfo(fileData, new Date(), acessTokenPayload);
        } catch (NoSuchAlgorithmException e) {
            logger.warning("unable to compute Sha256 from content: " + e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        JsonObject result = builder.build();
        return Response.ok(result.toString(), MediaType.APPLICATION_JSON).build();
    }

    /**
     * This method returns the fileData object form a workitem for a corresponding
     * file name. If the fileData object in the workitem has no content than the
     * method loads an optional snapshot workItem.
     * <p>
     * If a accessToken is provided, the method tries first to fetch the file
     * content from the local wopi file cache
     * <p>
     * 
     * The method returns null if no FileData object exists
     * 
     * @param uniqueid
     * @param accessToken
     * @param file        - file name
     * @return FileData object for the given filename.
     * @throws IOException
     */
    private FileData loadFileData(String uniqueid, String filename, String accessToken) {
        ItemCollection workitem;
        String snapshotID;
        FileData result;

        // do we have an accessToken?
        if (accessToken != null && !accessToken.isEmpty()) {
            // try to load the file form cache....
            result = wopiAccessHandler.fetchFileData(accessToken, filename);
            if (result != null) {
                return result;
            }
        }

        // load content form workitem....
        workitem = documentService.load(uniqueid);
        if (workitem == null) {
            return null;
        }
        result = workitem.getFileData(filename);
        // do we have a file content?
        if (result != null && result.getContent() != null && result.getContent().length > 3) {
            return result;
        }
        // no content found, try to load a snapshot
        snapshotID = workitem.getItemValueString("$snapshotid");
        ItemCollection snapshot = documentService.load(snapshotID);
        if (snapshot != null) {
            result = snapshot.getFileData(filename);
            // do we have a file content?
            if (result != null && result.getContent() != null && result.getContent().length > 3) {
                return result;
            }

        }

        // no data found!
        return null;
    }

    /**
     * Helper method to read bytes form a inputStream into an array
     * 
     * @param inputStream
     * @return
     * @throws IOException
     */
    private byte[] readAllBytes(InputStream inputStream) throws IOException {
        final int bufLen = 4 * 0x400; // 4KB
        byte[] buf = new byte[bufLen];
        int readLen;
        IOException exception = null;

        try {
            try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
                while ((readLen = inputStream.read(buf, 0, bufLen)) != -1)
                    outputStream.write(buf, 0, readLen);

                return outputStream.toByteArray();
            }
        } catch (IOException e) {
            exception = e;
            throw e;
        } finally {
            if (exception == null)
                inputStream.close();
            else
                try {
                    inputStream.close();
                } catch (IOException e) {
                    exception.addSuppressed(e);
                }
        }
    }

    /**
     * This method builds a fileInfo json object for the wopi client
     * 
     * @param fileData
     * @param modified
     * @param accessToken
     * @return
     * @throws NoSuchAlgorithmException
     */
    private JsonObjectBuilder buildJsonFileInfo(FileData fileData, Date modified, JsonObject acessTokenPayload)
            throws NoSuchAlgorithmException {
        // create the json object
        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add("BaseFileName", fileData.getName());
        builder.add("Size", fileData.getContent().length);

        // from the access token we extract the username
        builder.add("OwnerId", acessTokenPayload.getString("userid"));
        builder.add("UserId", acessTokenPayload.getString("userid"));
        builder.add("UserFriendlyName", acessTokenPayload.getString("username"));

        // Date modified = workitem.getItemValueDate(WorkflowKernel.MODIFIED);
        builder.add("Version", modified.getTime());

        // modifed to ISO 8601 String
        SimpleDateFormat sdf;
        sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        sdf.setTimeZone(TimeZone.getTimeZone("CET"));
        builder.add("LastModifiedTime", sdf.format(modified));

        // compute SHA-256

        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(fileData.getContent());
        // This bytes[] has bytes in decimal format;
        // Convert it to hexadecimal format
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < hash.length; i++) {
            sb.append(Integer.toString((hash[i] & 0xff) + 0x100, 16).substring(1));
        }
        builder.add("Sha256", sb.toString());

        // builder.add("AllowExternalMarketplace",true);
        builder.add("UserCanWrite", true);
        builder.add("SupportsUpdate", true);
        // builder.add("EditNotificationPostMessage", true);
        
        
        if (postMessageOrigin.isPresent() && !postMessageOrigin.get().isEmpty()) {
            logger.info("......setting postMessageOrigin=" + postMessageOrigin.get() );
            builder.add("PostMessageOrigin", postMessageOrigin.get());
        }
        

        return builder;
    }
}
