package name.ignat.commons.collect;

import java.util.List;

import javax.annotation.Nonnull;

/**
 * Utility methods for {@link List}s.
 * 
 * @author Dan Ignat
 */
public final class Lists
{
    /**
     * Gets the first element of {@code list}.
     * <p>
     * Using this is slightly nicer semantically than using {@link List#get(int)} directly.
     * 
     * @apiNote Not null-safe because then you couldn't distinguish between a null list and a null first element.  This
     * seems consistent with other Collections utilities.
     * 
     * @param <T> the class of the objects in the list
     * @param list the {@code List} from which to return the first element
     * @return the first element of {@code list}
     * @throws NullPointerException if {@code list} is {@code null}
     */
    public static <T> T getFirst(@Nonnull List<T> list)
    {
        return list.get(0);
    }

    /**
     * Gets the last element of {@code list}.
     * <p>
     * Using this is considerably nicer semantically than using {@link List#get(int)} directly.
     * 
     * @apiNote Not null-safe because then you couldn't distinguish between a null list and a null last element.  This
     * seems consistent with other Collections utilities.
     * 
     * @param <T> the class of the objects in the list
     * @param list the {@code List} from which to return the last element
     * @return the last element of {@code list}
     * @throws NullPointerException if {@code list} is {@code null}
     */
    public static <T> T getLast(@Nonnull List<T> list)
    {
        return list.get(list.size() - 1);
    }

    private Lists() { }
}
