package name.ignat.commons.lang;

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
public class TestStrings
{
    private static Stream<Arguments> containsAnyIgnoreCaseCases()
    {
        return Stream.of(

            Arguments.of(null,    null,                      false),
            Arguments.of(null,    List.of(),                 false),
            Arguments.of(null,    List.of("hello"),          false),

            Arguments.of("hello", null,                      false),
            Arguments.of("hello", List.of(),                 false),

            Arguments.of("hello", List.of("hello"),          true),
            Arguments.of("hello", List.of("hello", "world"), true),
            Arguments.of("hello", List.of("world", "hello"), true),

            Arguments.of("hello", List.of(""),               true),
            Arguments.of("hello", List.of("ell"),            true),
            Arguments.of("hello", List.of("hey", "ell"),     true),
            Arguments.of("hello", List.of("hey", "world"),   false),

            Arguments.of("hello", List.of("hello "),         false),
            Arguments.of("hello", List.of("hey", "eLL"),     true)
        );
    }

    @ParameterizedTest
    @MethodSource("containsAnyIgnoreCaseCases")
    public void containsAnyIgnoreCase(String string, List<String> candidates, boolean expectedResult)
    {
        boolean result = Strings.containsAnyIgnoreCase(string, candidates == null ? null : candidates.toArray(new String[0]));

        assertThat(result, is(expectedResult));
    }

    private static Stream<Arguments> findAllMatchesCases()
    {
        return Stream.of(

            Arguments.of("he(?:y|llo)", "hi Hey how are you?",           List.of()),

            Arguments.of("he(?:y|llo)", "hi hello Hey hey how are you?", List.of("hello", "hey")),

            // Tests that each match attempt consumes input.  Otherwise, the expectedMatches would be something
            // like: List.of("ann", "anna", "ann", "anna")
            Arguments.of("(ann|anna)", "hi anna, how are you anna?",    List.of("ann", "ann"))
        );
    }

    @ParameterizedTest
    @MethodSource("findAllMatchesCases")
    public void findAllMatches(String patternString, String input, List<String> expectedMatches)
    {
        List<String> matches = Strings.findAllMatches(patternString, input);

        assertThat(matches, is(expectedMatches));
    }
}
