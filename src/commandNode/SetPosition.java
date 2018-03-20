package commandNode;

import java.util.List;

import model.Model;
import model.Turtle;
import nodes.GeneralCommand;
import nodes.NodeInterface;

public class SetPosition extends GeneralCommand {

	public SetPosition(Model model, int numChildren) {
		super(model, numChildren);
	}

	@Override
	/**
	 * moves turtle to an absolute screen position, where (0, 0) is the center of the screen
	 * 
	 * @return the distance the turtle moved
	 */
	public double evaluate(List<NodeInterface> arguments)
	{
		model.update(t -> setPosition(t, arguments.get(0).getValue(),  arguments.get(1).getValue()));
		return value;
	}

	public void setPosition(Turtle t, double x, double y) {
		double distance = Math.sqrt(Math.pow(x + t.getHome()[0] -
				t.getXCoordinate(), 2) + Math.pow(y + t.getHome()[1] -
				t.getYCoordinate(), 2) );
		t.setCoordinates(x + t.getHome()[0], (-1 * y + t.getHome()[1]));
		value = distance;
	}

}
