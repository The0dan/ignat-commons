package name.ignat.commons.lang;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * @author Dan Ignat
 */
public class TestDoubles
{
    private static Stream<Arguments> toCompactStringCases()
    {
        return Stream.of(
            Arguments.of(37d,   "37"),
            Arguments.of(37.,   "37"),
            Arguments.of(37.0,  "37"),
            Arguments.of(37.1,  "37.1"),
            Arguments.of(37.10, "37.1"),
            Arguments.of(37.010000000000000, "37.01"),
            // Notice the loss of precision here, at not too high a scale; this is when BigDecimal would be needed
            Arguments.of(37.000000000000001, "37")
        );
    }

    @ParameterizedTest
    @MethodSource("toCompactStringCases")
    public void toCompactString(double d, String expectedOutputString)
    {
        String outputString = Doubles.toCompactString(d);

        assertThat(outputString, equalTo(expectedOutputString));
    }
}
