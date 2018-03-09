package view.screen_components;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class DrawerBackgroundComponent {
    private static final Color BORDER_COLOR = Color.BLACK;
    private Canvas canvas;
    private GraphicsContext gc;
    public DrawerBackgroundComponent(Canvas canvas){
        this.canvas = canvas;
        this.gc = canvas.getGraphicsContext2D();
    }

    public void changeBackgroundColor(Color color){
        gc.clearRect(0,0,canvas.getWidth(), canvas.getHeight());
        gc.setFill(color);
        gc.fillRect(0,0,canvas.getWidth(), canvas.getHeight());
        gc.setStroke(BORDER_COLOR);
        gc.strokeRect(0,0,canvas.getWidth(),canvas.getHeight());
    }
}
