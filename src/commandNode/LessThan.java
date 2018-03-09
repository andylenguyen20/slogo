package commandNode;

import java.util.List;

import model.Model;
import nodes.GeneralCommand;
import nodes.NodeInterface;

/**
 * @author Belanie Nagiel
 * 
 * LessThan class that creates a new extension of Node and sets the functionality for the evaluate method implemented from
 * the CommandInterface interface.
 *
 */
public class LessThan extends GeneralCommand  {

	/**
	 * Class Constructor
	 * 
	 * @param model
	 * @param numChildren
	 */
	public LessThan(Model model, int numChildren) {
		super(model, numChildren);
	}

	@Override
	/**
	 * returns 1 if the value of expr1 is strictly less than the value of expr2, otherwise 0
	 * 
	 * @return is expr1 < expr2
	 */
	public double evaluate(List<NodeInterface> arguments) {
		if(arguments.get(0).getValue() < arguments.get(1).getValue())
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
