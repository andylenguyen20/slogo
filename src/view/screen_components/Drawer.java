package view.screen_components;

import controller.ActivationDelegate;
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
public class Drawer extends ScreenComponent implements Observer{
    public static final double CANVAS_WIDTH = 450;
    public static final double CANVAS_HEIGHT = 450;
    private static final int VERTICAL_INSET = 10;
    private static final int HORIZONTAL_INSET = 20;

    private ComboBox<Integer> backgroundColorBox;
    private ComboBox<Integer> penColorBox;
    private ComboBox<Integer> turtleImageBox;

    private DrawerObservable drawerObservable;
    private List<DrawerTurtleComponent> turtlesOnScreen;
    private List<TurtleObservable> linkedTurtles;
    private DrawerBackgroundComponent backgroundComponent;

    private ParserActionDelegate parserActionDelegate;
    private ActivationDelegate activationDelegate;

    private StackPane drawingScreen;
    private Canvas linesLayer;
    private Canvas backgroundLayer;

    /**
     * Constructor
     * @see ScreenComponent
     * Adds two arraylists which hold information about turtles
     */
    public Drawer(ActivationDelegate activationDelegate, ParserActionDelegate parserActionDelegate){
        super();
        linkedTurtles = new ArrayList<>();
        turtlesOnScreen = new ArrayList<>();
        this.activationDelegate = activationDelegate;
        this.parserActionDelegate = parserActionDelegate;
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
        backgroundComponent = new DrawerBackgroundComponent(backgroundLayer);
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
        DrawerTurtleComponent additionalTurtle = new DrawerTurtleComponent(activationDelegate, turtleObservable, drawingScreen, linesLayer);
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
        for(DrawerTurtleComponent frontEndTurtle : turtlesOnScreen){
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
