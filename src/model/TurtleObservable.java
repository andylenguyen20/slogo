package model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import view.Observer;

import java.util.List;

public interface TurtleObservable {
    double getXCoordinate();
    double getYCoordinate();
    double getDirectionAngle();
    List<Line> getLines();
    boolean getTurtleShowing();
    String getTurtleShape();
    //boolean getTurtleStatus();
    double getID();
}
