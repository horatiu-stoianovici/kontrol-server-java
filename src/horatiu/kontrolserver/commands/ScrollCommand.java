package horatiu.kontrolserver.commands;

import horatiu.kontrolserver.components.Request;
import horatiu.kontrolserver.components.Response;
import horatiu.kontrolserver.components.Response.TcpStatusCodes;
import horatiu.kontrolserver.controllers.MouseController;

public class ScrollCommand implements Command {

	public Response handleRequest(Request request) {
		int command = Integer.parseInt(request.getParameters().get(0));
		switch(command){
		case 1:
			MouseController.scrollWithInitialVelocity(-Float.parseFloat(request.getParameters().get(1)), Float.parseFloat(request.getParameters().get(2)));
			break;
		case 2:
			MouseController.stopScrolling();
			break;
		case 3:
			MouseController.scrollHorizontally(-(int)Float.parseFloat(request.getParameters().get(1)));
			MouseController.scrollVertically((int)Float.parseFloat(request.getParameters().get(2)));
			break;
		}
		
		return new Response(TcpStatusCodes.Ok);
	}

	public String getCommandName() {
		return "scroll";
	}

}
