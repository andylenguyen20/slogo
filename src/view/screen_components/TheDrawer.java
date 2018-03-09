package view.screen_components;

import Experiment.TheParserActionDelegate;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import model.PaletteObservable;
import model.TurtleObservable;
import model.DrawerObservable;
import propertiesFiles.ResourceBundleManager;
import view.Observer;
import view.constants.PaletteConstants;
import view.factories.ComboBoxFactory;

import java.util.ArrayList;
import java.util.List;

public class TheDrawer extends ScreenComponent implements Observer{
    public static final double CANVAS_WIDTH = 450;
    public static final double CANVAS_HEIGHT = 450;
    private static final int VERTICAL_INSET = 10;
    private static final int HORIZONTAL_INSET = 20;

    //other turtle images taken from:
    // https://pixabay.com/en/turtle-animal-reptile-water-green-294522/
    // https://pixabay.com/en/sea-turtle-floral-flowers-2952470/

    private ComboBox<Integer> backgroundColorBox;
    private ComboBox<Integer> penColorBox;
    private ComboBox<Integer> turtleImageBox;

    private DrawerObservable drawerObservable;
    private List<TheDrawerTurtleComponent> turtlesOnScreen;
    private List<TurtleObservable> linkedTurtles;
    private TheDrawerBackgroundComponent backgroundComponent;

    private TheParserActionDelegate parserActionDelegate;

    private StackPane drawingScreen;
    private Canvas linesLayer;
    private Canvas backgroundLayer;

    public TheDrawer(){
        super();
        linkedTurtles = new ArrayList<>();
        turtlesOnScreen = new ArrayList<>();
    }

    public void setDrawerObservable(DrawerObservable drawerObservable){
        this.drawerObservable = drawerObservable;
        this.update();
    }

    public void setTheParserActionDelegate(TheParserActionDelegate theParserActionDelegate){
        this.parserActionDelegate = theParserActionDelegate;
    }

    @Override
    protected void mapUserActions() {
        backgroundColorBox.valueProperty().addListener((observable, oldValue, newValue) -> changeBackgroundColor());
        turtleImageBox.valueProperty().addListener((observable, oldValue, newValue) -> changeTurtleImage());
        penColorBox.valueProperty().addListener((observable, oldValue, newValue) -> changePenColor());
    }

    public void generateGUIComponent(){
        BorderPane borderPane = super.getBorderPane();
        borderPane.setPadding(new Insets(VERTICAL_INSET, HORIZONTAL_INSET, VERTICAL_INSET, HORIZONTAL_INSET));
        generateListOptions(borderPane);
        generateDrawingScreen(borderPane);
    }

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

    private void generateListOptions(BorderPane borderPane){
        HBox hbox = new HBox();
        backgroundColorBox = ComboBoxFactory.generateIntegerComboBox(PaletteConstants.COLORS.length);
        penColorBox = ComboBoxFactory.generateIntegerComboBox(PaletteConstants.COLORS.length);
        turtleImageBox = ComboBoxFactory.generateIntegerComboBox(PaletteConstants.TURTLE_IMAGES.length);
        hbox.getChildren().addAll(backgroundColorBox,penColorBox,turtleImageBox);
        borderPane.setBottom(hbox);
    }

    private void changeBackgroundColor(){
        parserActionDelegate.performParserAction(parser -> parser.makeTree(parser.parseActionCommand(ResourceBundleManager.retrieveOnScreenCommand("SET_BG") + backgroundColorBox.getValue())));
    }

    private void changePenColor(){
        parserActionDelegate.performParserAction(parser -> parser.makeTree(parser.parseActionCommand(ResourceBundleManager.retrieveOnScreenCommand("SET_PC") + penColorBox.getValue())));

    }

    private void changeTurtleImage(){
        parserActionDelegate.performParserAction(parser -> parser.makeTree(parser.parseActionCommand(ResourceBundleManager.retrieveOnScreenCommand("SET_SH") + turtleImageBox.getValue())));

    }

    private void addFrontEndTurtle(TurtleObservable turtleObservable){
        linkedTurtles.add(turtleObservable);
        turtlesOnScreen.add(new TheDrawerTurtleComponent(turtleObservable, drawingScreen, linesLayer));
    }

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

    @Override
    public void notifyOfChanges() {
        update();
    }
}
