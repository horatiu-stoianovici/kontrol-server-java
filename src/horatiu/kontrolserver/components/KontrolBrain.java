package horatiu.kontrolserver.components;

import horatiu.kontrolserver.commands.Command;
import horatiu.kontrolserver.components.Response.TcpStatusCodes;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;

public class KontrolBrain {
	private static KontrolBrain instance = null;
	private Map<String, Command> commands = new HashMap<String, Command>();
	
	/**
	 * Get the instance of the singleton
	 */
	public static KontrolBrain getInstance(){
		if(instance == null){
			synchronized(KontrolBrain.class){
				if(instance == null){
					instance = new KontrolBrain();
				}
			}
		}
		return instance;
	}
	
	/**
	 * Make constructor private for singleton
	 */
	private KontrolBrain(){
	}
	
	public Response createAndHandleRequest(String receivedText) throws JSONException{
		Request request = new Request(receivedText);
		
		Response response = new Response();
		response.setStatusCode(TcpStatusCodes.NotFound);
		
		if(!SecurityManager.getInstance().isAuthorized(request.getMacAddress())){
			response.setStatusCode(TcpStatusCodes.NotAuthorized);
		}
		else {
			if(commands.containsKey(request.getRequestedCommandName())){
				response = commands.get(request.getRequestedCommandName()).handleRequest(request);
			}
			else {
				response.setStatusCode(TcpStatusCodes.NotFound);
				response.write("The command you requested does not exist!");
			}
		}
		
		return response;
	}
	
	/**
	 * add a command to the list of commands
	 * @param command - the command to be added
	 */
	public void addCommand(Command command){
		commands.put(command.getCommandName(), command);
	}
	
}
