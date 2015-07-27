package name.ignat.commons.utils;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * @author Dan Ignat
 */
public class CollectionUtilsTest
{
	@Test
	public void containsAny_true()
	{
		List<String> strings = new ArrayList<>();

		strings.add("one");
		strings.add("two");
		strings.add("three");

		boolean result = CollectionUtils.containsAny(strings, "two", "four");

		assertThat(result, equalTo(true));
	}

	@Test
	public void containsAny_false()
	{
		List<String> strings = new ArrayList<>();

		strings.add("one");
		strings.add("two");
		strings.add("three");

		boolean result = CollectionUtils.containsAny(strings, "four", "five");

		assertThat(result, equalTo(false));
	}
}
