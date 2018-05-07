package controller;

import model.Model;
import view.GUI;
import view.screen_components.TurtleImageBox;

public class TurtleImageBoxController extends Controller implements ValueModifierDelegate{
	private Model model;
	private TurtleImageBox turtleImageBox;
	public TurtleImageBoxController(GUI gui, Model model) {
		super(gui);
		this.model = model;
	}

	@Override
	protected void initializeScreenComponents() {
		turtleImageBox = new TurtleImageBox(this);
	}

	@Override
	protected void setUpConnections() {
		turtleImageBox.setDrawerObservable(model);
		model.addDrawerObserver(turtleImageBox);
		turtleImageBox.notifyOfChanges();
	}

	@Override
	protected void addToGUI() {
		super.getGui().addToScreen(turtleImageBox);
	}

	@Override
	public void changeValue(String value, String variableName) {
		model.setTurtleShape(Double.parseDouble(value), Integer.parseInt(variableName));
	}

}
