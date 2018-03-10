package commandNode;

import java.util.List;

import model.Model;
import model.Turtle;
import nodes.GeneralCommand;
import nodes.NodeInterface;

public class SetTowards extends GeneralCommand
{
	private double degreeConversion = 180/Math.PI;
			
	public SetTowards(Model model, int numChildren) {
		super(model, numChildren);
	}

	@Override
	/**
	 * turns turtle to face the point (x, y), where (0, 0) is the center of the screen
	 * 
	 * @return the number of degrees turtle turned
	 */
	public double evaluate(List<NodeInterface> arguments) {
		model.update(t -> setTowards(t, degreeCalc(arguments.get(0).getValue(), arguments.get(1).getValue())));
		return value;
	}

	public void setTowards(Turtle t, double deg) {
		double change = Math.abs(t.getDirectionAngle() - deg);
		t.setDirectionAngle(deg);
		value = change;
	}

	private double degreeCalc (double x, double y) {
		double rad = Math.atan(y/x);
		double deg = rad*degreeConversion;
		if( x < 0) { deg = 180 + deg; }
		double finalDeg = deg;
		return finalDeg;
	}


}
