package view.screen_components;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import model.TurtleObservable;

public class DrawerTurtleComponent {
    private static final int TURTLE_WIDTH = 50;
    private static final int TURTLE_HEIGHT = 50;
    private final double X_OFFSET;
    private final double Y_OFFSET;
    private ImageView turtleImage;
    private TurtleObservable turtle;
    private StackPane drawingScreen;
    private TurtleLineDrawer turtleLineDrawer;
    public DrawerTurtleComponent(TurtleObservable turtle, StackPane drawingScreen, Canvas lineCanvas){
        this.turtle = turtle;
        this.drawingScreen = drawingScreen;
        this.turtleLineDrawer = new TurtleLineDrawer(lineCanvas);
        X_OFFSET = lineCanvas.getWidth()/2;
        Y_OFFSET = lineCanvas.getHeight()/2;
        turtleImage = new ImageView();
        turtleImage.setFitWidth(TURTLE_WIDTH);
        turtleImage.setFitHeight(TURTLE_HEIGHT);
        this.setTurtleImage();
        drawingScreen.getChildren().add(turtleImage);
        this.update();
    }

    private void drawLines(){
        turtleLineDrawer.draw(turtle.getLines());
    }

    private void setTurtleImage(){
        turtleImage.setImage(new Image(getClass().getClassLoader().getResourceAsStream(turtle.getTurtleShape())));
    }

    private void move(){
        drawingScreen.getChildren().remove(turtleImage);
        turtleImage.setTranslateX(turtle.getXCoordinate() - X_OFFSET);
        turtleImage.setTranslateY(turtle.getYCoordinate() - Y_OFFSET);
        drawingScreen.getChildren().add(turtleImage);
    }

    public void update(){
        if(!turtle.getTurtleShowing()) turtleImage.setVisible(false);
        this.setTurtleImage();
        turtleImage.setRotate(-turtle.getDirectionAngle());
        this.move();
        this.drawLines();
    }
}
