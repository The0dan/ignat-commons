package name.ignat.commons.io;

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
	public void getResource() throws IOException
	{
		int fileSize = 0;

		try (InputStream fileIn = Resources.getResource("utils/SomeFile.zip"))
		{
			for (int b = fileIn.read(); b != -1; b = fileIn.read())
			{
				fileSize++;
			}
		}

		assertThat(fileSize, equalTo(171));
	}

	@Test
	public void getResource_notFound() throws IOException
	{
	    Exception exception = assertThrows(IllegalArgumentException.class,
	        () -> Resources.getResource("utils/SomeOtherFile.zip"));

	    assertThat(exception.getMessage(), is("resource utils/SomeOtherFile.zip not found."));
	}

	@Test
	public void getResourceFile() throws IOException
	{
		File file = Resources.getResourceFile("utils/SomeFile.zip");

		assertThat(file.isFile(), equalTo(true));
		assertThat(file.getName(), equalTo("SomeFile.zip"));
	}

	@Test
	public void getResourceFile_notFound() throws IOException
	{
	    Exception exception = assertThrows(IllegalArgumentException.class,
            () -> Resources.getResourceFile("utils/SomeOtherFile.zip"));

        assertThat(exception.getMessage(), is("resource utils/SomeOtherFile.zip not found."));
	}

	@Test
	public void getResourceText() throws IOException
	{
		String contents = Resources.getResourceText("utils/SomeFile.txt");

		assertThat(contents, equalTo("Hello, world!"));
	}

	@Test
	public void getResourceText_notFound() throws IOException
	{
	    Exception exception = assertThrows(IllegalArgumentException.class,
            () -> Resources.getResourceText("utils/SomeOtherFile.txt"));

        assertThat(exception.getMessage(), is("resource utils/SomeOtherFile.txt not found."));
	}
}
