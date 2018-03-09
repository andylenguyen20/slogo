package controller;

import javafx.scene.paint.Color;
import model.Model;
import view.GUI;
import view.screen_components.Drawer;

public class tDrawerController extends tController implements PaletteDelegate {
    private Drawer drawer;
    private Model model;
    public tDrawerController(GUI gui, Model model){
        super(gui);
        this.model = model;
    }

    @Override
    protected void initializeScreenComponents() {
        drawer = new Drawer();
    }

    @Override
    protected void setUpConnections() {
        drawer.setPaletteDelegate(this);
        drawer.setModelObservable(model);
        model.addObserver(drawer);
    }

    @Override
    protected void addToGUI() {
        super.getGui().addToScreen(drawer);
    }

    @Override
    public void changeBackgroundColor(Color color) {
        //TODO:fix
        model.setBackgroundColor(0);
    }

    @Override
    public void changePenColor(Color color) {
        model.update((t)->t.setPenColor(color));
    }

    @Override
    public void changeTurtleImage(String image) {
        model.update((t)->t.setTurtleShape(image));
    }
}
