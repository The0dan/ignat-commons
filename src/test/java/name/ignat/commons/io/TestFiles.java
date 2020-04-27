package name.ignat.commons.io;

import static java.io.File.createTempFile;
import static java.util.UUID.randomUUID;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isA;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import org.junit.jupiter.api.Test;

import name.ignat.commons.exception.UnexpectedException;
import name.ignat.commons.io.Files.FileExistsException;

/**
 * @author Dan Ignat
 */
public class TestFiles
{
    private static final File TEMP_DIR = new File(System.getProperty("java.io.tmpdir"));
    private static final String TEMP_FILE_PREFIX = "IgnatCommons-" + TestFiles.class.getSimpleName() + "-";

    private static File getExistingFile() throws IOException
    {
        File existingFile = createTempFile(TEMP_FILE_PREFIX, null);
        existingFile.deleteOnExit();
        return existingFile;
    }

    private static File getNonexistingFile(File parent) throws IOException
    {
        File nonexistingFile = new File(parent, TEMP_FILE_PREFIX + randomUUID() + ".tmp");
        nonexistingFile.deleteOnExit();
        return nonexistingFile;
    }

    private static File getNonexistingFile() throws IOException
    {
        return getNonexistingFile(TEMP_DIR);
    }

    @Test
    public void openNewOutputFile_nestedDir() throws IOException
    {
        File nestedDir = getNonexistingFile();
        File nestedFile = getNonexistingFile(nestedDir);

        assertThat(nestedDir.exists(),  is(false));
        assertThat(nestedFile.exists(), is(false));

        try (OutputStream out = Files.openNewOutputFile(nestedFile)) { }

        assertThat(nestedDir.exists(),      is(true));
        assertThat(nestedDir.isDirectory(), is(true));

        assertThat(nestedFile.exists(),     is(true));
        assertThat(nestedFile.isFile(),     is(true));
    }

    @Test
    public void openNewOutputFile_fileExists() throws IOException
    {
        Exception exception = assertThrows(UnexpectedException.class, () -> {
            try (OutputStream out = Files.openNewOutputFile(getExistingFile())) { }
        });

        assertThat(exception.getCause(), isA(FileExistsException.class));
    }

    @Test
    public void openNewOutputFile_fileDoesNotExist() throws IOException
    {
        assertDoesNotThrow(() -> {
            try (OutputStream out = Files.openNewOutputFile(getNonexistingFile())) { }
        });
    }

    @Test
    public void openNewOutputFile_unsafely_fileExists() throws IOException
    {
        assertDoesNotThrow(() -> {
            try (OutputStream out = Files.openNewOutputFile(getExistingFile(), false)) { }
        });
    }

    @Test
    public void openNewOutputFile_unsafely_fileDoesNotExist() throws IOException
    {
        assertDoesNotThrow(() -> {
            try (OutputStream out = Files.openNewOutputFile(getNonexistingFile(), false)) { }
        });
    }

    @Test
    public void openNewOutputFileCautiously_fileExists() throws IOException
    {
        assertThrows(FileExistsException.class, () -> {
            try (OutputStream out = Files.openNewOutputFileCautiously(getExistingFile())) { }
        });
    }

    @Test
    public void openNewOutputFileCautiously_fileDoesNotExist() throws IOException
    {
        assertDoesNotThrow(() -> {
            try (OutputStream out = Files.openNewOutputFileCautiously(getNonexistingFile())) { }
        });
    }

    @Test
    public void openNewOutputFileCautiously_unsafely_fileExists() throws IOException
    {
        assertDoesNotThrow(() -> {
            try (OutputStream out = Files.openNewOutputFileCautiously(getExistingFile(), false)) { }
        });
    }

    @Test
    public void openNewOutputFileCautiously_unsafely_fileDoesNotExist() throws IOException
    {
        assertDoesNotThrow(() -> {
            try (OutputStream out = Files.openNewOutputFileCautiously(getNonexistingFile(), false)) { }
        });
    }
}
