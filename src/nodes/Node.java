package nodes;

import java.util.ArrayList;
import java.util.List;

import model.Turtle;

public abstract class Node 
{
	private Node[] children;
	private int current;
	protected double value;
	protected Turtle turtle;
	
	public Node(Turtle turt, int numChildren)
	{
		children = new Node[numChildren];
		current = 0;
		turtle = turt;
	}
	
	public boolean hasNext() 
	{
		if (current!=children.length) return true;
		return false;
	}
	
	public void addChild(Node n) 
	{
		if (current != children.length) {
			children[current] = n;
		}
	}
	
	public Node getChild() 
	{
		if (current!=children.length) {
			Node child = children[current];
			current++;
			return child;
		}
		return null;
	}
	
	public void reset() {
		current = 0;
	}
	
	public double getValue() 
	{
		return value;
	}
	
	public abstract double evaluate (List<Double> args);
}