package no.runsafe.framework.server.block;

import org.bukkit.block.Sign;

public class RunsafeSign extends RunsafeBlockState
{
	public RunsafeSign(Sign toWrap)
	{
		super(toWrap);
		sign = toWrap;
	}

	public String[] getLines()
	{
		return sign.getLines();
	}

	public String getLine(int i)
	{
		return sign.getLine(i);
	}

	public void setLine(int i, String s)
	{
		sign.setLine(i, s);
	}

	@Override
	public Sign getRaw()
	{
		return sign;
	}

	private final Sign sign;
}
