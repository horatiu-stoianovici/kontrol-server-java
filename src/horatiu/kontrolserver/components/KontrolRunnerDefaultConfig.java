package horatiu.kontrolserver.components;

import horatiu.kontrolserver.commands.Command;
import horatiu.kontrolserver.commands.KeyboardCommand;
import horatiu.kontrolserver.commands.LoginCommand;
import horatiu.kontrolserver.commands.MouseClickCommand;
import horatiu.kontrolserver.commands.MouseMoveCommand;
import horatiu.kontrolserver.commands.PresentationCommand;
import horatiu.kontrolserver.commands.ScrollCommand;
import horatiu.kontrolserver.servers.BluetoothServer;
import horatiu.kontrolserver.servers.Server;
import horatiu.kontrolserver.servers.TcpServer;
import horatiu.kontrolserver.servers.UdpServer;

import java.util.ArrayList;
import java.util.List;

public class KontrolRunnerDefaultConfig implements KontrolRunnerConfig {

	@Override
	public List<Server> getServers() {
		ArrayList<Server> servers = new ArrayList<Server>();
		servers.add(new TcpServer());
		servers.add(new UdpServer());
		servers.add(new BluetoothServer());
		return servers;
	}

	@Override
	public List<Command> getCommands() {
		ArrayList<Command> commands = new ArrayList<Command>();
		commands.add(new LoginCommand());
		commands.add(new MouseMoveCommand());
		commands.add(new MouseClickCommand());
		commands.add(new ScrollCommand());
		commands.add(new KeyboardCommand());
		commands.add(new PresentationCommand());
		return commands;
	}

	@Override
	public SecurityPrompter getPrompter() {
		return new DefaultSecurityPrompter();
	}

}
