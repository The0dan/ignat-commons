package name.ignat.commons.collect;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * @author Dan Ignat
 */
public class TestLists
{
    private static Stream<Arguments> getFirstCases()
    {
        return Stream.of(

            Arguments.of(null,                      NullPointerException.class),
            Arguments.of(List.of(),                 IndexOutOfBoundsException.class),
            Arguments.of(asList((Object) null),     null),
            Arguments.of(asList(null, null),        null),
            Arguments.of(asList(null, "hello"),     null),
            Arguments.of(asList("hello", null),     "hello"),
            Arguments.of(List.of("hello"),          "hello"),
            Arguments.of(List.of("hello", "world"), "hello"),
            Arguments.of(List.of(2),                2),
            Arguments.of(List.of(2, 7),             2)
        );
    }

    @ParameterizedTest
    @MethodSource("getFirstCases")
    public void getFirst(List<?> list, Object expectedResult)
    {
        if (expectedResult instanceof Class)
        {
            @SuppressWarnings("unchecked")
            Class<Exception> expectedException = (Class<Exception>) expectedResult;

            assertThrows(expectedException, () -> Lists.getFirst(list));
        }
        else
        {
            Object result = Lists.getFirst(list);

            assertThat(result, is(expectedResult));
        }
    }

    private static Stream<Arguments> getLastCases()
    {
        return Stream.of(

            Arguments.of(null,                      NullPointerException.class),
            Arguments.of(List.of(),                 IndexOutOfBoundsException.class),
            Arguments.of(asList((Object) null),     null),
            Arguments.of(asList(null, null),        null),
            Arguments.of(asList(null, "hello"),     "hello"),
            Arguments.of(asList("hello", null),     null),
            Arguments.of(List.of("hello"),          "hello"),
            Arguments.of(List.of("hello", "world"), "world"),
            Arguments.of(List.of(2),                2),
            Arguments.of(List.of(2, 7),             7)
        );
    }

    @ParameterizedTest
    @MethodSource("getLastCases")
    public void getLast(List<?> list, Object expectedResult)
    {
        if (expectedResult instanceof Class)
        {
            @SuppressWarnings("unchecked")
            Class<Exception> expectedException = (Class<Exception>) expectedResult;

            assertThrows(expectedException, () -> Lists.getLast(list));
        }
        else
        {
            Object result = Lists.getLast(list);

            assertThat(result, is(expectedResult));
        }
    }
}
