/**
 * 
 */
package net.sanjay.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * @author sanjaykdev
 *
 */
class URIEncoderTest {

	static final String URI_1 = "a";
	
	static final String ENCODED_URI_1 = "a";
	
	static final String URI_2 = "1234";
	
	static final String ENCODED_URI_2 = "1234";

	static final String URI_3 = "1%C2%B11";
	
	static final String ENCODED_URI_3 = "1%C2%B11";
	
	static final String URI_4 = "all+please";
	
	static final String ENCODED_URI_4 = "all%20please";
	
	static final String URI_5 = "1±2";
	
	static final String ENCODED_URI_5 = "1%C2%B12";
	
	static final String URI_6 = "%2TRUE%";
	
	static final String ENCODED_URI_6 = "%252TRUE%25";
	
	
	static final String[][] ENCODE_URI_TEST_DATA = {
			{URI_1, ENCODED_URI_1},
			{URI_2, ENCODED_URI_2},
			{URI_3, ENCODED_URI_3},
			{URI_4, ENCODED_URI_4},
			{URI_5, ENCODED_URI_5},
			{URI_6, ENCODED_URI_6}
	};

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
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	/**
	 * Parameterized Test method for {@link sanjay.tests.utils.URIEncoder#encode(java.lang.String, java.lang.String)}.
	 */
	@ParameterizedTest
	@MethodSource("encodeUriTestDataProvider")
	void testEncodeStringString(String uri, String expectedEncodedUri) {
		String actualEncodedUri = EncodingUtils.uriEncode(uri);

		assertEquals(expectedEncodedUri, actualEncodedUri);
	}

	static String[][] encodeUriTestDataProvider() {
		return ENCODE_URI_TEST_DATA;
	}

}
