package name.ignat.commons.utils;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import name.ignat.commons.utils.ObjectUtils;

/**
 * @author Dan Ignat
 */
public class ObjectUtilsTest
{
	@Test
	public void equalsAny_String()
	{
		equalsAny(false, null, (String[]) null);
		equalsAny(false, null);

		equalsAny(false, "hello", (String[]) null);
		equalsAny(false, "hello");

		equalsAny(false, null, "hello");

		equalsAny(true, "hello", "hello");
		equalsAny(true, "hello", "hello", "world");
		equalsAny(true, "hello", "world", "hello");

		equalsAny(false, "hello2", "hello");
		equalsAny(false, "hello2", "hello", "world");
		equalsAny(false, "hello2", "world", "hello");
	}

	@Test
	public void equalsAny_Integer()
	{
		equalsAny(false, null, (Integer[]) null);
		equalsAny(false, null);

		equalsAny(false, 123, (Integer[]) null);
		equalsAny(false, 123);

		equalsAny(false, null, 123);

		equalsAny(true, 123, 123);
		equalsAny(true, 123, 123, 456);
		equalsAny(true, 123, 456, 123);

		equalsAny(false, 789, 123);
		equalsAny(false, 789, 123, 456);
		equalsAny(false, 789, 456, 123);
	}

	private <T> void equalsAny(boolean expectedOutput, T inputObject, @SuppressWarnings("unchecked") T... inputCandidates)
	{
		boolean actualOutput = ObjectUtils.equalsAny(inputObject, inputCandidates);

		String reason = "inputObject = " + inputObject + ", inputCandidates = " + (inputCandidates == null ? null : asList(inputCandidates));

		assertThat(reason, actualOutput, equalTo(expectedOutput));
	}
}
