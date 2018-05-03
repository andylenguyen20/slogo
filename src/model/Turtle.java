package model;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

import nodes.NodeInterface;


import view.Observer;

/**
 * @author Belanie Nagiel and Charlie Dracos
 * 
 * The turtle class that creates new turtles and keeps track of all the information within a turtle. Also implements
 * interfaces to allow the front end to track changes on a turtle.
 *
 */
public class Turtle implements TurtleObservable, NodeInterface
{
	private double opacity = 1.0;
	private double XCoordinate;
	private double YCoordinate;
	private double[] home = new double[2];
	private double directionAngle;
	private List<Line> lines;
	private boolean penShowing;
	private boolean turtleShowing;
	private Color penColor;
	private double penSize;
	//private boolean activeStatus;

	
	private WraparoundHandler toroidal;

	private String turtleShape;
	private double ID;


	/**
	 * Class constructor
	 * 
	 * @param width
	 * @param height
	 * @param color
	 * @param ID
	 * @param startShape
	 */
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
		lines = new ArrayList<>();
		penColor = color;
		penSize = 1.0;
		this.ID = ID;
		turtleShape = startShape;
		toroidal = new WraparoundHandler(width, height);
	}

	public Turtle (Turtle t) {
		this(t.XCoordinate*2, t.YCoordinate*2, t.penColor, t.ID, t.turtleShape);
		this.setDirectionAngle(t.getDirectionAngle());
	}

	/**
	 *
	 * @return the current x coordinate
	 */
	public double getXCoordinate() {
		return XCoordinate;
	}
	
	/**
	 * 
	 * @return the current y coordinate
	 */
	public double getYCoordinate() {
		return YCoordinate;
	}

	/**
	 * Sets the coordinates and adds the appropriate lines to the turtle's path.
	 * 
	 */
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
	}

	/**
	 * @returns the home position of the turtle
	 */
	public double[] getHome(){
		return home;
	}

	/**
	 * @return the current direction angle
	 */
	public double getDirectionAngle() {
		return directionAngle;
	}


	/**
	 * Sets the direction angle of the turtle
	 * 
	 * @param directionAngle
	 */
	public void setDirectionAngle(double directionAngle) {
		this.directionAngle = directionAngle;
	}


	/**
	 * @return the turtle's path lines
	 */
	public List<Line> getLines() {
		return lines;
	}


	/**
	 * Adds a new line to the turtle's path lines
	 * 
	 * @param line
	 */
	private void addLine(Line line) {
		if(penShowing){
			line.setStroke(penColor);
			line.setStrokeWidth(penSize);
			lines.add(line);
		}
	}
	
	/**
	 * Clears the turtle's current path lines
	 * 
	 */
	public void clearLines()
	{
		lines = new ArrayList<Line>();
	}


	/**
	 *
	 * @return whether or not the pen is showing
	 */
	public boolean getPenShowing() {
		return penShowing;
	}

	/**
	 * Set whether the turtle's path is shown.
	 * 
	 * @param penShowing
	 */
	public void setPenShowing(boolean penShowing) {
		this.penShowing = penShowing;
	}

	/**
	 * 
	 * @return whether or not the turtle is showing
	 */
	public boolean getTurtleShowing() {
		return turtleShowing;
	}

	/**
	 * Sets the color of the turtle's path
	 * 
	 * @param color
	 */
	public void setPenColor(Color color) 
	{		
		this.penColor = color;
	}
	
	/**
	 * 
	 * @return the current color of the turtle's path
	 */
	public Color getPenColor()
	{
		return penColor;
	}

	/**
	 * Sets whether or not the turtle is showing
	 * 
	 * @param turtleShowing
	 */
	public void setTurtleShowing(boolean turtleShowing) {
		this.turtleShowing = turtleShowing;
	}

	/**
	 * Sets the width of the turtle path
	 * 
	 * @param pixels
	 */
	public void setPenSize(double pixels) {
		this.penSize = pixels;
	}
	
	/**
	 * 
	 * @return the turtle's current shape
	 */
	public String getTurtleShape() {
		return turtleShape;
	}

	/**
	 * Sets the turtle's current shape
	 * 
	 * @param turtleShape
	 */
	public void setTurtleShape(String turtleShape)
	{
		this.turtleShape = turtleShape;
	}

	public void setOpacity(double opacity){
		this.opacity = opacity;
	}

	public double getOpacity(){
		return opacity;
	}

	@Override
	/**
	 * 
	 * @return the turtle's ID
	 */
	public double getValue() {
		return ID;
	}

}
