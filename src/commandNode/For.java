package commandNode;

import java.util.List;

import model.Model;
import nodes.GeneralCommand;
import nodes.NodeInterface;
import nodes.ListNode;
import nodes.Variable;
import nodes.Constant;
import nodes.ImproperNodeException;

/**
 * Implements FOR as described on the course site
 */

public class For extends GeneralCommand  {

	public For(Model model, int numChildren) {
		super(model, numChildren);
	}

	@Override
	public double evaluate(List<NodeInterface> args) {
		try {
			ListNode l = (ListNode) args.get(0);
			Variable v = (Variable) l.getElement(0);
			Constant start = (Constant) l.getElement(1);
			Constant end = (Constant) l.getElement(2);
			Constant increment = (Constant) l.getElement(3);

			ListNode commands = (ListNode) args.get(1);

			for (double i = start.getValue(); i <= end.getValue(); i+=increment.getValue()) {
				v.setNewValue(i); //update the variable
				value = commands.evaluate();
			}

			return value;
		}
		catch (ClassCastException e) {
			e.printStackTrace();
			throw new ImproperNodeException();
		}
	}

}
