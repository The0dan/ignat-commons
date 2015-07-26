package name.ignat.commons.utils;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;

import org.junit.Test;

import name.ignat.commons.utils.NumberUtils;

/**
 * @author Dan Ignat
 */
public class NumberUtilsTest
{
	@Test
	public void normalizeScale()
	{
		normalizeScale("37", "37.00");
		normalizeScale("37", "37", false);

		normalizeScale("37.0", "37.00");
		normalizeScale("37.0", "37.0", false);

		normalizeScale("37.4", "37.40");
		normalizeScale("37.4", "37.4", false);

		normalizeScale("37.40", "37.40");
		normalizeScale("37.40", "37.400", false);
		normalizeScale("37.41", "37.41");
		normalizeScale("37.42", "37.42");
		normalizeScale("37.43", "37.43");
		normalizeScale("37.44", "37.44");
		normalizeScale("37.45", "37.45");
		normalizeScale("37.46", "37.46");
		normalizeScale("37.47", "37.47");
		normalizeScale("37.48", "37.48");
		normalizeScale("37.49", "37.49");

		normalizeScale("37.400", "37.40");
		normalizeScale("37.401", "37.40");
		normalizeScale("37.402", "37.40");
		normalizeScale("37.403", "37.40");
		normalizeScale("37.404", "37.40");
		normalizeScale("37.405", "37.41");
		normalizeScale("37.406", "37.41");
		normalizeScale("37.407", "37.41");
		normalizeScale("37.408", "37.41");
		normalizeScale("37.409", "37.41");

		normalizeScale("37.4000", "37.40");
		normalizeScale("37.4001", "37.40");
		normalizeScale("37.4002", "37.40");
		normalizeScale("37.4003", "37.40");
		normalizeScale("37.4004", "37.40");
		normalizeScale("37.4005", "37.40");
		normalizeScale("37.4006", "37.40");
		normalizeScale("37.4007", "37.40");
		normalizeScale("37.4008", "37.40");
		normalizeScale("37.4009", "37.40");

		normalizeScale("37.400000000000009", "37.40");
		normalizeScale("37.400000000000009", "37.400000000000009", false);
	}

	private void normalizeScale(String inputString, String expectedOutputString)
	{
		normalizeScale(inputString, expectedOutputString, true);
	}

	private void normalizeScale(String inputString, String expectedOutputString, boolean equal)
	{
		BigDecimal input = new BigDecimal(inputString);

		BigDecimal actualOutput = NumberUtils.normalizeScale(input);

		BigDecimal expectedOutput = new BigDecimal(expectedOutputString);

		String reason = "input = " + inputString;

		if (equal)
		{
			assertThat(reason, actualOutput, equalTo(expectedOutput));
		}
		else
		{
			assertThat(reason, actualOutput, not(equalTo(expectedOutput)));
		}
	}

	@Test
	public void normalizeScale_null()
	{
		BigDecimal actualOutput = NumberUtils.normalizeScale(null);

		assertThat(actualOutput, nullValue());
	}
}
