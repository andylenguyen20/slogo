package commandNode;

import model.Model;
import java.util.List;

import nodes.GeneralCommand;
import nodes.NodeInterface;
/**
 * @author Belanie Nagiel
 * 
 * And class that creates a new extension of Node and sets the functionality for the evaluate method implemented from
 * the CommandInterface interface.
 *
 */
public class And extends GeneralCommand {

	/**
	 * Class Constructor
	 * 
	 * @param model
	 * @param numChildren
	 */
	public And(Model model, int numChildren) {
		super(model, numChildren);
	}

	@Override
	/**
	 * returns 1 if test1 and test2 are non-zero, otherwise 0
	 * 
	 * @return are the two arguments non-zero
	 */
	public double evaluate(List<NodeInterface> arguments) {
		if(arguments.get(0).getValue() != 0 && arguments.get(1).getValue() != 0)
		{
			value = 1;
			return value;
		}
		else
		{
			value = 0;
			return value;
		}
	}
}
