package commandNode;

import java.util.List;

import model.Model;
import model.Turtle;
import nodes.GeneralCommand;
import nodes.NodeInterface;

public class XCoordinate extends GeneralCommand {

	public XCoordinate(Model model, int numChildren) {
		super(model, numChildren);
	}

	@Override
	/**
	 * returns the turtle's X coordinate from the center of the screen
	 * 
	 * @return turtle's x
	 */
	public double evaluate(List<NodeInterface> arguments) {
		model.update(t -> xCoordinate(t));
		return value;
	}

	public void xCoordinate (Turtle t) {
		value = t.getXCoordinate();
	}

}
