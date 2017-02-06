package net.baronnox.app.popups.windows;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public abstract class PopUpGeneric {
	private Stage window;
	private BorderPane root;
	private Scene scene;
	
	
	
	public PopUpGeneric(int width, int height) {
		this.window = new Stage();
		window.initStyle(StageStyle.UTILITY);
		window.setResizable(false);
		this.root = new BorderPane();
		this.scene = new Scene(root, width, height);
		
		initUI();
	}

	private void initUI() {
		window.setScene(scene);
		window.show();
	}
	
	public BorderPane getPane() {
		return root;
	}
	
	protected Stage getStage() {
		return window;
	}
	
	protected void setWindowTitle(String title) {
		this.window.setTitle(title);
	}
}
