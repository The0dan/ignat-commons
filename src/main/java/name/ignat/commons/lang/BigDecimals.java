package name.ignat.commons.lang;

import static java.math.RoundingMode.HALF_UP;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * BigDecimal-related utility methods.
 * 
 * @author Dan Ignat
 */
public final class BigDecimals
{
	/**
	 * Normalizes the scale of {@code bigDecimal} to 2 decimal places and {@link RoundingMode#HALF_UP}.  All {@link
	 * BigDecimal}s within the application should be normalized via this method for consistency.
	 */
	public static BigDecimal normalizeScale(BigDecimal bigDecimal)
	{
		return bigDecimal == null ? null : bigDecimal.setScale(2, HALF_UP);
	}

    public static String toPlainString(BigDecimal bd)
    {
        return bd.stripTrailingZeros().toPlainString();
    }

    private BigDecimals() { }
}
