package name.ignat.commons.io;

import static com.google.common.io.Files.createParentDirs;
import static java.lang.String.format;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import name.ignat.commons.exception.UnexpectedException;

/**
 * Utility methods for {@link File}s.
 * <p>
 * Standardizes the most common file operations, as otherwise they may be accomplished in several ways inconsistently.
 * 
 * @author Dan Ignat
 */
public final class Files
{
    /**
     * Opens a file for output, creating all nonexistent parent dirs, but throwing an {@link UnexpectedException} if the
     * file already exists.
     * <p>
     * This avoids the default behavior in {@link FileOutputStream} of overwriting an existing file, regardless of
     * whether its existence is expected.
     * 
     * @param file the file to open for output
     * @return a {@link FileOutputStream} to the open file
     */
    public static FileOutputStream openNewOutputFile(File file)
    {
        return openNewOutputFile(file, true);
    }

    /**
     * Opens a file for output, creating all nonexistent parent dirs, optionally throwing an {@link UnexpectedException}
     * if the file already exists.
     * <p>
     * This can avoid the default behavior in {@link FileOutputStream} of overwriting an existing file, regardless of
     * whether its existence is expected.
     * 
     * @param file the file to open for output
     * @param safely if {@code true} and the file already exists, throws an {@link UnexpectedException}; otherwise,
     * overwrites the file if it already exists
     * @return a {@link FileOutputStream} to the open file
     */
    public static FileOutputStream openNewOutputFile(File file, boolean safely)
    {
        try
        {
            return openNewOutputFileCautiously(file, safely);
        }
        catch (IOException e)
        {
            throw new UnexpectedException(e);
        }
    }

    /**
     * Opens a file for output, creating all nonexistent parent dirs, but throwing a {@link FileExistsException} if the
     * file already exists.
     * <p>
     * This avoids the default behavior in {@link FileOutputStream} of overwriting an existing file, regardless of
     * whether its existence is expected.
     * 
     * @param file the file to open for output
     * @return a {@link FileOutputStream} to the open file
     * @throws IOException if anything else goes wrong while opening the file
     */
    public static FileOutputStream openNewOutputFileCautiously(File file) throws IOException, FileExistsException
    {
        return openNewOutputFileCautiously(file, true);
    }

    /**
     * Opens a file for output, creating all nonexistent parent dirs, optionally throwing a {@link FileExistsException}
     * if the file already exists.
     * <p>
     * This can avoid the default behavior in {@link FileOutputStream} of overwriting an existing file, regardless of
     * whether its existence is expected.
     * 
     * @param file the file to open for output
     * @param safely if {@code true} and the file already exists, throws a {@link FileExistsException}; otherwise,
     * overwrites the file if it already exists
     * @return a {@link FileOutputStream} to the open file
     * @throws IOException if anything else goes wrong while opening the file
     */
    public static FileOutputStream openNewOutputFileCautiously(File file, boolean safely)
        throws IOException, FileExistsException
    {
        createParentDirs(file);

        if (safely && !file.createNewFile())
        {
            throw new FileExistsException(file);
        }

        return new FileOutputStream(file);
    }

    private Files() { }

    public static class FileExistsException extends IOException
    {
        private static final long serialVersionUID = 8889885538966030264L;

        public FileExistsException(File file)
        {
            super(format("File '%s' already exists", file.getPath()));
        }
    }
}
