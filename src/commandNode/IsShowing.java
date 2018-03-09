package commandNode;

import java.util.List;

import model.Model;
import model.Turtle;
import nodes.CommandInterface;
import nodes.GeneralCommand;
import nodes.NodeInterface;

/**
 * @author Belanie Nagiel
 * 
 * IsShowing class that creates a new extension of Node and sets the functionality for the evaluate method implemented from
 * the CommandInterface interface.
 *
 */
public class IsShowing extends GeneralCommand {

	/**
	 * Class Constructor
	 * 
	 * @param model
	 * @param numChildren
	 */
	public IsShowing(Model model, int numChildren) {
		super(model, numChildren);
	}

	@Override
	/**
	 * returns 1 if turtle is showing, 0 if it is hiding
	 * 
	 * @return turtle's visibility status
	 */
	public double evaluate(List<NodeInterface> arguments) {
		model.update(t -> isShowing(t));
		return value;
	}

	public void isShowing (Turtle t) {
		if(t.getTurtleShowing()) { value = 1; }
		else { value = 0; }
	}

}
