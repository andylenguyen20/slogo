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
		for(Turtle turtle: model.getActiveTurtles())
		{
			double deg = turtle.getDirectionAngle() + arguments.get(0).getValue();

			if (deg > 360)
			{
				deg = deg - 360;
			}

			turtle.setDirectionAngle(deg);
		}
		
		value = arguments.get(0).getValue();
		return value;
	}



}
