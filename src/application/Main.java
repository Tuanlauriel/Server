package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;

import controllers.HomeController;

public class Main extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public static Stage stage;
	private static HomeController home;
	
	@Override
	public void start(Stage stage) {
		Main.stage = stage;
		stage.setOnCloseRequest( even -> {
			if(home != null) {
				home.timerCounter.isRunning = false;
				home.server.close();
			}
		});
		init();
	}
	
	public void init() {
		switchToLogin();
	}
	
	public static void switchToLogin() {
		try {
			if(stage != null) {
				FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/views/login-view.fxml"));
				Scene scene = new Scene(fxmlLoader.load());
				stage.setScene(scene);
				stage.setTitle("Tuan Dev");
				stage.show();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void switchToHome(String iPAddress, int port) {
		try {
			if(stage != null) {
				FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/views/home-view.fxml"));
				Scene scene = new Scene(fxmlLoader.load());
				home = fxmlLoader.getController();
				home.setIPAddress(iPAddress);
				home.setPort(port);
				stage.setScene(scene);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
