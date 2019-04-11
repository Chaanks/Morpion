package application;


import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXProgressBar;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTextField;

import agent.MLP;
import core.Board;
import core.Player;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
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
	
	@FXML
	private JFXButton loadAgent;
	
	@FXML
	private JFXComboBox difficulty;
	
	@FXML
	private JFXTextField name;
	
	@FXML
	private JFXComboBox lr;
	
	@FXML
	private JFXComboBox iteration;
	
	@FXML
	private JFXComboBox file;
	
	@FXML 
	private JFXSlider hlc;
	
	@FXML
	private JFXSlider hls;
	
	@FXML
	private JFXButton trainBtn;
	
	@FXML
	private JFXButton deleteBtn;
	
	@FXML
	private JFXButton loadBtn;
	
	@FXML
	private JFXButton cancelBtn;
	
	@FXML
	private JFXProgressBar progressBar;
	
	public static Boolean stopTraining = false;
	
	@FXML
	private Text error;

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
		Morpion.SwitchScene(Morpion.agentScene);
	}
		
	public void clickPlay(ActionEvent event) {
		System.out.println("Play");
		Controller.game = new Model(true, getDifficutly());
		Morpion.SwitchScene(Morpion.gameScene);
		Controller.startTransition();
	}
	
	private String getDifficutly() {
		if (difficulty.getValue() == null) return "medium";
		else return difficulty.getValue().toString();
	}

	public void clickMenu(ActionEvent event) {
		JFXButton btn = (JFXButton) event.getSource();
		
		if(btn.getId().equals("backBtn")) {
			Morpion.SwitchScene(Morpion.lastScene);
		} else if (btn.getId().equals("quitBtn")) {
			Morpion.SwitchScene(Morpion.menuScene);
		} else if (btn.getId().equals("testBtn")) {
			System.out.println("test");
			Controller.game = new Model(false, null);
			Controller.cleanBoard();
			Morpion.SwitchScene(Morpion.gameScene);
			Controller.startTransition();
	        //Platform.exit();
	        //System.exit(0);
		} else if (btn.getId().equals("loadAgent")) {
			System.out.println("AI");
			Morpion.SwitchScene(Morpion.agentManagerScene);
		} 
	}
	
	public void clickAgent(ActionEvent event) {
		JFXButton btn = (JFXButton) event.getSource();
		
		if(btn.getId().equals("trainBtn")) {
			System.out.println("train");
			train();
		} else if (btn.getId().equals("cancelBtn")) {
			System.out.println("cancel");
			MenuController.stopTraining = true;
		} else if (btn.getId().equals("deleteBtn")) {
			System.out.println("delete");
			if (file.getValue() != null) {
				String filePath = new File(file.getValue().toString()).getAbsolutePath();
				File file = new File(filePath);
				file.delete();
				Morpion.getModels();
			}
		} else if (btn.getId().equals("loadBtn")) {
			if (file.getValue() != null) {
				Controller.game = new Model(true, file.getValue().toString());
				Morpion.SwitchScene(Morpion.gameScene);
				Controller.cleanBoard();
				Controller.startTransition();
			}
			
		}
		
	}
	
	public void train() {				
		   //Task for training
		new Thread(new Task<Void>() {
		    @Override
		    protected Void call() {
		    	System.out.println("Task");
		    	
				String filename = name.getText();
				System.out.println(filename);
				double learningRate = Double.valueOf(lr.getValue().toString());
				System.out.println(learningRate);
				int trainingIteration = Integer.valueOf(iteration.getValue().toString());
				System.out.println(trainingIteration);
				int nbHidden = (int) hlc.getValue();
				System.out.println(nbHidden);
				int hiddenSize = (int) hls.getValue();		    	
				System.out.println(hiddenSize);		
				System.out.println("start");
				
				
		        int[] layers = new int[nbHidden + 2];
		        layers[0] = 9;
		        for (int i=1; i<=nbHidden +1; i++) {
		        	layers[i] = hiddenSize;
		        }
		        layers[nbHidden + 1] = 9;
		        
		        for (int i=0; i<layers.length; i++) {
		        	System.out.println(layers[i]);
		        }
		        
		        Player p2 = new Player(false);
		        MLP agent = new MLP(learningRate, layers);
		        
		        
				for (int i=0; i<trainingIteration; i++) {
					if (MenuController.stopTraining) {
						cleanTrainScene();
						return null;
					}
					
					Board board = new Board();
					
					if (i%2 == 0) {
						while(1 == 1) {
							p2.play(board);
							if (board.isEnd()) break;
							agent.play(board);
							if (board.isEnd()) break;
						}
						
					} else {
						while(1 == 1) {
							agent.play(board);
							if (board.isEnd()) break;
							p2.play(board);
							if (board.isEnd()) break;
						}
						
					}
			        //board.display();
			        //board.display();
			        //System.out.println(agent.error);
			        //if ( i % 100 == 0 ) System.out.println("Error at step "+i+" is "+ (agent.error/agent.errorCpt));
					if ( i % 100 == 0 ) error.setText("Error at step "+i+" is "+ (agent.error/agent.errorCpt));
					progressBar.setProgress((double) i / trainingIteration);
				}
				
				System.out.println("end training");
				agent.net.save("model_" + filename);
				cleanTrainScene();
				error.setText("Training is end, error " + (agent.error/agent.errorCpt));
				Morpion.getModels();
		        return null;
		    }

			private void cleanTrainScene() {
				MenuController.stopTraining = false;
				name.setText("");
				hlc.setValue(0.0);
				hls.setValue(0.0);
				progressBar.setProgress(0.0);
				//iteration.getSelectionModel().selectFirst();
				//lr.getSelectionModel().selectFirst();
				error.setText("");
				
			}
		}).start();
		
	}

	
}
