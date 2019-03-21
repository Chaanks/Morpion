package application;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{
	
	public static void main(String[] args) {
		MenuController.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		new MenuController().start(primaryStage);
		
	}
}
