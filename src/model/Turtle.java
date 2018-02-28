package model;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import view.TurtleObserver;


public class Turtle implements TurtleObservable
{
	private double XCoordinate;
	private double YCoordinate;
	private double[] home = new double[2];
	private double directionAngle;
	private List<Line> lines;
	private boolean penShowing;
	private boolean turtleShowing;
	private Color penColor;
	
	//THIS IS ANDY'S SUGGESTION
	private TurtleObserver turtleObserver;

	//Could pass pen color in parameter! Right now we call setPenColor in controller. TODO: Discuss this idea, Also screen size in constructor?
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

	public void addTurtleObserver(TurtleObserver turtleObserver){
		this.turtleObserver = turtleObserver;
	}

	public double getXCoordinate() {
		return XCoordinate;
	}
	
	public double getYCoordinate() {
		return YCoordinate;
	}
	
	public void setCoordinates(double xCoordinate, double yCoordinate) {
		Line l = new Line(XCoordinate, YCoordinate, xCoordinate, yCoordinate);
		//This is Andy's change, feel free to discuss with him
		l.setStroke(penColor);
		XCoordinate = xCoordinate;
		YCoordinate = yCoordinate;
		//System.out.println(YCoordinate);
		addLine(l);
		turtleObserver.notifyTurtleObserver();
	}
	
	public double[] getHome(){
		return home;
	}

	public double getDirectionAngle() {
		return directionAngle;
	}


	public void setDirectionAngle(double directionAngle) {
		this.directionAngle = directionAngle;
		turtleObserver.notifyTurtleObserver();
	}


	public List<Line> getLines() {
		return lines;
	}


	public void addLine(Line line) {
		lines.add(line);
		turtleObserver.notifyTurtleObserver();
	}
	
	public void clearLines()
	{
		lines = new ArrayList<Line>();
		turtleObserver.notifyTurtleObserver();
	}


	public boolean getPenShowing() {
		return penShowing;
	}


	public void setPenShowing(boolean penShowing) {
		this.penShowing = penShowing;
		turtleObserver.notifyTurtleObserver();
	}

	public boolean getTurtleShowing() {
		return turtleShowing;
	}

	public void setPenColor(Color penColor) {
		this.penColor = penColor;
	}


	public void setTurtleShowing(boolean turtleShowing) {
		this.turtleShowing = turtleShowing;
		turtleObserver.notifyTurtleObserver();
	}
}
