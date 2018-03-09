package Tree;

import java.util.ArrayList;

import model.VariablesHistory;
import model.Model;
import nodes.*;
import commandNode.*;
import model.Turtle;
import model.CommandHistory;

import javafx.scene.paint.Color;

public class TreeTester {
	private static ArrayList<NodeInterface> nodes;

	public static void main (String [] args) {
		//Turtle t = new Turtle(0.0, 0.0, Color.WHITE, 1);
		Model m = new Model(100, 100);
		//m.addTurtle(t);
		//CommandHistory CH = new CommandHistory();
		//VariablesHistory VH = new VariablesHistory();
		nodes = new ArrayList<NodeInterface>();
		Tell t = new Tell(m, 1);
		nodes.add(t);
		ListNode ln = new ListNode();
		Constant c1 = new Constant(1);
		Constant c2 = new Constant(2);
		ln.add(c1);
		ln.add(c2);
		nodes.add(ln);
		Ask a = new Ask(m, 2);
		nodes.add(a);
		ListNode ln2 = new ListNode();
		ln2.add(c1);
		ln2.add(c2);
		nodes.add(ln2);
		ListNode ln3 = new ListNode();
		Forward f = new Forward(m, 1);
		ID id = new ID (m, 0);
		ln3.add(f);
		ln3.add(id);
		nodes.add(ln3);
		TreeMaker tm  = new TreeMaker(nodes);
		ArrayList<HeadInterface> heads = (ArrayList<HeadInterface>) tm.getHeads();
		TreeEvaluator te = new TreeEvaluator();
		System.out.println(te.evaluate(heads));
	}
}

