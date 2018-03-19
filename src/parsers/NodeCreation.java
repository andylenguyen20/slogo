package parsers;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import model.CommandHistory;
import model.Model;
import model.VariablesHistory;
import nodes.NodeInterface;

public abstract class NodeCreation 
{
	public NodeCreation(CommandHistory comHistory, VariablesHistory varHistory) 
	{
	}
	
	public abstract void makeNode(String text, Model model, String language, List<NodeInterface> nodeList);

}
