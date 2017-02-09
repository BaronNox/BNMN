package net.baronnox.app.popups.windows;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import net.baronnox.app.scenes.HomeScene;
import net.baronnox.dataobjects.addressbook.Contact;

public class PUCreateContact {
	private HomeScene homeScene;
	private Stage puStage;
	private Pane root;
	
	public PUCreateContact(HomeScene homeScene) {
		this.homeScene = homeScene;
		this.puStage = new Stage();
		puStage.centerOnScreen();
		puStage.requestFocus();
		puStage.setResizable(false);
		puStage.setTitle("Create New Contact - BNMN");
		this.root = new Pane();
		Scene scene = new Scene(root, 400, 75);
		
		initUI();
		
		puStage.setScene(scene);
		puStage.show();
		
		puStage.setOnCloseRequest(e -> {
			homeScene.setPUCreateContact(false);
		});
	}

	private void initUI() {
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setVgap(5);
		grid.setHgap(10);
		grid.setPadding(new Insets(10));
		root.getChildren().add(grid);
		Label enterAddress;
		Label enterPersonal;
		TextField address;
		TextField personal;
		Button create;
		Button cancel;
		
		enterAddress = new Label("Enter eMail address:");
		enterPersonal = new Label("Enter name:");
		address = new TextField();
		address.setPromptText("e.g. john.doe@test.test");
		personal = new TextField();
		personal.setPromptText("e.g. John Doe");
		cancel = new Button("Cancel");
		create = new Button("Create");
		create.setOnAction(e -> {
			if(address.getText() != null && address.getText().contains("@") && address.getText().contains(".")) {
				Contact newContact = null;
				if(personal.getText().isEmpty()) {
					newContact = new Contact(address.getText());
				} else {
					newContact = new Contact(address.getText(), personal.getText());
				}
				homeScene.getAddressBook().addContactToList(newContact);
				homeScene.updateContactListView();
			} else {
				new PopUpError("This ain't an eMail.", 250, 100);
			}
			cancel.fire();
		});
		
		cancel.setOnAction(e -> {
			homeScene.setPUCreateContact(false);
			this.puStage.close();
		});
		
		grid.add(enterAddress, 0, 0);
		grid.add(address, 1, 0);
		grid.add(enterPersonal, 0, 1);
		grid.add(personal, 1, 1);
		grid.add(cancel, 2, 0);
		grid.add(create, 2, 1);
	}
}
