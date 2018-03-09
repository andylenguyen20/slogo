package view.screen_components;

import Experiment.TheParserActionDelegate;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import propertiesFiles.ResourceBundleManager;


public class TurtleControlPanel extends ScreenComponent {

    private static final String FD_BUTTON = "fdButton";
    private static final String BK_BUTTON = "bkButton";
    private static final String LT_BUTTON = "ltButton";
    private static final String RT_BUTTON = "rtButton";
    private TheParserActionDelegate theParserActionDelegate;
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
            theParserActionDelegate.performParserAction(parser -> parser.makeTree(parser.parseActionCommand(ResourceBundleManager.retrieveOnScreenCommand("FORWARD"))));
        }));
        backwardButton.setOnAction((event -> {
            theParserActionDelegate.performParserAction(parser -> parser.makeTree(parser.parseActionCommand(ResourceBundleManager.retrieveOnScreenCommand("BACKWARD"))));
        }));
        rightTurnButton.setOnAction((event -> {
            theParserActionDelegate.performParserAction(parser -> parser.makeTree(parser.parseActionCommand(ResourceBundleManager.retrieveOnScreenCommand("RIGHT"))));
        }));
        leftTurnButton.setOnAction((event -> {
            theParserActionDelegate.performParserAction(parser -> parser.makeTree(parser.parseActionCommand(ResourceBundleManager.retrieveOnScreenCommand("LEFT"))));
        }));
    }

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

    public void setController(TheParserActionDelegate theParserActionDelegate){
        this.theParserActionDelegate = theParserActionDelegate;
    }
}
