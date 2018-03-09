package commandNode;

import java.util.List;

import model.Model;
import model.Turtle;
import nodes.GeneralCommand;
import nodes.NodeInterface;

public class PenUp extends GeneralCommand {

	public PenUp(Model model, int numChildren) {
		super(model, numChildren);
	}

	@Override
	/**
	 * puts pen up such that when the turtle moves, it does not leaves a trail
	 * 
	 * @return 0
	 */
	public double evaluate(List<NodeInterface> arguments)
	{
		model.update(t -> penUp(t));
		value = 0;
		return value;
	}

	public void penUp (Turtle t) {
		t.setPenShowing(false);
	}

}
