package commandNode;

import java.util.List;

import model.Model;
import nodes.GeneralCommand;
import nodes.NodeInterface;
import nodes.ListNode;
import nodes.Variable;
import nodes.Constant;
import nodes.ImproperNodeException;


public class DoTimes extends GeneralCommand {

	public DoTimes(Model model, int numChildren) {
		super(model, numChildren);
	}

	@Override
	/**
	 * runs command(s) for each value specified in the range, i.e., from (1 - limit) inclusive 
	 * returns the value of the final command executed (or 0 if no commands are executed)
	 * note, variable is assigned to each succeeding value so that it can be accessed by the command(s)
	 * 
	 * @return value of the final command executed
	 */

	public double evaluate(List<NodeInterface> args) {
		try {
			ListNode l = (ListNode) args.get(0);
			Variable v = (Variable) l.getElement(0);
			Constant limit = (Constant) l.getElement(1);
			ListNode l2 = (ListNode) args.get(1);
			while (v.getValue() <= limit.getValue()) {
				value = l2.evaluate();
				double next = v.getValue();
				next++;
				v.setNewValue(next);
			}
			return value;
		}
		catch (ClassCastException e) {
			e.printStackTrace();
			throw new ImproperNodeException();
		}
	}

}
