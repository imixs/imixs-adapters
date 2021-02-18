/*  
 *  Imixs-Workflow 
 *  
 *  Copyright (C) 2001-2020 Imixs Software Solutions GmbH,  
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
 *      https://www.imixs.org
 *      https://github.com/imixs/imixs-workflow
 *  
 *  Contributors:  
 *      Imixs Software Solutions GmbH - Project Management
 *      Ralph Soika - Software Developer
 */

package org.imixs.workflow.poi.excel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.imixs.workflow.FileData;
import org.imixs.workflow.ItemCollection;
import org.imixs.workflow.engine.ReportService;
import org.imixs.workflow.exceptions.QueryException;

/**
 * The SpreadsheetRestService provides a Rest API resource to export the result of a Imixs Report into a .xlsx spredsheet file.
 * The implementation is based on Apache POI.
 * 
 * @author rsoika
 * @version 1.0
 */
@Path("/poi")
@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_HTML, MediaType.TEXT_XML })
@Stateless
public class SpreadsheetRestService {

	@Inject
	private ReportService reportService;

	@Context
	private HttpServletRequest servletRequest;

	private static Logger logger = Logger.getLogger(SpreadsheetRestService.class.getName());

	/**
	 * Returns a excel file with the report data
	 * 
	 * @param name reportname or uniqueid of report
	 * @return
	 */

	@SuppressWarnings("unchecked")
	@GET
	@Produces({ "application/vnd.ms-excel" })
	@Path("report/{name}.xlsx")
	public Response getPOIResult(@PathParam("name") String reportName,
			@DefaultValue("1000") @QueryParam("pageSize") int pageSize,
			@DefaultValue("0") @QueryParam("pageIndex") int pageIndex, @QueryParam("sortBy") String sortBy,
			@QueryParam("sortReverse") boolean sortReverse, @DefaultValue("") @QueryParam("encoding") String encoding,
			@Context UriInfo uriInfo, @Context HttpServletResponse servlerResponse) {
		List<ItemCollection> data = null;

		try {
			ItemCollection report = reportService.findReport(reportName);
			if (report == null) {
				// report not found
				logger.warning("report " + reportName + " not found.");
				return Response.status(Response.Status.NOT_FOUND).build();
			}

			// read report params...
			List<List<String>> attributes = (List<List<String>>) report.getItemValue("attributes");
			List<String> items = new ArrayList<String>();
			List<String> labels = new ArrayList<String>();
			for (List<String> attribute : attributes) {
				items.add(attribute.get(0));
				String label = attribute.get(0);
				if ((attribute.size() >= 2) && !(attribute.get(1).isEmpty())) {
					label = attribute.get(1);
				}
				labels.add(label);
			}

			// execute report
			Map<String, String> params = getQueryParams(uriInfo);
			data = reportService.getDataSource(report, pageSize, pageIndex, sortBy, sortReverse, params);

			// build excel with POI...
			FileData fileData = buildExceObject(data, items, labels, reportName + ".xlsx");
			if (fileData != null) {
				Response.ResponseBuilder builder = Response.ok(fileData.getContent(), fileData.getContentType());
				return builder.build();
			} else {
				logger.warning("unable to open excel file");
				// workitem not found
				return Response.status(Response.Status.NOT_FOUND).build();
			}

		} catch (IOException | QueryException e) {
			logger.severe("unable to generate excel file - error: " + e.getMessage());
			e.printStackTrace();
			return Response.status(Response.Status.NOT_ACCEPTABLE).build();
		}

	}

	/**
	 * This method build a XSSFWorkbook based on a given data collection and returns
	 * a FileData object representing the file.
	 * 
	 * @param data
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("rawtypes")
	private FileData buildExceObject(List<ItemCollection> data, List<String> items, List<String> labels,
			String filename) throws IOException {

		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Workflow Data");

		Object[][] datatable = new Object[data.size() + 1][items.size()];

		// build header
		int irow = 0;
		int icol = 0;
		for (String label : labels) {
			datatable[irow][icol] = label;
			icol++;
		}
		irow++;
		// build body
		for (ItemCollection doc : data) {
			icol = 0;
			for (String item : items) {
				Object value = null;
				List valueList = doc.getItemValue(item);
				if (valueList == null || valueList.size() == 0) {
					value = "";
				} else {
					value = valueList.get(0);
					if (value == null) {
						value = "";
					}
				}
				datatable[irow][icol] = value;
				icol++;
			}
			irow++;
		}

		Object[][] datatypes = { { "Datatype", "Type", "Size(in bytes)" }, { "int", "Primitive", 2 },
				{ "float", "Primitive", 4 }, { "double", "Primitive", 8 }, { "char", "Primitive", 1 },
				{ "String", "Non-Primitive", "No fixed size" } };

		int rowNum = 0;
		System.out.println("Creating excel");

		for (Object[] workitem : datatable) {
			Row row = sheet.createRow(rowNum++);
			int colNum = 0;
			for (Object field : workitem) {
				Cell cell = row.createCell(colNum++);
				if (field instanceof String) {
					cell.setCellValue((String) field);
				} else if (field instanceof Integer) {
					cell.setCellValue((Integer) field);
				} else if (field instanceof Long) {
					cell.setCellValue((Long) field);
				} else if (field instanceof Float) {
					cell.setCellValue((Float) field);
				} else if (field instanceof Double) {
					cell.setCellValue((Double) field);
				}
			}
		}

		// Creates a ByteArrayOutputStream with default size
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		// FileOutputStream outputStream = new FileOutputStream(filename);
		workbook.write(out);
		workbook.close();

		byte[] content = out.toByteArray();

		FileData fileData = new FileData(filename, content, "application/vnd.ms-excel", null);

		out.close();
		return fileData;

	}

	/**
	 * This method parses the query Params of a Request URL and adds params to a
	 * given JPQL Query. In addition the method replace dynamic date values in the
	 * JPQLStatement
	 * 
	 * 
	 * @param uriInfo
	 * @return
	 */

	/**
	 * Extracts the query parameters and returns a hashmap with key value pairs
	 * 
	 * @param aQuery
	 * @param uriInfo
	 * @return
	 */
	private Map<String, String> getQueryParams(UriInfo uriInfo) {
		// test each given QueryParam if it is contained in the EQL Query...
		MultivaluedMap<String, String> mvm = uriInfo.getQueryParameters();
		Map<String, String> result = new HashMap<String, String>();
		Set<String> keys = mvm.keySet();
		Iterator<String> iter = keys.iterator();
		while (iter.hasNext()) {
			// read key
			String sKeyName = iter.next().toString();
			result.put(sKeyName, mvm.getFirst(sKeyName));
		}

		return result;
	}

}
