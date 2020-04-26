package name.ignat.commons.lang;

/**
 * Utility methods for {@code double}s.
 * 
 * @author Dan Ignat
 */
public final class Doubles
{
    /**
     * Returns a compact string representation of a {@code double}, without the trailing ".0" when it happens to be
     * exactly equal to an integer.
     * 
     * @param d the {@code double} to represent
     * @return the string representation
     */
    public static String toCompactString(double d)
    {
        int i = (int) d;

        return d == i ? String.valueOf(i) : String.valueOf(d);
    }

    private Doubles() { }
}
