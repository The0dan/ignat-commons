package name.ignat.commons.exception;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;

import org.junit.jupiter.api.Test;

/**
 * @author Dan Ignat
 */
public class TestUnexpectedCaseException
{
	@Test
	public void messageConstructor()
	{
		UnexpectedCaseException e = new UnexpectedCaseException("abc");

		assertThat(e, hasProperty("message", equalTo("Unexpected case: abc")));
	}
}
