package net.baronnox.app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class BNMailNotifier extends Application {

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("BaronNox's Mail Notifier");
		
		StackPane root = new StackPane();
		primaryStage.setScene(new Scene(root, 300, 250));
		primaryStage.show();
	}

}
