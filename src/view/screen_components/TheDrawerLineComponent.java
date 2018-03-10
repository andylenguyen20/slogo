package view.screen_components;


import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Line;

import java.util.List;

/**
 * This class is used to draw lines onto the second canvas of the Drawer's StackPane. It takes in the GraphicsContext
 * of a pre-existing Canvas.
 * @author Andy Nguyen
 * @author Edward Zhuang
 */
public class TheDrawerLineComponent {
    private GraphicsContext gc;

    /**
     * Constructor
     * Creates a DrawerLineComponent which can draw lines onto a Canvas
     * @param gc GraphicsContext of Drawer's line Canvas
     */
    public TheDrawerLineComponent(GraphicsContext gc){
        this.gc = gc;
    }

    /**
     * Takes in a list of lines and draws them onto the Canvas of gc
     * @param linesToDraw list of lines to draw with gc
     */
    public void draw(List<Line> linesToDraw){
        for(Line line : linesToDraw){
            gc.setStroke(line.getStroke());
            gc.setLineWidth(line.getStrokeWidth());
            gc.strokeLine(line.getStartX(), line.getStartY(), line.getEndX(), line.getEndY());
        }
    }
}
