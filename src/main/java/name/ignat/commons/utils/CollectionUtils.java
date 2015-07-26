package name.ignat.commons.utils;

import static java.util.Arrays.asList;

import java.util.Collection;

/**
 * Collection-related utility methods.
 * 
 * @author Dan Ignat
 */
public class CollectionUtils
{
    /**
     * Returns {@code true} iff at least one of {@code items} is in {@code collection}.
     */
	@SafeVarargs
	public static <T> boolean containsAny(Collection<T> collection, T... items)
	{
		return org.apache.commons.collections.CollectionUtils.containsAny(collection, asList(items));
	}
}
