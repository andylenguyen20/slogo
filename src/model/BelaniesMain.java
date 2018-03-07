package model;

import java.util.ArrayList;

import Tree.TreeEvaluator;
import Tree.TreeMaker;
import commandNode.Backward;
import commandNode.Forward;
import commandNode.Right;
import javafx.scene.paint.Color;
import nodes.Constant;
import nodes.HeadInterface;
import nodes.NodeInterface;

public class BelaniesMain {
	private static ArrayList<NodeInterface> nodes;

	public static void main (String [] args) {
		Model m = new Model(400,400);
		//Turtle t = new SingleTurtle(0.0, 0.0);
		CommandHistory CH = new CommandHistory();
		VariablesHistory VH = new VariablesHistory();
		nodes = new ArrayList<NodeInterface>();
		Right f = new Right(m, 1);
		nodes.add(f);
		Constant c = new Constant(10);
		nodes.add(c);
		TreeMaker tm  = new TreeMaker(nodes);
		ArrayList<HeadInterface> heads = (ArrayList<HeadInterface>) tm.getHeads();
		TreeEvaluator te = new TreeEvaluator();
		System.out.println(te.evaluate(heads));
	}

}
