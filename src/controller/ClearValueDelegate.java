package controller;

/**
 * The purpose of this interface is to allow view components to delegate clearing responsibility to the implementor of this method.
 * This ClearValueDelegate generally allows a given view component to clear anything that can't be accessed directly from that same view component.
 * For instance, if a command history box wanted to clear its command history, this interface provides a way for the command history box view
 * component to clear the commands stored in the command history backend.
 */
public interface ClearValueDelegate {
    /**
     * A general clear method, which allows us to clear a given component. In this program, used to clear things in the backend.
     */
    void clear();
}
