package commandNode;

import java.util.List;

import model.Model;
import nodes.GeneralCommand;
import nodes.NodeInterface;

public class Random extends GeneralCommand {

	public Random(Model model, int numChildren) {
		super(model, numChildren);
		// TODO Auto-generated constructor stub
	}

	@Override
	/**
	 * returns random non-negative number strictly less than max
	 * 
	 * @return num between 0 and max
	 */
	public double evaluate(List<NodeInterface> arguments) {
		value = Math.floor(Math.random() * arguments.get(0).getValue());
		return value;
	}


}
