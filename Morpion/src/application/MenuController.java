package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class MenuController extends Application implements Initializable{
	
	private AnchorPane rootLayout;
	
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

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// Create menuScene
		try {			
			FXMLLoader loader = new FXMLLoader();
	       	loader.setLocation(Main.class.getResource("Menu.fxml"));
		  	rootLayout =  (AnchorPane) loader.load();
	     
		  	String content_Url = "<iframe src=\"https://www.youtube.com/embed/9I9cvPZvdwA?\" width=\"438\" height=\"332\" frameborder=\"0\" ></iframe>";
		         
		  	webV = new WebView();
	       	//webV.setBlendMode(BlendMode.DARKEN);
		  	WebEngine webEngine = webV.getEngine();
		  	webEngine.loadContent(content_Url);
		           
		           
			Scene menuScene = new Scene(rootLayout,980,720);
			
			webPane = (VBox) menuScene.lookup("#webPane");
		    webPane.getChildren().add(webV);
		    //scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
		    //primaryStage.initStyle(StageStyle.TRANSPARENT);
		    menuScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		    primaryStage.setScene(menuScene);
		    primaryStage.show();
		    
		} catch(Exception e) {
			e.printStackTrace();
		}
				
		
	}
	
	public void clickSettings(ActionEvent event) {
		System.out.println("Settings");
	}
	
	public void clickStats(ActionEvent event) {
		System.out.println("Stats");
	}
		
	public void clickPlay(ActionEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("Game.fxml"));
			rootLayout = (AnchorPane) loader.load();
			Scene gameScene = new Scene(rootLayout,980,720);
			Controller.board = (GridPane) gameScene.lookup("#board");
			for (Node n : Controller.board.getChildren()) {
				Controller.buttons.add((JFXButton) n);
			}
			System.out.println(Controller.buttons.size());
			stage.setScene(gameScene);
			} catch (IOException e) {
				e.printStackTrace();
			}
		
	}
	
}
