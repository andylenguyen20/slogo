package parsers;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import model.CommandHistory;
import model.Model;
import model.VariablesHistory;
import nodes.ListNode;
import nodes.NodeInterface;
/**
 * Masterpiece Comments: This is one subclass of node creation that demonstrates how 
 * the parser is able to use polymorphism to call the make node method without
 * worrying about which kind of node it was referring to. 
 * 
 * @author Belanie Nagiel
 * 
 * This class is a subclass of the NodeCreation super class that deals
 * with creating a List node given information from the parser.
 */
public class ListCreation extends NodeCreation {
	private CommandHistory commandHistory;
	private VariablesHistory variablesHistory;
	
	/**
	 * Class Constructor
	 * 
	 * @param comHistory
	 * @param varHistory
	 */
	public ListCreation(CommandHistory comHistory, VariablesHistory varHistory) {
		super(comHistory, varHistory);
		commandHistory = comHistory;
		variablesHistory = varHistory;
	}

	/**
	 * Creates a list node and adds the appropriate children to the list node
	 * based on what command is within the brackets of the list. Adds the list 
	 * node to the current nodeList from Parser
	 * 
	 * @param text
	 * @param model
	 * @param language
	 * @param nodeList
	 */
	@Override
	public void makeNode(String text, Model model, String language, List<NodeInterface> nodeList) {
		ListNode list = new ListNode();
		String trimmed = text.substring(1,text.length()-1).trim();
		Parser newParser = new Parser(model, variablesHistory, commandHistory);
		newParser.setLanguage(language);
		List<NodeInterface> listNodes = newParser.parseString(trimmed);
		for(NodeInterface ln: listNodes)
		{
			list.add(ln);
		}
		nodeList.add(list);
	}
}
