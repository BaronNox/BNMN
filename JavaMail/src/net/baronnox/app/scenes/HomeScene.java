package net.baronnox.app.scenes;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;
import net.baronnox.app.popups.windows.PUCreateContact;
import net.baronnox.dataobjects.Account;
import net.baronnox.dataobjects.addressbook.AddressBook;
import net.baronnox.dataobjects.addressbook.Contact;

public class HomeScene extends Scene {
	private static final int WIDTH = 640;
	private static final int HEIGHT = WIDTH / 2;
	
	private boolean isPUCreateContactShown;
	
	private Stage primaryStage;
	private Account acc;
	private AddressBook addressBook;
	private ScrollPane scrollPane;
	private ListView<Contact> listView;
	
	public static HomeScene getScene(Stage primaryStage, Account acc) {
		ScrollPane scrollPane = new ScrollPane();
		return new HomeScene(scrollPane, primaryStage, acc);
	}
	
	private HomeScene(ScrollPane scrollPane, Stage primaryStage, Account acc) {
		super(scrollPane, WIDTH, HEIGHT);
		this.primaryStage = primaryStage;
		this.scrollPane = scrollPane;
		this.addressBook = new AddressBook();
		
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
		
		Pane innerPane = new Pane();
		innerPane.setPrefHeight(HEIGHT / 2);
		innerPane.setMinHeight(HEIGHT / 2);
		innerPane.setMaxHeight(HEIGHT / 2);
		
		listView = new ListView<>();
		listView.getItems().add(new Contact("test1@testerino.de", "Max Musterfrau"));
		listView.setPrefHeight(innerPane.getPrefHeight());
		innerPane.getChildren().add(listView);
		
		gridPane.add(innerPane, 0, 0);
		
		HBox cBtnBox = new HBox(3);
		cBtnBox.setAlignment(Pos.CENTER);
		Button addContactBtn = new Button("Add Contact");
		addContactBtn.setOnAction(e -> {
			if(!isPUCreateContactShown) {
				new PUCreateContact(this);
				isPUCreateContactShown = true;
			}
		});
		cBtnBox.getChildren().add(addContactBtn);
		
		Button delContactBtn = new Button("Remove Contact");
		delContactBtn.setTooltip(new Tooltip("Delete selected Contact from list."));
		delContactBtn.setOnAction(e -> {
			Contact selection = listView.getSelectionModel().getSelectedItem();
			listView.getItems().remove(selection);
		});
		cBtnBox.getChildren().add(delContactBtn);
		gridPane.add(cBtnBox, 0, 1);
		
		vBox.getChildren().add(gridPane);
		
		
	}
	
	public AddressBook getAddressBook() {
		return this.addressBook;
	}
	
	public void addContactToList(Contact contact) {
		this.listView.getItems().add(contact);
	}
	
	public void setPUCreateContact(boolean value) {
		this.isPUCreateContactShown = value;
	}
	
	public boolean getPUCreateContact() {
		return this.isPUCreateContactShown;
	}
}
