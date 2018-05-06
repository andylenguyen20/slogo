package commandNode;

import java.util.List;

import model.Model;
import model.Turtle;
import nodes.GeneralCommand;
import nodes.NodeInterface;

public class Stamp extends GeneralCommand {

	public Stamp(Model model, int numChildren) {
		super(model, numChildren);
	}

	@Override
	public double evaluate(List<NodeInterface> args) {
		for(Turtle t: model.getActiveTurtles())
		{
			double xCor = t.getXCoordinate();
			double yCor = t.getYCoordinate();
			double angle = t.getDirectionAngle();
			String shape = t.getTurtleShape();
			model.addStamp(xCor, yCor, angle, shape);
	
			value = model.getIndexOfShape(shape);
		}
		return value;
	}
}
