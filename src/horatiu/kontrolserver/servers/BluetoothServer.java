package horatiu.kontrolserver.servers;

import horatiu.kontrolserver.components.KontrolBrain;

import java.io.InputStream;

import javax.bluetooth.RemoteDevice;
import javax.bluetooth.UUID;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import javax.microedition.io.StreamConnectionNotifier;

public class BluetoothServer implements Server {
	UUID uuid = new UUID("fa87c0d0afac11de8a390800200c9a66",false);

	private boolean running = false;

	public void run() {
		//start running the server in a different thread
		Thread thread = new Thread(new Runnable(){

			@Override
			public void run() {
				if(!running){
					running = true;
					try{
						while(running){
							StreamConnection con = null;
							StreamConnectionNotifier service = null;
							try {
								String url = "btspp://localhost:" + uuid +
										//  new UUID( 0x1101 ).toString() + 
										";name=File Server";
								service = 
										(StreamConnectionNotifier) Connector.open( url );

								con = 
										(StreamConnection) service.acceptAndOpen();
//								OutputStream dos = con.openOutputStream();
								InputStream dis = con.openInputStream();
     
								RemoteDevice dev = RemoteDevice.getRemoteDevice(con);    
								while (running) {	
									if (dev !=null) 
									{	
										// dos.flush();
									}        	
									byte buffer[] = new byte[1024];
									int bytes_read = dis.read( buffer );                
									String received = new String(buffer, 0, bytes_read);
//									System.out.println("Received: '" + received + "'");
									KontrolBrain.getInstance().createAndHandleRequest(received).toString().getBytes();
//									dos.flush();
								}	
								// con.close();
							} catch ( Exception e ) {
								e.printStackTrace();
								if(con != null){
									con.close();
								}
								if(service != null){
									service.close();
								}
							}    
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
