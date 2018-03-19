package parsers;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import model.CommandHistory;
import model.Model;
import model.VariablesHistory;
import nodes.Constant;
import nodes.NodeInterface;

public class ConstantCreation extends NodeCreation {

	public ConstantCreation(CommandHistory comHistory, VariablesHistory varHistory) {
		super(comHistory, varHistory);
	}

	@Override
	public void makeNode(String text, Model model, String language, List<NodeInterface> nodeList) 
	{
		nodeList.add(new Constant(Integer.parseInt(text)));
	}

}
