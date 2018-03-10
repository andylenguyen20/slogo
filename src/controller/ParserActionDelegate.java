package controller;

import parsers.Parser;

import java.util.function.Consumer;

/**
 * @author Andy Nguyen
 * The purpose of this delegate interface is to allow a given view component to delegate the act of calling a command on the Parser class
 * to the class that implements this interface. For instance, a user might need to click buttons on the screen to move the turtle forward
 * or make it turn left. This interface allows these view components to delegate that responsibility to the controller that implements this
 */
public interface ParserActionDelegate {
    /**
     * Performs a given lambda action on a parser object. This allows us to generalize the action that a given view component
     * might want to act on parser.
     * @param p
     */
    void performParserAction(Consumer<Parser> p);
}
