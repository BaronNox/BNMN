package net.baronnox.app.scenes;

import java.io.File;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import net.baronnox.dataobjects.addressbook.Account;

public class LoginScene extends Scene {
	private Stage primaryStage;
	private VBox root;
	private HBox hBox;
	private GridPane grid;
	private File file;
	private Image img;
	private ImageView imgView;
	private Label usrName;
	private Label usrPw;
	private TextField usrTxtField;
	private PasswordField usrPwField;
	private Button loginBtn;
	
	public static LoginScene getScene(Stage primaryStage) {
		return new LoginScene(new VBox(10), primaryStage);
	}
	
	private LoginScene(VBox parent, Stage primaryStage) {
		super(parent, 350, 250);
		this.primaryStage = primaryStage;
		root = parent;
		hBox = new HBox(10);
		grid = new GridPane();
		
		file = new File("resources/net/baronnox/app/BNMN.png");
		img = new Image(file.toURI().toString());
		imgView = new ImageView(img);
		
		usrName = new Label("Gmail address:");
		usrPw = new Label("Gmail password:");
		usrTxtField = new TextField();
		usrPwField = new PasswordField();
		loginBtn = new Button("Login!");
		
		init();
	}
	
	private void init() {
		root.setAlignment(Pos.TOP_CENTER);
		root.getChildren().add(imgView);
		
		grid.setAlignment(Pos.BOTTOM_CENTER);
		grid.setPadding(new Insets(25));
		grid.setHgap(10);
		grid.setVgap(10);
		root.getChildren().add(grid);
		
		usrName.setTooltip(new Tooltip("Use the Gmail-Account responsible for sending automates messages."));
		grid.add(usrName, 0, 0);
		
		usrTxtField.setPrefWidth(175);
		usrTxtField.setPromptText("e.g. test@example.eu");
		grid.add(usrTxtField, 1, 0);
		
		usrPw.setTooltip(new Tooltip("Use Gmail Password of the account entered above."));
		grid.add(usrPw, 0, 1);
		
		usrPwField.setPrefWidth(175);
		usrPwField.setPromptText("*******");
		grid.add(usrPwField, 1, 1);
		
		loginBtn.setOnAction(e -> {
			Account usrAcc = new Account(usrTxtField.getText(), usrPwField.getText());
			primaryStage.setScene(HomeScene.getScene(primaryStage, usrAcc));
		});
		
//		{
//			final String name = usrName.getText();
//			@Override
//			public void handle(ActionEvent event) {
//				System.out.println(name + " : " + usrPw.getText());
//				Account usrAcc = new Account(usrName.getText(), usrPw.getText());
//				primaryStage.setScene(HomeScene.getScene(primaryStage, usrAcc));
//			}
//		});
		
		hBox.setAlignment(Pos.BOTTOM_RIGHT);
		hBox.getChildren().add(loginBtn);
		grid.add(hBox, 1, 2);
		grid.requestFocus();
	}
}
