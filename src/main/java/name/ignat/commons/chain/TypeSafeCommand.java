package name.ignat.commons.chain;

import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;

/**
 * Type-safe extension of {@link Command} that enforces {@link Context} compatibility.
 * 
 * @author Dan Ignat
 */
public interface TypeSafeCommand<C extends Context> extends Command
{
	/**
	 * Type-safe version of {@link Command#execute(Context)}.
	 */
	boolean executeSafely(C context) throws Exception;
}
