package name.ignat.commons.lang;

import static com.google.common.collect.ImmutableList.toImmutableList;
import static java.util.Arrays.stream;
import static org.apache.commons.lang3.StringUtils.containsIgnoreCase;

import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

import javax.annotation.Nullable;

import org.apache.commons.lang3.StringUtils;

/**
 * Utility methods for {@link String}s.
 * 
 * @author Dan Ignat
 */
public final class Strings
{
    /**
     * Returns {@code true} <em>iff</em> any of {@code candidates} are contained within {@code string}, irrespective of
     * case.
     * 
     * @param string the {@code CharSequence} in which to search
     * @param candidates the {@code CharSequence}s to search for within {@code string}
     * @return {@code true} <em>iff</em> at least one match is found; also {@code false} if either {@code string} or
     * {@code candidates} is null
     * 
     * @see StringUtils#containsIgnoreCase(CharSequence, CharSequence)
     */
    public static boolean containsAnyIgnoreCase(@Nullable CharSequence string, @Nullable CharSequence... candidates)
    {
        if (string == null || candidates == null)
        {
            return false;
        }

        return stream(candidates).anyMatch(searchString -> containsIgnoreCase(string, searchString));
    }

    /**
     * Returns all regex matches of {@code patternString} within {@code input}.
     * <p>
     * Proceeds one match attempt at a time, such that each attempt consumes part of the input.
     * 
     * @param patternString the regex to search for
     * @param input the {@code CharSequence} to search
     * @return a {@code List} of all matching substrings, in the order they were found; empty if there were none
     */
    public static List<String> findAllMatches(String patternString, CharSequence input)
    {
        Pattern pattern = Pattern.compile(patternString);

        return findAllMatches(pattern, input);
    }

    /**
     * Returns all regex matches of {@code pattern} within {@code input}.
     * <p>
     * Proceeds one match attempt at a time, such that each attempt consumes part of the input.
     * 
     * @param pattern the regex to search for
     * @param input the {@code CharSequence} to search
     * @return a {@code List} of all matching substrings, in the order they were found; empty if there were none
     */
    public static List<String> findAllMatches(Pattern pattern, CharSequence input)
    {
        return pattern.matcher(input).results().map(MatchResult::group).collect(toImmutableList());
    }

    private Strings() { }
}
