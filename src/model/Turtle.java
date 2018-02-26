package model;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.shape.Line;


public class Turtle 
{
	private double XCoordinate;
	private double YCoordinate;
	private double[] home = new double[2];
	private double directionAngle;
	private List<Line> lines;
	private boolean penShowing;
	private boolean turtleShowing;
	
	
	public Turtle(double xHome, double yHome)
	{
		XCoordinate = xHome;
		YCoordinate = yHome;
		home[0] = xHome;
		home[1] = yHome; 
		directionAngle = 90;
		penShowing = true;
		turtleShowing = true;
		lines = new ArrayList<Line>();
	}


	public double getXCoordinate() {
		return XCoordinate;
	}
	
	public double getYCoordinate() {
		return YCoordinate;
	}


	public void setCoordinates(double xCoordinate, double yCoordinate) {
		//System.out.println("old y" + YCoordinate);
		Line l = new Line(XCoordinate, YCoordinate, xCoordinate, yCoordinate);
		XCoordinate = xCoordinate;
		YCoordinate = yCoordinate;
		//System.out.println("new y" + YCoordinate);
		addLine(l);
	}

	public double getDirectionAngle() {
		return directionAngle;
	}


	public void setDirectionAngle(double directionAngle) {
		this.directionAngle = directionAngle;
	}


	public List<Line> getLines() {
		return lines;
	}


	public void addLine(Line line) {
		lines.add(line);
	}
	
	public void clearLines()
	{
		lines = new ArrayList<Line>();
	}


	public boolean getPenShowing() {
		return penShowing;
	}


	public void setPenShowing(boolean penShowing) {
		this.penShowing = penShowing;
	}


	public boolean getTurtleShowing() {
		return turtleShowing;
	}


	public void setTurtleShowing(boolean turtleShowing) {
		this.turtleShowing = turtleShowing;
	}
}
