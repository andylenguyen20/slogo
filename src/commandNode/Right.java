package commandNode;

import java.util.List;

import model.Model;
import model.Turtle;
import nodes.GeneralCommand;
import nodes.NodeInterface;

/**
 * @author Belanie Nagiel and Charlie Dracos
 * 
 * Right class that creates a new extension of Node and sets the functionality for the evaluate method implemented from
 * the CommandInterface interface.
 *
 */
public class Right extends GeneralCommand {

	public Right(Model model, int numChildren) {
		super(model, numChildren);
	}

	@Override
	/**
	 * turns turtle clockwise by degrees angle
	 * 
	 * @return the value of degrees
	 */
	public double evaluate(List<NodeInterface> arguments)
	{
		model.update(t -> right(t, arguments.get(0).getValue()));
		value = arguments.get(0).getValue();
		return value;
	}

	public void right (Turtle t, double x) {
		double deg = t.getDirectionAngle() - x;
		if (deg < 0) { deg = 360 + deg; }
		t.setDirectionAngle(deg);
	}
}
