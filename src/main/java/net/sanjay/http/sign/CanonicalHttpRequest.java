/**
 * 
 */
package net.sanjay.http.sign;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author sanjaykdev
 *
 */
public class CanonicalHttpRequest {

	private String httpMethod;

	private String canonicalUri;

	private Map<String, List<String>> queryParams;

	private Map<String, List<String>> headers;

	private String hashedPayload;

	/**
	 * 
	 */
	public CanonicalHttpRequest() {
		super();
	}

	/**
	 * @return the httpMethod
	 */
	public String getHttpMethod() {
		return httpMethod;
	}

	/**
	 * @param httpMethod the httpMethod to set
	 */
	public void setHttpMethod(String httpMethod) {
		this.httpMethod = httpMethod;
	}

	/**
	 * @return the canonicalUri
	 */
	public String getCanonicalUri() {
		return canonicalUri;
	}

	/**
	 * @param canonicalUri the canonicalUri to set
	 */
	public void setCanonicalUri(String canonicalUri) {
		this.canonicalUri = canonicalUri;
	}

	/**
	 * @return the queryParams
	 */
	public Map<String, List<String>> getQueryParams() {
		return queryParams;
	}

	/**
	 * @param queryParams the queryParams to set
	 */
	public void setQueryParams(Map<String, List<String>> queryParams) {
		this.queryParams = queryParams;
	}

	/**
	 * @return the headers
	 */
	public Map<String, List<String>> getHeaders() {
		return headers;
	}

	/**
	 * @param headers the headers to set
	 */
	public void setHeaders(Map<String, List<String>> headers) {
		this.headers = headers;
	}

	/**
	 * @return the hashedPayload
	 */
	public String getHashedPayload() {
		return hashedPayload;
	}

	public boolean addQueryParam(String name, String value) {
		boolean added = false;

		if (queryParams == null) {
			queryParams = new HashMap<>();
		}

		List<String> values = queryParams.get(name);

		if (values == null) {
			values = new ArrayList<>();
		}

		added = values.add(value);

		return added;
	}

	public boolean addHeader(String name, String value) {
		boolean added = false;

		if (headers == null) {
			headers = new HashMap<>();
		}

		List<String> values = headers.computeIfAbsent(name, k -> new ArrayList<>());
		added = values.add(value);

		return added;
	}

	/**
	 * @param hashedPayload the hashedPayload to set
	 */
	public void setHashedPayload(String hashedPayload) {
		this.hashedPayload = hashedPayload;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CanonicalHttpRequest [httpMethod=");
		builder.append(httpMethod);
		builder.append(", canonicalUri=");
		builder.append(canonicalUri);
		builder.append(", queryParams=");
		builder.append(queryParams);
		builder.append(", headers=");
		builder.append(headers);
		builder.append(", hashedPayload=");
		builder.append(hashedPayload);
		builder.append("]");
		return builder.toString();
	}

}
