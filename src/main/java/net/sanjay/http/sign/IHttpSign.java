/**
 * 
 */
package net.sanjay.http.sign;

import static net.sanjay.http.sign.HttpSignUtils.*;

/**
 * @author sanjaykdev
 *
 */
public interface IHttpSign {

	public static final String DEFAULT_SECRET_MASK = "######";

	public static final String DEFAULT_CURRENT_DATE_STRING = "20230801";

	public default String formattedCurrentDateString() {
		return DEFAULT_CURRENT_DATE_STRING;
	}

	public default String getSignature(
		String accessKey, String secretKey, String httpRequest) {

		String signature = null;

		boolean isValidHttpSignRequest = IHttpSignValidator.isValidHttpSignRequest(
				accessKey, secretKey, httpRequest);

		if (isValidHttpSignRequest) {
			String dateString = formattedCurrentDateString();
			String signingKey = signingKey(secretKey, accessKey, dateString);
			String stringToSign = stringToSign(httpRequest);

			signature = hmacSha256Value(signingKey, stringToSign);
		}
		else {
			String message = String.join("\\n",
					accessKey == null ? null : DEFAULT_SECRET_MASK,
							secretKey == null ? null : DEFAULT_SECRET_MASK,
									httpRequest);
			message = "Not a valid Http Sign Request:\n" + message;
			throw new HttpSignException(message);
		}

		return signature;
	}

	public default String canonicalizeHttpRequest(String httpRequest) {
		return HttpSignUtils.canonicalizeHttpRequest(httpRequest);
	}

	public default String stringToSign(String httpRequest) {
		String stringToSign = null;

		stringToSign = sha256HashValue(canonicalizeHttpRequest(httpRequest));

		return stringToSign;
	}

	public default String dateKey(String secretKey, String dateString) {
		String dateKey = null;

		dateKey = hmacSha256Value(secretKey, dateString);

		return dateKey;
	}

	public default String signingKey(String dateKey, String accessKey) {
		String signingKey = null;

		signingKey = hmacSha256Value(dateKey, accessKey);

		return signingKey;
	}

	public default String signingKey(
			String secretKey, String accessKey, String dateString) {

		String signingKey = null;

		String dateKey = dateKey(secretKey, dateString);
		signingKey = signingKey(dateKey, accessKey);

		return signingKey;
	}

}
