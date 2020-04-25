package name.ignat.commons.utils;

import static com.google.common.io.Resources.getResource;
import static java.nio.charset.Charset.defaultCharset;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;

import name.ignat.commons.exception.UnexpectedException;

/**
 * Resource-related utility methods.
 * 
 * @author Dan Ignat
 */
public final class Resources
{
	/**
	 * Standardizes the way an application retrieves a resource file on the classpath by using Guava's {@link
	 * Resources#getResource(String)}, returning it as an {@code InputStream}.
	 */
	public static InputStream getClassPathResource(String classPathResourcePath)
	{
		try
		{
			return getResource(classPathResourcePath).openStream();
		}
		catch (IOException e)
		{
			throw new UnexpectedException(e);
		}
	}

	/**
	 * Standardizes the way an application retrieves a resource file on the classpath by using Guava's {@link
     * Resources#getResource(String)}, returning it as a {@code File}.
	 */
	public static File getClassPathResourceFile(String classPathResourcePath)
	{
		try
		{
			return new File(getResource(classPathResourcePath).toURI());
		}
		catch (URISyntaxException e)
		{
			throw new UnexpectedException(e);
		}
	}

	/**
	 * Standardizes the way an application retrieves a resource file on the classpath by using Guava's {@link
     * Resources#getResource(String)}, returning its contents as a {@code String}.
	 */
	public static String getClassPathResourceAsString(String classPathResourcePath)
	{
		try
		{
		    return com.google.common.io.Resources.toString(getResource(classPathResourcePath), defaultCharset());
		}
		catch (IOException e)
		{
			throw new UnexpectedException(e);
		}
	}

	private Resources() { }
}
