package parsers;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import model.CommandHistory;
import model.Model;
import model.VariablesHistory;
import nodes.NodeInterface;
import nodes.UnlimitedCommand;

public class InfiniteCommandCreation extends NodeCreation{
	private CommandHistory commandHistory;
	private VariablesHistory variablesHistory;
	private Map<String,Integer> children;
	
	public InfiniteCommandCreation(CommandHistory comHistory, VariablesHistory varHistory) {
		super(comHistory, varHistory);
		commandHistory = comHistory;
		variablesHistory = varHistory;
		createChildrenMap();
	}

	@Override
	public void makeNode(String text, Model model, String language, List<NodeInterface> nodeList) {
		String noParentheses = text.substring(1,text.length()-1);
		String trimmed = noParentheses.trim();
		//newCommand = false;
		Parser newParser = new Parser(model, variablesHistory, commandHistory);
		newParser.setLanguage(language);
		List<NodeInterface> listNodes = newParser.parseString(trimmed);
		NodeInterface com = listNodes.get(0);
		String firstCommand =  com.getClass().toString().substring(com.getClass().toString().indexOf(".") + 1).trim();
		int numChildren = children.get(firstCommand);
		//System.out.println(numChildren);
		UnlimitedCommand l = new UnlimitedCommand(com, numChildren);
		for(int j = 1; j < listNodes.size(); j++)
		{
			l.add(listNodes.get(j));
		}
		nodeList.add(l);
	}
	
	private void createChildrenMap()
	{
		children = new HashMap<>();
		ResourceBundle numChildren = ResourceBundle.getBundle("parsers/numChildren");
		Enumeration<String> keys = numChildren.getKeys();
		while(keys.hasMoreElements())
		{
			String key = keys.nextElement();
			children.put(key, Integer.parseInt(numChildren.getString(key)));
		}

	}

}
