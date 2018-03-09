package commandNode;

import java.util.List;

import model.Model;
import model.Turtle;
import nodes.GeneralCommand;
import nodes.NodeInterface;

/**
 * @author Belanie Nagiel
 * 
 * Forward class that creates a new extension of Node and sets the functionality for the evaluate method implemented from
 * the CommandInterface interface.
 *
 */
public class Forward extends GeneralCommand {
	private static double radianConversion = Math.PI/180;
	
	/**
	 * Class Constructor
	 * 
	 * @param model
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
		double x = arguments.get(0).getValue();
		model.update(t -> forward(t, x));
		value = x;
		return value;
	}

	public void forward (Turtle t, double x) {
		double xCor = t.getXCoordinate() + x*Math.cos(t.getDirectionAngle()*radianConversion);
		double yCor = t.getYCoordinate() - x*Math.sin(t.getDirectionAngle()*radianConversion);
		t.setCoordinates(xCor,yCor);
	}
}
