package horatiu.kontrolserver.servers;

import horatiu.kontrolserver.components.HostInfo;
import horatiu.kontrolserver.components.KontrolBrain;
import horatiu.kontrolserver.components.Response;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import org.json.JSONException;
import org.json.JSONStringer;
import org.json.JSONWriter;

public class TcpServer implements Server{

	private boolean running = false;
	private static final int PORT = 4673;

	public void run() {
		//start running the server in a different thread
		Thread thread = new Thread(new Runnable(){

			@Override
			public void run() {
				if(!running){
					running = true;
					try{
						ServerSocket welcomeSocket = new ServerSocket(8001);
						while(true){     
							Socket connectionSocket = welcomeSocket.accept();    
							BufferedReader inFromClient =     
									new BufferedReader(new InputStreamReader(connectionSocket.getInputStream())); 
							DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());   
							String receivedData = inFromClient.readLine();    
							Response response = KontrolBrain.getInstance().createAndHandleRequest(receivedData);
							outToClient.writeBytes(response.toString());  
							connectionSocket.close();
						}
					}
					catch(Exception e){
						running = false;
					}
				}
			}
		});
		thread.start();

		Thread thread2 = new Thread(new Runnable(){

			@Override
			public void run() {
				try {
					while(true){
						Thread.sleep(100);
						DatagramSocket s = new DatagramSocket(4673);
						s.setBroadcast(true);

						//get the message with the ip address from a client that requests information about the server
						String message = "";
						DatagramPacket packet;
						byte[] buf = new byte[512];
						packet = new DatagramPacket(buf, buf.length);
						s.receive(packet);
						String received = new String(packet.getData()).trim();
						s.close();

						InetAddress clientAddress = InetAddress.getByName(received);
						JSONWriter writer = new JSONStringer();
						try {
							message = writer.object()
									.key("IPAddress")
									.value(HostInfo.getIpAddressString())
									.key("HostName")
									.value(HostInfo.getHostName())
									.key("Device")
									.value(1) //wifi or cable, unknown in java
									.key("HostId")
									.value(HostInfo.getHostId())
									.endObject().toString();
						} catch (JSONException e) {
							e.printStackTrace();
						}
						Thread.sleep(1000);

						//sending the information to the client
						Socket clientSocket = new Socket(); 
						clientSocket.connect(new InetSocketAddress(clientAddress, PORT), 10000);

						DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());  
						BufferedReader inFromServer =  new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
						outToServer.write((message + "\n").getBytes());
						clientSocket.close();
					}
				}
				catch(Exception e){
					e.printStackTrace();
				}

			}
			//the detection signal thread
		});
		thread2.start();
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
