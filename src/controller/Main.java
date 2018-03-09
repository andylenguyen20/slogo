package controller;

import javafx.application.Application;
import javafx.stage.Stage;
import model.ModelInterface;

public class Main extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		Workspace workspace = new Workspace(primaryStage);
		workspace.startUp();
	}

	public static void main(String[] args){
		Application.launch(args);
	}
}
