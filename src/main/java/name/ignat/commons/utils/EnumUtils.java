package name.ignat.commons.utils;

import static org.apache.commons.lang3.ArrayUtils.isEmpty;

import java.lang.reflect.Field;

import javax.xml.bind.annotation.XmlEnumValue;

import name.ignat.commons.exception.UnexpectedCaseException;
import name.ignat.commons.exception.UnexpectedException;

/**
 * Enum-related utility methods.
 * 
 * @author Dan Ignat
 */
public final class EnumUtils
{
	/**
	 * Gets the first enum constant from the specified enum class, or {@code null} if the enum class has no constants.
	 */
	public static <E extends Enum<E>> E getFirstConstant(Class<E> enumClass)
	{
		E[] constants = enumClass.getEnumConstants();

		return !isEmpty(constants) ? constants[0] : null;
	}

	/**
	 * Returns {@code true} iff any of the {@code candidates} have an {@link Enum#name()} equal to {@code name}.
	 */
	@SafeVarargs
	public static <E extends Enum<E>> boolean equalsAny(String name, E... candidates)
	{
		if (name == null)
		{
			return false;
		}

		for (E candidate : candidates)
		{
			if (name.equals(candidate.name()))
			{
				return true;
			}
		}

		return false;
	}

	/**
	 * Returns the constant from the specified {@code enumClass} which is annotated with an {@link XmlEnumValue} whose
	 * {@code value} is the specified {@code xmlEnumValue}.
	 */
	public static <E extends Enum<E>> E getByXmlEnumValue(Class<E> enumClass, String xmlEnumValue)
	{
		for (E enumConstant : enumClass.getEnumConstants())
		{
			try
			{
				Field enumConstantField = enumClass.getField(enumConstant.name());

				XmlEnumValue xmlEnumValueAnnotation = enumConstantField.getAnnotation(XmlEnumValue.class);

				if (xmlEnumValueAnnotation.value().equals(xmlEnumValue))
				{
					return enumConstant;
				}
			}
			catch (NoSuchFieldException e)
			{
				throw new UnexpectedException(e);
			}
		}

		throw new UnexpectedCaseException(xmlEnumValue);
	}

	private EnumUtils() { }
}
