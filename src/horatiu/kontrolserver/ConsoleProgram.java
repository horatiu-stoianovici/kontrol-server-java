package horatiu.kontrolserver;

import horatiu.kontrolserver.components.KontrolRunner;

public class ConsoleProgram {

	public static void main(String[] args) throws InterruptedException {
		KontrolRunner.getInstance().run();
		
		while(true) {
			Thread.sleep(100);
		}
	}

}
