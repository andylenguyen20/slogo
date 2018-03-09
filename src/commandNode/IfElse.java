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
 * IfElse class that creates a new extension of Node and sets the functionality for the evaluate method implemented from
 * the CommandInterface interface.
 *
 */
public class IfElse extends GeneralCommand {

	/**
	 * Class Constructor
	 * 
	 * @param model
	 * @param numChildren
	 */
	public IfElse(Model model, int numChildren) {
		super(model, numChildren);
	}

	@Override
	/**
	 * if expr is not 0, runs the trueCommands given in the first list, otherwise runs the falseCommands given in the second list
	 * returns the value of the final command executed (or 0 if no commands are executed)
	 * 
	 * @return value of the final command executed
	 */
	public double evaluate(List<NodeInterface> args) {
		try {
			value = 0;
			if(args.get(0).getValue() != 0)
			{
				ListNode l1 = (ListNode) args.get(1);
				value = l1.evaluate();
			}
			else
			{
				ListNode l2 = (ListNode) args.get(1);
				value = l2.evaluate();
			}
			return value;
		}
		catch (ClassCastException e) {
			e.printStackTrace();
			throw new ImproperNodeException();
		}
	}
	

}
