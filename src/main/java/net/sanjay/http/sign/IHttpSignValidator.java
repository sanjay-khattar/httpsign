/**
 * 
 */
package net.sanjay.http.sign;

import net.sanjay.utils.ICommonValidator;

/**
 * @author sanjaykdev
 *
 */
public interface IHttpSignValidator extends ICommonValidator {
	
	public static boolean isValidHttpSignRequest(
			String accessKey, String secretKey, String httpRequest) {
		boolean isValidHttpSignRequest = false;
		
		isValidHttpSignRequest = ICommonValidator.areNonNullAndNonEmpty(
				accessKey, secretKey, httpRequest);
				
		return isValidHttpSignRequest;
	}

}
