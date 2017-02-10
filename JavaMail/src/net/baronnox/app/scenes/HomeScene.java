package net.baronnox.app.scenes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import net.baronnox.app.popups.windows.PUCreateContact;
import net.baronnox.dataobjects.Account;
import net.baronnox.dataobjects.Notification;
import net.baronnox.dataobjects.addressbook.AddressBook;
import net.baronnox.dataobjects.addressbook.Contact;

public class HomeScene {
	private static final int WIDTH = 600;
	private static final int HEIGHT = 320;
	private static final String DATA_DIR = "./data/";
	
	private Stage primaryStage;
	private Scene scene;
	private Account acc;
	private ScrollPane scrollPane;
	
	private Contact oldSelection = null;
	
	//UI Controls
	private Button createContactBtn;
//	private MenuBar menuBar;
	private VBox vBox;
	private VBox vBoxButtons;
	private HBox hBoxButtons;
	private GridPane gridPane;
	private ListView<Contact> contactListView;
//	private ListView<String> notifListView;
	//END UI Controls
	
	public HomeScene(Stage primaryStage, Account acc) {
		this.primaryStage = primaryStage;
		this.scrollPane = new ScrollPane();
		this.scene = new Scene(scrollPane, WIDTH, HEIGHT);
		
		if(isAccPresentLoadIfSo(acc.getUserName(), acc.getUserPwHash())) {
		} else {
			this.acc = acc;
		}
		
		initScene();
	}
	
	private boolean isAccPresentLoadIfSo(String accUsrName, int hash) {
		Path p = Paths.get(DATA_DIR + String.valueOf(accUsrName.hashCode() + hash));
		if(Files.exists(p, new LinkOption[] { LinkOption.NOFOLLOW_LINKS })) {
			acc = loadAcc(p);
			return true;
		}
		return false;
	}

	private Account loadAcc(Path path) {
		Account loadedAcc = null;
		if(Files.exists(path, new LinkOption[] { LinkOption.NOFOLLOW_LINKS })) {
			try {
				FileInputStream fis = new FileInputStream(path.toString());
				ObjectInputStream ois = new ObjectInputStream(fis);
				loadedAcc = (Account) ois.readObject();
				ois.close();
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return loadedAcc;
	}

	private void initScene() {
		vBox = new VBox(0); 		 //Main Scene Layout
		scrollPane.setContent(vBox);
		initMenuBar();
		
		primaryStage.setResizable(true);
		
		gridPane = initGridPane();	 //Rest of Window Layout
		vBox.getChildren().add(gridPane);
		
		contactListView = initContactListView();
		initNotifListView();
		
		hBoxButtons = new HBox(3);	//was cBtnBox
		hBoxButtons.setAlignment(Pos.CENTER);
		gridPane.add(hBoxButtons, 0, 1);
		
		vBoxButtons = new VBox();	//was buttonBox
		gridPane.add(vBoxButtons, 1, 0);

		initButtons();
	}
	
	private MenuBar initMenuBar() {
		MenuBar menuBar = new MenuBar();
		
		menuBar.setPrefWidth(WIDTH - 2);
		final Menu file = new Menu("File");
		final Menu help = new Menu("Help");
		MenuItem createContactMItem = new MenuItem("Create New Contact");
		createContactMItem.setOnAction(e -> {
			createContactBtn.fire();
		});
		MenuItem exitMItem = new MenuItem("Exit");
		MenuItem manualMItem = new MenuItem("Manual");
		MenuItem aboutMItem = new MenuItem("About");
		SeparatorMenuItem separatorMenuItem = new SeparatorMenuItem();
		SeparatorMenuItem separatorMenuItem2 = new SeparatorMenuItem();
		
		file.getItems().addAll(createContactMItem, separatorMenuItem, exitMItem);
		help.getItems().addAll(manualMItem, separatorMenuItem2, aboutMItem);
		menuBar.getMenus().addAll(file, help);
		vBox.getChildren().add(menuBar);
		
		return menuBar;
	}
	
	private GridPane initGridPane() {
		GridPane gridPane = new GridPane();
		gridPane.setAlignment(Pos.TOP_LEFT);
		gridPane.setPrefWidth(WIDTH - 2);
		gridPane.setGridLinesVisible(true); //DEBUG
		
		return gridPane;
	}
	
	private ListView<Contact> initContactListView() {
		ListView<Contact> contactListView = new ListView<>();
		
		contactListView.setItems(FXCollections.observableArrayList(acc.getAddressBook().getContactList()));
		contactListView.setPrefHeight(150);
		gridPane.add(contactListView, 0, 0);
		
		return contactListView;
	}
	
	private ListView<String> initNotifListView() {
		ListView<String> notifListView = new ListView<>();
		
		notifListView.setPrefHeight(150);
		contactListView.setOnMouseClicked(e -> {
			Contact selected = contactListView.getSelectionModel().getSelectedItem();
			if(selected != null && !selected.equals(oldSelection)) {
				notifListView.getItems().clear();
				for(Notification n : acc.getAddressBook().getNotificationByContact(selected)) {
					notifListView.getItems().add("Subject: " + n.getSubject());
					notifListView.getItems().add("Recipients: " + n.getRecipients());
					notifListView.getItems().add("Due: " + n.getDue());
					notifListView.getItems().add("Message: \n" + n.getMsg());
				}
			}
			oldSelection = selected;
		});
		gridPane.add(notifListView, 2, 0);
		
		return notifListView;
	}
	
	private void initButtons() {
		createContactBtn = new Button("Create new Contact");
		Button delContactBtn = new Button("Remove Contact");
		Button addContact = new Button("Add Contact");
		Button saveBtn = new Button("Save");
		
		
		addContact.setPrefWidth(125);
		addContact.setTooltip(new Tooltip("Adds selected Contact to Notification"));
		//TODO: Click logic
		//END
		gridPane.add(addContact, 1, 0);
//		hBoxButtons.getChildren().add(addContact);
		
		delContactBtn.setTooltip(new Tooltip("Delete selected Contact from list."));
		delContactBtn.setOnAction(e -> {
			Contact selection = contactListView.getSelectionModel().getSelectedItem();
			contactListView.getItems().remove(selection);
		});
		hBoxButtons.getChildren().add(delContactBtn);
		
		createContactBtn.setOnAction(e -> {
			new PUCreateContact(this);
		});
		hBoxButtons.getChildren().add(createContactBtn);
		
		saveBtn.setOnAction(e -> {
			try {
				File dataDir = new File(DATA_DIR);
				dataDir.mkdirs();
				File saveFile = new File(DATA_DIR + String.valueOf(acc.getUserName().hashCode() + acc.getUserPwHash()));
				FileOutputStream foo = new FileOutputStream(saveFile);
				ObjectOutputStream oos = new ObjectOutputStream(foo);
				oos.writeObject(acc);
				oos.flush();
				oos.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		gridPane.add(saveBtn, 0, 2); //TODO: Change location
	}

	public Scene getScene() {
		return this.scene;
	}
	
	public Stage getStage() {
		return primaryStage;
	}
	
	public AddressBook getAddressBook() {
		return this.acc.getAddressBook();
	}
	
	public void updateContactListView() {
		contactListView.setItems(FXCollections.observableArrayList(acc.getAddressBook().getContactList()));
	}
}
