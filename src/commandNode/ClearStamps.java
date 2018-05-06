package commandNode;

import java.util.ArrayList;
import java.util.List;

import model.Model;
import model.Turtle;
import nodes.GeneralCommand;
import nodes.NodeInterface;

public class ClearStamps extends GeneralCommand{

	public ClearStamps(Model model, int numChildren) {
		super(model, numChildren);
	}

	@Override
	public double evaluate(List<NodeInterface> args) {
		if(model.getTurtleStamps().isEmpty())
		{
			value = 0;
		}
		else
		{
			value = 1;
		}

		for(Turtle t: model.getTurtleStamps())
		{
			model.removeStamp(t);
		}
		
		return value;
	}

}
