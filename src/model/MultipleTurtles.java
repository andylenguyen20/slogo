package model;

import java.util.List;
import java.util.Map;

import javafx.scene.shape.Line;
import nodes.NodeInterface;

public class MultipleTurtles extends Turtle implements NodeInterface{
	private List<SingleTurtle> multiples;
	
	public MultipleTurtles(double width, double height, List<SingleTurtle> turts) {
		super(width, height);	
		multiples = turts;
	}
	
	@Override
	public void move(double distance) {
		for (SingleTurtle t: multiples)
		{
			t.move(distance);
		}
		
	}

	@Override
	public void rotate(double degrees) {
		for (SingleTurtle t: multiples)
		{
			t.rotate(degrees);
		}
	}
	
	@Override
	public double getValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getXCoordinate() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getYCoordinate() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getDirectionAngle() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Line> getLines() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean getTurtleShowing() {
		// TODO Auto-generated method stub
		return false;
	}
}
