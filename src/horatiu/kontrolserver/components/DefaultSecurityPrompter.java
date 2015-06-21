package horatiu.kontrolserver.components;

import java.util.Scanner;

public class DefaultSecurityPrompter implements SecurityPrompter {

	public boolean promptUserToAuthorizeMac(String mac) {
		System.out.printf("A user with mac address '%s' wants to access this computer. Do you want to allow this? (Y/N)", mac);
		Scanner sc = new Scanner(System.in);
		String a = sc.next();
		if(a.toLowerCase().equals("y")){
			return true;
		}
		return false;
	}

}
