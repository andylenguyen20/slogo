package controller;

import parsers.Parser;
import view.GUI;
import view.screen_components.PenControlPanel;
import view.screen_components.TurtleControlPanel;

import java.util.function.Consumer;

/**
 * @author Andy Nguyn
 * The purpose of this class is to provide a way for button controls, such as the penup, pensize, forward, backward, etc. buttons
 * on the GUI to have some way of passing commands to the backend. The ButtonCommandPanelController initializes view components
 * that fit this criteria.
 */
public class ButtonCommandPanelController extends Controller implements ParserActionDelegate {
    private Parser parser;
    private TurtleControlPanel turtleControlPanel;
    private PenControlPanel penControlPanel;

    /**
     * Initializes a new ButtonCommandPanelController, which takes in a given gui to add its view components to, as well
     * as a parser, which is a backend component that the view components are associated with
     * @param gui
     * @param parser
     */
    public ButtonCommandPanelController(GUI gui, Parser parser){
        super(gui);
        this.parser = parser;
    }

    /**
     * initializes the screen components
     */
    @Override
    protected void initializeScreenComponents() {
        turtleControlPanel = new TurtleControlPanel(this);
        penControlPanel = new PenControlPanel(this);
    }

    /**
     * sets up the connections between controller, view, model if needed. Not needed in this case
     */
    @Override
    protected void setUpConnections() {
    }

    /**
     * adds the view components to the GUI
     */
    @Override
    protected void addToGUI() {
        super.getGui().addToScreen(turtleControlPanel);
        super.getGui().addToScreen(penControlPanel);
    }

    /**
     * an implementation of ParserActionDelegate method. It takes in a lambda calling some action on parser, which it then applies to given parser
     * @param p
     */
    @Override
    public void performParserAction(Consumer<Parser> p){
        p.accept(parser);
    }
}
