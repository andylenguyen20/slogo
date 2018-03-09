package view.screen_components;

import controller.DrawerController;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import model.TurtleObservable;
import view.constants.CanvasConstants;
import javafx.scene.input.MouseEvent;
import view.turtle_info.TurtleInformation;

import java.util.List;


public class DrawerCanvas {
    private static final int TRANSLATION_OFFSET_X = 410;
    private static final int TRANSLATION_OFFSET_Y = 250;
    private Canvas canvas;
    private GraphicsContext gc;
    private ImageView turtleIcon;
    private static final int TURTLE_WIDTH = 50;
    private static final int TURTLE_HEIGHT = 50;
    private String storedColor = "White";
    private StackPane stackPane;
    private TurtleObservable turtle;
    private DrawerController controller;

    public DrawerCanvas(BorderPane borderPane, TurtleObservable turtle){
        stackPane = new StackPane();
        this.turtle = turtle;
        canvas = new Canvas(CanvasConstants.CANVAS_WIDTH, CanvasConstants.CANVAS_HEIGHT);
        gc = canvas.getGraphicsContext2D();
        stackPane.getChildren().add(canvas);
        borderPane.setCenter(stackPane);
        setupRectangle(CanvasConstants.DEFAULT_FILL);
        setupTurtleImageView();
    }

    public void setTurtle(TurtleObservable turtle){
        this.turtle = turtle;
    }
    private void setupTurtleImageView(){
        Image turtleIconImage = new Image(getClass().getClassLoader().getResourceAsStream("black_and_white_turtle.PNG"));
        turtleIcon = new ImageView(turtleIconImage);
        turtleIcon.setFitWidth(TURTLE_HEIGHT);
        turtleIcon.setFitHeight(TURTLE_WIDTH);

        turtleIcon.setOnMouseClicked((MouseEvent mouseEvent) -> {
                    TurtleInformation info = new TurtleInformation(turtle);
                    info.showTurtleInformation();
        });
        turtleIcon.setOnMouseReleased((MouseEvent mouseEvent2) -> {
                controller.passCommand(findNewLocation((int) mouseEvent2.getSceneX(), (int) mouseEvent2.getSceneY()));
        });
    }

    private String findNewLocation(int x, int y){
        StringBuilder temp = new StringBuilder();
        temp.append("setxy ");
        temp.append(Integer.toString(x - TRANSLATION_OFFSET_X));
        temp.append(" ");
        temp.append(Integer.toString(TRANSLATION_OFFSET_Y - y));
        return temp.toString();
    }

    private void setupRectangle(Color color){
        gc.setFill(color);
        gc.fillRect(0,0,canvas.getWidth(), canvas.getHeight());
        gc.setStroke(CanvasConstants.DEFAULT_STROKE);
        gc.strokeRect(0,0, canvas.getWidth(),canvas.getHeight());
    }

    public void update(){
        gc.clearRect(0,0, canvas.getWidth(), canvas.getHeight());
        changeBackgroundColor(storedColor);
        drawLines(turtle.getLines());
        moveTurtle();
    }

    private void moveTurtle(){
        stackPane.getChildren().remove(turtleIcon);
        turtleIcon.setTranslateX(turtle.getXCoordinate() - CanvasConstants.CANVAS_WIDTH/2);
        turtleIcon.setTranslateY(turtle.getYCoordinate() - CanvasConstants.CANVAS_HEIGHT/2);
        turtleIcon.setRotate(-turtle.getDirectionAngle());
        stackPane.getChildren().add(turtleIcon);
    }

    private void drawLines(List<Line> lines){
        for (Line line: lines){
            gc.setStroke(line.getStroke());
            gc.strokeLine(line.getStartX(),line.getStartY(),line.getEndX(), line.getEndY());
        }
    }

    public void changeTurtleImage(String imageName){
        Image turtleIconImage = new Image(getClass().getClassLoader().getResourceAsStream(imageName));
        turtleIcon.setImage(turtleIconImage);
    }

    public void changeBackgroundColor(String color){
        storedColor = color;
        setupRectangle(Color.valueOf(color));
    }

    public void setController(DrawerController controller) {
        this.controller = controller;
    }
}
