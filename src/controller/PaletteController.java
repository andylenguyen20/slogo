package controller;

import model.Model;
import view.GUI;
import view.screen_components.Palette;

/**
 * @author Andy Nguyen
 * This class provides a way for the Palette View component to listen to the model for color information
 */
public class PaletteController extends Controller {
    private Model model;
    private Palette palette;

    /**
     * new instance
     * @param gui
     * @param model
     */
    public PaletteController(GUI gui, Model model){
        super(gui);
        this.model = model;
    }

    @Override
    protected void initializeScreenComponents() {
        palette = new Palette();
    }

    /**
     * sets up observer/observable connections
     */
    @Override
    protected void setUpConnections() {
        palette.setPaletteObservable(model);
        model.addObserver(palette);
        model.initializePalette();
    }

    /**
     * adds palette to the GUI
     */
    @Override
    protected void addToGUI() {
        super.getGui().addToScreen(palette);
    }
}
