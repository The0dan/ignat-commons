package name.ignat.commons.exception;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.lang3.exception.ContextedRuntimeException;

/**
 * <p>
 * Unchecked exception used to wrap checked exceptions that we cannot meaningfully handle but are forced to.
 * </p>
 * 
 * <p>
 * Many popular frameworks (e.g. Spring, Hibernate) now prefer unchecked exceptions because most of the checked
 * exceptions that they would throw are fatal or indicative of defects anyway, so developers usually don't want or need
 * to handle them.
 * </p>
 * 
 * <p>
 * Example:
 * </p>
 * 
 * <pre>
 * try
 * {
 *     myInputStream.read(myByteArray);
 * }
 * catch (IOException e)
 * {
 *     throw new UnexpectedException(e);
 * }
 * </pre>
 * 
 * <p>
 * In the above scenario, our requirements may specify that any exception thrown by {@link InputStream#read(byte[])}
 * should be considered fatal.  As such, we don't really want to have to handle the {@link IOException}.  But because
 * it's a checked exception, we are forced to do so.  So, in this case, {@code UnexpectedException} would allow us to
 * provide a safe, default handling of such an exception without having to write handling code that makes assumptions
 * beyond the requirements.
 * </p>
 * 
 * <p>
 * In other scenarios, we are forced to handle exceptions that we know can never happen.  {@code UnexpectedException}
 * can help similarly in those cases as well:
 * </p>
 * 
 * <pre>
 * try
 * {
 *     myInputStreamReader = new InputStreamReader(myInputStream, "ISO-8859-1");
 * }
 * catch (UnsupportedEncodingException e)
 * {
 *     throw new UnexpectedException(e);
 * }
 * </pre>
 * 
 * @author Dan Ignat
 */
public class UnexpectedException extends ContextedRuntimeException
{
    private static final long serialVersionUID = -5807058261990164239L;

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
