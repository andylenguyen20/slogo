package controller;

import model.VariablesHistory;
import parsers.Parser;
import view.GUI;
import view.screen_components.CustomCommandsBox;

import java.util.function.Consumer;

public class CustomCommandBoxController extends Controller implements ParserActionDelegate, ClearValueDelegate {
    private VariablesHistory variablesHistory;
    private CustomCommandsBox customCommandsBox;
    private Parser parser;
    public CustomCommandBoxController(GUI gui, VariablesHistory variablesHistory, Parser parser){
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
        customCommandsBox.setClearValueDelegate(this);
        customCommandsBox.setParserActionDelegate(this);
        customCommandsBox.setCustomCommandObservable(variablesHistory);
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
        variablesHistory.clearCommandHistory();
    }
}
