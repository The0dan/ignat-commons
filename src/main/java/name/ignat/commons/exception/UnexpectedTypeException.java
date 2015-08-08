package name.ignat.commons.exception;

/**
 * <p>
 * Signifies that an unexpected type (class) has been encountered in a conditional statement.  Should be used mainly in
 * {@code else} blocks to show the unexpected {@code value.getClass()} that was encountered.
 * </p>
 * 
 * <p>
 * Example:
 * </p>
 * 
 * <pre>
 * if (shape instanceof Circle)
 * {
 *     ...
 * }
 * else if (shape instanceof Rectangle)
 * {
 *     ...
 * }
 * else
 * {
 *     throw new UnexpectedTypeException(shape);
 * }
 * </pre>
 * 
 * <p>
 * Good object-oriented design should minimize the need for such type-based conditional statements, but once in a while
 * they are necessary.
 * </p>
 * 
 * <p>
 * This pattern makes your conditional statements <em>fail-fast</em> if the universe of values grows in the future and you
 * forget to update all of your code to handle the new values.
 * </p>
 * 
 * @author Dan Ignat
 */
@SuppressWarnings("serial")
public class UnexpectedTypeException extends UnexpectedException
{
	public UnexpectedTypeException(Object value)
	{
		super("Unexpected type: " + value.getClass());
	}
}
