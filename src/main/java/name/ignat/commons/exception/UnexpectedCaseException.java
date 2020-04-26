package name.ignat.commons.exception;

/**
 * <p>
 * Signifies that an unexpected case has occurred in a conditional statement.  Should be used mainly in {@code else} and
 * {@code default} blocks to show the unexpected {@code value} that was encountered.
 * </p>
 * 
 * <p>
 * Examples:
 * </p>
 * 
 * <pre>
 * if (value == 1)
 * {
 *     ...
 * }
 * else if (value == 2)
 * {
 *     ...
 * }
 * else
 * {
 *     throw new UnexpectedCaseException(value);
 * }
 * 
 * switch (value)
 * {
 *     case 1:
 *         ...
 *         break;
 *     case 2:
 *         ...
 *         break;
 *     default:
 *         throw new UnexpectedCaseException(value);
 * }
 * </pre>
 * 
 * <p>
 * This pattern makes your conditional statements <em>fail-fast</em> if the universe of values grows in the future and
 * you forget to update all of your code to handle the new values.
 * </p>
 * 
 * @author Dan Ignat
 */
public class UnexpectedCaseException extends UnexpectedException
{
    private static final long serialVersionUID = 321045026068030184L;

    public UnexpectedCaseException(Object value)
	{
		super("Unexpected case: " + value);
	}
}
