package parsers;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import model.CommandHistory;
import model.Model;
import model.VariablesHistory;
import nodes.NodeInterface;
import nodes.Variable;

public class VariableCreation extends NodeCreation{
	private VariablesHistory variablesHistory;
	
	public VariableCreation(CommandHistory comHistory, VariablesHistory varHistory) {
		super(comHistory, varHistory);
		variablesHistory = varHistory;
	}

	@Override
	public void makeNode(String text, Model model, String language, List<NodeInterface> nodeList) {
		nodeList.add(new Variable(text.substring(1), variablesHistory));
	}

}
