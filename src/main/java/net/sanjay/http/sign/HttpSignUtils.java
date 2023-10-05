/**
 * 
 */
package net.sanjay.http.sign;

import static net.sanjay.utils.EncodingUtils.hex;
import static net.sanjay.utils.EncodingUtils.hmacSha256;
import static net.sanjay.utils.EncodingUtils.sha256;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import net.sanjay.utils.EncodingUtils;
import net.sanjay.utils.StringUtils;

/**
 * @author sanjaykdev
 *
 */
public class HttpSignUtils {

	public static final String NEW_LINE = "\\n\n";

	private HttpSignUtils() {
	}

	/**
	 * Canonicalize HTTP Request.
	 * <p>
	 * Canonical Request Format:
	 * 
	 * <pre>
	 * {@code
	 *		<HTTPMethod>\n
	 *		<CanonicalURI>\n
	 *		<CanonicalQueryString>\n
	 *		<CanonicalHeaders>\n
	 * 		<HashedPayload>
	 * }
	 * </pre>
	 *
	 * @param httpRequest
	 *
	 * @return
	 */
	public static final String canonicalizeHttpRequest(String httpRequest) {
		String canonicalHttpRequestString = null;

		CanonicalHttpRequest canonicalHttpRequest = null;
		String payload = "";

		if (httpRequest != null) {

			canonicalHttpRequest = new CanonicalHttpRequest();

			String[] lines = httpRequest.split("[\\r\\n]");
			boolean headersEnded = false;
			for (String line : lines) {
				line = line.trim();
				boolean isBlankLine = line.startsWith("\\r\\n");
				if (isBlankLine) {
					headersEnded = true;
				}

				else if (HttpUtils.isStartLine(line)) {
					canonicalHttpRequest = parseStartLine(line);
				}
				else if (!headersEnded) { // Is Header Line
					canonicalHttpRequest = parseHeaderLine(line, canonicalHttpRequest);
				} else { // Is Body
					payload = line;
				}

			}

			if (canonicalHttpRequest != null) {
				String hashedPayload = hashedValue(payload);
				canonicalHttpRequest.setHashedPayload(hashedPayload);

				String canonicalQueryString = getCanonicalQueryString(
						canonicalHttpRequest.getQueryParams());
				String canonicalHeaders = getCanonicalHeaders(
						canonicalHttpRequest.getHeaders());

				StringBuilder sb = new StringBuilder();
				sb.append(canonicalHttpRequest.getHttpMethod());
				sb.append(NEW_LINE);
				sb.append(canonicalHttpRequest.getCanonicalUri());
				sb.append(NEW_LINE);
				sb.append(canonicalQueryString);
				sb.append(NEW_LINE);
				sb.append(canonicalHeaders);
				sb.append(NEW_LINE);
				sb.append(canonicalHttpRequest.getHashedPayload());

				canonicalHttpRequestString = sb.toString();
			}

		}

		return canonicalHttpRequestString;
	}

	public static final String getCanonicalQueryString(Map<String, List<String>> queryParams) {
		String canonicalQueryString = "";

		if (queryParams != null) {
			Set<String> paramNamesSet = queryParams.keySet();
			List<String> paramNamesList = new ArrayList<>(paramNamesSet);
			paramNamesList.sort(Comparator.naturalOrder());
			int numParams = paramNamesList.size();

			StringBuilder sb = new StringBuilder();

			int count = 0;
			for (String paramName : paramNamesList) {
				count++;
				if ((count > 1) && (count <= numParams)) {
					sb.append("&");
				}
				sb.append((uriEncode(paramName)));
				sb.append("=");
				List<String> values = queryParams.get(paramName);
				String valuesString = values.stream().map(v -> uriEncode(v)).collect(Collectors.joining("%2C"));
				sb.append(valuesString);

			}

			canonicalQueryString = sb.toString();
		}

		return canonicalQueryString;
	}

	/**
	 * @return the canonicalHeaders
	 */
	public static final String getCanonicalHeaders(Map<String, List<String>> headers) {
		String canonicalHeaders = "";

		if (headers != null) {
			Set<String> headerNamesSet = headers.keySet();
			List<String> headerNamesList = new ArrayList<>(headerNamesSet);
			headerNamesList.sort(Comparator.naturalOrder());
			StringBuilder sb = new StringBuilder();
			for (String headerName : headerNamesList) {
				sb.append(lowerCase(headerName));
				List<String> values = headers.get(headerName);
				String valuesString = values.stream().map(v -> trim(v)).collect(Collectors.joining(","));
				sb.append(valuesString);
				sb.append(NEW_LINE);
			}
			canonicalHeaders = sb.toString();
		}

		return canonicalHeaders;
	}

	public static final CanonicalHttpRequest parseStartLine(String startLine) {
		CanonicalHttpRequest canonicalHttpRequest = null;

		if (HttpUtils.isStartLine(startLine)) {
			canonicalHttpRequest = new CanonicalHttpRequest();
			String httpMethod = HttpUtils.parseHttpMethod(startLine);
			canonicalHttpRequest.setHttpMethod(httpMethod);
			String uri = HttpUtils.parseUri(startLine);
			canonicalHttpRequest.setCanonicalUri(uri);
			String queryString = HttpUtils.parseQueryString(startLine);
			if (queryString != null) {
				Map<String, List<String>> queryParams = HttpUtils.parseQueryParams(queryString);
				canonicalHttpRequest.setQueryParams(queryParams);
			}
		}

		return canonicalHttpRequest;
	}

	public static final CanonicalHttpRequest parseHeaderLine(
			String headerLine, CanonicalHttpRequest canonicalHttpRequest) {

		if (canonicalHttpRequest == null) {
			canonicalHttpRequest = new CanonicalHttpRequest();
		}

		Map<String, String> header = HttpUtils.parseHeader(headerLine);
		if (header != null) {
			for (Entry<String, String> headerEntry : header.entrySet()) {
				String headerName = lowerCase(headerEntry.getKey());
				String headerValue = headerEntry.getValue();
				headerValue = headerValue.trim();
				headerValue = headerValue.replace("\\r\\n", "");
				canonicalHttpRequest.addHeader(headerName, headerValue);
			}
		}

		return canonicalHttpRequest;
	}


	public static final String hashedValue(String text) {
		String hashedValue = null;

		hashedValue = lowerCase(hex(sha256(text)));

		return hashedValue;
	}

	public static final String hmacHashedValue(String key, String text) {
		String hmacHashedValue = null;

		hmacHashedValue = StringUtils.lowerCase(hex(hmacSha256(key, text)));

		return hmacHashedValue;
	}

	public static String lowerCase(String s) {
		return StringUtils.lowerCase(s);
	}

	public static String trim(String s) {
		return StringUtils.trim(s);
	}

	public static String uriEncode(String s) {
		return EncodingUtils.uriEncode(s);
	}

}
