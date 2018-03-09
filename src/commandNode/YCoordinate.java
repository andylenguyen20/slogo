package commandNode;

import java.util.List;

import model.Model;
import model.Turtle;
import nodes.GeneralCommand;
import nodes.NodeInterface;

public class YCoordinate extends GeneralCommand {

	public YCoordinate(Model model, int numChildren) {
		super(model, numChildren);
	}

	@Override
	/**
	 * returns the turtle's Y coordinate from the center of the screen
	 * 
	 * @return turtle's Y
	 */
	public double evaluate(List<NodeInterface> arguments) {
		model.update(t -> yCoordinate(t));
		return value;
	}

	public void yCoordinate (Turtle t) {
		value = t.getYCoordinate();
	}

}
