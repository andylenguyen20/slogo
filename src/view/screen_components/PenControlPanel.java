package view.screen_components;

import Experiment.TheParserActionDelegate;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import propertiesFiles.ResourceBundleManager;

public class PenControlPanel extends ScreenComponent{
    private static final double SMALL_PEN_SIZE = 50;
    private static final double MED_PEN_SIZE = 50;
    private static final double LARGE_PEN_SIZE = 50;
    private TheParserActionDelegate theParserActionDelegate;
    private Button penUpButton;
    private Button penDownButton;
    private Button smallPenButton;
    private Button medPenButton;
    private Button largePenButton;

    public PenControlPanel(){
        super();
    }

    public void setController(TheParserActionDelegate theParserActionDelegate){
        this.theParserActionDelegate = theParserActionDelegate;
    }

    @Override
    protected void mapUserActions() {
        penUpButton.setOnAction((event -> {
            theParserActionDelegate.performParserAction(parser -> parser.makeTree(parser.parseActionCommand(ResourceBundleManager.retrieveOnScreenCommand("PENUP"))));
        }));
        penDownButton.setOnAction((event -> {
            theParserActionDelegate.performParserAction(parser -> parser.makeTree(parser.parseActionCommand(ResourceBundleManager.retrieveOnScreenCommand("PENDOWN"))));
        }));
        smallPenButton.setOnAction((event -> {
            theParserActionDelegate.performParserAction(parser -> parser.makeTree(parser.parseActionCommand(ResourceBundleManager.retrieveOnScreenCommand("SMALL_FONT"))));
        }));
        medPenButton.setOnAction((event -> {
            theParserActionDelegate.performParserAction(parser -> parser.makeTree(parser.parseActionCommand(ResourceBundleManager.retrieveOnScreenCommand("MED_FONT"))));
        }));
        largePenButton.setOnAction((event -> {
            theParserActionDelegate.performParserAction(parser -> parser.makeTree(parser.parseActionCommand(ResourceBundleManager.retrieveOnScreenCommand("LARGE_FONT"))));
        }));
    }

    @Override
    public void generateGUIComponent() {
        BorderPane borderPane = super.getBorderPane();
        penUpButton = new Button(ResourceBundleManager.retrieveOnScreenCommand("UP_BUTTON"));
        penDownButton = new Button(ResourceBundleManager.retrieveOnScreenCommand("DOWN_BUTTON"));
        smallPenButton = new Button(ResourceBundleManager.retrieveOnScreenCommand("SMALL_BUTTON"));
        medPenButton = new Button(ResourceBundleManager.retrieveOnScreenCommand("MED_BUTTON"));
        largePenButton = new Button(ResourceBundleManager.retrieveOnScreenCommand("LARGE_BUTTON"));
        VBox vbox = new VBox();
        vbox.getChildren().add(penUpButton);
        vbox.getChildren().add(penDownButton);
        vbox.getChildren().add(smallPenButton);
        vbox.getChildren().add(medPenButton);
        vbox.getChildren().add(largePenButton);
        borderPane.setCenter(vbox);
    }
}
