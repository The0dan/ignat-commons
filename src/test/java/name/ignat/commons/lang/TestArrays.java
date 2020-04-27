package name.ignat.commons.lang;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

/**
 * @author Dan Ignat
 */
public class TestArrays
{
    @Test
    public void of_nullVarargs()
    {
        String[] strings = Arrays.of((String[]) null);

        assertThat(strings, equalTo(null));
    }

    @Test
    public void of_nullElement()
    {
        String[] strings = Arrays.of((String) null);

        assertThat(strings, is(new String[] { null }));
    }

    @Test
    public void of()
    {
        String[] strings = Arrays.of("hello", "world");

        assertThat(strings, is(new String[] { "hello", "world" }));
    }

    @Test
    public void of_integers()
    {
        Integer[] integers = Arrays.of(3, 7);

        assertThat(integers, is(new Integer[] { 3, 7 }));
    }
}
