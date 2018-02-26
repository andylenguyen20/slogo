package commandNode;

import java.util.List;

import model.Turtle;
import nodes.CommandNode;
import nodes.Node;

public class LessThan extends Node implements CommandNode {

	public LessThan(Turtle turt, int numChildren) {
		super(turt, numChildren);
	}

	@Override
	/**
	 * returns 1 if the value of expr1 is strictly less than the value of expr2, otherwise 0
	 * 
	 * @return is expr1 < expr2
	 */
	public double evaluate(List<Node> arguments) {
		if(arguments.get(0).getValue() < arguments.get(1).getValue())
		{
			value = 1;
			return value;
		}
		else
		{
			value = 0;
			return value;
		}	
	}

}
