package no.runsafe.framework.api;

import java.util.logging.Level;

public interface IDebug
{
	// Sends the supplied String to the console with &-codes replaced with ANSI sequences
	public void writeColoured(String message);

	public void writeColoured(String message, Object... params);

	public void writeColoured(String message, Level level, Object... params);

	public void logException(Exception exception);

	public void logWarning(String message, Object... params);

	public void logError(String message, Object... params);

	/**
	 * This will log a fatal error and make the server die in a great big fireball.
	 *
	 * @param message The message to print before exiting the process.
	 * @param params  Values to be passed into the message using String.format
	 */
	void logFatal(String message, Object... params);

	public void logInformation(String message, Object... params);

	// Sends the supplied String to the console/log the output handler has
	public void outputToConsole(String message);

	// Sends the supplied String with the supplied logging level to the console/log the output handler has
	public void outputToConsole(String message, Level level);

	// Sends the supplied String to the console/log the output handler has if the debug level is high enough
	public void outputDebugToConsole(String message, Level messageLevel, Object... params);

	// Gets the current debug output level
	public Level getDebugLevel();

	// Sets the debug output level
	public void setDebugLevel(Level level);

	void write(String message);

	void severe(String message, Object... params);

	void warning(String message, Object... params);

	void info(String message, Object... params);

	void config(String message, Object... params);

	void fine(String message, Object... params);

	void finer(String message, Object... params);

	void finest(String message, Object... params);

	void dumpData(Object object, Level messageLevel);
}
