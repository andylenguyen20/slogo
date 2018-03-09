package controller;

import model.VariablesHistory;
import parsers.Parser;
import view.GUI;
import view.screen_components.CustomCommandsBox;

import java.util.function.Consumer;

public class tCustomCommandBoxController extends tController implements ParserActionDelegate, ClearValueDelegate {
    private VariablesHistory variablesHistory;
    private CustomCommandsBox customCommandsBox;
    private Parser parser;
    public tCustomCommandBoxController(GUI gui, VariablesHistory variablesHistory, Parser parser){
        super(gui);
        this.variablesHistory = variablesHistory;
        this.parser = parser;
    }

    @Override
    protected void initializeScreenComponents() {
        customCommandsBox = new CustomCommandsBox();
    }

    @Override
    protected void setUpConnections() {
        customCommandsBox.setController(this);
        customCommandsBox.setCustomCommandHolder(variablesHistory);
        variablesHistory.addCustomCommandObserver(customCommandsBox);
    }

    @Override
    protected void addToGUI() {
        super.getGui().addToScreen(customCommandsBox);
    }


    @Override
    public void performParserAction(Consumer<Parser> p) {
        p.accept(parser);
    }

    @Override
    public void clear() {
        variablesHistory.clearHistory();
    }
}
