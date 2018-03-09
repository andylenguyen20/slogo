package commandNode;

import java.util.List;

import model.Model;
import model.Turtle;
import nodes.GeneralCommand;
import nodes.NodeInterface;

public class ShowTurtle extends GeneralCommand  {

	public ShowTurtle(Model model, int numChildren) {
		super(model, numChildren);
	}

	@Override
	/**
	 * makes turtle visible
	 * 
	 * @return 1
	 */
	public double evaluate(List<NodeInterface> arguments) {
		model.update(t -> showTurtle(t));
		value = 1;
		return value;
	}

	public void showTurtle (Turtle t) {
		t.setTurtleShowing(true);
	}

}
