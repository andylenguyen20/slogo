package commandNode;

import java.util.List;

import model.Model;
import model.Turtle;
import nodes.GeneralCommand;
import nodes.NodeInterface;


public class SetPenColor extends GeneralCommand {

	public SetPenColor(Model model, int numChildren) {
		super(model, numChildren);
	}

	@Override
	/**
	 * sets color of the pen to that represented by index
	 * 
	 * @return given index
	 */
	public double evaluate(List<NodeInterface> arguments) {
		for (Turtle turtle: model.getActiveTurtles())
		{
			turtle.setPenColor(model.getColorAtIndex(arguments.get(0).getValue()));
		}
		value = arguments.get(0).getValue();
		return value;
	}

}
