package name.ignat.commons.utils;

/**
 * Object-related utility methods.
 * 
 * @author Dan Ignat
 */
public final class ObjectUtils
{
	/**
	 * Returns {@code true} if any of the {@code candidates} are equal to {@code object} via {@link
	 * Object#equals(Object)}; {@code false} otherwise.
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

	private ObjectUtils() { }
}
