package nodes;

import java.util.ArrayList;

import Tree.TreeEvaluator;
import Tree.TreeMaker;

/**
 *  This is the List class. A list stores an array of other types of
 *  nodes and allows methods to call to the list evaluate to execute
 *  commands if it is a list of commands or get the variable elements
 *  if it is a list of variables.
 *
 *  author: Charles Dracos
 */
public class ListNode implements NodeInterface {
    private ArrayList<NodeInterface> elements;
    private double value;
    private TreeEvaluator te;
    private ArrayList<HeadInterface> heads;

    /**
     * ListNode has no parameters in its constructor as it has no children to be reached by
     * the treeMaker/evaluator and does not need to directly call the turtle.
     */
    public ListNode() {
        elements = new ArrayList<>();
        value = -1.0;
        te = new TreeEvaluator();
    }

    /**
     * Returns the node at a given index for use by other operations
     * @param index which index in the list to be accessed
     * @return node at the given index
     */
    public NodeInterface getElement (int index) {
        try {
            return elements.get(index);
        }
        catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    /**
     * Adds a node to the list
     * @param n node to be added
     */
    public void add(NodeInterface n) {
        elements.add(n);
    }

    /**
     * Using its own parameters creates a tree and then evaluates it
     * @return value done by last command
     */
    public double evaluate() {
        if (value < 0) {
            TreeMaker tm = new TreeMaker(elements);
            heads = (ArrayList<HeadInterface>) tm.getHeads();
        }
        value = te.evaluate(heads);
        return value;
    }

    @Override
    public double getValue() {
        return value; //returns value
    }
}