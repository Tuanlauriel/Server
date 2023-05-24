package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import server.Server;

public class HomeController implements Initializable {

	@FXML
	private Label ipLb;
	
	@FXML
	private Label portLb;
	
	@FXML
	private Label timeLb;

	@FXML
	private TextArea infoTA;
	
	private String iPAddress;
	
	private int port;
	
	public TimerCounter timerCounter;

	public Server server;

	public void setIPAddress(String iPAddress) {
		this.iPAddress = iPAddress;
		ipLb.setText("IP: " + iPAddress);
	}
	
	public void setPort(int port) {
		this.port = port;
		portLb.setText("Port: " + port);

		server = new Server(iPAddress, port, infoTA);
		new Thread(server).start();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		timerCounter = new TimerCounter(timeLb);
		new Thread(timerCounter).start();
	}
	

}
