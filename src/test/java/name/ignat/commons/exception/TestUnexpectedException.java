package name.ignat.commons.exception;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;

import org.junit.jupiter.api.Test;

/**
 * @author Dan Ignat
 */
public class TestUnexpectedException
{
	@Test
	public void messageConstructor()
	{
		Exception e = new UnexpectedException();

		assertThat(e, hasProperty("message", equalTo("")));
        assertThat(e, hasProperty("cause", equalTo(null)));
	}

    @Test
    public void messageConstructor2()
    {
        Exception e = new UnexpectedException("abc");

        assertThat(e, hasProperty("message", equalTo("abc")));
        assertThat(e, hasProperty("cause", equalTo(null)));
    }

    @Test
    public void messageConstructor3()
    {
        Throwable cause = new IllegalArgumentException();

        Exception e = new UnexpectedException(cause);

        assertThat(e, hasProperty("message", equalTo("java.lang.IllegalArgumentException")));
        assertThat(e, hasProperty("cause", equalTo(cause)));
    }

    @Test
    public void messageConstructor4()
    {
        Throwable cause = new IllegalArgumentException();

        Exception e = new UnexpectedException("abc", cause);

        assertThat(e, hasProperty("message", equalTo("abc")));
        assertThat(e, hasProperty("cause", equalTo(cause)));
    }

    @Test
    public void messageConstructor_withContext()
    {
        Exception e = new UnexpectedException().addContextValue("foo", 5).addContextValue("foo", "bar");

        assertThat(e, hasProperty("message", equalTo("Exception Context:\n\t[1:foo=5]\n\t[2:foo=bar]\n---------------------------------")));
        assertThat(e, hasProperty("cause", equalTo(null)));
    }
}
