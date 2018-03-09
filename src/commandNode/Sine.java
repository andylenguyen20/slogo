package commandNode;

import java.util.List;

import model.Model;
import nodes.GeneralCommand;
import nodes.NodeInterface;

public class Sine extends GeneralCommand {

	public Sine(Model model, int numChildren) {
		super(model, numChildren);
		// TODO Auto-generated constructor stub
	}

	@Override
	/**
	 * returns sine of degrees
	 * 
	 * @return sin(degrees)
	 */
	public double evaluate(List<NodeInterface> arguments) {
		double sin = Math.sin(arguments.get(0).getValue());
		value = sin;
		return value;
	}


}
