package name.ignat.commons.utils;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.junit.jupiter.api.Test;

/**
 * @author Dan Ignat
 */
public class TestResources
{
	@Test
	public void getClassPathResource() throws IOException
	{
		int fileSize = 0;

		try (InputStream fileIn = Resources.getClassPathResource("utils/SomeFile.zip"))
		{
			for (int b = fileIn.read(); b != -1; b = fileIn.read())
			{
				fileSize++;
			}
		}

		assertThat(fileSize, equalTo(171));
	}

	@Test
	public void getClassPathResource_notFound() throws IOException
	{
	    Exception exception = assertThrows(IllegalArgumentException.class,
	        () -> Resources.getClassPathResource("utils/SomeOtherFile.zip"));

	    assertThat(exception.getMessage(), is("resource utils/SomeOtherFile.zip not found."));
	}

	@Test
	public void getClassPathResourceFile() throws IOException
	{
		File file = Resources.getClassPathResourceFile("utils/SomeFile.zip");

		assertThat(file.isFile(), equalTo(true));
		assertThat(file.getName(), equalTo("SomeFile.zip"));
	}

	@Test
	public void getClassPathResourceFile_notFound() throws IOException
	{
	    Exception exception = assertThrows(IllegalArgumentException.class,
            () -> Resources.getClassPathResourceFile("utils/SomeOtherFile.zip"));

        assertThat(exception.getMessage(), is("resource utils/SomeOtherFile.zip not found."));
	}

	@Test
	public void getClassPathResourceAsString() throws IOException
	{
		String contents = Resources.getClassPathResourceAsString("utils/SomeFile.txt");

		assertThat(contents, equalTo("Hello, world!"));
	}

	@Test
	public void getClassPathResourceAsString_notFound() throws IOException
	{
	    Exception exception = assertThrows(IllegalArgumentException.class,
            () -> Resources.getClassPathResourceAsString("utils/SomeOtherFile.txt"));

        assertThat(exception.getMessage(), is("resource utils/SomeOtherFile.txt not found."));
	}
}
