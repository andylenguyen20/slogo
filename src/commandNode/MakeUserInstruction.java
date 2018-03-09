package commandNode;

import java.util.List;

import model.Model;
import nodes.GeneralCommand;
import nodes.NodeInterface;
import nodes.CustomCommand;

public class MakeUserInstruction extends GeneralCommand  {

	public MakeUserInstruction(Model model, int numChildren) {
		super(model, numChildren);
	}

	@Override
	public double evaluate(List<NodeInterface> args) {
		try {
			CustomCommand c = (CustomCommand) args.get(0);
			c.add (args.get(1));
			c.add (args.get(2));
			c.getVH().add(c);
			return 1;
		}
		catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

}
