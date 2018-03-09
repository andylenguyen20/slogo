package view.screen_components;

import controller.ParserActionDelegate;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class TurtleControlPanel extends ScreenComponent {
    private static final double STEP_SIZE = 50;
    private static final double TURN_SIZE = 20;
    private ParserActionDelegate parserActionDelegate;
    private Button forwardButton;
    private Button backwardButton;
    private Button leftTurnButton;
    private Button rightTurnButton;

    public TurtleControlPanel(){
        super();
    }

    @Override
    protected void mapUserActions() {
        forwardButton.setOnAction((event -> {
            parserActionDelegate.performParserAction((p)->p.parseString("fd " + STEP_SIZE));
        }));
        backwardButton.setOnAction((event -> {
            parserActionDelegate.performParserAction((p)->p.parseString("bk " + STEP_SIZE));
        }));
        rightTurnButton.setOnAction((event -> {
            parserActionDelegate.performParserAction((p)->p.parseString("rt " + TURN_SIZE));
        }));
        leftTurnButton.setOnAction((event -> {
            parserActionDelegate.performParserAction((p)->p.parseString("lt " + TURN_SIZE));
        }));
    }

    @Override
    protected void generateGUIComponent() {
        BorderPane borderPane = super.getBorderPane();
        forwardButton = new Button();
        forwardButton.getStyleClass().add("fdButton");
        backwardButton = new Button();
        backwardButton.getStyleClass().add("bkButton");
        leftTurnButton = new Button();
        leftTurnButton.getStyleClass().add("ltButton");
        rightTurnButton = new Button();
        rightTurnButton.getStyleClass().add("rtButton");
        VBox vbox = new VBox();
        HBox topPanel = new HBox();
        topPanel.getChildren().add(backwardButton);
        topPanel.getChildren().add(forwardButton);
        HBox bottomPanel = new HBox();
        bottomPanel.getChildren().add(leftTurnButton);
        bottomPanel.getChildren().add(rightTurnButton);
        vbox.getChildren().add(topPanel);
        vbox.getChildren().add(bottomPanel);
        borderPane.setCenter(vbox);
    }

    public void setController(ParserActionDelegate parserActionDelegate){
        this.parserActionDelegate = parserActionDelegate;
    }
}
