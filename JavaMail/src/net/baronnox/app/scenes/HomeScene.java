package net.baronnox.app.scenes;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import net.baronnox.dataobjects.addressbook.Account;
import net.baronnox.dataobjects.addressbook.Contact;

public class HomeScene extends Scene {
	private static final int WIDTH = 640;
	private static final int HEIGHT = WIDTH / 2;
	
	private Stage primaryStage;
	private Account acc;
	private ScrollPane scrollPane;
	
	public static HomeScene getScene(Stage primaryStage, Account acc) {
		ScrollPane scrollPane = new ScrollPane();
		return new HomeScene(scrollPane, primaryStage, acc);
	}
	
	private HomeScene(ScrollPane scrollPane, Stage primaryStage, Account acc) {
		super(scrollPane, WIDTH, HEIGHT);
		this.primaryStage = primaryStage;
		this.scrollPane = scrollPane;
		
		initScene();
	}

	private void initScene() {
		primaryStage.setResizable(true);
		
		VBox vBox = new VBox(0);
		scrollPane.setContent(vBox);
		
		MenuBar menuBar = new MenuBar();
		menuBar.setPrefWidth(WIDTH - 2);
		final Menu file = new Menu("File");
		final Menu help = new Menu("Help");
		menuBar.getMenus().addAll(file, help);
		
		vBox.getChildren().add(menuBar);
		
		GridPane gridPane = new GridPane();
		gridPane.setAlignment(Pos.TOP_LEFT);
		gridPane.setPrefWidth(WIDTH - 2);
		gridPane.setGridLinesVisible(true);
		
		ScrollPane innerScrollPan = new ScrollPane();
		innerScrollPan.setHbarPolicy(ScrollBarPolicy.NEVER);
		innerScrollPan.setPrefHeight(HEIGHT / 2);
		
		ListView<Contact> listView = new ListView<>();
		listView.getItems().add(new Contact("test1@byom.de", "Random fucker"));
		innerScrollPan.setContent(listView);
		
		gridPane.add(innerScrollPan, 0, 0);
		
		vBox.getChildren().add(gridPane);
		
		
	}
}
