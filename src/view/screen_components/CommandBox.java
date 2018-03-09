package view.screen_components;

import controller.ParserActionDelegate;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import propertiesFiles.ResourceBundleManager;
import view.constants.ComboBoxConstants;
import view.constants.TextAreaConstants;

public class CommandBox extends ScreenComponent{
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
		commandClearButton.setOnAction((e) -> commandTextArea.clear());
		commandRunButton.setOnAction((e)->this.runCommand());
		languageBox.valueProperty().addListener((x,y,z)-> this.changeLanguage());
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
		commandRunButton = new Button(ResourceBundleManager.retrieveButtonLabel("COMMAND_RUN_BUTTON_LABEL"));
		commandClearButton = new Button(ResourceBundleManager.retrieveButtonLabel("COMMAND_CLEAR_BUTTON_LABEL"));
		languageBox = this.getLanguageBox();
		rightComponent.getChildren().add(commandRunButton);
		rightComponent.getChildren().add(commandClearButton);
		rightComponent.getChildren().add(languageBox);
		borderPane.setRight(rightComponent);
	}

	private ComboBox<String> getLanguageBox(){
		languageBox = new ComboBox<>();
		String[] options = ComboBoxConstants.LANGUAGE_LIST;
		for (int i = 0 ; i < options.length; i++) {
            languageBox.getItems().add(options[i]);
            languageBox.getSelectionModel().selectFirst();
        }
		return languageBox;
	}

	private void runCommand(){
		parserActionDelegate.performParserAction((p)->p.parseString(commandTextArea.getText().trim()));
		commandTextArea.clear();
	}
	private void changeLanguage(){
		parserActionDelegate.performParserAction((p)->p.setLanguage(languageBox.getValue()));
	}

}
