package view.screen_components;

import Experiment.ClearValueDelegate;
import Experiment.ValueModifierDelegate;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import model.VariableHistoryObservable;
import propertiesFiles.ResourceBundleManager;
import view.Observer;
import view.constants.TextAreaConstants;

import java.util.Map;

public class VariableHistoryBox extends ScreenComponent implements Observer {
	public static final int TEXTAREA_ROWS = 10;
	public static final int EDITVALUEFIELD_WIDTH = 30;
	private VariableHistoryObservable variableHistory;
	private ClearValueDelegate clearValueDelegate;
	private ValueModifierDelegate valueModifierDelegate;
	private TextArea textArea;
	private Button clearButton;
	private Button submitButton;
	private TextField editValueField;
	private ComboBox<String> variableComboBox;
	public VariableHistoryBox(){
		super();
	}

	public void setClearValueDelegate(ClearValueDelegate clearValueDelegate){
		this.clearValueDelegate = clearValueDelegate;
	}

	public void setValueModifierDelegate(ValueModifierDelegate valueModifierDelegate){
		this.valueModifierDelegate = valueModifierDelegate;
	}

	public void setVariableHistory(VariableHistoryObservable variableHistory){
		this.variableHistory = variableHistory;
	}

	@Override
	protected void mapUserActions() {
		clearButton.setOnAction((event -> {
			clearValueDelegate.clear();
		}));
		submitButton.setOnAction((event -> {
			valueModifierDelegate.changeValue(variableComboBox.getValue(), editValueField.getText());
		}));
	}

	public void generateGUIComponent(){
		BorderPane borderPane = super.getBorderPane();
		this.addButtonAndLabels(borderPane);
		this.addTextArea(borderPane);
	}

	protected void fillBox(Map<String, Double> variableMap){
		StringBuilder commandsToDisplay = new StringBuilder();
		for(String variableName : variableMap.keySet()){
			commandsToDisplay.append(variableName);
			commandsToDisplay.append(": ");
			commandsToDisplay.append(variableMap.get(variableName));
			commandsToDisplay.append("\n");
		}
		textArea.setText(commandsToDisplay.toString());

		//new stuff with map
		variableComboBox.getItems().clear();
		for(String variableName : variableMap.keySet()){
			variableComboBox.getItems().add(variableName);
		}
		variableComboBox.getSelectionModel().selectFirst();
	}

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
		editValueField.setMaxWidth(EDITVALUEFIELD_WIDTH);
		bottomComponent.getChildren().add(editValueField);
		submitButton = new Button(ResourceBundleManager.retrieveButtonLabel("SUBMIT"));
		bottomComponent.getChildren().add(submitButton);
		borderPane.setBottom(bottomComponent);
	}


	private void addTextArea(BorderPane borderPane){
		textArea = new TextArea();
		textArea.setEditable(false);
		textArea.setPrefRowCount(TEXTAREA_ROWS);
		textArea.setPrefColumnCount(TextAreaConstants.VARIABLE_COLUMNS);
		borderPane.setCenter(textArea);
	}

	@Override
	public void notifyOfChanges() {
		this.fillBox(variableHistory.getVariableMapCopy());
	}
}
