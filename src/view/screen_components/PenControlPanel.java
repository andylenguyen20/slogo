package view.screen_components;

import controller.ParserActionDelegate;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import propertiesFiles.ResourceBundleManager;

/**
 * Class which provides buttons for user to click which manipulates pen options.
 * @author Andy Nguyen
 * @author Edward Zhuang
 */
public class PenControlPanel extends ScreenComponent{
    private static final int SMALL_PEN_SIZE = 5;
    private static final int MED_PEN_SIZE = 15;
    private static final int LARGE_PEN_SIZE = 30;
    private ParserActionDelegate parserActionDelegate;
    private Button penUpButton;
    private Button penDownButton;
    private Button smallPenButton;
    private Button medPenButton;
    private Button largePenButton;

    /**
     * Constructor
     * @see ScreenComponent
     */
    public PenControlPanel(){
        super();
    }

    /**
     * Sets up this class' parserActionDelegate
     * @param parserActionDelegate interface which allows passing of commands to parser
     */
    public void setController(ParserActionDelegate parserActionDelegate){
        this.parserActionDelegate = parserActionDelegate;
    }

    /**
     * Maps user actions of PenControlPanel buttons
     */
    @Override
    protected void mapUserActions() {
        penUpButton.setOnAction((event -> {
            parserActionDelegate.performParserAction((parser -> parser.passActionCommand(ResourceBundleManager.retrieveOnScreenCommand("PENUP"))));
        }));
        penDownButton.setOnAction((event -> {
            parserActionDelegate.performParserAction((parser -> parser.passActionCommand(ResourceBundleManager.retrieveOnScreenCommand("PENDOWN"))));
        }));
        smallPenButton.setOnAction((event -> {
            parserActionDelegate.performParserAction((parser -> parser.passActionCommand(ResourceBundleManager.retrieveOnScreenCommand("SET_PS") + SMALL_PEN_SIZE)));
        }));
        medPenButton.setOnAction((event -> {
            parserActionDelegate.performParserAction((parser -> parser.passActionCommand(ResourceBundleManager.retrieveOnScreenCommand("SET_PS") + MED_PEN_SIZE)));
        }));
        largePenButton.setOnAction((event -> {
            parserActionDelegate.performParserAction((parser -> parser.passActionCommand(ResourceBundleManager.retrieveOnScreenCommand("SET_PS") + LARGE_PEN_SIZE)));
        }));

    }

    /**
     * Adds the buttons to the BorderPane, formats them with VBox.
     */
    @Override
    public void generateGUIComponent() {
        BorderPane borderPane = super.getBorderPane();
        penUpButton = new Button(ResourceBundleManager.retrieveButtonLabel("PEN_UP"));
        penDownButton = new Button(ResourceBundleManager.retrieveButtonLabel("PEN_DOWN"));
        smallPenButton = new Button(ResourceBundleManager.retrieveButtonLabel("PEN_SMALL"));
        medPenButton = new Button(ResourceBundleManager.retrieveButtonLabel("PEN_MED"));
        largePenButton = new Button(ResourceBundleManager.retrieveButtonLabel("PEN_LARGE"));
        VBox vbox = new VBox();
        vbox.getChildren().add(penUpButton);
        vbox.getChildren().add(penDownButton);
        vbox.getChildren().add(smallPenButton);
        vbox.getChildren().add(medPenButton);
        vbox.getChildren().add(largePenButton);
        borderPane.setCenter(vbox);
    }
}
