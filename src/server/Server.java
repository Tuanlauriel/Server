package server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import javafx.scene.control.TextArea;

public class Server implements Runnable {

    public static TextArea showData;
    private String ip;
    private int port;
    private ServerSocket serverSocket;

    public Server(String ip, int port, TextArea showData) {
        this.ip = ip;
        this.port = port;
        Server.showData = showData;
    }

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(port, 0, InetAddress.getByName(ip));
            showData.appendText("Server started\n");
            while (!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                new Thread(new ClientHandler(socket)).start();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            close();
        }
    }

    public void close() {
        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
