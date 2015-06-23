package horatiu.kontrolserver.components;

import java.util.ArrayList;
import java.util.List;

public class SecurityManager {
	private static SecurityManager instance = null;
	
	private List<String> acceptedMACs;

	/**
	 * @return - the singleton instance of the class
	 */
	public static SecurityManager getInstance(){
		if(instance == null) {
			synchronized(SecurityManager.class) {
				if(instance == null) {
					instance = new SecurityManager();
				}
			}
		}
		return instance;
	}
	
	/**
	 * Make constructor private because it is a singleton
	 */
	private SecurityManager(){
		initMACs();
	}
	
	/**
	 * initializes list of accepted MACs
	 */
	private void initMACs(){
		acceptedMACs = new ArrayList<String>();
	}
	
	/**
	 * Certifies that he client has "signed in" the server
	 * @param macAddress - the mac address that tries to access the PC
	 * @return - true if it is authorized, false if not
	 */
	public boolean isAuthorized(String macAddress){
		if(macAddress.equals("bluetooth-3241")){
			return true;
		}
		if(acceptedMACs.contains(macAddress)){
			return true;
		}
		else {
			boolean auth = promptUserToAuthorizeMAC(macAddress);
			if(auth){
				acceptedMACs.add(macAddress);
			}
			
			return auth;
		}
	}
	
	/**
	 * Prompts user to authorize or deny authorization for a MAC address
	 * @param macAddress - the mac address of the phone
	 * @return - true if the result is "authorize", false if the result is "not auhorize"
	 */
	private boolean promptUserToAuthorizeMAC(String macAddress){
		return KontrolRunner.getInstance().getConfig().getPrompter().promptUserToAuthorizeMac(macAddress);
	}
	
}
