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
 * Forward class that creates a new extension of Node and sets the functionality for the evaluate method implemented from
 * the CommandInterface interface.
 *
 */
public class Forward extends GeneralCommand implements CommandInterface {
	private static double radianConversion = Math.PI/180;
	
	/**
	 * Class Constructor
	 * 
	 * @param turt
	 * @param numChildren
	 */
	public Forward(Model model, int numChildren) {
		super(model, numChildren);
	}

	@Override
	/**
	 * moves turtle forward in its current heading by pixels distance
	 * 
	 * @return the value of pixels
	 */
	public double evaluate(List<NodeInterface> arguments)
	{
		model.getActiveTurtles().get(model.getActiveTurtles().size()-1).move(arguments.get(0).getValue());
			
//		for (Turtle turtle: model.getActiveTurtles())
//		{
//			double xCor = turtle.getXCoordinate() + arguments.get(0).getValue()*Math.cos(turtle.getDirectionAngle()*radianConversion);
//			double yCor = turtle.getYCoordinate() - arguments.get(0).getValue()*Math.sin(turtle.getDirectionAngle()*radianConversion);
//			turtle.setCoordinates(xCor,yCor);
//		}
//		
		value = arguments.get(0).getValue();
		return value;
	}


}
