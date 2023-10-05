/**
 * 
 */
package net.sanjay.http.sign;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author sanjaykdev
 *
 */
public class HttpUtils {

	protected static final String[] HTTP_METHODS = {
			"GET", "HEAD", "POST", "PUT", "DELETE", "CONNECT", "OPTIONS", "TRACE" };
	
	protected static final Set<String> HTTP_METHODS_SET =
			new HashSet<>(Arrays.asList(HTTP_METHODS));

	/**
	 * 
	 */
	private HttpUtils() {
	}

	public static final boolean isBlankLine(String line) {
		boolean isBlankLine = false;

		if ((line != null) && line.isEmpty()) {
			isBlankLine = true;
		}

		return isBlankLine;
	}

	public static final boolean isStartLine(String line) {
		boolean isStartLine = false;

		if ((line != null) && startsWithHttpMethod(line)) {
			isStartLine = true;
		}

		return isStartLine;
	}

	public static final boolean startsWithHttpMethod(String line) {
		boolean startsWithHttpMethod = false;

		if (line != null) {
			String httpMethod = parseHttpMethod(line);
			if (httpMethod != null) {
				startsWithHttpMethod = true;
			}
		}

		return startsWithHttpMethod;
	}

	public static final String parseHttpMethod(String line) {
		String httpMethod = null;

		if (line != null) {
			String[] tokens = line.split(" ");
			if (tokens.length > 0) {
				String firstToken = tokens[0];
				if ((firstToken != null) && HTTP_METHODS_SET.contains(firstToken)) {
					httpMethod = firstToken;
				}
			}
		}

		return httpMethod;
	}

	public static final String parseUri(String line) {
		String uri = null;

		if (line != null) {
			String absolutePath = parseAbsolutePath(line);
			if ((absolutePath != null) && absolutePath.contains("?")) {
				uri = absolutePath.substring(0, absolutePath.indexOf("?"));
			}
		}

		return uri;
	}

	public static final String parseQueryString(String line) {
		String queryString = null;

		if (line != null) {
			String absolutePath = parseAbsolutePath(line);
			if ((absolutePath != null) && absolutePath.contains("?")) {
				queryString = absolutePath.substring(absolutePath.indexOf("?") + 1);
			}
		}

		return queryString;
	}

	public static final Map<String, List<String>> parseQueryParams(String queryString) {
		Map<String, List<String>> queryParams = null;

		if (queryString != null) {
			queryParams = new HashMap<>();
			String[] tokens = queryString.split("&");
			for (String queryParam : tokens) {
				String[] qpTokens = queryParam.split("=");
				if (qpTokens.length > 1) {
					String paramName = qpTokens[0];
					String paramValue = qpTokens[1];

					List<String> paramValues = queryParams.computeIfAbsent(paramName, k -> new ArrayList<>());
					paramValues.add(paramValue);
				}
			}
		}

		return queryParams;
	}

	public static final String parseAbsolutePath(String line) {
		String absolutePath = null;

		if (line != null) {
			String[] tokens = line.split(" ");
			if (tokens.length > 1) {
				String secondToken = tokens[1];

				if ((secondToken != null) && secondToken.startsWith("/")) {
					absolutePath = secondToken;
				}
			}
		}

		return absolutePath;
	}

	public static final Map<String, String> parseHeader(String line) {
		Map<String, String> header = null;

		if (line != null) {
			header = new HashMap<>();
			String headerName = null;
			String headerValue = null;
			int colonIndex = line.indexOf(":");
			if (colonIndex > 0) {
				headerName = line.substring(0, colonIndex + 1);
				headerName = headerName.trim();
				if (line.length() > colonIndex + 1) {
					headerValue = line.substring(colonIndex + 1);
					headerValue = headerValue.trim();
				}
			}

			if ((headerName != null) && !headerName.isEmpty()) {
				header.put(headerName, headerValue);
			}

		}

		return header;
	}

}
