package Experiment;

import view.GUI;

public abstract class Controller {
    private GUI gui;
    public Controller(GUI gui){
        this.gui = gui;
    }
    protected abstract void initializeScreenComponents();
    protected abstract void setUpConnections();
    protected abstract void addToGUI();
    protected GUI getGui(){
        return gui;
    }
}
