package commandNode;


import java.util.List;

import model.Model;
import model.Turtle;
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
		model.update(t -> clearScreen(t));
		return value;
	}

	public void clearScreen (Turtle t) {
		double distance = Math.sqrt(Math.pow(t.getHome()[0] - t.getXCoordinate(), 2)
				+ Math.pow(t.getHome()[1] - t.getYCoordinate(), 2) );
		t.setCoordinates(t.getHome()[0], t.getHome()[1]);
		t.setDirectionAngle(90);
		t.clearLines();
		value = distance;
	}




}
