/**
 * 
 */
package net.sanjay.utils;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.Stream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * @author sanjaykdev
 *
 */
class ICommonValidatorTest {

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
	 * Test method for {@link net.sanjay.utils.ICommonValidator#areNonNullAndNonEmpty(java.lang.String[])}.
	 */
	@ParameterizedTest
	@MethodSource("nonNullAndNonEmptyStrings")
	void testAreNonNullAndNonEmpty(String[] strings) {
		boolean areNonNullAndNonEmpty = ICommonValidator.areNonNullAndNonEmpty(strings);
		
		assertTrue(areNonNullAndNonEmpty);
	}

	/**
	 * Test method for {@link net.sanjay.utils.ICommonValidator#isNonNullAndNonEmpty(java.lang.String)}.
	 */
	@ParameterizedTest
	@ValueSource(strings = { "a", " b  ", "cde", " 1"})
	void testIsNonNullAndNonEmpty(String s) {
		boolean isNonNullAndNonEmpty = ICommonValidator.isNonNullAndNonEmpty(s);
		
		assertTrue(isNonNullAndNonEmpty);
	}
	
	
	static Stream<Arguments> nonNullAndNonEmptyStrings() {
		String[][] data = {{"1", "2"}, {"a", "b"}};

		return Stream.of(
				Arguments.of((Object) data[0]),
				Arguments.of((Object) data[1])
			);
	}

}
