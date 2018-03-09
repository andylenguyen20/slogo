package commandNode;

import java.util.List;

import model.Model;
import nodes.GeneralCommand;
import nodes.NodeInterface;
import nodes.ListNode;
import nodes.ImproperNodeException;

/**
 * @author Belanie Nagiel
 * 
 * If class that creates a new extension of Node and sets the functionality for the evaluate method implemented from
 * the CommandInterface interface.
 *
 */
public class If extends GeneralCommand {

	/**
	 * Class Constructor
	 * 
	 * @param model
	 * @param numChildren
	 */
	public If(Model model, int numChildren) {
		super(model, numChildren);
	}

	@Override
	/**
	 * if expr is not 0, runs the command(s) given in the list
	 * returns the value of the final command executed (or 0 if no commands are executed)
	 * 
	 * @return value of final command executed
	 */
	public double evaluate(List<NodeInterface> args) {
		try {
			value = 0;
			if(args.get(0).getValue() != 0)
			{
				ListNode l = (ListNode) args.get(1);
				value = l.evaluate();
			}
			return value;
		}
		catch (ClassCastException e) {
			e.printStackTrace();
			throw new ImproperNodeException();
		}
	}

}
