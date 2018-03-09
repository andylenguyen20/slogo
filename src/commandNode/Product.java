package commandNode;

import java.util.List;

import model.Model;
import nodes.GeneralCommand;
import nodes.NodeInterface;

public class Product extends GeneralCommand {

	public Product(Model model, int numChildren) {
		super(model, numChildren);
		// TODO Auto-generated constructor stub
	}

	@Override
	/**
	 * returns product of the values of expr1 and expr2
	 * 
	 * @return expr1 * expr2
	 */
	public double evaluate(List<NodeInterface> arguments) {
		double prod = arguments.get(0).getValue()*arguments.get(1).getValue();
		value = prod;
		return value;
	}


}
