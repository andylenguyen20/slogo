package commandNode;

import java.util.List;

import model.Model;
import model.Turtle;
import nodes.GeneralCommand;
import nodes.NodeInterface;

public class SetPenSize extends GeneralCommand {

	public SetPenSize(Model model, int numChildren) {
		super(model, numChildren);
	}

	@Override
	/**
	 * sets size of the pen to be pixels thickness
	 * 
	 * @return given pixels
	 */
	public double evaluate(List<NodeInterface> arguments) {
		for (Turtle turtle: model.getActiveTurtles())
		{
			turtle.setPenSize(arguments.get(0).getValue());
		}
		value = arguments.get(0).getValue();
		return value;
	}


}
