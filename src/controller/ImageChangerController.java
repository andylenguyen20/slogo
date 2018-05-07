package controller;

import model.Model;
import view.GUI;
import view.screen_components.ImageChanger;

public class ImageChangerController extends Controller {

    private ImageChanger imageChanger;
    private Model model;

    public ImageChangerController(GUI gui, Model model) {
        super(gui);
        this.model = model;
    }

    @Override
    protected void initializeScreenComponents() {
        imageChanger = new ImageChanger(this);
    }

    @Override
    protected void setUpConnections() {
        model.addImageChangerObserver(imageChanger);
        imageChanger.setImageChangerObservable(model);
        model.initializeImageChanger();
    }

    @Override
    protected void addToGUI() {
        super.getGui().addToScreen(imageChanger);
    }

    public void changeTurtleImage(double value, int shapeNumber) {
        model.changeTurtleColor(value, shapeNumber);
    }
}
