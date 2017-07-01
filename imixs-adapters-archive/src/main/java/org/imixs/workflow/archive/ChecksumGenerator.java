package org.imixs.workflow.archive;


import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

/**
 * This class is used to compute the checksum for the content of a file.
 * 
 * @see: http://stackoverflow.com/questions/304268/getting-a-files-md5-checksum-in-java
 * 
 * @version 1.0
 * @author rsoika
 *
 */
public class ChecksumGenerator {
 
	/**
	 * elegant way to calculate the checksum on the fly while reading from a
	 * stream...
	 *  
	 * works with JDK 8
	 * 
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws IOException
	 */
	public static String computeMD5() throws NoSuchAlgorithmException, IOException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		try (InputStream is = Files.newInputStream(Paths.get("file.txt"));
				DigestInputStream dis = new DigestInputStream(is, md)) {
			/* Read decorated stream (dis) to EOF as normal... */
		}
		byte[] digest = md.digest();

		return digest.toString();
	}

	/**
	 * Generates a MD5 from a byte array
	 * 
	 * @param b
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static String generateMD5(byte[] b) throws NoSuchAlgorithmException {
		byte[] hash_bytes = MessageDigest.getInstance("MD5").digest(b);
		return DatatypeConverter.printHexBinary(hash_bytes);
	}

	public static boolean verifyMD5(String expected, byte[] b) throws NoSuchAlgorithmException {
		byte[] hash = MessageDigest.getInstance("MD5").digest(b);
		String actual = DatatypeConverter.printHexBinary(hash);
		return expected.equalsIgnoreCase(actual);
	}
}
