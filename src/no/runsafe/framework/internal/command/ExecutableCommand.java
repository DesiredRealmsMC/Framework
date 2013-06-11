package no.runsafe.framework.internal.command;

@Deprecated
public abstract class ExecutableCommand extends no.runsafe.framework.api.command.ExecutableCommand
{
	/**
	 * Defines the command
	 *
	 * @param commandName The name of the command. For top level commands, this must be as defined in plugin.yml
	 * @param description A short descriptive text of what the command does
	 * @param permission  A permission string that a player must have to run the command or null to allow anyone to run it
	 * @param arguments   Optional list of required command parameters
	 */
	public ExecutableCommand(String commandName, String description, String permission, String... arguments)
	{
		super(commandName, description, permission, arguments);
	}
}