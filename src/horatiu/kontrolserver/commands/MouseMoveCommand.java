package horatiu.kontrolserver.commands;

import horatiu.kontrolserver.components.Request;
import horatiu.kontrolserver.components.Response;
import horatiu.kontrolserver.components.Response.TcpStatusCodes;
import horatiu.kontrolserver.controllers.MouseController;

import java.awt.Point;

public class MouseMoveCommand implements Command {
	
	private String currentTouchId = "";
	private Point clientStartPoint, serverStartPoint;
	
	public Response handleRequest(Request request) {
		int clientX = Integer.parseInt(request.getParameters().get(0)),
		clientY = Integer.parseInt(request.getParameters().get(1));
		
		String uid = request.getParameters().get(2);
		
		try{
			if(!uid.equals(currentTouchId)){
				clientStartPoint = new Point(clientX, clientY);
				serverStartPoint = MouseController.getCurrentMousePosition();
				currentTouchId = uid;
			}
			else {
				Point delta = new Point(clientX - clientStartPoint.x, clientY - clientStartPoint.y);
				Point newPos = new Point(serverStartPoint.x + delta.x, serverStartPoint.y + delta.y);
				MouseController.setMousePosition(newPos);
			}
		}
		catch(Exception e) {
			return new Response(TcpStatusCodes.WrongFormat);
		}
		return new Response(TcpStatusCodes.Ok);
	}

	public String getCommandName() {
		return "mouse-move";
	}

}
