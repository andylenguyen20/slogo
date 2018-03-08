package controller;

import java.util.ArrayList;
import java.util.List;

import commandNode.Backward;
import commandNode.Forward;
import commandNode.Left;
import commandNode.Right;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.*;
import nodes.Constant;
import nodes.NodeInterface;
import parsers.Parser;
import view.GUI;
import view.screen_components.*;

public class Controller implements CommandBoxController, DrawerController, CommandHistoryBoxController,
						VariableHistoryBoxController, TurtleControlPanelController, CustomCommandController,
						PenPanelController{
	private GUI gui;
	private Turtle turtle;
	private Parser parser;
	private CommandHistory commandHistory;
	private VariablesHistory variableHistory;
	private Drawer drawer;
	private CommandBox commandBox;
	private CommandHistoryBox commandHistoryBox;
	private VariableHistoryBox variableHistoryBox;
	private HelpButton helpButton;
	private TurtleControlPanel turtleControlPanel;
	private CustomCommandsBox customCommandsBox;
	private Model model;
	
	public Controller(Stage stage){
//		this.model = model;
		gui = new GUI();
		gui.start(stage);
		this.initializeScreenComponents();
		this.initializeModelComponents();
		this.setUpConnections();
		this.addToGUI();
	}

	private void initializeModelComponents(){
		//turtle = new Turtle(Drawer.CANVAS_WIDTH, Drawer.CANVAS_HEIGHT, Drawer.INITIAL_PEN_COLOR);
		model = new Model(Drawer.CANVAS_WIDTH, Drawer.CANVAS_HEIGHT);
        commandHistory = new CommandHistory();
        variableHistory = new VariablesHistory();
		parser = new Parser(model, variableHistory, commandHistory);
	}

	private void setUpConnections(){
		for (Turtle turtle: model.getActiveTurtles())
		{
			turtle.addObserver(drawer);
			drawer.setTurtle(turtle);
			drawer.update();
		}
		variableHistoryBox.setVariableHistory(variableHistory);
		commandHistoryBox.setCommandHistory(commandHistory);
		commandHistory.addObserver(commandHistoryBox);
		variableHistoryBox.setVariableHistory(variableHistory);
		variableHistory.addObserver(variableHistoryBox);
		variableHistory.addCustomCommandObserver(customCommandsBox);
		customCommandsBox.setCustomCommandHolder(variableHistory);
	}

	private void initializeScreenComponents(){
		drawer = new Drawer();
		drawer.setController(this);
		commandBox = new CommandBox();
		commandBox.setController(this);
		commandHistoryBox = new CommandHistoryBox();
		commandHistoryBox.setController(this);
		variableHistoryBox = new VariableHistoryBox();
		variableHistoryBox.setController(this);
		helpButton = new HelpButton();
		turtleControlPanel = new TurtleControlPanel();
		turtleControlPanel.setController(this);
		customCommandsBox = new CustomCommandsBox();
		customCommandsBox.setController(this);
	}

	private void addToGUI(){
		gui.addCommandBoxBorderPane(commandBox.getGUIComponent());
		gui.addCommandHistoryBoxBorderPane(commandHistoryBox.getGUIComponent());
		gui.addDrawerBorderPane(drawer.getGUIComponent());
		gui.addVariableHistoryBoxBorderPane(variableHistoryBox.getGUIComponent());
		gui.addHelpButtonBorderPane(helpButton.getGUIComponent());
		gui.addTurtleControlPanelBorderPane(turtleControlPanel.getGUIComponent());
		gui.addCustomCommandsBorderPane(customCommandsBox.getGUIComponent());
	}
	
    @Override
	public void passCommand(String command){
        List<NodeInterface> newTree = parser.parseString(command);
        parser.makeTree(newTree);
    }

	@Override
	public void clearCustomCommands() {

	}

	@Override
	public void changeLanguage(String language) {
		parser.setLanguage(language);
	}

	@Override
    public void clearVariableBox(){
		variableHistory.clearHistory();
    }

	@Override
	public void changeVariableValue(String variableName, String value) {
		System.out.println(variableName);
		variableHistory.changeValue(variableName, value);
	}

	@Override
    public void clearCommandHistoryBox() {
		commandHistory.clearHistory();
    }

	@Override
	public void setPenColor(Color color) {
		//turtle.setPenColor(color);
	}

	@Override
	public void forward(double value){
		List<NodeInterface> nodeList = new ArrayList<>();
		nodeList.add(new Forward(model, 1));
		nodeList.add(new Constant(value));
		parser.makeTree(nodeList);
	}

	@Override
	public void backward(double value){
		List<NodeInterface> nodeList = new ArrayList<>();
		nodeList.add(new Backward(model, 1));
		nodeList.add(new Constant(value));
		parser.makeTree(nodeList);
	}

	@Override
	public void rightTurn(double value){
		List<NodeInterface> nodeList = new ArrayList<>();
		nodeList.add(new Right(model, 1));
		nodeList.add(new Constant(value));
		parser.makeTree(nodeList);
	}

	@Override
	public void leftTurn(double value){
		List<NodeInterface> nodeList = new ArrayList<>();
		nodeList.add(new Left(model, 1));
		nodeList.add(new Constant(value));
		parser.makeTree(nodeList);
	}

	public void toggleActive(int ID) {
		//TODO: find a turtle with specific ID in backend, make it active/inactive
	}
}
