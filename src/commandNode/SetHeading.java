package commandNode;

import java.util.List;

import model.Model;
import model.Turtle;
import nodes.GeneralCommand;
import nodes.NodeInterface;

/**
 * @author Belanie Nagiel and Charlie Dracos
 * 
 * SetHeading class that creates a new extension of Node and sets the functionality for the evaluate method implemented from
 * the CommandInterface interface.
 *
 */
public class SetHeading extends GeneralCommand {

	public SetHeading(Model model, int numChildren) {
		super(model, numChildren);
	}

	@Override
	/**
	 * turns turtle to an absolute heading
	 * 
	 * @return number of degrees moved
	 */
	public double evaluate(List<NodeInterface> arguments) {
		double x = arguments.get(0).getValue();
		model.update(t -> setHeading(t, x));
		return value;
	}

	public void setHeading (Turtle t, double x) {
		double deg = Math.abs(t.getDirectionAngle() - x);
		t.setDirectionAngle(x);
		value = deg;
	}
}
