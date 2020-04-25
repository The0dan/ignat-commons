package name.ignat.commons.util;

/**
 * Object-related utility methods.
 * 
 * @author Dan Ignat
 */
public final class Objects
{
	/**
	 * Returns {@code true} <em>iff</em> any of the {@code candidates} are equal to {@code object} via {@link
	 * Object#equals(Object)}.
	 */
	@SafeVarargs
	public static <T> boolean equalsAny(T object, T... candidates)
	{
		if (object == null || candidates == null)
		{
			return false;
		}

		for (T candidate : candidates)
		{
			if (object.equals(candidate))
			{
				return true;
			}
		}

		return false;
	}

	private Objects() { }
}
