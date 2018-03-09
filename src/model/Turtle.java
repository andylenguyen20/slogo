package model;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

import javafx.scene.shape.Shape;
import nodes.NodeInterface;


import view.Observer;


public class Turtle implements TurtleObservable, NodeInterface
{
	private double XCoordinate;
	private double YCoordinate;
	private double[] home = new double[2];
	private double directionAngle;
	private List<Line> lines;
	private boolean penShowing;
	private boolean turtleShowing;
	private Color penColor;
	private double penSize;

	
	private WraparoundHandler toroidal;

	private String turtleShape;
	private double ID;
	
	//THIS IS ANDY'S SUGGESTION
	private Observer turtleObserver;

	//Could pass pen color in parameter! Right now we call setPenColor in controller. TODO: Discuss this idea, Also screen size in constructor?
	public Turtle(double width, double height, Color color, double ID, String startShape)
	{
		turtleShape = startShape;
		XCoordinate = width/2;
		YCoordinate = height/2;
		home[0] = width/2;
		home[1] = height/2; 
		directionAngle = 90;
		penShowing = true;
		turtleShowing = true;
		lines = new ArrayList<Line>();
		penColor = color;
		penSize = 1.0;
		this.ID = ID;
		turtleShape = startShape;
		toroidal = new WraparoundHandler(width, height);
	}

	public void addObserver(Observer turtleObserver){
		this.turtleObserver = turtleObserver;
	}

	public double getXCoordinate() {
		return XCoordinate;
	}
	
	public double getYCoordinate() {
		return YCoordinate;
	}

	public void setCoordinates(double futureX, double futureY) {
		double currentX = XCoordinate;
		double currentY = YCoordinate;
		List<Line> addedLines = toroidal.drawLine(currentX, currentY, futureX, futureY);
		XCoordinate = addedLines.get(addedLines.size() - 1).getEndX();
		YCoordinate = addedLines.get(addedLines.size() - 1).getEndY();
		for (Line l: addedLines)
		{
			addLine(l);
		}

		turtleObserver.notifyOfChanges();
	}

	public double[] getHome(){
		return home;
	}

	public double getDirectionAngle() {
		return directionAngle;
	}


	public void setDirectionAngle(double directionAngle) {
		this.directionAngle = directionAngle;
		turtleObserver.notifyOfChanges();
	}


	public List<Line> getLines() {
		return lines;
	}


	private void addLine(Line line) {
		if(penShowing){
			line.setStroke(penColor);
			line.setStrokeWidth(penSize);
			lines.add(line);
		}
	}
	
	public void clearLines()
	{
		lines = new ArrayList<Line>();
		turtleObserver.notifyOfChanges();
	}


	public boolean getPenShowing() {
		return penShowing;
	}


	public void setPenShowing(boolean penShowing) {
		this.penShowing = penShowing;
		turtleObserver.notifyOfChanges();
	}

	public boolean getTurtleShowing() {
		return turtleShowing;
	}

	public void setPenColor(Color color) 
	{		
		this.penColor = color;
	}
	
	public Color getPenColor()
	{
		return penColor;
	}

	public void setTurtleShowing(boolean turtleShowing) {
		this.turtleShowing = turtleShowing;
		turtleObserver.notifyOfChanges();
	}

	public void setPenSize(double pixels) {
		this.penSize = pixels;
	}
	
	public String getTurtleShape() {
		return turtleShape;
	}

	public void setTurtleShape(String turtleShape)
	{
		this.turtleShape = turtleShape;
		turtleObserver.notifyOfChanges();
	}

	@Override
	public double getValue() {
		return ID;
	}
}
