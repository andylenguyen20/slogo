package view.screen_components;

import controller.ClearValueDelegate;
import controller.ValueModifierDelegate;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import model.VariableHistoryObservable;
import propertiesFiles.ResourceBundleManager;
import view.Observer;

import java.util.Map;

/**
 * Class which provides a front end representation of variables within the system. Also provides the user a way to
 * edit variables through an input field
 * @author Andy Nguyen
 * @author Edward Zhuang
 */
public class VariableHistoryBox extends ScreenComponent implements Observer {
	private static final int TEXTAREA_ROWS = 10;
	private static final int VALUEFIELD_WIDTH = 30;
	private static final int VARIABLE_COLUMNS = 12;
	private VariableHistoryObservable variableHistory;
	private ClearValueDelegate clearValueDelegate;
	private ValueModifierDelegate valueModifierDelegate;
	private TextArea textArea;
	private Button clearButton;
	private Button submitButton;
	private TextField editValueField;
	private ComboBox<String> variableComboBox;

	/**
	 * Constructor
	 * @see ScreenComponent
	 */
	public VariableHistoryBox(){
		super();
	}


	/**
	 * Set's up this class' clearValueDelegate
	 * @param clearValueDelegate interface which allows clearing of variables
	 */
	public void setClearValueDelegate(ClearValueDelegate clearValueDelegate){
		this.clearValueDelegate = clearValueDelegate;
	}

	/**
	 * Set's up this class' clearValueDelegate
	 * @param valueModifierDelegate interface which allows modifying of variables =
	 */
	public void setValueModifierDelegate(ValueModifierDelegate valueModifierDelegate){
		this.valueModifierDelegate = valueModifierDelegate;
	}

	/**
	 * Set's up this class' variableHistory
	 * @param variableHistory connects to back end variable history
	 */
	public void setVariableHistory(VariableHistoryObservable variableHistory){
		this.variableHistory = variableHistory;
	}

	/**
	 * Maps actions of this class' buttons
	 * clearButton clears variables
	 * submitButton modifies a designated variable's value
	 */
	@Override
	protected void mapUserActions() {
		clearButton.setOnAction((event -> {
			clearValueDelegate.clear();
		}));
		submitButton.setOnAction((event -> {
			valueModifierDelegate.changeValue(variableComboBox.getValue(), editValueField.getText());
		}));
	}

	/**
	 * Creates BorderPane and adds front end items to it
	 * @see ScreenComponent
	 */
	public void generateGUIComponent(){
		BorderPane borderPane = super.getBorderPane();
		this.addButtonAndLabels(borderPane);
		this.addTextArea(borderPane);
	}


	/**
	 * Fills textArea with variables and values from a map, and updates variableComboBox to allow for editing of
	 * these variables
	 * @param variableMap map of variables to their values
	 */
	private void fillBox(Map<String, Double> variableMap){
		StringBuilder commandsToDisplay = new StringBuilder();
		for(String variableName : variableMap.keySet()){
			commandsToDisplay.append(variableName);
			commandsToDisplay.append(": ");
			commandsToDisplay.append(variableMap.get(variableName));
			commandsToDisplay.append("\n");
		}
		textArea.setText(commandsToDisplay.toString());

		variableComboBox.getItems().clear();
		for(String variableName : variableMap.keySet()){
			variableComboBox.getItems().add(variableName);
		}
		variableComboBox.getSelectionModel().selectFirst();
	}

	/**
	 * Adds buttons and labels to a passed in BorderPane
	 * @param borderPane BorderPane on which buttons and labels are added
	 */
	private void addButtonAndLabels(BorderPane borderPane){
		HBox topComponent = new HBox();
		clearButton = new Button(ResourceBundleManager.retrieveButtonLabel("CLEAR"));
		Label label = new Label(ResourceBundleManager.retrieveLabel("VARIABLE_LABEL_TEXT"));
		topComponent.getChildren().add(label);
		topComponent.getChildren().add(clearButton);
		borderPane.setTop(topComponent);
		HBox bottomComponent = new HBox();
		variableComboBox = new ComboBox<>();
		bottomComponent.getChildren().add(variableComboBox);
		editValueField = new TextField();
		editValueField.setMaxWidth(VALUEFIELD_WIDTH);
		bottomComponent.getChildren().add(editValueField);
		submitButton = new Button(ResourceBundleManager.retrieveButtonLabel("SUBMIT"));
		bottomComponent.getChildren().add(submitButton);
		borderPane.setBottom(bottomComponent);
	}

	/**
	 * Adds text area to a passed in BorderPane
	 * @param borderPane BorderPane on which text area is added
	 */
	private void addTextArea(BorderPane borderPane){
		textArea = new TextArea();
		textArea.setEditable(false);
		textArea.setPrefRowCount(TEXTAREA_ROWS);
		textArea.setPrefColumnCount(VARIABLE_COLUMNS);
		borderPane.setCenter(textArea);
	}

	/**
	 * updates items in textArea with back end variable items
	 */
	@Override
	public void notifyOfChanges() {
		this.fillBox(variableHistory.getVariableMapCopy());
	}
}
