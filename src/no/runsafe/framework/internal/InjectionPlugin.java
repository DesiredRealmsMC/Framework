package no.runsafe.framework.internal;

import no.runsafe.framework.api.IKernel;
import no.runsafe.framework.api.IOutput;
import no.runsafe.framework.api.event.plugin.IPluginDisabled;
import no.runsafe.framework.api.event.plugin.IPluginEnabled;
import no.runsafe.framework.internal.command.CommandEngine;
import no.runsafe.framework.internal.configuration.Configuration;
import no.runsafe.framework.internal.configuration.ConfigurationEngine;
import no.runsafe.framework.internal.database.SchemaUpdater;
import no.runsafe.framework.internal.database.jdbc.Database;
import no.runsafe.framework.internal.event.EventEngine;
import no.runsafe.framework.minecraft.RunsafeServer;
import no.runsafe.framework.timer.Scheduler;
import org.bukkit.plugin.java.JavaPlugin;
import org.picocontainer.DefaultPicoContainer;
import org.picocontainer.behaviors.Caching;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Base plugin class containing all the injection handling code
 */
public abstract class InjectionPlugin extends JavaPlugin implements IKernel
{
	public static final HashMap<String, InjectionPlugin> Instances = new HashMap<String, InjectionPlugin>();

	/**
	 * get the first implementation of a given API from any plugin
	 *
	 * @param apiType The interface specification needed
	 * @return The first available implementation of the interface
	 */
	public static <T> T getFirstPluginAPI(Class<T> apiType)
	{
		for (InjectionPlugin plugin : Instances.values())
		{
			T instance = plugin.getComponent(apiType);
			if (instance != null)
				return instance;
		}
		return null;
	}

	/**
	 * get all implementations of a given API from all plugins
	 *
	 * @param apiType The interface specification needed
	 * @return The first available implementation of the interface
	 */
	public static <T> List<T> getPluginAPI(Class<T> apiType)
	{
		List<T> results = new ArrayList<T>();
		for (InjectionPlugin plugin : Instances.values())
		{
			List<T> instance = plugin.getComponents(apiType);
			if (instance != null)
				results.addAll(instance);
		}
		return results;
	}

	@Override
	public final void addComponent(Object implOrInstance)
	{
		output.finer(
			"Plugin %s added component %s",
			this.getName(),
			implOrInstance instanceof Class ? ((Class) implOrInstance).getCanonicalName() : implOrInstance.getClass().getCanonicalName()
		);
		container.addComponent(implOrInstance);
	}

	@Override
	public final <T> T getComponent(Class<T> type)
	{
		return this.container.getComponent(type);
	}

	@Override
	public final <T> T getInstance(Class<T> type)
	{
		container.addComponent(type);
		T instance = container.getComponent(type);
		container.removeComponent(type);
		return instance;
	}

	@Override
	public final <T> List<T> getComponents(Class<T> type)
	{
		output.finer("Got request for all instances of %s", type.getCanonicalName());
		return this.container.getComponents(type);
	}

	@Override
	public final void onEnable()
	{
		if (container == null)
			initializePlugin();
		this.container.start();
		output.fine("Plugin initialized.");

		for (IPluginEnabled impl : getComponents(IPluginEnabled.class))
			impl.OnPluginEnabled();
		output.fine("Plugin enabled event executed.");
	}

	@Override
	public final void onDisable()
	{
		output.fine("Disabling plugin %s", this.getName());
		for (IPluginDisabled impl : getComponents(IPluginDisabled.class))
			impl.OnPluginDisabled();
	}

	protected void initializePlugin()
	{
		Instances.put(getName(), this);
		if (RunsafeServer.Instance == null)
			RunsafeServer.Instance = new RunsafeServer(this.getServer());

		container = new DefaultPicoContainer(new Caching());
		addStandardComponents();
	}

	private void addStandardComponents()
	{
		this.container.addComponent(this);
		this.container.addComponent(ConfigurationEngine.class);
		this.container.addComponent(this.getServer().getPluginManager());
		this.container.addComponent(new RunsafeServer(this.getServer()));
		this.container.addComponent(this.getLogger());
		this.container.addComponent(Configuration.class);
		this.container.addComponent(Broadcaster.class);
		this.container.addComponent(Database.class);
		this.container.addComponent(new Scheduler(this.getServer().getScheduler(), this));
		this.container.addComponent(SchemaUpdater.class);
		this.container.addComponent(EventEngine.class);
		this.container.addComponent(CommandEngine.class);
		this.container.addComponent(HookEngine.class);
		this.container.addComponent(VersionEngine.class);
		this.container.addComponent(no.runsafe.framework.internal.lua.Environment.class);
	}

	private DefaultPicoContainer container = null;
	protected IOutput output;
}
