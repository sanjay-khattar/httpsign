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
class StringUtilsTest {

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
	 * Test method for
	 * {@link sanjay.tests.utils.StringUtils#lowerCase(java.lang.String)}.
	 */
	@ParameterizedTest
	@MethodSource("lowerCaseTestDataProvider")
	void testLowerCase(String s, String expected) {
		String actual = StringUtils.lowerCase(s);
		assertEquals(expected, actual);
	}

	/**
	 * Test method for
	 * {@link sanjay.tests.utils.StringUtils#trim(java.lang.String)}.
	 */
	@ParameterizedTest
	@MethodSource("trimTestDataProvider")
	void testTrim(String s, String expected) {
		String actual = StringUtils.trim(s);
		assertEquals(expected, actual);
	}

	static String[][] lowerCaseTestDataProvider() {
		String[][] strings = {
				{ "", "" },
				{ "ABC", "abc" },
				{ "AbCd", "abcd" },
				{ "123", "123" }
		};
		return strings;
	}

	static String[][] trimTestDataProvider() {
		String[][] strings = {
				{ "1", "1" },
				{ "12\r\n", "12" },
				{ "ab\\r\\n", "ab" }
		};
		return strings;
	}

}
