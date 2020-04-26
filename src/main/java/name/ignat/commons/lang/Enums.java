package name.ignat.commons.lang;

import javax.annotation.Nullable;

/**
 * Utility methods for enums.
 * 
 * @author Dan Ignat
 */
public final class Enums
{
	/**
	 * Returns {@code true} <em>iff</em> any of {@code candidates} has an {@link Enum#name()} equal to {@code name}.
	 * 
	 * @param name the string name to check
	 * @param candidates the enum candidates whose names should be checked
	 * @return {@code true} <em>iff</em> at least one match is found; also {@code false} if either {@code name} or
	 * {@code candidates} is null
	 */
	@SafeVarargs
	public static <E extends Enum<E>> boolean equalsAny(@Nullable String name, @Nullable E... candidates)
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
