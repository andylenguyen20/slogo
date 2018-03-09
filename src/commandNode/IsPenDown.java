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
 * IsPenDown class that creates a new extension of Node and sets the functionality for the evaluate method implemented from
 * the CommandInterface interface.
 *
 */
public class IsPenDown extends GeneralCommand {

	/**
	 * Class Constructor
	 * 
	 * @param model
	 * @param numChildren
	 */
	public IsPenDown(Model model, int numChildren) {
		super(model, numChildren);
	}

	@Override
	/**
	 * returns 1 if turtle's pen is down, 0 if it is up
	 * 
	 * @return turtle's pen status
	 */
	public double evaluate(List<NodeInterface> arguments) {
		model.update(t -> isPenDown(t));
		return value;
	}

	public void isPenDown (Turtle t) {
		if(t.getPenShowing()) { value = 1; }
		else { value = 0; }
	}

}
