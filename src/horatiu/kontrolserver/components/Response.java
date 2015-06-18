package horatiu.kontrolserver.components;

import org.json.JSONException;
import org.json.JSONStringer;
import org.json.JSONWriter;

public class Response {
	private int statusCode;
	private StringBuilder responseContent;
	
	public int getStatusCode(){
		return statusCode;
	}
	
	public void setStatusCode(int code){
		statusCode = code;
	}
	
	public Response(int statusCode, String content){
		this.statusCode = statusCode;
	}
	
	public Response(){
		this(TcpStatusCodes.Ok, "");
	}
	
	public Response(int statusCode){
		this(statusCode, "");
	}
	
	/**
	 * Writes content to the response
	 * @param content - the content to be written
	 */
	public void write(String content){
		responseContent.append(content);
	}
	
	/**
	 * Get the string representation of the request
	 */
	@Override
	public String toString() {
		JSONWriter writer = new JSONStringer();
		try {
			return writer.object()
				.key("StatusCode")
				.value(statusCode)
				.key("Content")
				.value(responseContent.toString())
			.endObject().toString();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}
	
	/**
	 * Class that holds the values for the TCP status codes
	 * @author Horatiu
	 *
	 */
	public class TcpStatusCodes {
		public static final int
			Ok = 200,
	        NotAuthorized = 303,
	        WrongFormat = 405,
	        NotFound = 404;
	}
}
