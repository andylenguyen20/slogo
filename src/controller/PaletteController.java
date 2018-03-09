package controller;

import controller.Controller;
import model.Model;
import view.GUI;
import view.screen_components.Palette;

public class PaletteController extends Controller {
    private Model model;
    private Palette palette;
    public PaletteController(GUI gui, Model model){
        super(gui);
        this.model = model;
    }

    @Override
    protected void initializeScreenComponents() {
        palette = new Palette();
    }

    @Override
    protected void setUpConnections() {
        palette.setPaletteObservable(model);
        model.addObserver(palette);
        model.initializePalette();
    }


    @Override
    protected void addToGUI() {
        super.getGui().addToScreen(palette);
    }
}
