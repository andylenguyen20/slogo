package model;

import javafx.scene.paint.Color;

import java.util.List;

/**
 * @author Andy Nguyen
 * Allows the Model to give information about things that the Drawer needs in order to update itself
 */
public interface DrawerObservable {
    /**
     *
     * @return the background color of this model
     */
    Color getBackgroundColor();

    /**
     *
     * @return the list of turtle observables in this model
     */
    List<TurtleObservable> getTurtleObservables();
}
