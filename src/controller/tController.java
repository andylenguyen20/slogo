package controller;

import view.GUI;

public abstract class tController {
    private GUI gui;
    public tController(GUI gui){
        this.gui = gui;
    }
    protected abstract void initializeScreenComponents();
    protected abstract void setUpConnections();
    protected abstract void addToGUI();
    protected GUI getGui(){
        return gui;
    }
}
