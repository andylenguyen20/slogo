package controller;

import model.CommandHistory;
import parsers.Parser;
import view.GUI;
import view.screen_components.CommandHistoryBox;

import java.util.function.Consumer;

/**
 * @author Andy Nguyen
 * The purpose of this class is to provide a way for the command history box to listen to changes in the command history so that
 * it can make these changes in the front end. It also provides a way for the command history box to interact/modify things in the backend
 * as a result of user interaction.
 */
public class CommandHistoryBoxController extends Controller implements ParserActionDelegate, ClearValueDelegate {
    private CommandHistoryBox commandHistoryBox;
    private CommandHistory commandHistory;
    private Parser parser;

    /**
     * initializes a new instance, which contains a given set of backend components which the CommandHistoryBox is associated with
     * @param gui
     * @param commandHistory
     * @param parser
     */
    public CommandHistoryBoxController(GUI gui, CommandHistory commandHistory, Parser parser){
        super(gui);
        this.commandHistory = commandHistory;
        this.parser = parser;
    }

    /**
     * initializes a new instance of the command history box, which takes in a ParserActionDelegate (allows command history box to
     * pass a previous command to parser) and a ClearValueDelegate (allows command history box to clear the command history in the backend)
     */
    @Override
    protected void initializeScreenComponents() {
        commandHistoryBox = new CommandHistoryBox((ParserActionDelegate) this, (ClearValueDelegate) this);
    }

    /**
     * sets the observer/observable relationship with command history backend and command history box front end
     */
    @Override
    protected void setUpConnections() {
        commandHistoryBox.setCommandHistory(commandHistory);
        commandHistory.addObserver(commandHistoryBox);
    }

    /**
     * adds the command history box to the GUI
     */
    @Override
    protected void addToGUI() {
        super.getGui().addToScreen(commandHistoryBox);
    }

    /**
     * performs a given parser command passed through on a lambda
     * @param p
     */
    @Override
    public void performParserAction(Consumer<Parser> p) {
        p.accept(parser);
    }

    /**
     * allows the commannd history box to clear the command history in the backend
     */
    @Override
    public void clear(){
        commandHistory.clearHistory();
    }
}
