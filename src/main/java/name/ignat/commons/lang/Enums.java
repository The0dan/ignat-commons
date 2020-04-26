package name.ignat.commons.lang;

/**
 * Enum-related utility methods.
 * 
 * @author Dan Ignat
 */
public final class Enums
{
	/**
	 * Returns {@code true} iff any of the {@code candidates} have an {@link Enum#name()} equal to {@code name}.
	 */
	@SafeVarargs
	public static <E extends Enum<E>> boolean equalsAny(String name, E... candidates)
	{
		if (name == null || candidates == null)
		{
			return false;
		}

		for (E candidate : candidates)
		{
			if (name.equals(candidate.name()))
			{
				return true;
			}
		}

		return false;
	}

	private Enums() { }
}
