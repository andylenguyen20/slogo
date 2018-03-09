package commandNode;

import java.util.List;

import model.Model;
import nodes.GeneralCommand;
import nodes.NodeInterface;

public class Tangent extends GeneralCommand {

	public Tangent(Model model, int numChildren) {
		super(model, numChildren);
		// TODO Auto-generated constructor stub
	}

	@Override
	/**
	 *return tangent of degrees
	 * 
	 * @return tan(degrees)
	 */
	public double evaluate(List<NodeInterface> arguments) {
		double tan = Math.tan(arguments.get(0).getValue());
		value = tan;
		return value;
	}
	

}
