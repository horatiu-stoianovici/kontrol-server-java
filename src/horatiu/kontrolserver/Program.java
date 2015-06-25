package horatiu.kontrolserver;

import horatiu.kontrolserver.components.KontrolRunner;
import horatiu.kontrolserver.components.KontrolRunnerConfig;
import horatiu.kontrolserver.components.KontrolRunnerDefaultConfig;
import horatiu.kontrolserver.components.SecurityPrompter;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Program {
	private static boolean running = true;
	private static TrayIcon trayIcon;


	public static void main(String[] args) throws InterruptedException {
		KontrolRunnerConfig config = new KontrolRunnerDefaultConfig(){
			@Override
			public SecurityPrompter getPrompter() {
				return new SecurityPrompter(){

					@Override
					public boolean promptUserToAuthorizeMac(String mac) {
						return JOptionPane.showConfirmDialog(null,
								"Do you want to allow the phone with MAC " + mac + " to connect to the server?", "Authorization required", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
					}
					
				};
			}
		};
		KontrolRunner.getInstance().run(config);

		if (!SystemTray.isSupported()) {
			System.out.println("SystemTray is not supported");
			return;
		}
		final PopupMenu popup = new PopupMenu();
		trayIcon =
				new TrayIcon(createImage("/images/launcher-2.gif", "tray icon"));
		final SystemTray tray = SystemTray.getSystemTray();

		// Create a pop-up menu components
		MenuItem aboutItem = new MenuItem("About");
		MenuItem exitItem = new MenuItem("Exit");

		//Add components to pop-up menu
		popup.add(aboutItem);
		popup.addSeparator();
		popup.add(exitItem);

		trayIcon.setPopupMenu(popup);
		trayIcon.setImageAutoSize(true);

		try {
			tray.add(trayIcon);
		} catch (AWTException e) {
			System.out.println("TrayIcon could not be added.");
		}

		ActionListener aboutActionListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,
						"The Kontrol server is running normally. You can connect to it using the Android Kontrol app");
			}
		};
		trayIcon.addActionListener(aboutActionListener);

		aboutItem.addActionListener(aboutActionListener);

		//type = TrayIcon.MessageType.ERROR;
		trayIcon.displayMessage("Kontrol",
				"The Kontrol server is running", TrayIcon.MessageType.INFO);

		exitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tray.remove(trayIcon);
				System.exit(0);
			}
		});

		while(true) {
			Thread.sleep(100);
		}
	}
	
	public static void infoBluetoothClientConnected(){
		trayIcon.displayMessage("Kontrol",
				"A client has connected to the server using Bluetooth!", TrayIcon.MessageType.INFO);
	}
	
	public static void infoBluetoothClientDisconnected(){
		trayIcon.displayMessage("Kontrol",
				"A Bluetooth client has disconnected from the server!", TrayIcon.MessageType.INFO);
	}

	protected static Image createImage(String path, String description) {
		URL imageURL = Program.class.getResource(path);

		if (imageURL == null) {
			System.err.println("Resource not found: " + path);
			return null;
		} else {
			return (new ImageIcon(imageURL, description)).getImage();
		}
	}

}
