package nodes;

/**
 * This error is thrown when an operation is expecting a certain type of node but
 * instead sees a different type of node which would make the operation unable to be run.
 * Example: dotimes is passed a constant instead of a list, throwing an ImproperNodeException
 *
 * author: Charlie Dracos
 */
public class ImproperNodeException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public ImproperNodeException() {
        super("Error: Incorrect node arguments");
    }
}