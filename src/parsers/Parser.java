package parsers;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import Tree.TreeEvaluator;
import Tree.TreeMaker;
import commandNode.MakeUserInstruction;
import model.Turtle;
import model.VariablesHistory;
import model.CommandHistory;
import model.Model;
import nodes.*;
import propertiesFiles.ResourceBundleManager;

/**
 * @author Belanie Nagiel
 *
 * This class takes in command strings and outputs them as lists of nodes so that the the tree building class
 * evaluate the command. It also throws errors for incorrect syntax and use of words that are not recognized
 * as commands.
 *
 */
public class Parser
{
	protected Map<String,Pattern> myTranslation;
	private boolean newCommand = true;
	private Map<String,Pattern> regex;
	protected Map<String,Integer> children;
	private static final String REGEX_FILE = "parsers/regex";
	private Model model;
	private VariablesHistory varHistory;
	private CommandHistory comHistory;
	private String lang;

	/**
	 * Class Constructor
	 * Creates the two hashmaps for the syntax recognition and command recognition
	 * given the current language.
	 *
	 * @param m the current model
	 * @param VH the variable history
	 * @param CH the command history
	 */
	public Parser(Model m, VariablesHistory VH, CommandHistory CH)
	{
		myTranslation = new HashMap<>();

		regex = new HashMap<>();
		addResources(REGEX_FILE, regex);

		createChildrenMap();

		model = m;
		varHistory = VH;
		comHistory = CH;
		lang = "English";
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

	/**
	 * Creates a hashmap given a file path to a properties file and a map to fill. Used to
	 * create key value pairs of syntax to patterns and commands to patterns in the given
	 * language
	 *
	 * @param filepath
	 * @param map
	 */
	public void addResources(String filepath, Map<String,Pattern> map)
	{
		ResourceBundle language = ResourceBundle.getBundle(filepath);
		Enumeration<String> keys = language.getKeys();
		while(keys.hasMoreElements())
		{
			String key = keys.nextElement();
			map.put(key, Pattern.compile(language.getString(key), Pattern.CASE_INSENSITIVE));
		}
	}

	/**
	 * Creates a list of nodes out of the string command given as an argument
	 *
	 * @param language The language from the GUI
	 * @return a list of nodes that the tree builder can use to create the tree
	 * @throws ClassNotFoundException
	 * @throws InvalidEntryException
	 */
	public void setLanguage(String language)
	{
		lang = language;
	}

	public List<NodeInterface> parseString(String command)
	{
		String languageFilePath = "resources.languages/" + lang;
		addResources(languageFilePath, myTranslation);
		if(newCommand)
		{
			comHistory.addCommand(command);
		}
		newCommand = true;
		int commentIndex = command.indexOf("#");
		if (commentIndex >= 0)
		{
			command = command.substring(0, commentIndex);
		}
		String[] commandList = command.trim().split("\\s+(?![^\\[]*\\])(?![^\\(]*\\))");
		List<NodeInterface> nodeList = new ArrayList<>();
		checkSyntax(commandList, nodeList);
		return nodeList;
	}

	public void passActionCommand(String command)
	{
		String oldLanguage = lang;
		setLanguage(ResourceBundleManager.retrieveOnScreenCommand("DEFAULT_LANGUAGE"));
		List<NodeInterface> fromButton = parseString(command);
		makeTree(fromButton);
		setLanguage(oldLanguage);
	}

	public void passTextCommand(String command){
		List<NodeInterface> nodeList = this.parseString(command);
		this.makeTree(nodeList);
	}


	public void makeTree(List<NodeInterface> nodeList)
	{
		TreeMaker tm  = new TreeMaker(nodeList);
		ArrayList<HeadInterface> heads = (ArrayList<HeadInterface>) tm.getHeads();
		TreeEvaluator te = new TreeEvaluator();
		te.evaluate(heads);
	}

	/**
	 * fill the nodeList with the appropriate nodes based on matching string input to node types
	 * also checks for syntax errors
	 *
	 * @param commandList the list of strings given by the user
	 * @param nodeList the empty nodeList that will be filled
	 */
	private void checkSyntax(String[] commandList, List<NodeInterface> nodeList)
	{
		for (int i = 0; i<commandList.length; i++)
		{
			String text = commandList[i];
			boolean match = false;
			for (String key: regex.keySet())
			{
				if(regex.get(key).matcher(text).matches())
				{
					match = true;
					//nf.makeToken(key, text, varHistory, model, children.get(commandType));
					if(key.equals("Command") && !nodeList.isEmpty() && nodeList.get(i-1) instanceof MakeUserInstruction)
					{
						CustomCommand n = new CustomCommand(text,varHistory);
						nodeList.add(n);
					}
					else if(key.equals("List"))
					{
						ListNode l = new ListNode();
						String noBrackets = text.substring(1,text.length()-1);
						String trimmed = noBrackets.trim();
						newCommand = false;
						List<NodeInterface> listNodes = parseString(trimmed);
						for(NodeInterface ln: listNodes)
						{
							l.add(ln);
						}
						nodeList.add(l);
					}
					else if(key.equals("InfiniteCommand"))
					{
						String noParentheses = text.substring(1,text.length()-1);
						String trimmed = noParentheses.trim();
						newCommand = false;
						List<NodeInterface> listNodes = parseString(trimmed);
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
					else
					{
						NodeInterface node = NodeFactory.createNode(key, text, varHistory, model, myTranslation, comHistory, children);
						nodeList.add(node);
					}
				}
			}
			if(!match)
			{
				comHistory.addCommand("Error: Invalid entry, no command " + text );
				throw new InvalidEntryException("Error: Invalid entry, no such command");
			}
		}

	}

}
