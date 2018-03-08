package view.screen_components;

import controller.VariableHistoryBoxController;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import model.VariableHistoryObservable;
import propertiesFiles.ResourceBundleManager;
import view.Observer;
import view.constants.ButtonConstants;
import view.constants.LabelConstants;
import view.constants.TextAreaConstants;

import java.util.Map;

public class VariableHistoryBox extends ScreenComponent implements Observer {
	private VariableHistoryObservable variableHistory;
	private VariableHistoryBoxController controller;
	private TextArea textArea;
	private Button clearButton;
	private Button submitButton;
	private TextField editValueField;
	private ComboBox<String> variableComboBox;
	public VariableHistoryBox(){
		super();
	}

	public void setController(VariableHistoryBoxController controller){
		this.controller = controller;
	}

	public void setVariableHistory(VariableHistoryObservable variableHistory){
		this.variableHistory = variableHistory;
	}

	@Override
	protected void mapUserActions() {
		clearButton.setOnAction((event -> {
			controller.clearVariableBox();
		}));
		submitButton.setOnAction((event -> {
			controller.changeVariableValue(variableComboBox.getValue(), editValueField.getText());
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
		clearButton = new Button(ResourceBundleManager.retrieveButtonLabel("VARIABLE_BUTTON_LABEL"));
		Label label = new Label(ResourceBundleManager.retrieveLabel("VARIABLE_LABEL_TEXT"));
		topComponent.getChildren().add(label);
		topComponent.getChildren().add(clearButton);
		borderPane.setTop(topComponent);
		HBox bottomComponent = new HBox();
		variableComboBox = new ComboBox<>();
		bottomComponent.getChildren().add(variableComboBox);
		editValueField = new TextField();
		editValueField.setMaxWidth(30);
		bottomComponent.getChildren().add(editValueField);
		submitButton = new Button("Submit");
		bottomComponent.getChildren().add(submitButton);
		borderPane.setBottom(bottomComponent);
	}


	private void addTextArea(BorderPane borderPane){
		textArea = new TextArea();
		textArea.setEditable(false);
		textArea.setPrefRowCount(10);
		textArea.setPrefColumnCount(TextAreaConstants.VARIABLE_COLUMNS);
		borderPane.setCenter(textArea);
	}

	@Override
	public void notifyOfChanges() {
		this.fillBox(variableHistory.getVariableMapCopy());
	}
}
