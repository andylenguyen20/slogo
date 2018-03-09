package commandNode;

import java.util.List;

import model.Model;
import model.Turtle;
import nodes.GeneralCommand;
import nodes.NodeInterface;

/**
 * @author Belanie Nagiel
 * 
 * HideTurtle class that creates a new extension of Node and sets the functionality for the evaluate method implemented from
 * the CommandInterface interface.
 *
 */
public class HideTurtle extends GeneralCommand {

	/**
	 * Class Constructor
	 * 
	 * @param model
	 * @param numChildren
	 */
	public HideTurtle(Model model, int numChildren) {
		super(model, numChildren);
	}

	@Override
	/**
	 * makes turtle invisible
	 * 
	 * @return 0
	 */
	public double evaluate(List<NodeInterface> arguments) {
		model.update(t -> hideTurtle(t));
		value = 0;
		return value;
	}

	public void hideTurtle (Turtle t) {
		t.setTurtleShowing(false);
	}
}
