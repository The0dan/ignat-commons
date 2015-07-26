package name.ignat.commons.utils;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import javax.xml.bind.annotation.XmlEnumValue;

import org.junit.Test;

import name.ignat.commons.exception.UnexpectedCaseException;
import name.ignat.commons.utils.EnumUtils;

/**
 * @author Dan Ignat
 */
public class EnumUtilsTest
{
	@Test
	public void getFirstConstant_RGBColor()
	{
		RGBColor rgbColor = EnumUtils.getFirstConstant(RGBColor.class);

		assertThat(rgbColor, equalTo(RGBColor.RED));
	}

	@Test
	public void getFirstConstant_BRGColor()
	{
		BRGColor brgColor = EnumUtils.getFirstConstant(BRGColor.class);

		assertThat(brgColor, equalTo(BRGColor.BLUE));
	}

	@Test
	public void getFirstConstant_EmptyColor()
	{
		EmptyColor emptyColor = EnumUtils.getFirstConstant(EmptyColor.class);

		assertThat(emptyColor, nullValue());
	}

	@Test
	public void equalsAny_matches()
	{
		boolean result = EnumUtils.equalsAny("RED", RGBColor.RED, RGBColor.BLUE);

		assertThat(result, is(true));
	}

	@Test
	public void equalsAny_matches2()
	{
		boolean result = EnumUtils.equalsAny("BLUE", RGBColor.RED, RGBColor.BLUE);

		assertThat(result, is(true));
	}

	@Test
	public void equalsAny_noMatch()
	{
		boolean result = EnumUtils.equalsAny("GREEN", RGBColor.RED, RGBColor.BLUE);

		assertThat(result, is(false));
	}

	@Test
	public void equalsAny_matches3()
	{
		boolean result = EnumUtils.equalsAny("GREEN", RGBColor.RED, RGBColor.GREEN, RGBColor.BLUE);

		assertThat(result, is(true));
	}

	@Test
	public void equalsAny_noCandidates()
	{
		boolean result = EnumUtils.<RGBColor>equalsAny("GREEN");

		assertThat(result, is(false));
	}

	@Test
	public void equalsAny_null()
	{
		boolean result = EnumUtils.equalsAny(null, RGBColor.RED, RGBColor.GREEN, RGBColor.BLUE);

		assertThat(result, is(false));
	}

	@Test
	public void equalsAny_nullAndNoCandidates()
	{
		boolean result = EnumUtils.<RGBColor>equalsAny(null);

		assertThat(result, is(false));
	}

	@Test
	public void getByXmlEnumValue()
	{
		getByXmlEnumValue(XmlColor.class, "Blue", XmlColor.BLUE);
	}

	@Test(expected = UnexpectedCaseException.class)
	public void getByXmlEnumValue_noMatch()
	{
		getByXmlEnumValue(XmlColor.class, "Green", null);
	}

	private <E extends Enum<E>> void getByXmlEnumValue(Class<E> enumClass, String xmlEnumValue, E expectedConstant)
	{
		E constant = EnumUtils.getByXmlEnumValue(enumClass, xmlEnumValue);

		assertThat(constant, equalTo(expectedConstant));
	}

	private enum RGBColor { RED, GREEN, BLUE };

	private enum BRGColor { BLUE, RED, GREEN };

	private enum EmptyColor { };

	private enum XmlColor
	{
		@XmlEnumValue("Red")
		RED,

		@XmlEnumValue("Blue")
		BLUE
	}
}
