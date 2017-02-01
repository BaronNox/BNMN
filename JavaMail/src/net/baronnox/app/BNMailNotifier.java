package net.baronnox.app;

import javafx.application.Application;
import javafx.stage.Stage;
import net.baronnox.app.scenes.LoginScene;

public class BNMailNotifier extends Application {
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("BNMN - v0.0.0");
		primaryStage.setResizable(false);
		
		primaryStage.setScene(LoginScene.getScene(primaryStage));
		primaryStage.show();
	}
}