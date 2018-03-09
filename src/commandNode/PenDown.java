package commandNode;

import java.util.List;

import model.Model;
import model.Turtle;
import nodes.GeneralCommand;
import nodes.NodeInterface;

/**
 * @author Belanie Nagiel
 * 
 * PenDown class that creates a new extension of Node and sets the functionality for the evaluate method implemented from
 * the CommandInterface interface.
 *
 */
public class PenDown extends GeneralCommand {

	/**
	 * Class Constructor
	 * 
	 * @param model
	 * @param numChildren
	 */
	public PenDown(Model model, int numChildren) {
		super(model, numChildren);
	}

	@Override
	/**
	 * puts pen down such that when the turtle moves, it leaves a trail
	 * 
	 * @return 1
	 */
	public double evaluate(List<NodeInterface> arguments)
	{
		model.update(t -> penDown(t));
		value = 1;
		return value;
	}

	public void penDown (Turtle t) {
		t.setPenShowing(true);
	}
}
