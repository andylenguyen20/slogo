package commandNode;

import nodes.GeneralCommand;

import model.Model;
import model.Turtle;

import nodes.NodeInterface;
import nodes.ListNode;

import java.util.List;
import java.util.ArrayList;

public class Ask extends GeneralCommand {

    public Ask (Model m, int numChildren) { super (m, numChildren); }

    @Override
    public double evaluate(List<NodeInterface> args) {
        ListNode turtles = (ListNode) args.get(0);
        ListNode commands = (ListNode) args.get(1);

        List<Turtle> tempActive = new ArrayList<>();
        List<Turtle> oldActive = model.getActiveTurtles();

        int i = 0;
        while (turtles.getElement(i) != null ) {
            Turtle newActive = model.getAllTurtles().get(turtles.getElement(i).getValue());
            tempActive.add(newActive);
            i++;
        }

        model.setActiveTurtles(tempActive);
        model.update((t) -> command(commands));
        model.setActiveTurtles(oldActive);

        return value;
    }

    public void command (ListNode commands) {
        value = commands.evaluate();
    }
}
