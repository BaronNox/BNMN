package net.baronnox.app.popups.windows;

import javafx.scene.text.Text;

public class PopUpInfo extends PopUpGeneric{
	public PopUpInfo(String info, int width, int height) {
		super(width, height);
		setWindowTitle("Info");
		getStage().requestFocus();
		getStage().setAlwaysOnTop(true);
		Text txt = new Text(info);
		txt.setStyle("-fx-font: 12 arial;");
		
		getPane().setCenter(txt);
	}
}
