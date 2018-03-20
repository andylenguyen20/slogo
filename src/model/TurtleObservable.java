package model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import view.Observer;

import java.util.List;

/**
 * @author Andy Nguyen
 * The purpose of this interface is to provide an API that allows a front end component to listen and update itself based off information in this TurtleObservable
 */
public interface TurtleObservable {
    double getXCoordinate();
    double getYCoordinate();
    double getDirectionAngle();
    List<Line> getLines();
    boolean getTurtleShowing();
    String getTurtleShape();
    double getValue();
    double getOpacity();
}
