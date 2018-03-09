package nodes;

import java.util.ArrayList;

import model.Model;

/**
 * This is the abstract class which defines a general command (non-custom command). A command node
 * has a certain number of children which it will operate upon. It calls the turtle when acting upon it,
 * stores its children and keeps track of how many children it should have. It can be the head of a tree,
 * and per all elements in a tree it is a node.
 *
 * author: Charlie Dracos
 */
public abstract class GeneralCommand implements HeadInterface, NodeInterface, CommandInterface {
    protected Model model;
    private ArrayList<NodeInterface> children;
    private int numChildren;
    private int current = 0;
    protected double value;

    /**
     * Constructor for a general command. Passes in the turtle it will act upon and
     * how many children the command should have.
     *
     * @param model the turtle called when doing operations
     * @param numChildren amount of children the command has
     */
    public GeneralCommand(Model model, int numChildren) {
        this.model = model;
        children = new ArrayList<>();
        this.numChildren = numChildren;
        value = 0.0;
    }

    /**
     * Gives the next available child node
     * @return next child node
     */
    public NodeInterface getNext() {
        NodeInterface nextChild = children.get(current);
        current++; //updates the current node to be accessed to the next child
        return nextChild;
    }

    /**
     * Sees if there is a child node to be accessed
     * @return true if there is a child, false is there is not
     */
    public boolean hasNext() {
        return current < numChildren;
    }

    /**
     * adds a child node
     * @param n node to be added
     */
    public void add(NodeInterface n) {
        current++;
        children.add(n);
    }

    public void reset() {
        current = 0; //sets the current back to the first child
    }

    @Override public double getValue() {
        return value; //returns value
    }
}
