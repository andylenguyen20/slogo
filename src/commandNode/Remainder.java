package commandNode;

import java.util.List;

import model.Model;
import nodes.GeneralCommand;
import nodes.NodeInterface;

public class Remainder extends GeneralCommand  {

	public Remainder(Model model, int numChildren) {
		super(model, numChildren);
		// TODO Auto-generated constructor stub
	}

	@Override
	/**
	 * returns remainder on dividing the values of expr1 by expr2
	 * 
	 * @return expr1 % expr2
	 */
	public double evaluate(List<NodeInterface> arguments) {
		double remainder = arguments.get(0).getValue() % arguments.get(1).getValue();
		value = remainder;
		return value;
	}

}
