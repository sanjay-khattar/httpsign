/**
 * 
 */
package net.sanjay.utils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author sanjaykdev
 *
 */
public class EncodingUtils {

	public static final String SHA_256 = "SHA-256";

	public static final String HEX_FORMAT = "%02x";

	private EncodingUtils() {
	}

	public static final String hex(byte[] bytes) {
		String hex = null;

		if (bytes != null) {
			StringBuilder sb = new StringBuilder();

			for (byte b : bytes) {
				sb.append(String.format(HEX_FORMAT, b & 0xff));
			}

			hex = sb.toString();
		}

		return hex;
	}

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

	public static final byte[] hmacSha256(String key, String text) {
		byte[] hmacSha256 = null;

		if (text != null) {
			hmacSha256 = sha256(text);
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
