package commandNode;

import java.util.List;

import model.Model;
import nodes.GeneralCommand;
import nodes.NodeInterface;

public class SetBackground extends GeneralCommand {

	public SetBackground(Model model, int numChildren) {
		super(model, numChildren);
		// TODO Auto-generated constructor stub
	}

	@Override
	/**
	 * sets background color of screen to that represented by index
	 * 
	 * @return given index
	 */
	public double evaluate(List<NodeInterface> arguments) {
		model.setBackgroundColor(arguments.get(0).getValue());
		value = arguments.get(0).getValue();
		return value;
	}

}
