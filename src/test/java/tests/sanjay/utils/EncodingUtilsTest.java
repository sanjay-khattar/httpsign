/**
 * 
 */
package tests.sanjay.utils;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
	 * Test method for {@link sanjay.tests.utils.EncodingUtils#sha256(java.lang.String)}.
	 */
	@Test
	void testSha256() throws Exception {
		String testString = "abCdEf123";
		byte[]  bytes = testString.getBytes(StandardCharsets.UTF_8.toString());
		String hex = EncodingUtils.hex(bytes);
		assertEquals(hex, hex);
		
	}

}
