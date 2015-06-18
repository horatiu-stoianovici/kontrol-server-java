package horatiu.kontrolserver.commands;

import horatiu.kontrolserver.components.Request;
import horatiu.kontrolserver.components.Response;
import horatiu.kontrolserver.controllers.KeyboardController;

/**
 * The command class for pressing a keyboard key
 * @author Horatiu
 *
 */
public class KeyboardCommand implements Command {

	public Response handleRequest(Request request) {
		KeyboardController.pressKey(Integer.parseInt(request.getParameters().get(0)));
		return new Response();
	}

	public String getCommandName() {
		return "keyboard";
	}

}
