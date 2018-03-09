package commandNode;

import java.util.List;

import model.Model;
import model.Turtle;
import nodes.GeneralCommand;
import nodes.NodeInterface;

public class GetPenColor extends GeneralCommand {

	public GetPenColor(Model model, int numChildren) {
		super(model, numChildren);
	}


	@Override
	/**
	 * returns turtle's current color index
	 * 
	 * @return turtle's current color index
	 */
	public double evaluate(List<NodeInterface> arguments) 
	{
		for (Turtle turtle: model.getActiveTurtles())
		{
			value = model.getColorOptions().indexOf(turtle.getPenColor());
		}
		return value;
	}

}
