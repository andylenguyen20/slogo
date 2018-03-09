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

public class Workspace {
    private GUI gui;
    private Parser parser;
    private CommandHistory commandHistory;
    private VariablesHistory variableHistory;
    private Model model;
    private Stage stage;

    private List<tController> controllerList;

    public Workspace(Stage stage){
        gui = new GUI();
        this.initializeModelElements();
        this.createControllers();
        this.initializeControllers();
        this.stage = stage;
    }

    private void initializeModelElements(){
        this.model = new Model(Drawer.CANVAS_WIDTH, Drawer.CANVAS_HEIGHT);
        this.commandHistory = new CommandHistory();
        this.variableHistory = new VariablesHistory();
        this.parser = new Parser(model, variableHistory, commandHistory);
    }

    private void createControllers(){
        controllerList = new ArrayList<>();
        controllerList.add(new tButtonCommandPanelController(gui, parser));
        controllerList.add(new tVariableHistoryBoxController(gui, variableHistory));
        controllerList.add(new tPaletteController(gui, model));
        controllerList.add(new tDrawerController(gui, model));
        controllerList.add(new tCustomCommandBoxController(gui, variableHistory, parser));
        controllerList.add(new tCommandHistoryBoxController(gui, commandHistory, parser));
        controllerList.add(new tCommandBoxController(gui, parser));
    }

    private void initializeControllers(){
        for(tController controller : controllerList){
            controller.initializeScreenComponents();
            controller.setUpConnections();
            controller.addToGUI();
        }
    }

    public void startUp(){
        gui.start(stage);
    }
}
