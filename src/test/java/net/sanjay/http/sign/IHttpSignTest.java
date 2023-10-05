/**
 * 
 */
package net.sanjay.http.sign;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * @author sanjaykdev
 *
 */
class IHttpSignTest {

	static final String DEFAULT_TEST_ACCESS_KEY = "AKIAIOSFODNN7EXAMPLE";
	static final String DEFAULT_TEST_SECRET_KEY = "wJalrXUtnFEMI/K7MDENG/bPxRfiCYEXAMPLEKEY";
	static final String DEFAULT_TEST_CURRENT_DATE_STRING = "20230801";
	
	static final String HTTP_REQUEST_1 = "GET /resource?test=true&mix=1%C2%B11 HTTP/1.1\\r\\n\n"
			+ "Host: test.com\\r\\n\n"
			+ "Timestamp: 2023-08-03T10:24:03.012Z\\r\\n\n"
			+ "\\r\\n";
	
	static final String CANONICAL_REQUEST_1 = "GET\\n\n"
			+ "/resource\\n\n"
			+ "mix=1%C2%B11&test=true\\n\n"
			+ "host:test.com\\n\n"
			+ "timestamp:2023-08-03T10:24:03.012Z\\n\n"
			+ "\\n\n"
			+ "e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855";
	
	static final String STRING_TO_SIGN_1 = "0c5a284aca21eb658a5e4503729f23c47a648e6acaf4613e917d3406e6e8cec0";
	
	static final String SIGNATURE_1 = "48c48534128e1603216519035b52821c1c945c563f4d06031369b0552396635e";
	
	
	static final String HTTP_REQUEST_2 = "POST /resource//posts?test=2&example=all+please&1234=4321&test=1 HTTP/1.1\\r\\n\n"
			+ "Host: test.com\\r\\n\n"
			+ "Choice: C\\r\\n\n"
			+ "Choice: A\\r\\n\n"
			+ "Choice: B\\r\\n\n"
			+ "Content-Length: 25\\r\\n\n"
			+ "\\r\\n\n"
			+ "{\"some_key\":\"some_value\"}";
	
	static final String CANONICAL_REQUEST_2 = "POST\\n\n"
			+ "/resource//posts\\n\n"
			+ "1234=4321&example=all%20please&test=2%2C1\\n\n"
			+ "choice:C,A,B\\n\n"
			+ "content-length:25\\n\n"
			+ "host:test.com\\n\n"
			+ "\\n\n"
			+ "43ee763040973ca602549c94c5357a41c280afbb54e48d436af88f4e40d73081";
	
	static final String STRING_TO_SIGN_2 = "28fe39b38e2590cbc242dd417f604ca4a6fe91fd9572d8f47520efedd49670e0";
	
	static final String SIGNATURE_2 = "18e53de99fb8cf5824fc879336a12927dcf7f6d7c42607f87a02a13f690134b1";
	
	
	static final String HTTP_REQUEST_3 = "POST /resource/123/comments?test=%2TRUE%&evaluation=1±2 HTTP/1.1\\r\\n\n"
			+ "Host: test.com\\r\\n\n"
			+ "Content-Length: 65\\r\\n\n"
			+ "Content-Type: text/plain\\r\\n\n"
			+ "\\r\\n\n"
			+ "this is a sentence.\\r\\nthis sentence is on a new line.\\r\\nso is this.";
	
	static final String CANONICAL_REQUEST_3 = "POST\\n\n"
			+ "/resource/123/comments\\n\n"
			+ "evaluation=1%C2%B12&test=%252TRUE%25\\n\n"
			+ "content-length:65\\n\n"
			+ "content-type:text/plain\\n\n"
			+ "host:test.com\\n\n"
			+ "\\n\n"
			+ "a41088b4f429f0a67ce7c5a2b8507d24ae475f3a93e0840a0986fc4e45ff0487";
	
	static final String STRING_TO_SIGN_3 = "5fc252d8fa4e4335f8e22b6109a59922858c2c0b3e71405096ea2fd5f68c667b";
	
	static final String SIGNATURE_3 = "b73c62f23924c051464a4342ed26389c9e68182a8601c701820c5155d4acbb22";
	
	static final String[][] testData = {
			{HTTP_REQUEST_1, CANONICAL_REQUEST_1, STRING_TO_SIGN_1, SIGNATURE_1},
			{HTTP_REQUEST_2, CANONICAL_REQUEST_2, STRING_TO_SIGN_2, SIGNATURE_2},
			{HTTP_REQUEST_3, CANONICAL_REQUEST_3, STRING_TO_SIGN_3, SIGNATURE_3}		
	};
	
	String testAccessKey = DEFAULT_TEST_ACCESS_KEY;
	String testSecretKey = DEFAULT_TEST_SECRET_KEY;

	
	private IHttpSign httpSign;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		httpSign = new HttpSign();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}
	
	/**
	 * Parameterized Test method for {@link sanjay.tests.http.IHttpSign#canonicalizeHttpRequest(java.lang.String)}.
	 */
	@ParameterizedTest
	@MethodSource("canonicalTestDataProvider")
	void testCanonicalizeHttpRequest(String httpRequest, String expectedCanonicalRequest) {
	
		String actualCanonicalRequest = httpSign.canonicalizeHttpRequest(httpRequest);
		
		assertEquals(expectedCanonicalRequest, actualCanonicalRequest);
	}

	/**
	 * Parameterized Test method for {@link sanjay.tests.http.IHttpSign#stringToSign(java.lang.String)}.
	 */
	@ParameterizedTest
	@MethodSource("stringToSignTestDataProvider")
	void testStringToSign(String httpRequest, String expectedStringToSign) {
		
		String actualStringToSign = httpSign.stringToSign(httpRequest);
		
		assertEquals(expectedStringToSign, actualStringToSign);
	}

	/**
	 * Parameterized Test method for {@link sanjay.tests.http.IHttpSign#getSignature(java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@ParameterizedTest
	@MethodSource("signatureTestDataProvider")
	void testGetSignature(String httpRequest, String expectedSignature) {

		String actualSignature = httpSign.getSignature(
				testAccessKey, testSecretKey, httpRequest);

		assertEquals(expectedSignature, actualSignature);
	}

	/**
	 * Test method for {@link sanjay.tests.http.IHttpSign#dateKey(java.lang.String, java.util.Date)}.
	 */
	@Disabled("Disabled until resolved")
	@Test
	void testDateKey() {
		String testSecretKey = DEFAULT_TEST_SECRET_KEY;
		String testCurrentDateString = DEFAULT_TEST_CURRENT_DATE_STRING;
		

		String expectedDateKey = "bedqjqjhnlqwjklwq";
		
		String actualDateKey = httpSign.dateKey(testSecretKey, testCurrentDateString);
		
		assertEquals(expectedDateKey, actualDateKey);
		
	}

	/**
	 * Test method for {@link sanjay.tests.http.IHttpSign#signingKey(java.lang.String, java.lang.String)}.
	 */
	@Disabled("Disabled until resolved")
	@Test
	void testSigningKey() {
		String testCurrentDateString = DEFAULT_TEST_CURRENT_DATE_STRING;
		

		String expectedSigningKey = "28cfe47c386456f844def6a497e09cb7de1a52569bd65449792938acf550ca34";

		String actualSigningKey = httpSign.signingKey(testSecretKey, testAccessKey, testCurrentDateString);
		
		assertEquals(expectedSigningKey, actualSigningKey);
	}
	
	/**
	 * Returns a String Array of HTTP Requests, and corresponding expected signatures.
	 */
	static String[][] signatureTestDataProvider() {
		return extractColumns(testData, 0, 3);
	}

	/**
	 * Returns a String Array of HTTP Requests, and corresponding expected signatures.
	 */
	static String[][] canonicalTestDataProvider() {
		return extractColumns(testData, 0, 1);
	}
	
	/**
	 * Returns a String Array of HTTP Requests, and corresponding expected signatures.
	 */
	static String[][] stringToSignTestDataProvider() {
		return extractColumns(testData, 0, 2);
	}
	
	/**
	 * Returns a String SubArray for Tests.
	 */
	static String[][] extractColumns(final String[][] inputArray, final int... columnIndices) {
		int numColumnIndices = columnIndices.length;
		return Stream.of(inputArray).map(d -> {
			String[] extractedColumnsArray = new String[numColumnIndices];
			int i = 0;
			for (int index : columnIndices) {
				extractedColumnsArray[i++] = d[index];
			}
			return extractedColumnsArray;
		}).collect(Collectors.toList()).toArray(new String[0][0]);
	}
	

}
