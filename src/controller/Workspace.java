package controller;

import controller.*;
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

    private List<Controller> controllerList;

    public Workspace(Stage stage){
        gui = new GUI();
        this.initializeModelElements();
        this.initializeControllers();
        this.renderControllers();
        this.stage = stage;
    }

    private void initializeModelElements(){
        this.model = new Model(Drawer.CANVAS_WIDTH, Drawer.CANVAS_HEIGHT);
        this.commandHistory = new CommandHistory();
        this.variableHistory = new VariablesHistory();
        this.parser = new Parser(model, variableHistory, commandHistory);
    }

    private void initializeControllers(){
        controllerList = new ArrayList<>();
        controllerList.add(new ButtonCommandPanelController(gui, parser));
        controllerList.add(new VariableHistoryBoxController(gui, variableHistory));
        controllerList.add(new PaletteController(gui, model));
        controllerList.add(new DrawerController(gui, model,parser));
        controllerList.add(new CustomCommandBoxController(gui, variableHistory, parser));
        controllerList.add(new CommandHistoryBoxController(gui, commandHistory, parser));
        controllerList.add(new CommandBoxController(gui, parser));
    }

    private void renderControllers(){
        for(Controller controller : controllerList){
            controller.initializeScreenComponents();
            controller.setUpConnections();
            controller.addToGUI();
        }
    }

    public void startUp(){
        gui.start(stage);
    }
}
