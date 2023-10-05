/**
 * 
 */
package tests.sanjay.http.sign;

import static tests.sanjay.http.sign.HttpSignUtils.*;

/**
 * @author sanjaykdev
 *
 */
public interface IHttpSign {
	
	public static final String DEFAULT_CURRENT_DATE_STRING = "20230801";
	
	public default String getSignature(String accessKey, String secretKey, String httpRequest) {
		String signature = null;
		
		String dateString = formattedCurrentDateString();
		String signingKey = signingKey(secretKey, accessKey, dateString);
		String stringToSign = stringToSign(httpRequest);
		
		signature = hmacHashedValue(signingKey, stringToSign);

		return signature;
	}
	
	public default String canonicalizeHttpRequest(String httpRequest) {
		return HttpSignUtils.canonicalizeHttpRequest(httpRequest);
	}
	
	public default String stringToSign(String httpRequest) {
		String stringToSign = null;
		
		stringToSign = hashedValue(canonicalizeHttpRequest(httpRequest));
		
		return stringToSign;
	}
	
	public default String dateKey(String secretKey, String dateString) {
		String dateKey = null;
		
		dateKey = hmacHashedValue(secretKey, dateString);
		
		return dateKey;
	}
	
	public default String signingKey(String dateKey, String accessKey) {
		String signingKey = null;
				
		signingKey = hmacHashedValue(dateKey, accessKey);
		
		return signingKey;
	}

	public default String signingKey(String secretKey, String accessKey, String dateString) {
		String signingKey = null;
				
		String dateKey = hmacHashedValue(secretKey, dateString);
		signingKey = signingKey(dateKey, accessKey);
		
		return signingKey;
	}
	
	public default String formattedCurrentDateString() {
		return DEFAULT_CURRENT_DATE_STRING;
	}

}
