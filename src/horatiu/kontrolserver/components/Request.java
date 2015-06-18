package horatiu.kontrolserver.components;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Request {
	private String requestText, commandName, macAddress;
	private List<String> parameters;
	
	public Request(String rawRequestText) throws JSONException{
		requestText = rawRequestText;
		
		//parsing the json of the request
		JSONObject json = new JSONObject(rawRequestText);
		
		//get the macaddress of the requestor
		macAddress = json.getString("MACAddress");
		
		//converting json parameters array to java array
		JSONArray jarray = json.getJSONArray("Parameters");
		parameters = new ArrayList<String>();
		if(jarray != null){
			for(int i = 0; i < jarray.length(); i++) {
				parameters.add(jarray.getString(i));
			}
		}
		
		//get the command name from the request
		commandName = json.getString("CommandName");
	}
	
	/**
	 * @return the raw request text
	 */
	public String getRequestText(){
		return requestText;
	}
	
	/**
	 * @return the name of the requested command
	 */
	public String getRequestedCommandName(){
		return commandName;
	}
	
	/**
	 * @return get the parameters sent in the request
	 */
	public List<String> getParameters(){
		return parameters;
	}
	
	/**
	 * @return get the mac address of the requestor
	 */
	public String getMacAddress(){
		return macAddress;
	}
	
	/**
	 * Determines if the request is authorised or not
	 * @return true if the request is authorised or false if it is not 
	 */
	private boolean isAuthorised(){
		if (this.getRequestedCommandName().toUpperCase() != "AUTHORIZE")
		{
			boolean auth = SecurityManager.getInstance().isAuthorized(macAddress);
			if(!auth)
			{
				//TODO: log
			}
			return auth;
		}
		
		return true;
	}
}
