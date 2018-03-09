package commandNode;

import java.util.List;

import model.Model;
import nodes.CommandInterface;
import nodes.GeneralCommand;
import nodes.NodeInterface;

/**
 * @author Belanie Nagiel
 * 
 * Difference class that creates a new extension of Node and sets the functionality for the evaluate method implemented from
 * the CommandInterface interface.
 *
 */
public class Difference extends GeneralCommand implements CommandInterface {

	/**
	 * Class Constructor
	 * 
	 * @param model
	 * @param numChildren
	 */
	public Difference(Model model, int numChildren) {
		super(model, numChildren);
		// TODO Auto-generated constructor stub
	}

	@Override
	/**
	 * returns difference of the values of expr1 and expr2
	 * 
	 * @return expr1 - expr2
	 */
	public double evaluate(List<NodeInterface> arguments) {
		double diff = arguments.get(0).getValue() - arguments.get(1).getValue();
		value = diff;
		return value;
	}
}
