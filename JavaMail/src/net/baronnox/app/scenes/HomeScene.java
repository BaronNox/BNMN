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
	
	private boolean isPUCreateContactShown;
	
	private Stage primaryStage;
	private Scene scene;
	private Account acc;
	private ScrollPane scrollPane;
	private ListView<Contact> listView;
	
	private Contact oldSelection = null;
	
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
		primaryStage.setResizable(true);
		
		VBox vBox = new VBox(0);
		scrollPane.setContent(vBox);
		
		MenuBar menuBar = new MenuBar();
		menuBar.setPrefWidth(WIDTH - 2);
		final Menu file = new Menu("File");
		final Menu help = new Menu("Help");
		MenuItem createContact = new Menu("Create New Contact");
//		file.getItems().setAll(createContact);
		menuBar.getMenus().addAll(file, createContact, help);
		
		vBox.getChildren().add(menuBar);
		
		GridPane gridPane = new GridPane();
		gridPane.setAlignment(Pos.TOP_LEFT);
		gridPane.setPrefWidth(WIDTH - 2);
		gridPane.setGridLinesVisible(true);
		
//		Pane innerPane = new Pane();
//		innerPane.setPrefHeight(HEIGHT / 2);
//		innerPane.setMinHeight(HEIGHT / 2);
//		innerPane.setMaxHeight(HEIGHT / 2);
		
		listView = new ListView<>();
		listView.setItems(FXCollections.observableArrayList(acc.getAddressBook().getContactList()));
//		listView.setPrefHeight(innerPane.getPrefHeight());
		listView.setPrefHeight(150);
//		innerPane.getChildren().add(listView);
		
//		gridPane.add(innerPane, 0, 0);
		gridPane.add(listView, 0, 0);
		
//		Pane contentPane = new Pane();
//		contentPane.setPrefHeight(HEIGHT / 2);
//		contentPane.setMinHeight(HEIGHT / 2);
//		contentPane.setMaxHeight(HEIGHT / 2);
		
		ListView<String> notifList = new ListView<>();
//		notifList.setPrefHeight(contentPane.getPrefHeight());
		notifList.setPrefHeight(150);
		listView.setOnMouseClicked(e -> {
			Contact selected = listView.getSelectionModel().getSelectedItem();
			if(selected != null && !selected.equals(oldSelection)) {
				notifList.getItems().clear();
				for(Notification n : acc.getAddressBook().getNotificationByContact(selected)) {
					notifList.getItems().add("Subject: " + n.getSubject());
					notifList.getItems().add("Recipients: " + n.getRecipients());
					notifList.getItems().add("Due: " + n.getDue());
					notifList.getItems().add("Message: \n" + n.getMsg());
				}
			}
			oldSelection = selected;
		});
		
//		contentPane.getChildren().add(notifList);
//		gridPane.add(contentPane, 2, 0);
		gridPane.add(notifList, 2, 0);
		
		HBox cBtnBox = new HBox(3);
		cBtnBox.setAlignment(Pos.CENTER);
		
		VBox buttonBox = new VBox();
		Button addContact = new Button("Add Contact");
		addContact.setPrefWidth(125);
		addContact.setTooltip(new Tooltip("Adds selected Contact to Notification"));
		//TODO: Click logic
		//END
		
		buttonBox.getChildren().add(addContact);
		gridPane.add(buttonBox, 1, 0);
		
		
		
		Button createContactBtn = new Button("Create new Contact");
		createContactBtn.setOnAction(e -> {
			if(!isPUCreateContactShown) {
				new PUCreateContact(this);
				isPUCreateContactShown = true;
			}
		});
		cBtnBox.getChildren().add(createContactBtn);
		
		Button delContactBtn = new Button("Remove Contact");
		delContactBtn.setTooltip(new Tooltip("Delete selected Contact from list."));
		delContactBtn.setOnAction(e -> {
			Contact selection = listView.getSelectionModel().getSelectedItem();
			listView.getItems().remove(selection);
		});
		cBtnBox.getChildren().add(delContactBtn);
		gridPane.add(cBtnBox, 0, 1);
		
		Button saveBtn = new Button("Save");
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
		gridPane.add(saveBtn, 0, 2);
		
		vBox.getChildren().add(gridPane);
		
		
	}
	
	public Scene getScene() {
		return this.scene;
	}
	
	public AddressBook getAddressBook() {
		return this.acc.getAddressBook();
	}
	
	public void setPUCreateContact(boolean value) {
		this.isPUCreateContactShown = value;
	}
	
	public boolean getPUCreateContact() {
		return this.isPUCreateContactShown;
	}
	
	public void updateContactListView() {
		listView.setItems(FXCollections.observableArrayList(acc.getAddressBook().getContactList()));
	}
}
