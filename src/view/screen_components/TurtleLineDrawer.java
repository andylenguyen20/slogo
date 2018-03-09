package view.screen_components;


import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Line;

import java.util.List;

public class TurtleLineDrawer {
    private Canvas canvas;
    private GraphicsContext gc;
    public TurtleLineDrawer(Canvas canvas){
        this.canvas = canvas;
        gc = canvas.getGraphicsContext2D();
    }
    public void draw(List<Line> linesToDraw){
        for(Line line : linesToDraw){
            gc.setStroke(line.getStroke());
            gc.setLineWidth(line.getStrokeWidth());
            gc.strokeLine(line.getStartX(), line.getStartY(), line.getEndX(), line.getEndY());
        }
    }
}
