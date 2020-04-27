package name.ignat.commons.lang;

import static java.math.RoundingMode.HALF_UP;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.annotation.Nullable;

/**
 * Utility methods for {@link BigDecimal}s.
 * 
 * @author Dan Ignat
 */
public final class BigDecimals
{
    /**
     * Normalizes the scale of {@code bigDecimal} to 2 decimal places and {@link RoundingMode#HALF_UP}.  All currency
     * {@link BigDecimal}s should be normalized after instantiation for consistency.
     * 
     * @param bigDecimal the currency {@link BigDecimal} to normalize
     * @return the normalized {@link BigDecimal}
     * 
     * @see BigDecimal#setScale(int, java.math.RoundingMode)
     */
    public static BigDecimal normalizeCurrency(@Nullable BigDecimal bigDecimal)
    {
        return normalize(bigDecimal, 2);
    }

    /**
     * Normalizes the scale of {@code bigDecimal} to {@code scale} decimal places and {@link RoundingMode#HALF_UP}.  All
     * {@link BigDecimal}s should be normalized after instantiation for consistency.
     * 
     * @param bigDecimal the {@link BigDecimal} to normalize
     * @param scale the scale to which to normalize
     * @return the normalized {@link BigDecimal}
     * 
     * @see BigDecimal#setScale(int, java.math.RoundingMode)
     */
    public static BigDecimal normalize(@Nullable BigDecimal bigDecimal, int scale)
    {
        return bigDecimal == null ? null : bigDecimal.setScale(scale, HALF_UP);
    }

    /**
     * Returns a canonical string representation of {@code bigDecimal} without any trailing zeros and without scientific
     * notation.
     * 
     * @param bigDecimal the {@link BigDecimal} to represent
     * @return the string representation
     * 
     * @see BigDecimal#stripTrailingZeros()
     * @see BigDecimal#toPlainString()
     */
    public static String toSimpleString(BigDecimal bigDecimal)
    {
        return bigDecimal.stripTrailingZeros().toPlainString();
    }

    private BigDecimals() { }
}
