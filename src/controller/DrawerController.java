package controller;

import model.Model;
import parsers.Parser;
import view.GUI;
import view.screen_components.Drawer;

import java.util.function.Consumer;

public class DrawerController extends Controller implements ParserActionDelegate {
    private Drawer drawer;
    private Model model;
    private Parser parser;
    public DrawerController(GUI gui, Model model, Parser parser){
        super(gui);
        this.model = model;
        this.parser = parser;
    }

    @Override
    protected void initializeScreenComponents() {
        drawer = new Drawer();
    }

    @Override
    protected void setUpConnections() {
        drawer.setDrawerObservable(model);
        drawer.setTheParserActionDelegate(this);
        drawer.notifyOfChanges();
        model.addDrawerObserver(drawer);
    }


    @Override
    protected void addToGUI() {
        super.getGui().addToScreen(drawer);
    }


    @Override
    public void performParserAction(Consumer<Parser> p) {
        p.accept(parser);
    }
}
