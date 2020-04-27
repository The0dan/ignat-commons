package name.ignat.commons.exception;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;

import org.junit.jupiter.api.Test;

/**
 * @author Dan Ignat
 */
public class TestUnexpectedTypeException
{
    @Test
    public void messageConstructor()
    {
        UnexpectedTypeException e = new UnexpectedTypeException("abc");

        assertThat(e, hasProperty("message", equalTo("Unexpected type: class java.lang.String")));
    }
}
