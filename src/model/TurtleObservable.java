package model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

import java.util.List;

public interface TurtleObservable {
    public double getXCoordinate();
    public double getYCoordinate();
    public double getDirectionAngle();
    public boolean getPenShowing();
    public List<Line> getLines();
    public boolean getTurtleShowing();
    public void setPenColor(Color color);
}
