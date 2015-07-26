package name.ignat.commons.utils;

import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.codec.binary.Base64;
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
	 * 
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
	 * 
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
	 * 
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

	/**
	 * Decodes a Base64 string and wraps its decoded contents in a {@link ByteArrayInputStream}.
	 * <p>
	 * <em>NOTE:</em> Returns {@link ByteArrayInputStream} instead of {@link InputStream} so that clients are aware that
	 * no resource leaks will result if they don't call {@link ByteArrayInputStream#close()}.
	 * 
	 * @author Dan Ignat
	 */
	public static ByteArrayInputStream decodeBase64ToInputStream(String base64Content)
	{
		byte[] base64Bytes = Base64.decodeBase64(base64Content);

		return new ByteArrayInputStream(base64Bytes);
	}

	/**
	 * Similar to {@link IOUtils#closeQuietly(Closeable)}, but wraps any {@link IOException} instead of swallowing it.
	 * 
	 * @author Dan Ignat
	 */
	public static void closeGracefully(Closeable closeable)
	{
		if (closeable != null)
		{
			try
			{
				closeable.close();
			}
			catch (IOException e)
			{
				throw new UnexpectedException(e);
			}
		}
	}

	private IoUtils() { }
}
