package commandNode;

import java.util.List;

import model.Model;
import model.Turtle;
import nodes.GeneralCommand;
import nodes.NodeInterface;

public class SetShape extends GeneralCommand {

	public SetShape(Model model, int numChildren) {
		super(model, numChildren);
		// TODO Auto-generated constructor stub
	}

	@Override
	/**
	 * sets shape of turtle to that represented by index
	 * 
	 * @return given index
	 */
	public double evaluate(List<NodeInterface> arguments) {
		for (Turtle turtle: model.getActiveTurtles())
		{
			turtle.setTurtleShape(model.getShapeOptions().get((int) arguments.get(0).getValue()));
		}
		value = arguments.get(0).getValue();
		return value;
	}

}
