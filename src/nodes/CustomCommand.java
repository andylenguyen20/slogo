package nodes;

import java.util.ArrayList;

import model.VariablesHistory;

import java.util.List;

/**
 * This is the command class which represent a custom command, allowing it to be called
 * in the future. It extends the variables abstract class because its value will need
 * to be stored for future use. It is a head of a tree as it can be called on its own, despite
 * having no children.
 *
 * author: Charles Dracos
 */
public class CustomCommand extends Variables implements HeadInterface, NodeInterface {
    private ArrayList<ListNode> lists;
    private double value;
    /**
     * Creates a new CustomCommand node
     *
     * @param name name of the custom command
     * @param VH the variable history to track all custom commands
     */
    public CustomCommand(String name, VariablesHistory VH) {
        super(name, VH);
        lists = new ArrayList<>();
        value = 0.0;
    }

    public double getValue() {
        return value; //returns the value of evaluate the commands
    }

    /**
     * Adds a list to the array of list nodes a custom command stores, to be used
     * later in the program. A custom command uses lists to define what it does, as can
     * be seen in the documentation page for SLogo.
     *
     * @param n ListNode to be added
     */
    @Override
    public void add(NodeInterface n) {
        try {
            ListNode l = (ListNode) n; //casts to a list node
            lists.add(l);
        }
        catch (ClassCastException e) {
            e.printStackTrace();
            throw new ImproperNodeException(); //catches when a non list node is attempted to be added
        }
    }

    /**
     * A list node evaluates by calling the commands list that it was originally declared with
     *
     * @param args the arguments to be used, which in the custom command case is null
     * @return value of the last command executed in its command list
     */
    @Override
    public double evaluate(List<NodeInterface> args) {
        ListNode commands = lists.get(1);
        commands.evaluate();
        value = commands.getValue();
        return value;
    }
}
