package name.ignat.commons.chain;

import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;
import org.apache.commons.chain.impl.ChainBase;

/**
 * Type-safe extension of {@link ChainBase} that enforces {@link Context} compatibility.
 * 
 * @author Dan Ignat
 */
public class TypeSafeChainBase<C extends Context> extends ChainBase implements TypeSafeChain<C>
{
	@Override
	public void addCommand(TypeSafeCommand<? super C> command)
	{
		super.addCommand(command);
	}

	/**
	 * Since we can't hide this method, and since type erasure prevents us from delegating to {@link #addCommand(
	 * TypeSafeCommand)}, this overridden method must simply always throw an IllegalArgumentException.
	 * 
	 * @throws IllegalArgumentException always
	 */
	@Override
	public void addCommand(Command command)
	{
		throw new IllegalArgumentException("TypeSafeChainBase only supports addCommand(TypeSafeCommand<? super C>)");

		// Can't do this because type casting occurs at runtime and, due to type erasure, this would allow addCommand to
		// be called with a TypeSafeCommand<C2>, where C is not assignable from C2
		//addCommand((TypeSafeCommand<C>) command);
	}

	/**
	 * We decided not to wrap checked exceptions with an unchecked exception here, because sometimes you want a command
	 * to throw a checked exception (e.g. {@code ValidationException}) and have that bubble up and be thrown out of
	 * the the outermost method as-is, without any extra, unnecessary wrapping.
	 */
	@Override
	public boolean executeSafely(C context) throws Exception
	{
		return super.execute(context);
	}

	@Override
	public boolean execute(Context context) throws Exception
	{
		@SuppressWarnings("unchecked")
		C typeSafeContext = (C) context;

		return executeSafely(typeSafeContext);
	}
}
