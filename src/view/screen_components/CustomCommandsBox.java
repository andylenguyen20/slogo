package view.screen_components;

import Experiment.ClearValueDelegate;
import Experiment.ParserActionDelegate;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.CustomCommandObservable;
import propertiesFiles.ResourceBundleManager;
import view.Observer;
import view.help_items.HelpPopup;

import java.util.List;

public class CustomCommandsBox extends ScreenComponent implements Observer {
    public static final int SCROLLPANE_WIDTH = 200;
    public static final int SCROLLPANE_HEIGHT = 100;
    private CustomCommandObservable customCommandObservable;
    private Button clearButton;
    private Button helpButton;
    private VBox commandList;

    private ParserActionDelegate parserActionDelegate;
    private ClearValueDelegate clearValueDelegate;

    public void setParserActionDelegate(ParserActionDelegate parserActionDelegate){
        this.parserActionDelegate = parserActionDelegate;
    }

    public  void setClearValueDelegate(ClearValueDelegate clearValueDelegate){
        this.clearValueDelegate = clearValueDelegate;
    }

    private void addButtonAndLabels(BorderPane borderPane) {
        HBox topComponent = new HBox();
        clearButton = new Button(ResourceBundleManager.retrieveButtonLabel("CLEAR"));
        helpButton = new Button(ResourceBundleManager.retrieveButtonLabel("HELP"));
        Label label = new Label("Custom Commands");
        topComponent.getChildren().add(label);
        topComponent.getChildren().add(clearButton);
        borderPane.setTop(topComponent);
        commandList = new VBox();
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setMaxWidth(SCROLLPANE_WIDTH);
        scrollPane.setPrefHeight(SCROLLPANE_HEIGHT);
        scrollPane.setContent(commandList);
        borderPane.setCenter(scrollPane);
        borderPane.setBottom(helpButton);
        BorderPane.setAlignment(helpButton, Pos.BOTTOM_RIGHT);

    }

    public void setCustomCommandObservable(CustomCommandObservable customCommandObservable){
        this.customCommandObservable = customCommandObservable;
    }

    @Override
    public void notifyOfChanges() {
        List<String> commands = customCommandObservable.getCommands();
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

    @Override
    protected void mapUserActions() {
        clearButton.setOnAction((event -> {
            clearValueDelegate.clear();
        }));
        helpButton.setOnAction((event -> {
            HelpPopup popup = new HelpPopup();
            popup.open();
        }));
    }

    @Override
    public void generateGUIComponent() {
        BorderPane borderPane = super.getBorderPane();
        this.addButtonAndLabels(borderPane);
    }
}
