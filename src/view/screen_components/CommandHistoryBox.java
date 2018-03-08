package view.screen_components;

import controller.CommandBoxController;
import controller.CommandHistoryBoxController;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.CommandHistory;
import model.CommandHistoryObservable;
import view.Observer;
import view.constants.ButtonConstants;
import view.constants.LabelConstants;

import java.util.List;

public class CommandHistoryBox extends ScreenComponent implements Observer {
	private CommandHistoryObservable commandHistory;
	private Button clearButton;
	private VBox commandList;
	public CommandHistoryBox(){
		super();
	}

	private CommandHistoryBoxController controller;

	public void setController(CommandHistoryBoxController controller){
		this.controller = controller;
	}

	public void setCommandHistory(CommandHistory commandHistory){
		this.commandHistory = commandHistory;
	}

	@Override
	protected void mapUserActions() {
		clearButton.setOnAction((event -> {
			controller.clearCommandHistoryBox();
		}));
	}

	public void generateGUIComponent(){
		BorderPane borderPane = super.getBorderPane();
		this.addButtonAndLabels(borderPane);
	}

	private void addButtonAndLabels(BorderPane borderPane){
		HBox topComponent = new HBox();
		clearButton = new Button(ButtonConstants.HISTORY_BUTTON_LABEL);
		Label label = new Label(LabelConstants.HISTORY_LABEL_TEXT);
		topComponent.getChildren().add(label);
		topComponent.getChildren().add(clearButton);
		borderPane.setTop(topComponent);
		commandList = new VBox();
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setMaxWidth(200);
		scrollPane.setPrefHeight(300);
		scrollPane.setContent(commandList);
		borderPane.setCenter(scrollPane);
	}

	@Override
	public void notifyOfChanges() {
		List<String> commands = commandHistory.getCommands();
		commandList.getChildren().clear();
		for(String command: commands){
			Button commandButton = new Button(command);
			commandButton.getStyleClass().add("commandHistoryBoxButton");
			commandButton.setOnAction((event -> {
				controller.passCommand(commandButton.getText());
			}));
			commandList.getChildren().add(commandButton);
		}
	}
}
