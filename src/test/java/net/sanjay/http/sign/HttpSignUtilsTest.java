/**
 * 
 */
package net.sanjay.http.sign;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author sanjaykdev
 *
 */
class HttpSignUtilsTest {

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
	 * {@link sanjay.tests.http.HttpSignUtils#hashedValue(java.lang.String)}.
	 */
	@Test
	void testHashedValueEmptyString() {
		String testText = "";
		String expectedHashedValue = "e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855";

		String actualHashedValue = HttpSignUtils.hashedValue(testText);

		assertEquals(expectedHashedValue, actualHashedValue);
	}

	/**
	 * Test method for
	 * {@link sanjay.tests.http.HttpSignUtils#hmacHashedValue(java.lang.String, java.lang.String)}.
	 */
	@Test
	void testHmacHashedValue() {
		String testSigningKey = "testkey";
		String testText = "";

		String expectedHashedValue = "e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855";

		String actualHashedValue = HttpSignUtils.hmacHashedValue(testSigningKey, testText);

		assertEquals(expectedHashedValue, actualHashedValue);
	}

}
