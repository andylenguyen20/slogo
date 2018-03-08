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
 * Equal class that creates a new extension of Node and sets the functionality for the evaluate method implemented from
 * the CommandInterface interface.
 *
 */
public class Equal extends GeneralCommand {

	/**
	 * Class Constructor
	 * 
	 * @param model
	 * @param numChildren
	 */
	public Equal(Model model, int numChildren) {
		super(model, numChildren);
	}

	@Override
	/**
	 * returns 1 if the value of expr1 and the value of expr2 are equal, otherwise 0
	 * 
	 * @return expr1 == expr2
	 */
	public double evaluate(List<NodeInterface> arguments) {
		if(arguments.get(0).getValue() == arguments.get(1).getValue())
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
