package view.screen_components;

import controller.ActivationDelegate;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import model.TurtleObservable;

/**
 * Class to represent a front end version of a back end turtle. Created through Drawer.
 * @author Andy Nguyen
 * @author Edward Zhuang
 */
public class DrawerTurtleComponent {
    private static final int TURTLE_WIDTH = 50;
    private static final int TURTLE_HEIGHT = 50;
    private final double X_OFFSET;
    private final double Y_OFFSET;
    private ImageView turtleImage;
    private TurtleObservable turtle;
    private StackPane drawingScreen;

    private DrawerLineComponent drawerLineComponent;
    private ActivationDelegate activationDelegate;
    /**
     * Constructor
     * Sets up turtle, drawingScreen, and drawerLineComponent
     * @param a ActivationDelegate which allows the turtle to delegate the responsibility of changing its active status for it
     * @param t TurtleObservable which contains information about how the front end turtle and its lines should
     *               appear
     * @param s StackPane where DrawerTurtleComponent's Imageview is added
     * @param c Canvas where TurtleObservable's lines will be added
     */
    
    public DrawerTurtleComponent(ActivationDelegate a, TurtleObservable t, StackPane s, Canvas c){
        this.turtle = t;
        this.drawingScreen = s;
        this.drawerLineComponent = new DrawerLineComponent(c.getGraphicsContext2D());
        X_OFFSET = c.getWidth()/2;
        Y_OFFSET = c.getHeight()/2;
        turtleImage = new ImageView();
        turtleImage.setFitWidth(TURTLE_WIDTH);
        turtleImage.setFitHeight(TURTLE_HEIGHT);
        this.setTurtleImage();
        drawingScreen.getChildren().add(turtleImage);
        this.activationDelegate = a;
        this.setActivationToggle();
        this.update();
    }

    private void setActivationToggle(){
        turtleImage.setOnMouseClicked(e->activationDelegate.toggleTurtle(turtle.getValue()));
    }

    private void drawLines(){
        drawerLineComponent.draw(turtle.getLines());
    }

    /**
     * Changes image of turtleImage
     */
    private void setTurtleImage(){
        turtleImage.setVisible(turtle.getTurtleShowing());
        turtleImage.setImage(new Image(getClass().getClassLoader().getResourceAsStream(turtle.getTurtleShape())));
        turtleImage.setRotate(-turtle.getDirectionAngle());
        turtleImage.setOpacity(turtle.getOpacity());
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
        this.setTurtleImage();
        this.move();
        this.drawLines();
    }
}
