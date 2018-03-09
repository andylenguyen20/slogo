package commandNode;

import java.util.List;

import model.Model;
import nodes.GeneralCommand;
import nodes.NodeInterface;

public class GreaterThan extends GeneralCommand {

	public GreaterThan(Model model, int numChildren) {
		super(model, numChildren);
	}

	@Override
	/**
	 * returns 1 if the value of expr1 is strictly greater than the value of expr2, otherwise 0
	 * 
	 * @return is expr1 > expr2
	 */
	public double evaluate(List<NodeInterface> arguments) {
		if(arguments.get(0).getValue() > arguments.get(1).getValue())
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
