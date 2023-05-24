package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import controllers.CommandType;

public class ClientHandler implements Runnable {
	private static ClientManager clientManager = new ClientManager();
	private Socket socket;
	private BufferedWriter writer;
	private BufferedReader reader;

	public ClientHandler(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String line;
			while (!socket.isClosed()) {
				line = reader.readLine();
				if (line == null || line.isEmpty()) {
					break;
				}
				System.out.println(line);
				String[] tokens = line.split(",");
				handlerCommand(tokens);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void handlerCommand(String[] data) {
		CommandType command = CommandType.valueOf(data[0]);
		switch (command) {
			case LOGIN: {
				clientManager.addClient(data[1], socket);
				Server.showData.appendText(data[1] + ": connect\n");
				sendMessageToAll(CommandType.CLIENT_CONNECTED, data[1]);
			}
				break;
			case CLOSE: {
				try {
					clientManager.getClientByName(data[1]).close();
					if (clientManager.deleteClient(data[1])) {
						Server.showData.appendText(data[1] + ": close\n");
					}
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
			}
				break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + command);
		}
	}

	public synchronized void sendMessageToAll(CommandType commandType, String content) {
		System.out.println("send message");
		BufferedWriter bw = null;
		String message = commandType + "," + content;
		try {
			for (Socket socketSend : clientManager.getAllClients().values()) {
				System.out.println("huhu1");
				if (socketSend == socket) {
					System.out.println("huhu2");
					continue;
				}
				System.out.println("huhu 3");
				bw = new BufferedWriter(new OutputStreamWriter(socketSend.getOutputStream()));
				bw.write(message);
				bw.newLine();
				bw.flush();
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} finally {
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
			}
		}
	}
}
