package horatiu.kontrolserver.commands;

import horatiu.kontrolserver.components.Request;
import horatiu.kontrolserver.components.Response;

public interface Command {
	Response handleRequest(Request request);
	
	String getCommandName();
}
