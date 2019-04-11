package application;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXSlider;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Translate;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;



public class Morpion extends Application{

	public static Stage stage;
	public static Scene menuScene;
	public static Scene settingsScene;
	public static Scene gameScene;
	public static Scene agentScene;
	public static Scene agentManagerScene;
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
	       	
	       	// Create the agent Scene
	       	loader = new FXMLLoader();
	       	loader.setLocation(getClass().getResource("Agent.fxml"));
	       	rootLayout =  (AnchorPane) loader.load();
	       	agentScene = new Scene(rootLayout,980,720);
	       	agentScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	       	
	       	// Create the agentManager Scene
	       	loader = new FXMLLoader();
	       	loader.setLocation(getClass().getResource("AgentManager.fxml"));
	       	rootLayout =  (AnchorPane) loader.load();
	       	agentManagerScene = new Scene(rootLayout,980,720);
	       	agentManagerScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	       	
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
	       	
		    JFXComboBox difficulty = (JFXComboBox) menuScene.lookup("#difficulty");
			difficulty.getItems().addAll(
					"easy",
			        "medium",
			        "hard"
				);
		    
			JFXSlider hlc = (JFXSlider) agentScene.lookup("#hlc");
			hlc.setValue(0.0);
			JFXSlider hls = (JFXSlider) agentScene.lookup("#hls");
			hls.setValue(0.0);
			
		    JFXComboBox iteration = (JFXComboBox) agentScene.lookup("#iteration");
			iteration.getItems().addAll(
					"10",
					"100",
			        "1000",
			        "10000",
			        "100000"
				);
			
		    JFXComboBox lr = (JFXComboBox) agentScene.lookup("#lr");
			lr.getItems().addAll(
					"0.001",
			        "0.01",
			        "0.1",
			        "1"
				);
			
			Morpion.getModels();
			
	       	primaryStage.setScene(menuScene);
	       	primaryStage.show();
	       	

		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	public static void SwitchScene(Scene newScene) {
		stage.setScene(newScene);
	}

	public static void getModels() {
	    JFXComboBox file = (JFXComboBox) agentManagerScene.lookup("#file");
	    file.getItems().clear();
		file.getItems().addAll(getFilesNames());
	}
	
	public static ArrayList<String> getFilesNames(){
		ArrayList<String> filesNames = new ArrayList<String>();
	  	String filePath = new File("").getAbsolutePath();
		//filePath += "/src/ai/";
		File folder = new File(filePath);
		File[] listOfFiles = folder.listFiles();

		for (File file : listOfFiles) {
			if (file.getName().contains("model_")) {
				filesNames.add(file.getName());
				System.out.println(file.getName());				
			}
		}
		
		return filesNames;
	}
}
