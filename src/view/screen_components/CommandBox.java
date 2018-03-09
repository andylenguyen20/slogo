package view.screen_components;

import controller.ParserActionDelegate;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import propertiesFiles.ResourceBundleManager;
import view.constants.TextAreaConstants;
import view.factories.ComboBoxFactory;

public class CommandBox extends ScreenComponent{
	public static final String[] LANGUAGE_LIST = {"English", "Chinese"};
	private Button commandClearButton;
    private Button commandRunButton;
    private TextArea commandTextArea;
    private Label commandLabel;
    private ComboBox<String> languageBox;
    private ParserActionDelegate parserActionDelegate;

	public CommandBox() {
		super();
	}

	public void setController(ParserActionDelegate parserActionDelegate){
		this.parserActionDelegate = parserActionDelegate;
	}

	@Override
	protected void mapUserActions() {
		commandClearButton.setOnAction((event -> {
			commandTextArea.clear();
		}));
		commandRunButton.setOnAction((event -> {
			parserActionDelegate.performParserAction(parser -> parser.passTextCommand(commandTextArea.getText().trim()));
			commandTextArea.clear();
		}));
		languageBox.valueProperty().addListener((x,y,z)->changeLanguage());
	}

	public void generateGUIComponent(){
		BorderPane borderPane = super.getBorderPane();
		this.addInputMenu(borderPane);
		this.addConsoleWindow(borderPane);
		commandLabel = new Label(ResourceBundleManager.retrieveLabel("COMMAND_LABEL_TEXT"));
		borderPane.setTop(commandLabel);
	}
	
	private void addConsoleWindow(BorderPane borderPane){
		commandTextArea = new TextArea();
		commandTextArea.setPrefRowCount(TextAreaConstants.COMMAND_ROWS);
		commandTextArea.setPrefColumnCount(TextAreaConstants.COMMAND_COLUMNS);
		borderPane.setCenter(commandTextArea);
	}
	
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

	private void changeLanguage(){
    	parserActionDelegate.performParserAction(parser -> parser.setLanguage(languageBox.getValue()));
	}
}
