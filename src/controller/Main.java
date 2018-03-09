package controller;

import Experiment.Workspace;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		//Controller controller = new Controller(primaryStage);
		Workspace workspace = new Workspace(primaryStage);
		workspace.startUp();
	}

	public static void main(String[] args){
		Application.launch(args);
	}
}
