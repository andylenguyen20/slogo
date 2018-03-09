package Tree;

import java.util.ArrayList;
import java.util.List;

import nodes.HeadInterface;
import nodes.NodeInterface;
import nodes.CommandInterface;

/**
 * This is the class that evaluates the tree of commands after created by the TreeMaker. It uses
 * recursion to evaluate the bottom nodes first, using their values for later use by higher
 * command nodes, accessing the children and adding them as arguments for their parents.
 * It returns a return value for the most recently done operation, to be used by the program.
 *
 * author: Charles Dracos
 */
public class TreeEvaluator {
	private Double returnValue;

	/**
	 * Empty constructor for TreeEvaluator, initializing the return value to zero
	 */
	public TreeEvaluator () {
		returnValue = 0.0;
	}

	/**
	 * Evaluates the series of head nodes, calling evaluateHead for each head of a tree
	 * which is represented in the List passed to it
	 * @param heads List of head nodes of trees
	 * @return the most recently evaluated command node's value
	 */
	public double evaluate(List<HeadInterface> heads) {
		for (int i = 0; i < heads.size(); i++) {
			if (heads.get(i) instanceof CommandInterface) {
				CommandInterface head = (CommandInterface) heads.get(i);
				evaluateHead(head); //evaluates a new tree head
			}
			else { returnValue = heads.get(i).evaluate(null); }
		}
		return returnValue;
	}

	/**
	 * Recursively evaluates a tree, going bottom up evaluating and adding children as
	 * arguments for their parent nodes to use to evaluate
	 * @param node Node being evaluated
	 */
	private void evaluateHead(CommandInterface node) {
		ArrayList<NodeInterface> nArgs = new ArrayList<>(); //arguments to be passed to a commandNode
		while (node.hasNext()) {	 //if there is a child node
			NodeInterface curr = node.getNext(); //curr is set to the child
			if (curr instanceof CommandInterface) {
				//if the child is another command, cast and enter recursion
				CommandInterface head = (CommandInterface) curr;
				evaluateHead(head);
				nArgs.add(curr);
			}
			else {
				//if not another command add to the args
				nArgs.add(curr);
			}
		}
		node.reset(); //sets the node's pointer to its first child for later use
		returnValue = node.evaluate(nArgs); //returnValue set to the return of evaluate of a node
	}
}
