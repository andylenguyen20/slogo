package commandNode;

import java.util.List;

import model.Model;
import nodes.GeneralCommand;
import nodes.NodeInterface;

public class SetPalette extends GeneralCommand {

	public SetPalette(Model model, int numChildren) {
		super(model, numChildren);
		// TODO Auto-generated constructor stub
	}

	@Override
	/**
	 * sets color corresponding at given index to given r g b color values
	 * note, color component values are nonnegative integers less than 256 specifying an amount of red, green, and blue
	 * 
	 * @return given index
	 */
	public double evaluate(List<NodeInterface> arguments) {
		model.setColorOptions(arguments.get(0).getValue(), arguments.get(1).getValue(), arguments.get(2).getValue(), arguments.get(3).getValue());
		value = arguments.get(0).getValue();
		return value;
	}

}
