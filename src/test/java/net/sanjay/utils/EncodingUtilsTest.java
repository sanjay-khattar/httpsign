/**
 * 
 */
package net.sanjay.utils;

import static net.sanjay.utils.EncodingUtils.hex;
import static net.sanjay.utils.EncodingUtils.hmacSha256;
import static net.sanjay.utils.EncodingUtils.lowerCase;
import static net.sanjay.utils.EncodingUtils.sha256;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * @author sanjaykdev
 *
 */
class EncodingUtilsTest {

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
	 * Test method for {@link net.sanjay.utils.EncodingUtils#hex(byte[])}.
	 */
	@Test
	void testHex() {
		assertTrue(true);
	}

	/**
	 * Parameterized Test method for {@link net.sanjay.utils.EncodingUtils#sha256(java.lang.String)}.
	 */
	@ParameterizedTest
	@MethodSource("sha256TestDataProvider")
	void testSha256(String text, String expectedHexSha256Digest) {

		String actualHexSha256Digest = lowerCase(hex(sha256(text)));

		assertEquals(expectedHexSha256Digest, actualHexSha256Digest);
	}

	/**
	 * Parameterized Test method for {@link net.sanjay.utils.EncodingUtils#hmacSha256(java.lang.String, java.lang.String)}.
	 */
	@ParameterizedTest
	@MethodSource("hmacSha256TestDataProvider")
	void testHmacSha256(String key, String text, String expectedHmacSha256) {

		String actualHmacSha256 = lowerCase(hex(hmacSha256(key, text)));

		assertEquals(expectedHmacSha256, actualHmacSha256);
	}

	/**
	 * Test method for {@link net.sanjay.utils.EncodingUtils#lowerCase(java.lang.String)}.
	 */
	@Test
	void testLowerCase() {
		assertTrue(true);
	}

	/**
	 * Test method for {@link net.sanjay.utils.EncodingUtils#trim(java.lang.String)}.
	 */
	@Test
	void testTrim() {
		assertTrue(true);
	}

	/**
	 * Test method for {@link net.sanjay.utils.EncodingUtils#uriEncode(java.lang.String)}.
	 */
	@Test
	void testUriEncode() {
		assertTrue(true);
	}

	static String[][] sha256TestDataProvider() {
		String[][] data = {
			{"",
				"e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855"},
			{"Hello World",
				"a591a6d40bf420404a011733cfb7b190d62c65bf0bcda32b57b277d9ad9f146e"}
		};
		return data;
	}

	static String[][] hmacSha256TestDataProvider() {
		String[][] data = {
			{"key", "The quick brown fox jumps over the lazy dog",
				"f7bc83f430538424b13298e6aa6fb143ef4d59a14946175997479dbc2d1a3cd8"}
		};
		return data;
	}


}
