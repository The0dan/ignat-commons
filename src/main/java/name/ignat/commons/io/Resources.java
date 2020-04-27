package name.ignat.commons.io;

import static java.nio.charset.Charset.defaultCharset;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;

import name.ignat.commons.exception.UnexpectedException;

/**
 * Utility methods for resources.
 * <p>
 * Resources are non-class files on the class path, which may be either directly on the file system or within a JAR.
 * They are generally limited to reading, as writing may result in writing to an unexpected destination, due to the
 * nature of class paths and how they blur physical file system locations.
 * <p>
 * Standardizes the way an application retrieves a resource, as there are several options:
 * <ul>
 * <li>{@link Class#getResource(String)}</li>
 * <li>{@link com.google.common.io.Resources#getResource(String)}</li>
 * <li>{@code org.springframework.core.io.ClassPathResource}</li>
 * </ul>
 * Since Ignat Commons already depends on Google Guava, it uses that option, so as not to introduce a more heavyweight
 * Spring dependency that not all clients may want.
 * 
 * @author Dan Ignat
 */
public final class Resources
{
	/**
	 * Opens a resource for reading.
     * <p>
     * <strong>Note:</strong> Use this instead of {@link #getResourceCautiously(String)} when you don't expect an
     * exception and have no handling code, which is the majority of cases.
	 * 
	 * @param resourcePath the path to the resource, relative to the class path
	 * @return an {@link InputStream} from which the resource may be read
	 */
	public static InputStream getResource(String resourcePath)
	{
		try
		{
			return getResourceCautiously(resourcePath);
		}
		catch (IOException e)
		{
			throw new UnexpectedException(e);
		}
	}

    /**
     * Opens a resource for reading, expecting a possible exception.
     * <p>
     * <strong>Note:</strong> Use this instead of {@link #getResource(String)} when you expect and want to handle an
     * exception.
     * 
     * @param resourcePath the path to the resource, relative to the class path
     * @return an {@link InputStream} from which the resource may be read
     */
    public static InputStream getResourceCautiously(String resourcePath) throws IOException
    {
        return com.google.common.io.Resources.getResource(resourcePath).openStream();
    }

    /**
     * Gets a {@code File} corresponding to a resource.
     * <p>
     * <strong>Note:</strong> Use this instead of {@link #getResourceFileCautiously(String)} when you don't expect an
     * exception and have no handling code, which is the majority of cases.
     * 
     * @param resourcePath the path to the resource, relative to the class path
     * @return a {@link File} corresponding to the resource
     */
	public static File getResourceFile(String resourcePath)
	{
		try
		{
			return getResourceFileCautiously(resourcePath);
		}
		catch (URISyntaxException e)
		{
			throw new UnexpectedException(e);
		}
	}

    /**
     * Gets a {@code File} corresponding to a resource, expecting a possible exception.
     * <p>
     * <strong>Note:</strong> Use this instead of {@link #getResourceFile(String)} when you expect and want to handle an
     * exception.
     * 
     * @param resourcePath the path to the resource, relative to the class path
     * @return a {@link File} corresponding to the resource
     */
    public static File getResourceFileCautiously(String resourcePath) throws URISyntaxException
    {
        return new File(com.google.common.io.Resources.getResource(resourcePath).toURI());
    }

    /**
     * Gets the text contents of a resource.
     * <p>
     * <strong>Note:</strong> Use this instead of {@link #getResourceTextCautiously(String)} when you don't expect an
     * exception and have no handling code, which is the majority of cases.
     * 
     * @param resourcePath the path to the resource, relative to the class path
     * @return the text contents of the resource as a {@link String}
     */
	public static String getResourceText(String resourcePath)
	{
		try
		{
		    return getResourceTextCautiously(resourcePath);
		}
		catch (IOException e)
		{
			throw new UnexpectedException(e);
		}
	}

    /**
     * Gets the text contents of a resource, expecting a possible exception.
     * <p>
     * <strong>Note:</strong> Use this instead of {@link #getResourceText(String)} when you expect and want to handle an
     * exception.
     * 
     * @param resourcePath the path to the resource, relative to the class path
     * @return the text contents of the resource as a {@link String}
     */
    public static String getResourceTextCautiously(String resourcePath) throws IOException
    {
        return com.google.common.io.Resources.toString(
            com.google.common.io.Resources.getResource(resourcePath), defaultCharset());
    }

	private Resources() { }
}
