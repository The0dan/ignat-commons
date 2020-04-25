package name.ignat.commons.utils;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

import java.math.BigDecimal;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * @author Dan Ignat
 */
public class TestNumbers
{
    private static Stream<Arguments> normalizeScaleCases()
	{
        return Stream.of(

            Arguments.of("37", "37.00"),

            Arguments.of("37.0", "37.00"),

            Arguments.of("37.4", "37.40"),

    		Arguments.of("37.40", "37.40"),
    		Arguments.of("37.41", "37.41"),
    		Arguments.of("37.42", "37.42"),
    		Arguments.of("37.43", "37.43"),
    		Arguments.of("37.44", "37.44"),
    		Arguments.of("37.45", "37.45"),
    		Arguments.of("37.46", "37.46"),
    		Arguments.of("37.47", "37.47"),
    		Arguments.of("37.48", "37.48"),
    		Arguments.of("37.49", "37.49"),

    		Arguments.of("37.400", "37.40"),
    		Arguments.of("37.401", "37.40"),
    		Arguments.of("37.402", "37.40"),
    		Arguments.of("37.403", "37.40"),
    		Arguments.of("37.404", "37.40"),
    		Arguments.of("37.405", "37.41"),
    		Arguments.of("37.406", "37.41"),
    		Arguments.of("37.407", "37.41"),
    		Arguments.of("37.408", "37.41"),
    		Arguments.of("37.409", "37.41"),

    		Arguments.of("37.4000", "37.40"),
    		Arguments.of("37.4001", "37.40"),
    		Arguments.of("37.4002", "37.40"),
    		Arguments.of("37.4003", "37.40"),
    		Arguments.of("37.4004", "37.40"),
    		Arguments.of("37.4005", "37.40"),
    		Arguments.of("37.4006", "37.40"),
    		Arguments.of("37.4007", "37.40"),
    		Arguments.of("37.4008", "37.40"),
    		Arguments.of("37.4009", "37.40"),

    		Arguments.of("37.400000000000009", "37.40")
        );
	}

    @ParameterizedTest
    @MethodSource("normalizeScaleCases")
    public void normalizeScale(String inputString, String expectedOutputString)
	{
		BigDecimal input = new BigDecimal(inputString);

		BigDecimal actualOutput = Numbers.normalizeScale(input);

		BigDecimal expectedOutput = new BigDecimal(expectedOutputString);

		assertThat(actualOutput, equalTo(expectedOutput));

		if (!inputString.equals(expectedOutputString))
        {
			assertThat(actualOutput, not(equalTo(input)));
		}
	}

	@Test
	public void normalizeScale_null()
	{
		BigDecimal actualOutput = Numbers.normalizeScale(null);

		assertThat(actualOutput, nullValue());
	}
}
