package no.runsafe.framework.messaging;

import no.runsafe.framework.RunsafePlugin;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MessagePump implements IMessagePump
{
	public static IMessagePump GetPump(RunsafePlugin plugin)
	{
		Plugin pump = plugin.getServer().getPluginManager().getPlugin("RunsafeMessageBus");
		if (pump == null || !(pump instanceof IPumpProvider))
			return null;

		return ((IPumpProvider) pump).getInstance();
	}

	@Override
	public void RegisterService(IMessageBusService service)
	{
		if (!services.containsKey(service.getServiceName()))
			services.put(service.getServiceName(), new ArrayList<IMessageBusService>());

		if (!services.get(service.getServiceName()).contains(service))
			services.get(service.getServiceName()).add(service);
	}

	@Override
	public void UnregisterService(IMessageBusService service)
	{
		if (!services.containsKey(service.getServiceName()))
			return;

		if (!services.get(service.getServiceName()).contains(service))
			return;

		services.get(service.getServiceName()).remove(service);
	}

	@Override
	public Response HandleMessage(Message message)
	{
		Response response = new Response();
		response.setStatus(MessageBusStatus.MISSING);

		if (services.containsKey((message.getTargetService())))
		{
			for (IMessageBusService svc : services.get(message.getTargetService()))
			{
				Response reply = svc.processMessage(message);
				if (reply != null)
				{
					response = reply;
					break;
				}
			}
		}
		return response;
	}

	@Override
	public List<Response> HandleMessageAll(Message message)
	{
		if (!services.containsKey(message.getTargetService()))
			return null;

		ArrayList<Response> responses = new ArrayList<Response>();
		for (IMessageBusService svc : services.get(message.getTargetService()))
		{
			Response reply = svc.processMessage(message);
			if (reply != null)
			{
				responses.add(reply);
			}
		}
		return responses;
	}

	private HashMap<String, ArrayList<IMessageBusService>> services = new HashMap<String, ArrayList<IMessageBusService>>();
}
