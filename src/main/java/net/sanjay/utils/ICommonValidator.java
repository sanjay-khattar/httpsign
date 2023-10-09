/**
 * 
 */
package net.sanjay.utils;

/**
 * Interface for common Validation Routines.
 *
 * @author sanjaykdev
 *
 */
public interface ICommonValidator {

	
	/**
	 * Check whether each one of the given 'args' is non-null and non-empty.
	 *
	 * @param args
	 *
	 * @return
	 */
	public static boolean areNonNullAndNonEmpty(String... args) {
		boolean areNonNullAndNonEmpty = false;

		for (String arg : args) {
			boolean isNonNullAndNonEmpty = isNonNullAndNonEmpty(arg);
			if (isNonNullAndNonEmpty) {
				areNonNullAndNonEmpty = true;
			}
			else {
				break;
			}
		}
		
		return areNonNullAndNonEmpty;
	}

	/**
	 * Check whether the given string is non-null and non-empty.
	 *
	 * @param s
	 *
	 * @return
	 */
	public static boolean isNonNullAndNonEmpty(String s) {
		boolean isNonNullAndNonEmpty = false;

		if (s != null) {
			s = s.trim();
			isNonNullAndNonEmpty = !s.isEmpty();
		}

		return isNonNullAndNonEmpty;
	}

}
