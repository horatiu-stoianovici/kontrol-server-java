package horatiu.kontrolserver.components;

import horatiu.kontrolserver.commands.Command;
import horatiu.kontrolserver.servers.Server;

import java.util.List;

public interface KontrolRunnerConfig {
	
	/**
	 * @return - the servers that can get requests
	 */
	public List<Server> getServers();
	
	/**
	 * @return - the commands that can be executed
	 */
	public List<Command> getCommands();
	
	/**
	 * @return - the security prompter that will be use to prompt the user about a phone trying to access the PC
	 */
	public SecurityPrompter getSecurityPrompter();
}
