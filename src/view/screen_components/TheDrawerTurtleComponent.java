package view.screen_components;

import controller.DeactivationDelegate;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.layout.StackPane;
import model.TurtleObservable;
import view.Observer;

/**
 * Class to represent a front end version of a back end turtle. Created through Drawer.
 * @author Andy Nguyen
 * @author Edward Zhuang
 */
public class TheDrawerTurtleComponent {

    private static final int TURTLE_WIDTH = 50;
    private static final int TURTLE_HEIGHT = 50;
    private final double X_OFFSET;
    private final double Y_OFFSET;
    private ImageView turtleImage;
    private TurtleObservable turtle;
    private StackPane drawingScreen;

    private TheDrawerLineComponent theDrawerLineComponent;
    private DeactivationDelegate deactivationDelegate;
    private TurtleInformationPanel informationPanel;

    /**
     * Constructor
     * Sets up turtle, drawingScreen, and drawerLineComponent
     * @param turtle TurtleObservable which contains information about how the front end turtle and its lines should
     *               appear
     * @param drawingScreen StackPane where DrawerTurtleComponent's Imageview is added
     * @param lineCanvas Canvas where TurtleObservable's lines will be added
     */
    
    public TheDrawerTurtleComponent(TurtleObservable turtle, StackPane drawingScreen, Canvas lineCanvas){
        this.turtle = turtle;
        this.drawingScreen = drawingScreen;
        this.theDrawerLineComponent = new TheDrawerLineComponent(lineCanvas.getGraphicsContext2D());
        X_OFFSET = lineCanvas.getWidth()/2;
        Y_OFFSET = lineCanvas.getHeight()/2;
        turtleImage = new ImageView();
        turtleImage.setFitWidth(TURTLE_WIDTH);
        turtleImage.setFitHeight(TURTLE_HEIGHT);
        this.setTurtleImage();
        drawingScreen.getChildren().add(turtleImage);
        this.update();
    }

    /**
     * Draws lines of turtle onto line Canvas
     */
    public void setDeactivationDelegate(DeactivationDelegate deactivationDelegate){
        this.deactivationDelegate = deactivationDelegate;
        System.out.println(deactivationDelegate == null);
        turtleImage.setOnMouseClicked((e)->deactivationDelegate.deactivate(turtle.getID()));
        this.setClickable();
    }

    private void setClickable(){
        informationPanel = new TurtleInformationPanel(turtle);
        turtleImage.setOnMouseEntered(event -> informationPanel.renderInformation());
    }

    private void drawLines(){
        theDrawerLineComponent.draw(turtle.getLines());
    }

    /**
     * Changes image of turtleImage
     */
    private void setTurtleImage(){
        turtleImage.setImage(new Image(getClass().getClassLoader().getResourceAsStream(turtle.getTurtleShape())));
    }

    /**
     * Moves position of turtleImage on the drawingScreen
     */
    private void move(){
        drawingScreen.getChildren().remove(turtleImage);
        turtleImage.setTranslateX(turtle.getXCoordinate() - X_OFFSET);
        turtleImage.setTranslateY(turtle.getYCoordinate() - Y_OFFSET);
        drawingScreen.getChildren().add(turtleImage);
    }

    /**
     * Updates turtleImage by checking it's visibility, orientation, and position
     */
    public void update(){
        turtleImage.setVisible(turtle.getTurtleShowing());
        this.setTurtleImage();
        turtleImage.setRotate(-turtle.getDirectionAngle());
        this.move();
        this.drawLines();
    }
}
