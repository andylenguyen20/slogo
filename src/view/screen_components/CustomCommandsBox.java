package view.screen_components;

import Experiment.TheClearValueDelegate;
import Experiment.TheParserActionDelegate;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.CustomCommandObservable;
import view.Observer;
import view.help_items.HelpPopup;

import java.util.List;

public class CustomCommandsBox extends ScreenComponent implements Observer {
    public static final int SCROLLPANE_WIDTH = 200;
    public static final int SCROLLPANE_HEIGHT = 100;
    private CustomCommandObservable customCommandHolder;
    private Button clearButton;
    private Button helpButton;
    private VBox commandList;

    private TheParserActionDelegate theParserActionDelegate;
    private TheClearValueDelegate theClearValueDelegate;

    public void setTheParserActionDelegate(TheParserActionDelegate theParserActionDelegate){
        this.theParserActionDelegate = theParserActionDelegate;
    }

    public  void setTheClearValueDelegate(TheClearValueDelegate theClearValueDelegate){
        this.theClearValueDelegate = theClearValueDelegate;
    }

    private void addButtonAndLabels(BorderPane borderPane) {
        HBox topComponent = new HBox();
        clearButton = new Button("Clear");
        helpButton = new Button("Help");
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

    public void setCustomCommandHolder(CustomCommandObservable holder){
        this.customCommandHolder = holder;
    }

    @Override
    public void notifyOfChanges() {
        List<String> commands = customCommandHolder.getCommands();
        commandList.getChildren().clear();
        for(String command: commands){
            Button commandButton = new Button(command);
            commandButton.getStyleClass().add("runnableCommandButton");
            commandButton.setOnAction((event -> {
                theParserActionDelegate.performParserAction(parser -> parser.parseString(commandButton.getText()));
            }));
            commandList.getChildren().add(commandButton);
        }
    }

    @Override
    protected void mapUserActions() {
        clearButton.setOnAction((event -> {
            theClearValueDelegate.clear();
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
