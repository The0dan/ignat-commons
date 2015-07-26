package name.ignat.commons.exception;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import name.ignat.commons.exception.UnexpectedCaseException;

/**
 * @author Dan Ignat
 */
public class UnexpectedCaseExceptionTest
{
	@Test
	public void messageConstructor()
	{
		UnexpectedCaseException e = new UnexpectedCaseException("Test");

		assertThat(e, hasProperty("message", equalTo("Unexpected case: Test")));
	}
}
