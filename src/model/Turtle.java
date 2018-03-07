package model;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import nodes.NodeInterface;

import java.awt.geom.Line2D;
import view.Observer;

public abstract class Turtle implements TurtleObservable, NodeInterface
{	
	private double screenWidth;
	private double screenHeight;
	
	public Turtle(double width, double height)
	{
		screenWidth = width;
		screenHeight = height;
	}

	public abstract void move(double distance);
	public abstract void rotate(double degrees);
}
