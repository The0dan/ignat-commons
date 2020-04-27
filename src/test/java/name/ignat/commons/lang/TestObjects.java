package name.ignat.commons.lang;

import static java.lang.System.lineSeparator;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * @author Dan Ignat
 */
public class TestObjects
{
    private static Stream<Arguments> equalsAnyCases()
    {
        return Stream.of(

            Arguments.of(null,     null,                      false),
            Arguments.of(null,     List.of(),                 false),

            // Strings

            Arguments.of("hello",  null,                      false),
            Arguments.of("hello",  List.of(),                 false),

            Arguments.of(null,     List.of("hello"),          false),

            Arguments.of("hello",  List.of("hello"),          true),
            Arguments.of("hello",  List.of("hello", "world"), true),
            Arguments.of("hello",  List.of("world", "hello"), true),

            Arguments.of("hello2", List.of("hello"),          false),
            Arguments.of("hello2", List.of("hello", "world"), false),
            Arguments.of("hello2", List.of("world", "hello"), false),

            // Integers

            Arguments.of(123,      null,                      false),
            Arguments.of(123,      List.of(),                 false),

            Arguments.of(null,     List.of(123),              false),

            Arguments.of(123,      List.of(123),              true),
            Arguments.of(123,      List.of(123, 456),         true),
            Arguments.of(123,      List.of(456, 123),         true),

            Arguments.of(789,      List.of(123),              false),
            Arguments.of(789,      List.of(123, 456),         false),
            Arguments.of(789,      List.of(456, 123),         false)
        );
    }

    @ParameterizedTest
    @MethodSource("equalsAnyCases")
    public void equalsAny(Object object, List<Object> candidates, boolean expectedResult)
    {
        boolean result = Objects.equalsAny(object, candidates == null ? null : candidates.toArray(new Object[0]));

        assertThat(result, is(expectedResult));
    }

    private static Stream<Arguments> toLinesStringCases()
    {
        String sep = lineSeparator();

        return Stream.of(
            Arguments.of(List.of(), ""),
            Arguments.of(List.of("hello"), "hello"),
            Arguments.of(List.of("hello", "world"), "hello" + sep + "world"),
            Arguments.of(List.of(" hello ", " world "), " hello " + sep + " world "),
            Arguments.of(List.of("hello" + sep + "again", "world"), "hello" + sep + "again" + sep + "world"),
            Arguments.of(List.of(1, 2), "1" + sep + "2")
        );
    }

    @ParameterizedTest
    @MethodSource("toLinesStringCases")
    public void toLinesString(List<Object> objects, String expectedOutput)
    {
        String output = Objects.toLinesString(objects);

        assertThat(output, is(expectedOutput));
    }

    @Test
    public void toLinesString_null()
    {
        assertThrows(NullPointerException.class, () -> Objects.toLinesString(null));
    }
}
