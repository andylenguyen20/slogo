package commandNode;

import java.util.List;

import model.Model;
import nodes.GeneralCommand;
import nodes.NodeInterface;

/**
 * @author Belanie Nagiel
 * 
 * NaturalLog class that creates a new extension of Node and sets the functionality for the evaluate method implemented from
 * the CommandInterface interface.
 *
 */
public class NaturalLog extends GeneralCommand {

	/**
	 * Class Constructor
	 * 
	 * @param model
	 * @param numChildren
	 */
	public NaturalLog(Model model, int numChildren) {
		super(model, numChildren);
		// TODO Auto-generated constructor stub
	}

	@Override
	/**
	 * returns natural log of expr
	 * 
	 * @return ln(expr)
	 */
	public double evaluate(List<NodeInterface> arguments) {
		value = Math.log(arguments.get(0).getValue());
		return value;
	}

}
