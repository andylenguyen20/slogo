package model;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import nodes.NodeInterface;
import view.Observer;

public class SingleTurtle extends Turtle implements TurtleObservable, NodeInterface{
	private double XCoordinate;
	private double YCoordinate;
	private double[] home = new double[2];
	private double directionAngle;
	private List<Line> lines;
	private boolean penShowing;
	private boolean turtleShowing;
	private Color penColor;
	private double penSize;
	private Shape turtleShape;
	private double ID;
	private static double radianConversion = Math.PI/180;

	private HandleWraparound toroidal;
	
	//THIS IS ANDY'S SUGGESTION
	private Observer turtleObserver;
	
	public SingleTurtle(double width, double height) {
		super(width, height);
		XCoordinate = width/2;
		YCoordinate = height/2;
		home[0] = width/2;
		home[1] = height/2; 
		directionAngle = 90;
		penShowing = true;
		turtleShowing = true;
		lines = new ArrayList<Line>();
		penColor = Color.BLUE;
		penSize = 1.0;
		toroidal = new HandleWraparound(width, height);
	}

	
	@Override
	public void move(double distance) {
		double xCor = getXCoordinate() + distance*Math.cos(getDirectionAngle()*radianConversion);
		double yCor = getYCoordinate() - distance*Math.sin(getDirectionAngle()*radianConversion);
		System.out.println(xCor);
		System.out.println(yCor);
		setCoordinates(xCor,yCor);	
	}

	@Override
	public void rotate(double degrees) 
	{
		double deg = getDirectionAngle() - degrees;
		if (deg < 0)
		{
			deg = 360 + deg;
		}
		else if (deg > 360)
		{
			deg = deg - 360;
		}
		System.out.println(deg);
		setDirectionAngle(deg);
	}
	@Override
	public double getValue() {
		return ID;
	}
	
	public double[] getHome(){
		return home;
	}

	@Override
	public double getXCoordinate() {
		return XCoordinate;
	}

	@Override
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
		
		//turtleObserver.notifyOfChanges();	
	}

	@Override
	public double getDirectionAngle() {
		return directionAngle;
	}


	public void setDirectionAngle(double directionAngle) {
		this.directionAngle = directionAngle;
		//turtleObserver.notifyOfChanges();
	}

	@Override
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

	@Override
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
	
	public Shape getTurtleShape() {
		return turtleShape;
	}

	public void setTurtleShape(Shape turtleShape) 
	{
		this.turtleShape = turtleShape;
	}
}
