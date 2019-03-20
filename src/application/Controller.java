package application;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class Controller  extends Application implements Initializable{

	private static Stage STAGE = null;
	private int SIZE = 3;	
	private AnchorPane rootLayout;
	private Model game =  new Model();
	
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
	private static GridPane board;

	@FXML
	private JFXButton rematchBtn;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		try {			
			// Load root layout from fxml file.
           FXMLLoader loader = new FXMLLoader();
           loader.setLocation(Main.class.getResource("Menu.fxml"));
           loader.setController(new Controller());
           rootLayout =  (AnchorPane) loader.load();
           
           String content_Url = "<iframe src=\"https://www.youtube.com/embed/9I9cvPZvdwA?\" width=\"438\" height=\"332\" frameborder=\"0\" ></iframe>";
           
           webV = new WebView();
           //webV.setBlendMode(BlendMode.DARKEN);
           WebEngine webEngine = webV.getEngine();
           webEngine.loadContent(content_Url);
           
           
			Scene scene = new Scene(rootLayout,980,720);
			
           webPane = (VBox) scene.lookup("#webPane");
           webPane.getChildren().add(webV);
           Controller.setSTAGE(primaryStage);
           //scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
           //primaryStage.initStyle(StageStyle.TRANSPARENT);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
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
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Controller.class.getResource("Game.fxml"));
			loader.setController(new Controller());
			AnchorPane rootLayout =  (AnchorPane) loader.load();
			
			Scene scene = new Scene(rootLayout,980,720);
			board = (GridPane) scene.lookup("#board");
			STAGE.setScene(scene);
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@FXML
	public void clickBoard(ActionEvent event) {
		Node node = (Node) event.getSource();
		int row = toIndex(GridPane.getRowIndex(node));
		int column = toIndex(GridPane.getColumnIndex(node));
		
		System.out.println(row + " " + column);
		if (game.isEnd()) {
			System.out.println("end");
		} else if (game.computeMove(row * SIZE + column)) {
			Button current = (Button) node;
			current.setStyle(game.getPlayerStyle());
			
			if (game.isAiGame()) {
				int moveID = game.computeMove();
				// afficher le coup de l'ia
			}
		}
	}
	
	@FXML
	public void rematch(ActionEvent event) {
		System.out.println("rematch");
		game = new Model();
		cleanBoard();
	}
	
	public void cleanBoard() {
		for (Node n : board.getChildren()) {
			Button current = (Button) n;
			current.setStyle("-fx-border-color: black;");
		}
	}
	
	
	private static int toIndex(Integer value) {
	    return value == null ? 0 : value;
	}
	
	public static Stage getSTAGE() {
		return STAGE;
	}
	
	public static void setSTAGE(Stage sTAGE) {
		STAGE = sTAGE;
	}
	
}
