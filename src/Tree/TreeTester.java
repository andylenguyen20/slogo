package Tree;

import java.util.ArrayList;

import nodes.*;
import commandNode.*;
import model.Turtle;
import model.VariableHistory;
import model.CommandHistory;

import javafx.scene.paint.Color;

public class TreeTester {
	private static ArrayList<NodeI> nodes;

	public static void main (String [] args) {
		Turtle t = new Turtle(0.0, 0.0, Color.WHITE);
		CommandHistory CH = new CommandHistory();
		VariableHistory VH = new VariableHistory();
		nodes = new ArrayList<NodeI>();
		Forward f = new Forward(t, 1);
		nodes.add(f);
		Constant c = new Constant(10);
		nodes.add(c);
		TreeMaker tm  = new TreeMaker(nodes);
		ArrayList<HeadI> heads = (ArrayList<HeadI>) tm.getHeads();
		TreeEvaluator te = new TreeEvaluator();
		System.out.println(te.evaluate(heads));

	}
}

