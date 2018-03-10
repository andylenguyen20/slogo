package controller;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * @author Andy Nguyen, Eddie Zhuang
 * The purpose of this class is to start up the SLOGO application by creating a workspace
 */
public class Main extends Application{

	/**
	 * starts the application by creating a new workspace and starting it up. Right now this class only creates one workspace, but
	 * theoretically, it could create multiple workspaces if it wanted to, which is flexible.
	 * @param primaryStage
	 * @throws Exception
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		Workspace workspace = new Workspace(primaryStage);
		workspace.startUp();
	}

	public static void main(String[] args){
		Application.launch(args);
	}
}
