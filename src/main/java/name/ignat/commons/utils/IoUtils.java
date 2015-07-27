package name.ignat.commons.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;

import name.ignat.commons.exception.UnexpectedException;

/**
 * I/O-related utility methods.
 * 
 * @author Dan Ignat
 */
public final class IoUtils
{
	/**
	 * Standardizes the way an application retrieves a resource file on the classpath by using Spring's {@link
	 * ClassPathResource}.  This version returns it as an {@code InputStream}.
	 */
	public static InputStream getClassPathResource(String classPathResourcePath)
	{
		try
		{
			return new ClassPathResource(classPathResourcePath).getInputStream();
		}
		catch (IOException e)
		{
			throw new UnexpectedException(e);
		}
	}

	/**
	 * Standardizes the way an application retrieves a resource file on the classpath by using Spring's {@link
	 * ClassPathResource}.  This version returns it as a {@code File}.
	 */
	public static File getClassPathResourceFile(String classPathResourcePath)
	{
		try
		{
			return new ClassPathResource(classPathResourcePath).getFile();
		}
		catch (IOException e)
		{
			throw new UnexpectedException(e);
		}
	}

	/**
	 * Standardizes the way an application retrieves a resource file on the classpath by using Spring's {@link
	 * ClassPathResource}.  This version returns its contents as a {@code String}.
	 */
	public static String getClassPathResourceAsString(String classPathResourcePath)
	{
		try
		{
			InputStream classPathResourceIn = getClassPathResource(classPathResourcePath);

			String classPathResourceAsString = IOUtils.toString(classPathResourceIn);

			classPathResourceIn.close();

			return classPathResourceAsString;
		}
		catch (IOException e)
		{
			throw new UnexpectedException(e);
		}
	}

	private IoUtils() { }
}
