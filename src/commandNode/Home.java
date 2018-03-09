package commandNode;

import java.util.List;

import model.Model;
import model.Turtle;
import nodes.GeneralCommand;
import nodes.NodeInterface;

/**
 * @author Belanie Nagiel
 * 
 * Home class that creates a new extension of Node and sets the functionality for the evaluate method implemented from
 * the CommandInterface interface.
 *
 */
public class Home extends GeneralCommand  {

	/**
	 * Class Constructor
	 * 
	 * @param model
	 * @param numChildren
	 */
	public Home(Model model, int numChildren) {
		super(model, numChildren);
	}

	@Override
	/**
	 * moves turtle to the center of the screen (0 0)
	 * 
	 * @return the distance turtle moved
	 */
	public double evaluate(List<NodeInterface> arguments) {
		model.update(t -> home(t));
		return value;
	}

	public void home (Turtle t) {
		double distance = Math.sqrt(Math.pow(t.getHome()[0] - t.getXCoordinate(), 2)
				+ Math.pow(t.getHome()[1] - t.getYCoordinate(), 2) );
		t.setCoordinates(t.getHome()[0], t.getHome()[1]);
		value = distance;
	}

}
