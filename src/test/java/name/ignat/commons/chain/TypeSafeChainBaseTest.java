package name.ignat.commons.chain;

import static org.apache.commons.chain.Command.CONTINUE_PROCESSING;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;
import org.apache.commons.chain.impl.ContextBase;
import org.junit.Test;

import name.ignat.commons.chain.TypeSafeChainBase;
import name.ignat.commons.chain.TypeSafeCommandBase;

/**
 * @author Dan Ignat
 */
public class TypeSafeChainBaseTest
{
	@Test
	public void addCommand() throws Exception
	{
		executeSafely();
	}

	@Test(expected = IllegalArgumentException.class)
	public void addCommand_unsafely()
	{
		TypeSafeChainBase<TestContext> chain = new TypeSafeChainBase<TestContext>();

		chain.addCommand(new TestNonTypeSafeCommand());
	}

	@Test
	public void executeSafely() throws Exception
	{
		TestContext context = new TestContext();

		TypeSafeChainBase<TestContext> chain = new TypeSafeChainBase<TestContext>();

		chain.addCommand(new TestTypeSafeCommand());

		assertThat(context.getCounter(), nullValue());

		boolean result = chain.executeSafely(context);

		assertThat(context.getCounter(), equalTo(7));
		assertThat(result, equalTo(CONTINUE_PROCESSING));
	}

	@Test(expected = TestException.class)
	public void executeSafely_exception() throws Exception
	{
		TestContext context = new TestContext();

		TypeSafeChainBase<TestContext> chain = new TypeSafeChainBase<TestContext>();

		chain.addCommand(new TestExceptionTypeSafeCommand());

		chain.executeSafely(context);
	}

	@Test
	public void execute() throws Exception
	{
		TestContext context = new TestContext();

		TypeSafeChainBase<TestContext> chain = new TypeSafeChainBase<TestContext>();

		chain.addCommand(new TestTypeSafeCommand());

		assertThat(context.getCounter(), nullValue());

		boolean result = chain.execute(context);

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

	private class TestNonTypeSafeCommand implements Command
	{
		@Override
		public boolean execute(Context context) throws Exception
		{
			TestContext testContext = (TestContext)context;

			testContext.setCounter(7);

			return CONTINUE_PROCESSING;
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

	private class TestExceptionTypeSafeCommand extends TypeSafeCommandBase<TestContext>
	{
		@Override
		public boolean executeSafely(TestContext context) throws Exception
		{
			throw new TestException();
		}
	}

	@SuppressWarnings("serial")
	private class TestException extends Exception { }
}
