package horatiu.kontrolserver.components;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class HostInfo {
	
	/**
	 * Gets the current IP address of the machine(as a string)
	 * @return - the string representation of the ip address
	 * @throws UnknownHostException 
	 */
	public static String getIpAddressString() throws UnknownHostException{
		//TODO: test
		return InetAddress.getLocalHost().getHostAddress();
	}
	
	public static int getPort(){
		return 4672;
	}
	
	public static String getHostName() throws UnknownHostException{
		//TODO: test
		return InetAddress.getLocalHost().getHostName();
	}
	
	public static String getHostId() {
		//TODO: implement
		return "";
	}
}
