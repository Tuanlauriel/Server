package controllers;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class LoginController {
	
	@FXML
	private TextField ipTF;
	
	@FXML
	private TextField portTF;
	
	@FXML
	public void startClick(ActionEvent event) {
		String ip = ipTF.getText();
		int port;
		if(ip == null || ip.isBlank()) {
			Alert notif = new Alert(AlertType.WARNING);
			notif.setTitle("Notification");
			notif.setHeaderText("The IP address is invalid");
			notif.show();
			return;
		}
		try {
			port = Integer.parseInt(portTF.getText());
		} catch (NumberFormatException e) {
			Alert notif = new Alert(AlertType.WARNING);
			notif.setTitle("Notification");
			notif.setHeaderText("Invalid port");
			notif.show();
			return;
		}
		
		Main.switchToHome(ip, port);
		
	}
	
}
