package name.ignat.commons.lang;

import static com.google.common.collect.Streams.stream;
import static java.lang.System.lineSeparator;
import static java.util.stream.Collectors.joining;

/**
 * Utility methods for {@link Object}s.
 * 
 * @author Dan Ignat
 */
public final class Objects
{
    /**
     * Returns {@code true} <em>iff</em> any of {@code candidates} is equal to {@code object}.
     * 
     * @param object the object to check
     * @param candidates the candidates to check
     * @return {@code true} <em>iff</em> at least one match is found; also {@code false} if either {@code object} or
     * {@code candidates} is null
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

    /**
     * Returns a string representation of {@code objects} that joins each of their {@link #toString()} with a line
     * separator between them.
     * <p>
     * This is preferable to using the {@link #toString()} of the entire {@link Iterable} when e.g. each object has a
     * long {@link #toString()} representation and having them all on a single line would make it hard to read.
     * 
     * @param objects the objects to represent
     * @return the string representation
     */
    public static <T> String toLinesString(Iterable<T> objects)
    {
        return stream(objects).map(T::toString).collect(joining(lineSeparator()));
    }

    private Objects() { }
}
