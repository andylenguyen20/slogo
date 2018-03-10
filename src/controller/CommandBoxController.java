package controller;

import parsers.Parser;
import view.GUI;
import view.screen_components.CommandBox;

import java.util.function.Consumer;

/**
 * @author Andy Nguyen
 * The purpose of this class is to allow the command box to simply pass commands from the front end (typed) to the backend (parser).
 * It also adds the command box to the GUI.
 */
public class CommandBoxController extends Controller implements ParserActionDelegate {
    private Parser parser;
    private CommandBox commandBox;

    /**
     * initializes a commandBoxController, which takes in a gui that the command box will be added to, as well as the parser that it
     * needs to pass information to
     * @param gui
     * @param parser
     */
    public CommandBoxController(GUI gui, Parser parser){
        super(gui);
        this.parser = parser;
    }

    /**
     *
     */
    @Override
    protected void initializeScreenComponents() {
        commandBox = new CommandBox(this);
    }

    @Override
    protected void setUpConnections() {
        //no connections to set up
    }

    /**
     * adds the command box to the GUI
     */
    @Override
    protected void addToGUI() {
        super.getGui().addToScreen(commandBox);
    }

    /**
     * allows the command box to call a parser action on the parser using a lambda function that is passed in
     * @param p
     */
    @Override
    public void performParserAction(Consumer<Parser> p) {
        p.accept(parser);
    }
}
