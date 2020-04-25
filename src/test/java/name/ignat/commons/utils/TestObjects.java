package name.ignat.commons.utils;

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
}
