package view.screen_components;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * This class is used to create the bottom layer of the overall drawer portion of the Slogo program. It is responsible
 * for managing the background color of the Drawer's bottom StackPane Canvas.
 * @author Andy Nguyen
 * @author Edward Zhuang
 */
public class DrawerBackgroundComponent {

    private static final Color BORDER_COLOR = Color.BLACK;
    private Canvas canvas;
    private GraphicsContext gc;

    /**
     * Constructor
     * Initializes Canvas and GraphicsContext
     * @param canvas canvas which will hold background color
     */
    public DrawerBackgroundComponent(Canvas canvas){
        this.canvas = canvas;
        this.gc = canvas.getGraphicsContext2D();
    }

    /**
     * Changes background color of stored canvas
     * @param color desired background color
     */
    public void changeBackgroundColor(Color color){
        gc.clearRect(0,0,canvas.getWidth(), canvas.getHeight());
        gc.setFill(color);
        gc.fillRect(0,0,canvas.getWidth(), canvas.getHeight());
        gc.setStroke(BORDER_COLOR);
        gc.strokeRect(0,0,canvas.getWidth(),canvas.getHeight());
    }
}
