package name.ignat.commons.chain;

import static org.apache.commons.chain.Command.CONTINUE_PROCESSING;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import org.apache.commons.chain.impl.ContextBase;
import org.junit.Test;

import name.ignat.commons.chain.TypeSafeCommandBase;

/**
 * @author Dan Ignat
 */
public class TypeSafeCommandBaseTest
{
	@Test
	public void execute() throws Exception
	{
		TestContext context = new TestContext();

		TestTypeSafeCommand command = new TestTypeSafeCommand();

		assertThat(context.getCounter(), nullValue());

		boolean result = command.execute(context);

		assertThat(context.getCounter(), equalTo(7));
		assertThat(result, equalTo(CONTINUE_PROCESSING));
	}

	@SuppressWarnings("serial")
	private class TestContext extends ContextBase
	{
		private Integer counter;

		public Integer getCounter()
		{
			return counter;
		}

		public void setCounter(Integer counter)
		{
			this.counter = counter;
		}
	}

	private class TestTypeSafeCommand extends TypeSafeCommandBase<TestContext>
	{
		@Override
		public boolean executeSafely(TestContext context)
		{
			context.setCounter(7);

			return CONTINUE_PROCESSING;
		}
	}
}
