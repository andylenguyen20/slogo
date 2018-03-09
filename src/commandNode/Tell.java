package commandNode;

import model.Model;
import model.Turtle;
import nodes.GeneralCommand;
import nodes.NodeInterface;
import nodes.ListNode;

import java.util.List;

public class Tell extends GeneralCommand {

    public Tell(Model model, int numChildren) {
        super(model, numChildren);
    }

    @Override
    public double evaluate(List<NodeInterface> args) {
        ListNode turtles = (ListNode) args.get(0);
        int i = 0;
        model.getActiveTurtles().removeAll(model.getActiveTurtles());
        while (turtles.getElement(i)!=null) {
            NodeInterface node = turtles.getElement(i);
            value = node.getValue();
            if (model.getAllTurtles().containsKey(value)) {
                Turtle t = model.getAllTurtles().get(value);
                if (!model.getActiveTurtles().contains(t)) { model.addActiveTurtle(t); }
            }
            else {
                model.addTurtle(value);
            }
            i++;
        }
        return value;
    }
}
