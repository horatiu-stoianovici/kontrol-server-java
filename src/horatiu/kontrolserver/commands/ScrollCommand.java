package horatiu.kontrolserver.commands;

import horatiu.kontrolserver.components.Request;
import horatiu.kontrolserver.components.Response;
import horatiu.kontrolserver.components.Response.TcpStatusCodes;
import horatiu.kontrolserver.controllers.MouseController;

public class ScrollCommand implements Command {

	public Response handleRequest(Request request) {
		//the command is a parameters that tells exactly which type of scroll command the client issued
		int command = Integer.parseInt(request.getParameters().get(0));
		switch(command){
		case 2:
			//the command 1 is to start scrolling with an initial velocity
			MouseController.scrollWithInitialVelocity(-Float.parseFloat(request.getParameters().get(1)), Float.parseFloat(request.getParameters().get(2)));
			break;
		case 3:
			//the command 2 is to stop the scrolling immediately
			MouseController.stopScrolling();
			break;
		case 1:
			//the command 1 is for scrolling with a certain amount
			try{
				MouseController.scrollHorizontally(-(int)Float.parseFloat(request.getParameters().get(1)));
				MouseController.scrollVertically(-(int)Float.parseFloat(request.getParameters().get(2)));
			}
			catch(Exception e){
				e.printStackTrace();
			}
			break;
		}
		
		return new Response(TcpStatusCodes.Ok);
	}

	public String getCommandName() {
		return "scroll";
	}

}
