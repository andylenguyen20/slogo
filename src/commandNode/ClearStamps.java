package commandNode;

import model.Model;
import nodes.GeneralCommand;
import nodes.NodeInterface;

import java.util.List;

public class ClearStamps extends GeneralCommand {

    public ClearStamps (Model m, int numChildren) {
        super(m, numChildren);
    }


    @Override
    public double evaluate(List<NodeInterface> args) {
        return model.clearStamps();
    }
}
