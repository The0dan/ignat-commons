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
public class TestFloats
{
    private static Stream<Arguments> toCompactStringCases()
    {
        return Stream.of(
            Arguments.of(37f,    "37"),
            Arguments.of(37.f,   "37"),
            Arguments.of(37.0f,  "37"),
            Arguments.of(37.1f,  "37.1"),
            Arguments.of(37.10f, "37.1"),
            Arguments.of(37.010000f, "37.01"),
            // Notice the loss of precision here, at an even lower scale than doubles; this is when BigDecimal would be
            // needed
            Arguments.of(37.000001f, "37")
        );
    }

    @ParameterizedTest
    @MethodSource("toCompactStringCases")
    public void toCompactString(float f, String expectedOutputString)
    {
        String outputString = Floats.toCompactString(f);

        assertThat(outputString, equalTo(expectedOutputString));
    }
}
