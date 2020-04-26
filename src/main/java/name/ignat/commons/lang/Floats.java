package name.ignat.commons.lang;

/**
 * Utility methods for {@code float}s.
 * 
 * @author Dan Ignat
 */
public final class Floats
{
    /**
     * Returns a compact string representation of a {@code float}, without the trailing ".0" when it happens to be
     * exactly equal to an integer.
     * 
     * @param f the {@code float} to represent
     * @return the string representation
     */
    public static String toCompactString(float f)
    {
        int i = (int) f;

        return f == i ? String.valueOf(i) : String.valueOf(f);
    }

    private Floats() { }
}
