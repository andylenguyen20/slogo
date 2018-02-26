package commandNode;

import java.util.List;

import model.Turtle;
import nodes.Node;

public class Forward extends Node{
	private static double radianConversion = Math.PI/180;
	
	public Forward(Turtle turt, int numChildren) {
		super(turt, numChildren);
	}

	@Override
	/**
	 * moves turtle forward in its current heading by pixels distance
	 * 
	 * @return the value of pixels
	 */
	public double evaluate(List<Double> arguments) 
	{
		//System.out.println(arguments.toString());
		double xCor = turtle.getXCoordinate() + 
				arguments.get(0)*Math.cos(turtle.getDirectionAngle()*radianConversion);
		//System.out.println("xCor" + xCor);
		double yCor = turtle.getYCoordinate() + 
				arguments.get(0)*Math.sin(turtle.getDirectionAngle()*radianConversion);
		//System.out.println("yCor" + yCor);
		turtle.setCoordinates(xCor,yCor);
		//add a line
		value = arguments.get(0);
		return arguments.get(0);
	}


}
