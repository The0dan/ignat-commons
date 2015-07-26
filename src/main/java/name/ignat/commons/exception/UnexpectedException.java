package name.ignat.commons.exception;

import org.apache.commons.lang3.exception.ContextedRuntimeException;

/**
 * Unchecked exception used to wrap checked exceptions that we cannot meaningfully handle but are forced to.
 * <p>
 * Many popular frameworks (e.g. Spring, Hibernate) now prefer unchecked exceptions because most of the checked
 * exceptions that they would throw are fatal or indicative of defects anyway, so developers usually don't want or need
 * to handle them.
 * 
 * @author Dan Ignat
 */
@SuppressWarnings("serial")
public class UnexpectedException extends ContextedRuntimeException
{
	public UnexpectedException()
	{
		super();
	}

	public UnexpectedException(String message)
	{
		super(message);
	}

	public UnexpectedException(Throwable cause)
	{
		super(cause);
	}

	public UnexpectedException(String message, Throwable cause)
	{
		super(message, cause);
	}
}
