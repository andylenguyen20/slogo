package view.screen_components;

import controller.DeactivationDelegate;
import controller.ParserActionDelegate;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import model.TurtleObservable;
import model.DrawerObservable;
import propertiesFiles.ResourceBundleManager;
import view.Observer;
import view.constants.PaletteConstants;
import view.factories.ComboBoxFactory;

import java.util.ArrayList;
import java.util.List;

/**
 *  Represents the drawer portion of SLogo.
 *  Connected to backend which holds data to be represented on screen. Responsible for creating other components of the
 *  overall Drawer, including DrawerBackgroundComponent, DrawerLineComponent, and DrawerTurtleComponent.
 *  @author Andy Nguyen
 *  @author Edward Zhuang
 */
public class TheDrawer extends ScreenComponent implements Observer{
    public static final double CANVAS_WIDTH = 450;
    public static final double CANVAS_HEIGHT = 450;
    private static final int VERTICAL_INSET = 10;
    private static final int HORIZONTAL_INSET = 20;

    private ComboBox<Integer> backgroundColorBox;
    private ComboBox<Integer> penColorBox;
    private ComboBox<Integer> turtleImageBox;

    private DrawerObservable drawerObservable;
    private List<TheDrawerTurtleComponent> turtlesOnScreen;
    private List<TurtleObservable> linkedTurtles;
    private TheDrawerBackgroundComponent backgroundComponent;

    private ParserActionDelegate parserActionDelegate;
    private DeactivationDelegate deactivationDelegate;

    private StackPane drawingScreen;
    private Canvas linesLayer;
    private Canvas backgroundLayer;

    /**
     * Constructor
     * @see ScreenComponent
     * Adds two arraylists which hold information about turtles
     */
    public TheDrawer(){
        super();
        this.deactivationDelegate = deactivationDelegate;
        linkedTurtles = new ArrayList<>();
        turtlesOnScreen = new ArrayList<>();
    }

    /**
     * Sets up Drawer's DrawerObservable
     * @param drawerObservable interface with methods to access turtles and background color
     */
    public void setDrawerObservable(DrawerObservable drawerObservable){
        this.drawerObservable = drawerObservable;
        this.update();
    }

    /**
     * Sets up Drawer's ParserActionDelegate
     * @param parserActionDelegate interface which allows Drawer to pass commands to parser
     */
    public void setTheParserActionDelegate(ParserActionDelegate parserActionDelegate){
        this.parserActionDelegate = parserActionDelegate;
    }

    /**
     * Maps choices of ComboBoxes to its actions
     */
    @Override
    protected void mapUserActions() {
        backgroundColorBox.valueProperty().addListener((observable, oldValue, newValue) -> changeBackgroundColor());
        turtleImageBox.valueProperty().addListener((observable, oldValue, newValue) -> changeTurtleImage());
        penColorBox.valueProperty().addListener((observable, oldValue, newValue) -> changePenColor());
    }

    /**
     * Creates BorderPane and adds front end items to it
     * @see ScreenComponent
     */
    public void generateGUIComponent(){
        BorderPane borderPane = super.getBorderPane();
        borderPane.setPadding(new Insets(VERTICAL_INSET, HORIZONTAL_INSET, VERTICAL_INSET, HORIZONTAL_INSET));
        generateListOptions(borderPane);
        generateDrawingScreen(borderPane);
    }

    /**
     * Adds StackPane and adds two canvases which hold background color and lines
     * @param borderPane BorderPane on which StackPane is added upon
     */
    private void generateDrawingScreen(BorderPane borderPane){
        drawingScreen = new StackPane();
        linesLayer = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
        backgroundLayer = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
        backgroundComponent = new TheDrawerBackgroundComponent(backgroundLayer);
        backgroundComponent.changeBackgroundColor(Color.WHITE);
        drawingScreen.getChildren().add(backgroundLayer);
        drawingScreen.getChildren().add(linesLayer);
        borderPane.setCenter(drawingScreen);
    }

    /**
     * Adds ComboBoxes to a HBox, which is placed on bottom of BorderPane
     * @param borderPane BorderPane on which ComboBoxes are added upon
     */
    private void generateListOptions(BorderPane borderPane){
        HBox hbox = new HBox();
        backgroundColorBox = ComboBoxFactory.generateIntegerComboBox(PaletteConstants.COLORS.length);
        penColorBox = ComboBoxFactory.generateIntegerComboBox(PaletteConstants.COLORS.length);
        turtleImageBox = ComboBoxFactory.generateIntegerComboBox(PaletteConstants.TURTLE_IMAGES.length);
        hbox.getChildren().addAll(backgroundColorBox,penColorBox,turtleImageBox);
        borderPane.setBottom(hbox);
    }

    /**
     * Creates method for backgroundColorBox to use to invoke Parser methods
     */
    private void changeBackgroundColor(){
        parserActionDelegate.performParserAction(parser -> parser.passActionCommand(ResourceBundleManager.retrieveOnScreenCommand("SET_BG") + backgroundColorBox.getValue()));
    }

    /**
     * Creates method for penColorBox to use to invoke Parser methods
     */
    private void changePenColor(){
        parserActionDelegate.performParserAction(parser -> parser.passActionCommand(ResourceBundleManager.retrieveOnScreenCommand("SET_PC") + penColorBox.getValue()));
    }

    /**
     * Creates method for turtleImageBox to use to invoke Parser methods
     */
    private void changeTurtleImage(){
        parserActionDelegate.performParserAction(parser -> parser.passActionCommand(ResourceBundleManager.retrieveOnScreenCommand("SET_SH") + turtleImageBox.getValue()));
    }

    /**
     * Adds new TurtleObservable to list and creates a DrawerTurtleComponent for visually displaying this TurtleObservable
     * @param turtleObservable TurtleObservable to be added to list
     */
    private void addFrontEndTurtle(TurtleObservable turtleObservable){
        linkedTurtles.add(turtleObservable);
        TheDrawerTurtleComponent additionalTurtle = new TheDrawerTurtleComponent(turtleObservable, drawingScreen, linesLayer);
        additionalTurtle.setDeactivationDelegate(deactivationDelegate);
        turtlesOnScreen.add(additionalTurtle);
    }

    /**
     * Checks to see if new TurtleObservables exist and creates front end components for them
     * Tells each DrawerTurtleComponent to update its states to match its back end turtle
     */
    public void update(){
        for(TurtleObservable turt : drawerObservable.getTurtleObservables()){
            if(!linkedTurtles.contains(turt)){
                this.addFrontEndTurtle(turt);
            }
        }
        linesLayer.getGraphicsContext2D().clearRect(0,0,CANVAS_WIDTH, CANVAS_HEIGHT);
        for(TheDrawerTurtleComponent frontEndTurtle : turtlesOnScreen){
            frontEndTurtle.update();
        }
        backgroundComponent.changeBackgroundColor(drawerObservable.getBackgroundColor());
    }

    /**
     * Calls update()
     */
    @Override
    public void notifyOfChanges() {
        update();
    }
}
