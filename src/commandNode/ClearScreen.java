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
 * ClearScreen class that creates a new extension of Node and sets the functionality for the evaluate method implemented from
 * the CommandInterface interface.
 *
 */
public class ClearScreen extends GeneralCommand  {

	/**
	 * Class Constructor
	 * 
	 * @param model
	 * @param numChildren
	 */
	public ClearScreen(Model model, int numChildren) {
		super(model, numChildren);
	}

	@Override
	/**
	 * erases turtle's trails and sends it to the home position
	 * 
	 * @return the distance the turtle moved
	 */
	public double evaluate(List<NodeInterface> arguments) {
		double distance = 0;
		for (Turtle turtle: model.getActiveTurtles())
		{
			distance = Math.sqrt(Math.pow(turtle.getHome()[0] - turtle.getXCoordinate(), 2) + Math.pow(turtle.getHome()[1] - turtle.getYCoordinate(), 2) );
			turtle.setCoordinates(turtle.getHome()[0], turtle.getHome()[1]);
			turtle.setDirectionAngle(90);
			turtle.clearLines();	
		}
		value = distance;
		return value;
	}


}
