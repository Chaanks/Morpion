package application;


import java.net.URL;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class MenuController implements Initializable{
	
	@FXML
	private WebView webV;
	
	@FXML
	private VBox webPane;
	
	@FXML
	private JFXButton settingsBtn;
	
	@FXML
	private JFXButton statsBtn;
	
	@FXML
	private JFXButton playBtn;
	
	@FXML
	private JFXButton backBtn;
	
	@FXML
	private JFXButton quitBtn;
	
	@FXML
	private JFXButton testBtn;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		
	}
	
	public void clickSettings(ActionEvent event) {
		System.out.println("Settings");
		Morpion.lastScene = Morpion.stage.getScene();
		Morpion.SwitchScene(Morpion.settingsScene);
	}
	
	public void clickStats(ActionEvent event) {
		System.out.println("Stats");
		Morpion.lastScene = Morpion.stage.getScene();
		Morpion.SwitchScene(Morpion.menuScene);
	}
		
	public void clickPlay(ActionEvent event) {
		System.out.println("Play");
		Morpion.SwitchScene(Morpion.gameScene);
	}
	
	public void clickMenu(ActionEvent event) {
		JFXButton btn = (JFXButton) event.getSource();
		
		if(btn.getId().equals("backBtn")) {
			Morpion.SwitchScene(Morpion.lastScene);
		} else if (btn.getId().equals("quitBtn")) {
			Morpion.SwitchScene(Morpion.menuScene);
		} else if (btn.getId().equals("testBtn")) {
			System.out.println("test");
	        Platform.exit();
	        System.exit(0);
		} 
	}

	
}
