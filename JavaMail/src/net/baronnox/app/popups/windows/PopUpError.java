package net.baronnox.app.popups.windows;

import javafx.scene.text.Text;

public class PopUpError extends PopUpGeneric {
	public PopUpError(String error, int width, int height) {
		super(width, height);
		setWindowTitle("Error");
		Text txt = new Text(error);
		txt.setStyle("-fx-font: 20 arial;");
		
		getPane().setCenter(txt);
	}
}
