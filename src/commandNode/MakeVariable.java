package commandNode;

import java.util.List;

import model.Model;
import model.VariablesHistory;
import nodes.GeneralCommand;
import nodes.ImproperNodeException;
import nodes.NodeInterface;
import nodes.Variable;

/**
 * Makes a new variable as described on the course site
 */

public class MakeVariable extends GeneralCommand {
	

	public MakeVariable(Model model, int numChildren) {
		super(model, numChildren);
	}

	@Override
	public double evaluate(List<NodeInterface> args) {
		try {
			Variable v = (Variable) args.get(0);
			v.setValue(args.get(1).getValue()); //sets the value of the variable
			VariablesHistory VH = v.getVH();
			VH.add(v); //adds to its vh
			value = args.get(1).getValue();
			return value;
		}
		catch (ClassCastException e) {
			e.printStackTrace();
			throw new ImproperNodeException();
		}
	}

}
