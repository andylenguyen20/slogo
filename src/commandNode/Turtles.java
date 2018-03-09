package commandNode;

import java.util.List;

import model.Model;
import nodes.GeneralCommand;
import nodes.NodeInterface;

public class Turtles extends GeneralCommand {

	public Turtles(Model model, int numChildren) {
		super(model, numChildren);
	}

	@Override
	/**
	 * returns number of turtles created so far
	 * 
	 * @return num turtles
	 */
	public double evaluate(List<NodeInterface> arguments) {
		value = model.getAllTurtles().size();
		return value;
	}

}
