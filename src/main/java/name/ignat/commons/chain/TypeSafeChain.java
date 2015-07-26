package name.ignat.commons.chain;

import org.apache.commons.chain.Chain;
import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;

/**
 * Type-safe extension of {@link Chain} that enforces {@link Context} compatibility.
 * 
 * @author Dan Ignat
 */
public interface TypeSafeChain<C extends Context> extends Chain, TypeSafeCommand<C>
{
	/**
	 * Type-safe overload of {@link Chain#addCommand(Command)}.
	 */
	void addCommand(TypeSafeCommand<? super C> command);

	/**
	 * Type-safe version of {@link Chain#execute(Context)}.
	 */
	boolean executeSafely(C context) throws Exception;
}
