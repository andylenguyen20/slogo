package commandNode;

import java.util.List;

import model.Turtle;
import nodes.Node;

public class Pi extends Node{

	public Pi(Turtle turt, int numChildren) {
		super(turt, numChildren);
		// TODO Auto-generated constructor stub
	}

	@Override
	/**
	 * reports the number Pi
	 * 
	 * @return Pi
	 */
	public double evaluate(List<Double> arguments) {
		value = Math.PI;
		return value;
	}

}
