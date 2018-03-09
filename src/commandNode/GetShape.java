package commandNode;

import java.util.List;

import model.Model;
import model.Turtle;
import nodes.GeneralCommand;
import nodes.NodeInterface;

public class GetShape extends GeneralCommand {

	public GetShape(Model model, int numChildren) {
		super(model, numChildren);
		// TODO Auto-generated constructor stub
	}

	@Override
	/**
	 * returns turtle's current shape index
	 * 
	 * @return index of shape
	 */
	public double evaluate(List<NodeInterface> arguments) {
		// TODO Auto-generated method stub
		for (Turtle turtle: model.getActiveTurtles())
		{
			value = model.getShapeOptions().indexOf(turtle.getTurtleShape());
		}
		return value;
	}

}
