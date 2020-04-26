package name.ignat.commons.lang;

/**
 * Utility methods for arrays.
 * 
 * @author Dan Ignat
 */
public final class Arrays
{
    /**
     * Less verbose alternative to array constructors, since the type is inferred.
     * <p>
     * Also allows varargs to be used with methods that expect an array.  This may happen either when the method wasn't
     * written with varargs and is in an external library, or when the parameter cannot come last for whatever reason
     * (maybe it's not as elegant semantically), and therefore must be an array.
     * 
     * @param <T> the class of the objects in the varargs
     * @param values the varargs to be returned as an array
     * @return {@code values} cast as an array
     * @throws NullPointerException if {@code values} is {@code null}
     * 
     * @see java.util.Arrays#asList(Object...)
     */
    @SafeVarargs
    public static <T> T[] of(T... values)
    {
        return values;
    }

    private Arrays() { }
}
