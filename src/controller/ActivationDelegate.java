package controller;

/**
 * @author Andy Nguyen
 * The purpose of this interface is to allow some component within the view to change the active status of a turtle.
 * It allows the view (in this case the DrawerTurtleComponent) to delegate the action of changing a turtles status
 * to another object that implements these methods. The class that implements this is the DrawerController.
 */
public interface ActivationDelegate {
    /**
     * Toggles the (turtle with the given ID)'s active status
     * @param ID
     */
    void toggleTurtle(double ID);
}
