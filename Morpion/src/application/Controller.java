package application;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.ObservableList;
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
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Controller implements Initializable {

	private int SIZE = 3;	
	private AnchorPane rootLayout;
	public static Model game;
	public static ArrayList<JFXButton> buttons;
	
	
	@FXML
	public static GridPane board;

	@FXML
	private JFXButton rematchBtn;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {	
		buttons =  new ArrayList<JFXButton>();
		
	}

	public void clickSettings(ActionEvent event) {
		System.out.println("Settings");
		Morpion.lastScene = Morpion.stage.getScene();
		Morpion.SwitchScene(Morpion.settingsScene);
	}
	
	public void clickStats(ActionEvent event) {
		System.out.println("Stats");
		Morpion.lastScene = Morpion.stage.getScene();
		Morpion.SwitchScene(Morpion.agentScene);
	}
	

	@FXML
	public void clickBoard(ActionEvent event) {
		//System.out.println(buttons.size());

		Node node = (Node) event.getSource();
		int row = toIndex(GridPane.getRowIndex(node));
		int column = toIndex(GridPane.getColumnIndex(node));
		//System.out.println(row + " " + column);
		game.computeMove(row * SIZE + column);
		if (game.isEnd()) {
			System.out.println("end");
			if (!game.isDraw()) winTransition();
		}
		
		Scale test = new Scale(1, 1);
		node.getTransforms().add(test);

		Timeline timeline = new Timeline();
        timeline.getKeyFrames().addAll(            
            new KeyFrame(Duration.ZERO, new KeyValue(test.xProperty(), 0.0)),
            new KeyFrame(Duration.ZERO, new KeyValue(test.yProperty(), 0.0)),
            new KeyFrame(new Duration(300), new KeyValue(test.xProperty(), 1)),
            new KeyFrame(new Duration(300), new KeyValue(test.yProperty(), 1))

        	
        );
        timeline.setAutoReverse(true);
        timeline.play();
	}
	
	@FXML
	public void rematch(ActionEvent event) {
		System.out.println("rematch");
		cleanBoard();
		game = new Model(game.isOnePlayer(), "");
		
		
	}
	
	public static void cleanBoard() {
		for (Node n : board.getChildren()) {
			Button current = (Button) n;
			current.setStyle("-fx-border-color: black;");
		}
		startTransition();
	}
	
	public static void startTransition() {
		
		Translate translate = new Translate();
		ObservableList<Node> nodes = board.getChildren();
			Timeline timeline = new Timeline();
			timeline.getKeyFrames().addAll(
					new KeyFrame(Duration.ZERO, new KeyValue(nodes.get(0).translateYProperty(), -1000.0)),
					new KeyFrame(Duration.ZERO, new KeyValue(nodes.get(1).translateYProperty(), -1000.0)),
					new KeyFrame(Duration.ZERO, new KeyValue(nodes.get(2).translateYProperty(), -1000.0)),
					new KeyFrame(Duration.ZERO, new KeyValue(nodes.get(3).translateYProperty(), -1000.0)),
					new KeyFrame(Duration.ZERO, new KeyValue(nodes.get(4).translateYProperty(), -1000.0)),
					new KeyFrame(Duration.ZERO, new KeyValue(nodes.get(5).translateYProperty(), -1000.0)),
					new KeyFrame(Duration.ZERO, new KeyValue(nodes.get(6).translateYProperty(), -1000.0)),
					new KeyFrame(Duration.ZERO, new KeyValue(nodes.get(7).translateYProperty(), -1000.0)),
					new KeyFrame(Duration.ZERO, new KeyValue(nodes.get(8).translateYProperty(), -1000.0)),
					
					new KeyFrame(new Duration(200), new KeyValue(nodes.get(8).translateYProperty(), 0.0)),
					new KeyFrame(new Duration(400), new KeyValue(nodes.get(7).translateYProperty(), 0.0)),
					new KeyFrame(new Duration(600), new KeyValue(nodes.get(6).translateYProperty(), 0.0)),
					new KeyFrame(new Duration(800), new KeyValue(nodes.get(5).translateYProperty(), 0.0)),
					new KeyFrame(new Duration(1000), new KeyValue(nodes.get(4).translateYProperty(), 0.0)),
					new KeyFrame(new Duration(1200), new KeyValue(nodes.get(3).translateYProperty(), 0.0)),
					new KeyFrame(new Duration(1400), new KeyValue(nodes.get(2).translateYProperty(), 0.0)),
					new KeyFrame(new Duration(1600), new KeyValue(nodes.get(1).translateYProperty(), 0.0)),
					new KeyFrame(new Duration(1800), new KeyValue(nodes.get(0).translateYProperty(), 0.0))
					
					
					);
			timeline.setAutoReverse(true);
			timeline.play();
		
	}
	
	public void winTransition() {
		int[] pos = game.getWinPos();
		System.out.println(pos[0]);
		System.out.println(pos[1]);
		System.out.println(pos[2]);
		
		Rotate rotation = new Rotate(0, 0, 0);
		board.getChildren().get(pos[0]).getTransforms().add(rotation);
		board.getChildren().get(pos[1]).getTransforms().add(rotation);
		board.getChildren().get(pos[2]).getTransforms().add(rotation);
		
		Scale scale = new Scale(1, 1);
		board.getChildren().get(pos[0]).getTransforms().add(scale);
		board.getChildren().get(pos[1]).getTransforms().add(scale);
		board.getChildren().get(pos[2]).getTransforms().add(scale);
		
		Timeline timeline = new Timeline();
		timeline.getKeyFrames().addAll(
				new KeyFrame(Duration.ZERO, new KeyValue(scale.xProperty(), 1.8)),
	            new KeyFrame(Duration.ZERO, new KeyValue(scale.yProperty(), 1.8)),
	            new KeyFrame(Duration.ZERO, new KeyValue(rotation.angleProperty(), -15)),
	            new KeyFrame(new Duration(500), new KeyValue(rotation.angleProperty(), 0)),
	            new KeyFrame(new Duration(300), new KeyValue(scale.xProperty(), 1)),
	            new KeyFrame(new Duration(300), new KeyValue(scale.yProperty(), 1))
				);
		timeline.setAutoReverse(true);
		timeline.setCycleCount(5);
		timeline.play();
		
		
	}
	
	
	private static int toIndex(Integer value) {
	    return value == null ? 0 : value;
	}
	
	
}
