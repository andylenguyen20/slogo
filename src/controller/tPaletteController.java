package controller;

import javafx.stage.Stage;
import model.Model;
import view.GUI;
import view.screen_components.Palette;

public class tPaletteController extends tController{
    private Model model;
    private Palette palette;
    public tPaletteController(GUI gui, Model model){
        super(gui);
        this.model = model;
    }

    @Override
    protected void initializeScreenComponents() {
        palette = new Palette();
    }

    @Override
    protected void setUpConnections() {
        palette.setColorIndex(model);
        model.addObserver(palette);
    }

    @Override
    protected void addToGUI() {
        super.getGui().addToScreen(palette);
    }
}
