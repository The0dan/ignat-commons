package name.ignat.commons.lang;

import static com.google.common.collect.Streams.stream;
import static java.lang.System.lineSeparator;
import static java.util.stream.Collectors.joining;

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

    public static <T> String toLines(Iterable<T> objects)
    {
        return stream(objects).map(T::toString).collect(joining(lineSeparator(), lineSeparator(), ""));
    }

	private Objects() { }
}
