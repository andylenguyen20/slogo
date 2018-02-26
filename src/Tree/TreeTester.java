package Tree;

import java.util.ArrayList;

import nodes.*;
import commandNode.*;
import model.Turtle;

public class TreeTester {
	private static ArrayList<Node> nodes;
	
	public static void main (String [] args) {
		Turtle t = new Turtle(0, 0);
		nodes = new ArrayList<Node>();
		nodes.add(new Difference(null, 2));
		nodes.add(new Constant(50));
		nodes.add(new Forward(t, 1));
		nodes.add(new Constant (20));
		TreeMaker tm  = new TreeMaker(nodes);
		ArrayList<Node> heads = (ArrayList<Node>) tm.getHeads();
		TreeEvaluator te = new TreeEvaluator(heads);
		System.out.println(te.getValue());
	}

}

