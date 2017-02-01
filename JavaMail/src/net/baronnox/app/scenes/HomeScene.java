package net.baronnox.app.scenes;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import net.baronnox.dataobjects.addressbook.Account;

public class HomeScene extends Scene {
	private Stage primaryStage;
	private Account acc;
	private GridPane grid;
	
	public static HomeScene getScene(Stage primaryStage, Account acc) {
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.TOP_LEFT);
		grid.setGridLinesVisible(true);
		return new HomeScene(grid, primaryStage, acc);
	}
	
	private HomeScene(GridPane parent, Stage primaryStage, Account acc) {
		super(parent, 640, 320);
		this.primaryStage = primaryStage;
		this.grid = parent;
		
		initScene();
	}

	private void initScene() {
		
	}
}
