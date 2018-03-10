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
import parsers.Parser;
import propertiesFiles.ResourceBundleManager;
import view.Observer;

import java.util.List;

/**
 * Class which sets up front end representation of custom commands
 * @author Andy Nguyen
 * @author Edward Zhuang
 */
public class CustomCommandsBox extends ScreenComponent implements Observer {
    public static final int SCROLLPANE_WIDTH = 200;
    public static final int SCROLLPANE_HEIGHT = 100;
    private CustomCommandObservable customCommandObservable;
    private Button clearButton;
    private VBox commandList;

    private ParserActionDelegate parserActionDelegate;
    private ClearValueDelegate clearValueDelegate;

    /**
     * @see ScreenComponent
     */
    public CustomCommandsBox(ParserActionDelegate p, ClearValueDelegate c){
        super();
        this.parserActionDelegate = p;
        this.clearValueDelegate = c;
    }


    /**
     * Adds buttons and labels to a passed in BorderPane
     * @param borderPane BorderPane on which buttons and labels are added
     */
    private void addButtonAndLabels(BorderPane borderPane) {
        HBox topComponent = new HBox();
        clearButton = new Button(ResourceBundleManager.retrieveButtonLabel("CLEAR"));
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

    /**
     * Sets up customCommandObservable
     * @param holder interface with methods to manipulate custom commands
     */
    public void setCustomCommandObservable(CustomCommandObservable holder){
        this.customCommandObservable = holder;
    }

    /**
     * updates custom command history
     */
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

    /**
     * Maps actions of this class' buttons
     * clearButton clears history
     */
    @Override
    protected void mapUserActions() {
        clearButton.setOnAction((event -> clearValueDelegate.clear()));
    }

    /**
     * Creates BorderPane and adds front end items to it
     * @see ScreenComponent
     */
    @Override
    public void generateGUIComponent() {
        BorderPane borderPane = super.getBorderPane();
        this.addButtonAndLabels(borderPane);
    }
}
