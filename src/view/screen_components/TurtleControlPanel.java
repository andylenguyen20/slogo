package view.screen_components;

import controller.ParserActionDelegate;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import propertiesFiles.ResourceBundleManager;

/**
 * Class which provides buttons for user to click which manipulates on screen turtles.
 * @author Andy Nguyen
 * @author Edward Zhuang
 */
public class TurtleControlPanel extends ScreenComponent {
    private static final int STEP_SIZE = 50;
    private static final int TURN_SIZE = 20;
    private static final String FD_BUTTON = "fdButton";
    private static final String BK_BUTTON = "bkButton";
    private static final String LT_BUTTON = "ltButton";
    private static final String RT_BUTTON = "rtButton";
    private ParserActionDelegate parserActionDelegate;
    private Button forwardButton;
    private Button backwardButton;
    private Button leftTurnButton;
    private Button rightTurnButton;

    /**
     * Constructor
     * @see ScreenComponent
     */
    public TurtleControlPanel(ParserActionDelegate p){
        super();
        this.parserActionDelegate = p;
    }

    /**
     * Maps user actions of TurtleControlPanel buttons
     */
    @Override
    protected void mapUserActions() {
        forwardButton.setOnAction((event -> {
            parserActionDelegate.performParserAction(parser -> parser.passActionCommand(ResourceBundleManager.retrieveOnScreenCommand("FORWARD") + STEP_SIZE));
        }));
        backwardButton.setOnAction((event -> {
            parserActionDelegate.performParserAction(parser -> parser.passActionCommand(ResourceBundleManager.retrieveOnScreenCommand("BACKWARD") + STEP_SIZE));
        }));
        rightTurnButton.setOnAction((event -> {
            parserActionDelegate.performParserAction(parser -> parser.passActionCommand(ResourceBundleManager.retrieveOnScreenCommand("RIGHT") + TURN_SIZE));
        }));
        leftTurnButton.setOnAction((event -> {
            parserActionDelegate.performParserAction(parser -> parser.passActionCommand(ResourceBundleManager.retrieveOnScreenCommand("LEFT") + TURN_SIZE));
        }));

    }

    /**
     * Adds the buttons to the BorderPane, formats them with HBox and VBox.
     */
    @Override
    public void generateGUIComponent() {
        BorderPane borderPane = super.getBorderPane();
        forwardButton = new Button();
        forwardButton.getStyleClass().add(FD_BUTTON);
        backwardButton = new Button();
        backwardButton.getStyleClass().add(BK_BUTTON);
        leftTurnButton = new Button();
        leftTurnButton.getStyleClass().add(LT_BUTTON);
        rightTurnButton = new Button();
        rightTurnButton.getStyleClass().add(RT_BUTTON);
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
}
