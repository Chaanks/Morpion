package application;

import java.lang.reflect.Field;

import com.jfoenix.controls.JFXButton;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;



public class Morpion extends Application{

	public static Stage stage;
	public static Scene menuScene;
	public static Scene settingsScene;
	public static Scene gameScene;
	public static Scene lastScene;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			// Create the menu Scene
			FXMLLoader loader = new FXMLLoader();
	       	loader.setLocation(getClass().getResource("Menu.fxml"));
	       	AnchorPane rootLayout =  (AnchorPane) loader.load();
	       	menuScene = new Scene(rootLayout,980,720);
	       	menuScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	       	
	       	// Create the settings Scene
	       	loader = new FXMLLoader();
	       	loader.setLocation(getClass().getResource("Settings.fxml"));
	       	rootLayout =  (AnchorPane) loader.load();
	       	settingsScene = new Scene(rootLayout,980,720);
	       	settingsScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	       	
	       	// Create the game Scene
			loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("Game.fxml"));
			rootLayout = (AnchorPane) loader.load();
			gameScene = new Scene(rootLayout,980,720);
			settingsScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			Controller.board = (GridPane) gameScene.lookup("#board");
			for (Node n : Controller.board.getChildren()) {
				Controller.buttons.add((JFXButton) n);
			}
			
	
	       	stage = primaryStage;
	       	
		  	String content_Url = "<iframe src=\"https://www.youtube.com/embed/9I9cvPZvdwA?\" width=\"438\" height=\"332\" frameborder=\"0\" ></iframe>";      
		  	WebView webV = new WebView();
		  	WebEngine webEngine = webV.getEngine();
		  	webEngine.loadContent(content_Url);		      			
		  	VBox webPane = (VBox) menuScene.lookup("#webPane");
		    webPane.getChildren().add(webV);
	       	
	       	primaryStage.setScene(menuScene);
	       	primaryStage.show();
	       	

		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	public static void SwitchScene(Scene newScene) {
		stage.setScene(newScene);
	}

}
