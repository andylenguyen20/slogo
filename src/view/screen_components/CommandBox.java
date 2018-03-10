package view.screen_components;

import controller.ParserActionDelegate;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import propertiesFiles.ResourceBundleManager;
import view.factories.ComboBoxFactory;

/**
 * CommandBox class
 * Responsible for the text area in which user inputs commands
 * @author Andy Nguyen
 * @author Edward Zhuang
 */
public class CommandBox extends ScreenComponent{
	private static final String[] LANGUAGE_LIST = {"English", "Chinese"};
	private Button commandClearButton;
    private Button commandRunButton;
    private TextArea commandTextArea;
    private Label commandLabel;
    private ComboBox<String> languageBox;
    private ParserActionDelegate parserActionDelegate;
	private final int COMMAND_ROWS = 4;
	private final int COMMAND_COLUMNS = 30;

	/**
	 * @see ScreenComponent
	 */
	public CommandBox(ParserActionDelegate p) {
		super();
		this.parserActionDelegate = p;
	}

	/**
	 * Maps actions of language box, clear button, and run button
	 */
	@Override
	protected void mapUserActions() {
		commandClearButton.setOnAction((event -> {
			commandTextArea.clear();
		}));
		commandRunButton.setOnAction((event -> {
			parserActionDelegate.performParserAction(parser -> parser.passTextCommand(commandTextArea.getText().trim()));
			commandTextArea.clear();
		}));
		languageBox.valueProperty().addListener(((observable, oldValue, newValue) -> changeLanguage()));
	}

	/**
	 * Creates BorderPane and adds front end items to it
	 * @see ScreenComponent
	 */
	public void generateGUIComponent(){
		BorderPane borderPane = super.getBorderPane();
		this.addInputMenu(borderPane);
		this.addConsoleWindow(borderPane);
		commandLabel = new Label(ResourceBundleManager.retrieveLabel("COMMAND_LABEL_TEXT"));
		borderPane.setTop(commandLabel);
	}

	/**
	 * Adds commandTextArea
	 * @param borderPane BorderPane where text area is appended
	 */
	private void addConsoleWindow(BorderPane borderPane){
		commandTextArea = new TextArea();
		commandTextArea.setPrefRowCount(COMMAND_ROWS);
		commandTextArea.setPrefColumnCount(COMMAND_COLUMNS);
		borderPane.setCenter(commandTextArea);
	}

	/**
	 * Adds Buttons and ComboBox
	 * @param borderPane BorderPane where text area is appended
	 */
	private void addInputMenu(BorderPane borderPane){
		VBox rightComponent = new VBox();
		commandRunButton = new Button(ResourceBundleManager.retrieveButtonLabel("RUN"));
		commandClearButton = new Button(ResourceBundleManager.retrieveButtonLabel("CLEAR"));
		languageBox = ComboBoxFactory.generateStringComboBox(LANGUAGE_LIST);
		rightComponent.getChildren().add(commandRunButton);
		rightComponent.getChildren().add(commandClearButton);
		rightComponent.getChildren().add(languageBox);
		borderPane.setRight(rightComponent);
	}

	/**
	 * Changes language used to read commands inputted by user
	 */
	private void changeLanguage(){
		parserActionDelegate.performParserAction(parser -> parser.setLanguage(languageBox.getValue()));
	}
}
