package name.ignat.commons.utils;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.isA;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import name.ignat.commons.exception.UnexpectedException;

/**
 * @author Dan Ignat
 */
public class IoUtilsTest
{
	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();

	@Test
	public void getClassPathResource() throws IOException
	{
		int fileSize = 0;

		try (InputStream fileIn = IoUtils.getClassPathResource("utils/SomeFile.zip"))
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
		exceptionRule.expect(UnexpectedException.class);
		exceptionRule.expectCause(isA(FileNotFoundException.class));

		IoUtils.getClassPathResource("utils/SomeOtherFile.zip");
	}

	@Test
	public void getClassPathResourceFile() throws IOException
	{
		File file = IoUtils.getClassPathResourceFile("utils/SomeFile.zip");

		assertThat(file.isFile(), equalTo(true));
		assertThat(file.getName(), equalTo("SomeFile.zip"));
	}

	@Test
	public void getClassPathResourceFile_notFound() throws IOException
	{
		exceptionRule.expect(UnexpectedException.class);
		exceptionRule.expectCause(isA(FileNotFoundException.class));

		IoUtils.getClassPathResourceFile("utils/SomeOtherFile.zip");
	}

	@Test
	public void getClassPathResourceAsString() throws IOException
	{
		String contents = IoUtils.getClassPathResourceAsString("utils/SomeFile.txt");

		assertThat(contents, equalTo("Hello, world!"));
	}

	@Test
	public void getClassPathResourceAsString_notFound() throws IOException
	{
		exceptionRule.expect(UnexpectedException.class);
		exceptionRule.expectCause(isA(FileNotFoundException.class));

		IoUtils.getClassPathResourceAsString("utils/SomeOtherFile.txt");
	}
}
