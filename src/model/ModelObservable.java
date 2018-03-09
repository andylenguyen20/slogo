package model;

import javafx.scene.paint.Color;

import java.util.List;
import java.util.Map;

public interface ModelObservable {
    Map<Double, Turtle> getAllTurtles();
}
