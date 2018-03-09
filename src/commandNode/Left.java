package commandNode;

import java.util.List;

import model.Model;
import model.Turtle;
import nodes.CommandInterface;
import nodes.GeneralCommand;
import nodes.NodeInterface;

/**
 * @author Belanie Nagiel
 * 
 * Left class that creates a new extension of Node and sets the functionality for the evaluate method implemented from
 * the CommandInterface interface.
 *
 */
public class Left extends GeneralCommand {

	/**
	 * Class Constructor
	 * 
	 * @param model
	 * @param numChildren
	 */
	public Left(Model model, int numChildren) {
		super(model, numChildren);
	}

	@Override
	/**
	 * turns turtle counterclockwise by degrees angle
	 * 
	 * @return the value of degrees
	 */
	public double evaluate(List<NodeInterface> arguments)
	{
		double x = arguments.get(0).getValue();
		model.update(t -> left(t, x));
		value = x;
		return value;
	}

	public void left (Turtle t, double x) {
		double deg = t.getDirectionAngle() + x;
		if (deg > 360) { deg = deg - 360; }
		t.setDirectionAngle(deg);
	}



}
