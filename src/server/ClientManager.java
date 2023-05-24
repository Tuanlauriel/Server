package server;

import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class ClientManager {
	private Map<String, Socket> clients;
	
	public ClientManager() {
		clients = new HashMap<>();
	}
	
	public void addClient(String name, Socket socket)  {
		clients.put(name, socket);
	}
	
	public boolean deleteClient(String name) {
		return clients.remove(name) != null;
	}
	
	public Socket getClientByName(String name) {
		return clients.get(name);
	}
	
	public Map<String, Socket> getAllClients() {
		return clients;
	}
}
