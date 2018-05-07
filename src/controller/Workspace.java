package controller;

import javafx.stage.Stage;
import model.CommandHistory;
import model.Model;
import model.VariablesHistory;
import parsers.Parser;
import view.GUI;
import view.screen_components.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Andy Nguyen
 * @collaborator Edward Zhuang
 * The purpose of this class is to initialize all of the backend components (essentially parser, commandhistory, variableshistory, and model)
 * and then sets up various controllers that serve the purpose of allowing the view (GUI) components to listen/interact with these backend components
 */
public class Workspace {
    private GUI gui;
    private Parser parser;
    private CommandHistory commandHistory;
    private VariablesHistory variableHistory;
    private Model model;
    private Stage stage;

    private List<Controller> controllerList;

    /**
     * sets up a given workspace
     * @param stage
     */
    public Workspace(Stage stage){
        gui = new GUI();
        this.initializeModelElements();
        this.createControllers();
        this.renderControllers();
        this.stage = stage;
    }

    /**
     * initializes the backend components described in the description above
     */
    private void initializeModelElements(){
        this.model = new Model(Drawer.CANVAS_WIDTH, Drawer.CANVAS_HEIGHT);
        this.commandHistory = new CommandHistory();
        this.variableHistory = new VariablesHistory();
        this.parser = new Parser(model, variableHistory, commandHistory);
    }

    /**
     * Creates all of the controllers in this workspace. The model elements are essentially being connected/listened to by all of the
     * screen components controlled by the controllers in this method. Since model is always only initialized once, it can be passed down/referenced
     * by any component in a workspace and that all happens in this method.
     */
    private void createControllers(){
        controllerList = new ArrayList<>();
        controllerList.add(new ButtonCommandPanelController(gui, parser));
        controllerList.add(new VariableHistoryBoxController(gui, variableHistory));
        controllerList.add(new PaletteController(gui, model));
        controllerList.add(new DrawerController(gui, model,parser));
        controllerList.add(new CustomCommandBoxController(gui, variableHistory, parser));
        controllerList.add(new CommandHistoryBoxController(gui, commandHistory, parser));
        controllerList.add(new CommandBoxController(gui, parser));
        controllerList.add(new ImageChangerController(gui, model));
    }

    /**
     * Renders all of the controllers in this workspace by initializing their screen components, setting up any necessary front to back end connections, and adding
     * the screen elements to the GUI
     */
    private void renderControllers(){
        for(Controller controller : controllerList){
            controller.initializeScreenComponents();
            controller.setUpConnections();
            controller.addToGUI();
        }
    }

    /**
     * Starts up the workspace. This is called after all of the controllers, view components, and model elements have been initialized
     * and rendered. Running this method will allow the application to open up a workspace that the user can interact with (the application, essentially)
     */
    public void startUp(){
        gui.start(stage);
    }
}
