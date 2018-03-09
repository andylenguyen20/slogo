package view.screen_components;

import controller.ClearValueDelegate;
import controller.ParserActionDelegate;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.CustomCommandObservable;
import view.Observer;

import java.util.List;

public class CustomCommandsBox extends ScreenComponent implements Observer {
    public static final int SCROLLPANE_WIDTH = 200;
    public static final int SCROLLPANE_HEIGHT = 100;
    private CustomCommandObservable customCommandHolder;
    private Button clearButton;
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
        clearButton = new Button("Clear");
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
                parserActionDelegate.performParserAction(parser -> parser.parseString(commandButton.getText()));
            }));
            commandList.getChildren().add(commandButton);
        }
    }

    @Override
    protected void mapUserActions() {
        clearButton.setOnAction((event -> {
            clearValueDelegate.clear();
        }));
    }

    @Override
    public void generateGUIComponent() {
        BorderPane borderPane = super.getBorderPane();
        this.addButtonAndLabels(borderPane);
    }
}
