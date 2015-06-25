package horatiu.kontrolserver.components;

import horatiu.kontrolserver.commands.Command;
import horatiu.kontrolserver.servers.Server;

import java.util.ArrayList;
import java.util.List;

public class KontrolRunner {
	private static KontrolRunner instance = null;
	private KontrolBrain brain = KontrolBrain.getInstance();
	private List<Server> servers = new ArrayList<Server>();
	private KontrolRunnerConfig config = new KontrolRunnerDefaultConfig();
	
	/**
	 * Make KontrolRunner constructor private for the singleton
	 */
	private KontrolRunner(){
	}
	
	/**
	 * Get the instance of the singleton
	 */
	public static KontrolRunner getInstance(){
		if(instance == null){
			synchronized(KontrolRunner.class){
				if(instance == null){
					instance = new KontrolRunner();
				}
			}
		}
		return instance;
	}
	
	public KontrolRunnerConfig getConfig(){
		return config;
	}
	
	/**
	 * Runs the whole Kontrol app / server part using a custom configuration object
	 * @param config - the custom configuration object
	 */
	public void run(KontrolRunnerConfig config){
		this.config = config;
		for(Server server : config.getServers()){
			server.run();
		}
		
		for(Command command : config.getCommands()){
			brain.addCommand(command);
		}
	}
	
	/**
	 * Runs the whole Kontrol app / server part using standard configuration
	 */
	public void run(){
		run(new KontrolRunnerDefaultConfig());
	}
}
