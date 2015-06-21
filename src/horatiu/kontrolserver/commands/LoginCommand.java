package horatiu.kontrolserver.commands;

import horatiu.kontrolserver.components.Request;
import horatiu.kontrolserver.components.Response;
import horatiu.kontrolserver.components.Response.TcpStatusCodes;
import horatiu.kontrolserver.components.SecurityManager;

public class LoginCommand implements Command {

	public Response handleRequest(Request request) {
		Response response = new Response();
		response.setStatusCode(TcpStatusCodes.Ok);
		
		if (!SecurityManager.getInstance().isAuthorized(request.getMacAddress())){
			response.setStatusCode(TcpStatusCodes.NotAuthorized);
		}
		else{
			response.setStatusCode(TcpStatusCodes.Ok);
		}
		return response;
	}

	public String getCommandName() {
		return "authorize";
	}

}
