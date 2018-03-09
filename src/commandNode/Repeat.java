package commandNode;

import model.Model;
import nodes.GeneralCommand;
import nodes.NodeInterface;
import nodes.ListNode;
import nodes.ImproperNodeException;

import java.util.List;

/**
 * Implements REPEAT as describe on the course website
 */

public class Repeat extends GeneralCommand {

    public Repeat (Model model, int numChildren) {
        super(model, numChildren);
    }

    @Override
    public double evaluate(List<NodeInterface> args) {
        try {
            double d = args.get(0).getValue();
            for (int i = 0; i < d; i++) {
                ListNode l = (ListNode) args.get(1);
                value = l.evaluate();
            }
            return value;
        }
        catch (ClassCastException e) {
            e.printStackTrace();
            throw new ImproperNodeException();
        }
    }
}
