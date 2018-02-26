package nodes;

import java.util.List;

public class Constant extends Node{
	private double value;
	
	public Constant (double value) {
		super(null, 0);
		this.value = value;
	}
	
	@Override
	public double getValue () {
		return value;
	}

	public double evaluate(List<Double> args) {
		System.out.println(value + " " + args.toString());
		return value;
	}
}