package name.ignat.commons.utils;

import static name.ignat.commons.utils.TestEnums.RGBColor.BLUE;
import static name.ignat.commons.utils.TestEnums.RGBColor.GREEN;
import static name.ignat.commons.utils.TestEnums.RGBColor.RED;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * @author Dan Ignat
 */
public class TestEnums
{
    private static Stream<Arguments> equalsAnyCases()
    {
        return Stream.of(
            Arguments.of(null,    null,                      false),
            Arguments.of(null,    List.of(),                 false),
            Arguments.of(null,    List.of(RED, GREEN, BLUE), false),
            Arguments.of("RED",   null,                      false),
            Arguments.of("RED",   List.of(),                 false),
            Arguments.of("RED",   List.of(RED, BLUE),        true),
            Arguments.of("BLUE",  List.of(RED, BLUE),        true),
            Arguments.of("GREEN", List.of(RED, BLUE),        false),
            Arguments.of("GREEN", List.of(RED, GREEN, BLUE), true)
        );
    }

    @ParameterizedTest
    @MethodSource("equalsAnyCases")
    public void equalsAny(String name, List<RGBColor> candidates, boolean expectedResult)
    {
        boolean result = Enums.equalsAny(name, candidates == null ? null : candidates.toArray(new RGBColor[0]));

        assertThat(result, is(expectedResult));
    }

    public enum RGBColor { RED, GREEN, BLUE };
}
