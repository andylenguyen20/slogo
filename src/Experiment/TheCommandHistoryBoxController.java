package Experiment;

import model.CommandHistory;
import parsers.Parser;
import view.GUI;
import view.screen_components.CommandHistoryBox;

import java.util.function.Consumer;

public class TheCommandHistoryBoxController extends TheController implements TheParserActionDelegate, TheClearValueDelegate {
    private CommandHistoryBox commandHistoryBox;
    private CommandHistory commandHistory;
    private Parser parser;
    public TheCommandHistoryBoxController(GUI gui, CommandHistory commandHistory, Parser parser){
        super(gui);
        this.commandHistory = commandHistory;
        this.parser = parser;
    }

    @Override
    protected void initializeScreenComponents() {
        commandHistoryBox = new CommandHistoryBox();
    }

    @Override
    protected void setUpConnections() {
        commandHistoryBox.setTheParserActionDelegate(this);
        commandHistoryBox.setTheClearValueDelegate(this);
        commandHistoryBox.setCommandHistory(commandHistory);
        commandHistory.addObserver(commandHistoryBox);
    }

    @Override
    protected void addToGUI() {
        super.getGui().addToScreen(commandHistoryBox);
    }

    @Override
    public void performParserAction(Consumer<Parser> p) {
        p.accept(parser);
    }

    @Override
    public void clear(){
        commandHistory.clearHistory();
    }
}
