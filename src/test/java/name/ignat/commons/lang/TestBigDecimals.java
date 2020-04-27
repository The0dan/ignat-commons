package name.ignat.commons.lang;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * @author Dan Ignat
 */
public class TestBigDecimals
{
    private static Stream<Arguments> normalizeCurrencyCases()
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
    @MethodSource("normalizeCurrencyCases")
    public void normalizeCurrency(String inputString, String expectedOutputString)
	{
		BigDecimal input = new BigDecimal(inputString);

		BigDecimal actualOutput = BigDecimals.normalizeCurrency(input);

		BigDecimal expectedOutput = new BigDecimal(expectedOutputString);

		assertThat(actualOutput, equalTo(expectedOutput));

		if (!inputString.equals(expectedOutputString))
        {
			assertThat(actualOutput, not(equalTo(input)));
		}
	}

	@Test
	public void normalizeCurrency_null()
	{
		BigDecimal actualOutput = BigDecimals.normalizeCurrency(null);

		assertThat(actualOutput, nullValue());
	}

    private static Stream<Arguments> normalizeCases()
    {
        return Stream.of(

            Arguments.of("37", "37.000"),

            Arguments.of("37.0", "37.000"),

            Arguments.of("37.4", "37.400"),

            Arguments.of("37.40", "37.400"),
            Arguments.of("37.41", "37.410"),
            Arguments.of("37.42", "37.420"),
            Arguments.of("37.43", "37.430"),
            Arguments.of("37.44", "37.440"),
            Arguments.of("37.45", "37.450"),
            Arguments.of("37.46", "37.460"),
            Arguments.of("37.47", "37.470"),
            Arguments.of("37.48", "37.480"),
            Arguments.of("37.49", "37.490"),

            Arguments.of("37.400", "37.400"),
            Arguments.of("37.401", "37.401"),
            Arguments.of("37.402", "37.402"),
            Arguments.of("37.403", "37.403"),
            Arguments.of("37.404", "37.404"),
            Arguments.of("37.405", "37.405"),
            Arguments.of("37.406", "37.406"),
            Arguments.of("37.407", "37.407"),
            Arguments.of("37.408", "37.408"),
            Arguments.of("37.409", "37.409"),

            Arguments.of("37.4000", "37.400"),
            Arguments.of("37.4001", "37.400"),
            Arguments.of("37.4002", "37.400"),
            Arguments.of("37.4003", "37.400"),
            Arguments.of("37.4004", "37.400"),
            Arguments.of("37.4005", "37.401"),
            Arguments.of("37.4006", "37.401"),
            Arguments.of("37.4007", "37.401"),
            Arguments.of("37.4008", "37.401"),
            Arguments.of("37.4009", "37.401"),

            Arguments.of("37.400000000000009", "37.400")
        );
    }

    @ParameterizedTest
    @MethodSource("normalizeCases")
    public void normalize(String inputString, String expectedOutputString)
    {
        BigDecimal input = new BigDecimal(inputString);

        BigDecimal actualOutput = BigDecimals.normalize(input, 3);

        BigDecimal expectedOutput = new BigDecimal(expectedOutputString);

        assertThat(actualOutput, equalTo(expectedOutput));

        if (!inputString.equals(expectedOutputString))
        {
            assertThat(actualOutput, not(equalTo(input)));
        }
    }

    @Test
    public void normalize_null()
    {
        BigDecimal actualOutput = BigDecimals.normalize(null, 3);

        assertThat(actualOutput, nullValue());
    }

    private static Stream<Arguments> toSimpleStringCases()
    {
        return Stream.of(

            Arguments.of("37", "37"),
            Arguments.of("37.", "37"),
            Arguments.of("37.0", "37"),
            Arguments.of("37.1", "37.1"),
            Arguments.of("37.10", "37.1"),
            Arguments.of("37.0100000000000000000000000000000000000000000000000000000000000000000000000000000", "37.01"),
            Arguments.of(
                "37.00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001",
                "37.00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001")
        );
    }

    @ParameterizedTest
    @MethodSource("toSimpleStringCases")
    public void toSimpleString(String inputString, String expectedOutputString)
    {
        BigDecimal input = new BigDecimal(inputString);

        String outputString = BigDecimals.toSimpleString(input);

        assertThat(outputString, equalTo(expectedOutputString));
    }

    @Test
    public void toSimpleString_null()
    {
        assertThrows(NullPointerException.class, () -> BigDecimals.toSimpleString(null));
    }
}
