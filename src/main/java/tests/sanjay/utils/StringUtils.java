/**
 * 
 */
package tests.sanjay.utils;

/**
 * @author sanjaykdev
 *
 */
public class StringUtils {

	/**
	 * 
	 */
	private StringUtils() {}

	public static String lowerCase(String s) {
		return (s == null) ? null : s.toLowerCase();
	}

	public static String trim(String s) {
		return (s == null) ? null : s.trim().replace("\\r\\n", "").replaceAll("^[\\r\\n]", "").replaceAll("[\\r\\n]$", "");
	}

}
