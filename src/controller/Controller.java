package controller;

import view.GUI;

/**
 * @author Andy Nguyen
 * The purpose of this class is to set up an abstraction as to which the Model-View-Controller can be implemented.
 * The Controller class is an abstract class that connects the View to the Model in some way, whether it be through user interaction
 * or the Model changing state.
 */
public abstract class Controller {
    private GUI gui;

    /**
     * Creates a new instance of the Controller, which takes in a gui that the controller will add its screen components (View components) onto
     * @param gui
     */
    public Controller(GUI gui){
        this.gui = gui;
    }

    /**
     * Initializes all screen components in the controller. This method is meant to be implemented depending on what type of controller
     * subclass it is. For instance, a CommandHistoryBoxController would initialize the CommandHistoryBox screen component here, while
     * a DrawerController would initialize the Drawer component here
     */
    protected abstract void initializeScreenComponents();

    /**
     * Sets up the connections between the ScreenComponents (View Components) and their associated backend components that they want
     * to listen to. In other words, this method should set up the observer, observable relationships and the implementation depends on
     * what type of Controller this is
     */
    protected abstract void setUpConnections();

    /**
     * Adds all of the screen components to the GUI
     */
    protected abstract void addToGUI();

    /**
     *
     * @return gui
     */
    protected GUI getGui(){
        return gui;
    }
}
