package no.runsafe.framework.api;

import java.util.List;

public interface IKernel
{
	public abstract void addComponent(Object implOrInstance);

	public abstract <T> T getComponent(Class<T> type);

	public abstract <T> List<T> getComponents(Class<T> type);

	<T> T getInstance(Class<T> type);
}