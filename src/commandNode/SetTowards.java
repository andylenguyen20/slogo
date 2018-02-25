package commandNode;

import java.util.List;

import nodes.Node;
import parsers.Turtle;

public class SetTowards extends Node 
{

	public SetTowards(Turtle turt, int numChildren) {
		super(turt, numChildren);
	}

	@Override
	/**
	 * turns turtle to face the point (x, y), where (0, 0) is the center of the screen
	 * 
	 * @return the number of degrees turtle turned
	 */
	public double evaluate(List<Double> arguments) {
		double deg = Math.atan(arguments.get(0)/arguments.get(1));
		double change = Math.abs(turtle.getDirectionAngle() - deg);
		turtle.setDirectionAngle(deg);
		value = change;
		return change;
	}


}
