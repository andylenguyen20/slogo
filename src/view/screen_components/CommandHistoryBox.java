package view.screen_components;

import controller.ClearValueDelegate;
import controller.ParserActionDelegate;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.CommandHistory;
import model.CommandHistoryObservable;
import propertiesFiles.ResourceBundleManager;
import view.Observer;

import java.util.List;

/**
 * Class which sets up display of previously input commands
 * @author Andy Nguyen
 * @author Edward Zhuang
 */
public class CommandHistoryBox extends ScreenComponent implements Observer {
	private static final int WIDTH = 200;
	private static final int HEIGHT = 300;
	private CommandHistoryObservable commandHistory;
	private Button clearButton;
	private VBox commandList;
	private ParserActionDelegate parserActionDelegate;
	private ClearValueDelegate clearValueDelegate;

	/**
	 * @see ScreenComponent
	 */
	public CommandHistoryBox(ParserActionDelegate p, ClearValueDelegate c){
		super();
		this.parserActionDelegate = p;
		this.clearValueDelegate = c;
	}

	/**
	 * Set's up this class' commandHistory
	 * @param commandHistory connects to back end command history
	 */
	public void setCommandHistory(CommandHistory commandHistory){
		this.commandHistory = commandHistory;
	}

	/**
	 * Maps actions of this class' buttons
	 * clearButton clears history
	 */
	@Override
	protected void mapUserActions() {
		clearButton.setOnAction((event -> {
			clearValueDelegate.clear();
		}));
	}

	/**
	 * Creates BorderPane and adds front end items to it
	 * @see ScreenComponent
	 */
	public void generateGUIComponent(){
		BorderPane borderPane = super.getBorderPane();
		this.addButtonAndLabels(borderPane);
	}

	/**
	 * Adds buttons and labels to a passed in BorderPane
	 * @param borderPane BorderPane on which buttons and labels are added
	 */
	private void addButtonAndLabels(BorderPane borderPane){
		HBox topComponent = new HBox();
		clearButton = new Button(ResourceBundleManager.retrieveButtonLabel("CLEAR"));
		Label label = new Label(ResourceBundleManager.retrieveLabel("HISTORY_LABEL_TEXT"));
		topComponent.getChildren().add(label);
		topComponent.getChildren().add(clearButton);
		borderPane.setTop(topComponent);
		commandList = new VBox();
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setMaxWidth(WIDTH);
		scrollPane.setPrefHeight(HEIGHT);
		scrollPane.setContent(commandList);
		borderPane.setCenter(scrollPane);
	}

	/**
	 * updates command history, populates with previous commands as buttons which allow for reuse
	 */
	@Override
	public void notifyOfChanges() {
		List<String> commands = commandHistory.getCommands();
		commandList.getChildren().clear();
		for(String command: commands){
			Button commandButton = new Button(command);
			commandButton.getStyleClass().add("runnableCommandButton");
			commandButton.setOnAction((event -> {
				parserActionDelegate.performParserAction(parser -> parser.passTextCommand(commandButton.getText()));
			}));
			commandList.getChildren().add(commandButton);
		}
	}
}
