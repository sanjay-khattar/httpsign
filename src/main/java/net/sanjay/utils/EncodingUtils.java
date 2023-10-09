/**
 * 
 */
package net.sanjay.utils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author sanjaykdev
 *
 */
public class EncodingUtils {

	public static final String SHA_256 = "SHA-256";

	public static final String HEX_FORMAT = "%02x";

	private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();

	private EncodingUtils() {}

	public static String hex(byte[] bytes) {
	    char[] hexChars = new char[bytes.length * 2];
	    for (int j = 0; j < bytes.length; j++) {
	        int v = bytes[j] & 0xFF;
	        hexChars[j * 2] = HEX_ARRAY[v >>> 4];
	        hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
	    }
	    return new String(hexChars);
	}


	/**
	 * Calculates the hash digest of the given 'text'
	 * using the 256 bit version of the Secure Hashing Algorithm (SHA).
	 *
	 * @param text
	 *
	 * @return
	 */
	public static final byte[] sha256(String text) {

		byte[] sha256 = null;

		if (text != null) {
			try {
				final MessageDigest digest = MessageDigest.getInstance(SHA_256);
				sha256 = digest.digest(text.getBytes(StandardCharsets.UTF_8));
			} catch (NoSuchAlgorithmException e) {
				throw new EncodingException(e);
			}
		}

		return sha256;
	}

	/**
	 * Computes an HMAC of the given text'
	 * by using the SHA256 algorithm with the 'secretKey' provided.
	 *
	 * @param secretKey
	 * @param text
	 *
	 * @return
	 */
	public static final byte[] hmacSha256(String secretKey, String text) {
		byte[] hmacSha256 = null;

		if ((secretKey != null) && (text != null)) {
			String algorithm = "HmacSHA256";

			try {
				Mac mac = Mac.getInstance("HmacSHA256");
				SecretKeySpec sks = new SecretKeySpec(
						secretKey.getBytes(StandardCharsets.UTF_8), algorithm);
				mac.init(sks);
				hmacSha256 = mac.doFinal(text.getBytes(StandardCharsets.UTF_8));
			} catch (Exception e) {
				throw new EncodingException("Failed to generate HMAC-SHA256.", e);
			}
		}

		return hmacSha256;
	}

	public static String lowerCase(String s) {
		return StringUtils.lowerCase(s);
	}

	public static String trim(String s) {
		return StringUtils.trim(s);
	}

	public static final String uriEncode(String s) {
		String uriEncoded = null;

		try {
			uriEncoded = URIEncoder.encode(s, StandardCharsets.UTF_8.toString());
		} catch (UnsupportedEncodingException e) {
			throw new EncodingException(e);
		}
		
		return uriEncoded;
	}


}
