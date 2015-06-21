package horatiu.kontrolserver.servers;

import horatiu.kontrolserver.components.KontrolBrain;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UdpServer implements Server {
	private boolean running = false;
	private static final int PORT = 4672;

	public void run() {
		//start running the server in a different thread
		Thread thread = new Thread(new Runnable(){

			@Override
			public void run() {
				if(!running){
					running = true;
					try{
						DatagramSocket serverSocket = new DatagramSocket(PORT);
						byte[] receiveData = new byte[1024];

						while(true) {           
							DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);  
							serverSocket.receive(receivePacket);       
							String receivedData = new String( receivePacket.getData());
							KontrolBrain.getInstance().createAndHandleRequest(receivedData);
						}
					}
					catch(Exception e){
						running = false;
					}
				}
			}
		});
		thread.start();
	}

	public void stop() {
		running = false;
	}

	public void setLogRequests(boolean logRequests) {
		// TODO Auto-generated method stub

	}

	public boolean getLogRequests() {
		// TODO Auto-generated method stub
		return false;
	}

}
