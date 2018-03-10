package parsers;

/**
 * @author Belanie Nagiel
 *
 * Creates a new exception that is passed to the command history when there is an error parsing the command.
 */
public class InvalidEntryException extends RuntimeException{
	// Parameterless Constructor
    public InvalidEntryException() {}

    // Constructor that accepts a message
    public InvalidEntryException(String message)
    {
       super(message);
    }

}
