package horatiu.kontrolserver.servers;

public interface Server {
	void run();
	
	void stop();
	
	void setLogRequests(boolean logRequests);
	
	boolean getLogRequests();
}
