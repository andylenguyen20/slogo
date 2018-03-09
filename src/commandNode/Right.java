package commandNode;

import java.util.List;

import model.Model;
import model.Turtle;
import nodes.GeneralCommand;
import nodes.NodeInterface;

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
		double x = arguments.get(0).getValue();
		model.update(t -> right(t, x));
		value = x;
		return value;
	}

	public void right (Turtle t, double x) {
		double deg = t.getDirectionAngle() - x;
		if (deg < 0) { deg = 360 + deg; }
		t.setDirectionAngle(deg);
	}
}
