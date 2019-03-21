package application;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
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

public class Controller implements Initializable {

	private int SIZE = 3;	
	private AnchorPane rootLayout;
	private Model game;
	public static ArrayList<JFXButton> buttons;
	
	
	@FXML
	public static GridPane board;

	@FXML
	private JFXButton rematchBtn;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		game = new Model(true);
		buttons =  new ArrayList<JFXButton>();
		
	}

	public void clickSettings(ActionEvent event) {
		System.out.println("Settings");
	}
	
	public void clickStats(ActionEvent event) {
		System.out.println("Stats");
	}
	

	@FXML
	public void clickBoard(ActionEvent event) {
		System.out.println(buttons.size());

		Node node = (Node) event.getSource();
		int row = toIndex(GridPane.getRowIndex(node));
		int column = toIndex(GridPane.getColumnIndex(node));
		System.out.println(row + " " + column);
		if (game.isEnd()) {
			System.out.println("end");
		} else if (game.computeMove(row * SIZE + column)) {
			JFXButton current = (JFXButton) node;
			current.setStyle(game.getPlayerStyle(false));
		} 
		
		if (game.isEnd()) cleanBoard();
	}
	
	@FXML
	public void rematch(ActionEvent event) {
		System.out.println("rematch");
		game = new Model(true);
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
	
	
}
