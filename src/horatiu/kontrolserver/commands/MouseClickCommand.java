package horatiu.kontrolserver.commands;

import horatiu.kontrolserver.components.Request;
import horatiu.kontrolserver.components.Response;
import horatiu.kontrolserver.components.Response.TcpStatusCodes;
import horatiu.kontrolserver.controllers.MouseController;

public class MouseClickCommand implements Command {

	public Response handleRequest(Request request) {
		int action = Integer.parseInt(request.getParameters().get(0));
		try {
			switch(action){
			case 0:
				MouseController.leftClickDown();
				break;
			case 1:
				MouseController.leftClickUp();
				break;
			case 2:
				MouseController.rightClickDown();
				break;
			case 3:
				MouseController.rightClickUp();
				break;
			}
		}
		catch(Exception e){
			return new Response(TcpStatusCodes.WrongFormat);
		}
		
		Response response = new Response();
		response.setStatusCode(TcpStatusCodes.Ok);
		return response;
	}

	public String getCommandName() {
		return "mouse-click";
	}

}
