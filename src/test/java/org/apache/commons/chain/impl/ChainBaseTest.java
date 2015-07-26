package org.apache.commons.chain.impl;

import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import java.util.Map;

import org.apache.commons.chain.Chain;
import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;
import org.junit.Test;

/**
 * Tests that short-circuiting commands (i.e. those that return {@link Command#PROCESSING_COMPLETE}) work as expected
 * even from sub-chains or sub-sub-chains.
 * 
 * @author Dan Ignat
 */
public class ChainBaseTest
{
	@Test
	public void testShortCircuitingFromSubChain() throws Exception
	{
		Chain chain = new ChainBase();
		chain.addCommand(new RegularCommand(1));

		Chain subChain = new ChainBase();
		subChain.addCommand(new RegularCommand(2));
		subChain.addCommand(new ShortCircuitingCommand(3));
		subChain.addCommand(new RegularCommand(4));

		chain.addCommand(subChain);
		chain.addCommand(new RegularCommand(5));

		Context context = new ContextBase();

		chain.execute(context);

		@SuppressWarnings("unchecked")
		Map<String, ?> contextMap = (Map<String, ?>) context;

		assertThat(contextMap, hasKey("result1"));
		assertThat(contextMap, hasKey("result2"));
		assertThat(contextMap, hasKey("result3"));
		assertThat(contextMap, not(hasKey("result4")));
		assertThat(contextMap, not(hasKey("result5")));
	}

	@Test
	public void testShortCircuitingFromSubSubChain() throws Exception
	{
		Chain chain = new ChainBase();
		chain.addCommand(new RegularCommand(1));

		Chain subChain = new ChainBase();
		subChain.addCommand(new RegularCommand(2));

		Chain subSubChain = new ChainBase();
		subSubChain.addCommand(new RegularCommand(3));
		subSubChain.addCommand(new ShortCircuitingCommand(4));
		subSubChain.addCommand(new RegularCommand(5));

		subChain.addCommand(subSubChain);
		subChain.addCommand(new RegularCommand(6));

		chain.addCommand(subChain);
		chain.addCommand(new RegularCommand(7));

		Context context = new ContextBase();

		chain.execute(context);

		@SuppressWarnings("unchecked")
		Map<String, ?> contextMap = (Map<String, ?>) context;

		assertThat(contextMap, hasKey("result1"));
		assertThat(contextMap, hasKey("result2"));
		assertThat(contextMap, hasKey("result3"));
		assertThat(contextMap, hasKey("result4"));
		assertThat(contextMap, not(hasKey("result5")));
		assertThat(contextMap, not(hasKey("result6")));
		assertThat(contextMap, not(hasKey("result7")));
	}

	private class RegularCommand implements Command
	{
		private int index;

		public RegularCommand(int index)
		{
			this.index = index;
		}

		@Override
		@SuppressWarnings("unchecked")
		public boolean execute(Context context) throws Exception
		{
			context.put("result" + index, null);

			return CONTINUE_PROCESSING;
		}
	}

	private class ShortCircuitingCommand extends RegularCommand
	{
		public ShortCircuitingCommand(int index)
		{
			super(index);
		}

		@Override
		public boolean execute(Context context) throws Exception
		{
			super.execute(context);

			return PROCESSING_COMPLETE;
		}
	}
}
