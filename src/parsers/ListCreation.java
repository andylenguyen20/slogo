package parsers;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import model.CommandHistory;
import model.Model;
import model.VariablesHistory;
import nodes.ListNode;
import nodes.NodeInterface;

public class ListCreation extends NodeCreation {
	private CommandHistory commandHistory;
	private VariablesHistory variablesHistory;
	
	public ListCreation(CommandHistory comHistory, VariablesHistory varHistory) {
		super(comHistory, varHistory);
		commandHistory = comHistory;
		variablesHistory = varHistory;
	}

	@Override
	public void makeNode(String text, Model model, String language, List<NodeInterface> nodeList) {
		ListNode l = new ListNode();
		String noBrackets = text.substring(1,text.length()-1);
		String trimmed = noBrackets.trim();
		//newCommand = false;
		Parser newParser = new Parser(model, variablesHistory, commandHistory);
		newParser.setLanguage(language);
		List<NodeInterface> listNodes = newParser.parseString(trimmed);
		for(NodeInterface ln: listNodes)
		{
			l.add(ln);
		}
		nodeList.add(l);
	}

}
