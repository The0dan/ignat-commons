package name.ignat.commons.chain;

import org.apache.commons.chain.Context;

/**
 * Base implementation of {@link TypeSafeCommand}.
 * 
 * @author Dan Ignat
 */
public abstract class TypeSafeCommandBase<C extends Context> implements TypeSafeCommand<C>
{
	/**
	 * We decided not to wrap checked exceptions with an unchecked exception here, because sometimes you want a command
	 * to throw a checked exception (e.g. {@code ValidationException}) and have that bubble up and be thrown out of
	 * the the outermost method as-is, without any extra, unnecessary wrapping.
	 */
	@Override
	public boolean execute(Context context) throws Exception
	{
		@SuppressWarnings("unchecked")
		C typeSafeContext = (C) context;

		return executeSafely(typeSafeContext);
	}
}
