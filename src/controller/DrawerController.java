package controller;

import model.Model;
import parsers.Parser;
import view.GUI;
import view.screen_components.Drawer;

import java.util.function.Consumer;

/**
 * @author Andy Nguyen
 * The purpose of this class is allow the Front End Drawer component to listen to changes in the backend Model class, which
 * gives it information about turtles. Likewise, it proveds a way for the Drawer to interact with the backend using the implementation
 * of delegate interfaces
 */
public class DrawerController extends Controller implements ParserActionDelegate, ActivationDelegate {
    private Drawer drawer;
    private Model model;
    private Parser parser;

    /**
     * initializes drawer controller
     * @param gui
     * @param model
     * @param parser
     */
    public DrawerController(GUI gui, Model model, Parser parser){
        super(gui);
        this.model = model;
        this.parser = parser;
    }

    /**
     * initializes the drawer with two delegates:
     * first, the ActivationDelegate, which allows the drawer to activate/deactivate the turtles by clicking on the turtles on the screen
     * second, the ParserActionDelegate, which allows the drawer to set background color, image, pencolor in backend
     */
    @Override
    protected void initializeScreenComponents() {
        drawer = new Drawer((ActivationDelegate) this, (ParserActionDelegate) this);
    }

    /**
     * sets up observer, observable relationship between model and the drawer
     */
    @Override
    protected void setUpConnections() {
        drawer.setDrawerObservable(model);
        drawer.notifyOfChanges();
        model.addDrawerObserver(drawer);
    }

    /**
     * adds to GUI
     */
    @Override
    protected void addToGUI() {
        super.getGui().addToScreen(drawer);
    }

    /**
     * allows the Drawer to call set background color, setshape, setpencolor on a given model
     * @param p
     */
    @Override
    public void performParserAction(Consumer<Parser> p) {
        p.accept(parser);
    }

    /**
     * allows the Drawer to toggle the active status of the turtle through user interaction
     * @param ID
     */
    @Override
    public void toggleTurtle(double ID){
        model.changeTurtleActivity(ID);
    }
}
