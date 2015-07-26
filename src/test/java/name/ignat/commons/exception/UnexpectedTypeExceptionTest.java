package name.ignat.commons.exception;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import name.ignat.commons.exception.UnexpectedTypeException;

/**
 * @author Dan Ignat
 */
public class UnexpectedTypeExceptionTest
{
	@Test
	public void messageConstructor()
	{
		UnexpectedTypeException e = new UnexpectedTypeException("Test");

		assertThat(e, hasProperty("message", equalTo("Unexpected type: class java.lang.String")));
	}
}
