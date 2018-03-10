package controller;

import model.VariablesHistory;
import parsers.Parser;
import view.GUI;
import view.screen_components.CustomCommandsBox;

import java.util.function.Consumer;

/**
 * @author Andy Nguyen
 * The purpose of this class is to allow the CustomCommandBox to pass commands to the parser, as well as clear the history. In addition,
 * the purpose of this class is to set up the Observer/observable relationships between the CustomCommand front end with the backend, which
 * in this case is the: Parser, as well as the VariablesHistory (which contains the custom commands)
 */
public class CustomCommandBoxController extends Controller implements ParserActionDelegate, ClearValueDelegate {
    private VariablesHistory customCommandsHistory;
    private CustomCommandsBox customCommandsBox;
    private Parser parser;

    /**
     * initializes a new instance of this controller
     * @param gui
     * @param customCommandsHistory
     * @param parser
     */
    public CustomCommandBoxController(GUI gui, VariablesHistory customCommandsHistory, Parser parser){
        super(gui);
        this.customCommandsHistory = customCommandsHistory;
        this.parser = parser;
    }

    @Override
    protected void initializeScreenComponents() {
        customCommandsBox = new CustomCommandsBox((ParserActionDelegate)this, (ClearValueDelegate) this);
    }

    @Override
    protected void setUpConnections() {
        customCommandsBox.setCustomCommandObservable(customCommandsHistory);
        customCommandsHistory.addCustomCommandObserver(customCommandsBox);
    }

    @Override
    protected void addToGUI() {
        super.getGui().addToScreen(customCommandsBox);
    }

    /**
     * performs a given parser action passed from the custom command box controller
     * @param p
     */
    @Override
    public void performParserAction(Consumer<Parser> p) {
        p.accept(parser);
    }

    /**
     * clears the command history in the backend. This method is called whenever the custom command box controller wants to clear the history,
     * which happens based off of a user action
     */
    @Override
    public void clear() {
        customCommandsHistory.clearCommandHistory();
    }
}
