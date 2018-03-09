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
	private Map<String,Pattern> myTranslation;
	private static boolean NEW_COMMAND = true;
	private Map<String,Pattern> regex;
	private Map<String,Integer> children;
	private String languageFilePath;
	private static final String REGEX_FILE = "parsers/regex";
	private static final String NODE_PACKAGE = "commandNode.";
	private Model model;
	private VariablesHistory varHistory;
	private CommandHistory comHistory;
	private String lang;
	private NodeFactory nodeMaker;
	
	/**
	 * Class Constructor
	 * Creates the two hashmaps for the syntax recognition and command recognition
	 * given the current language.
	 * 
	 * @param t the current turtle
	 * @param VH the variable history
	 * @param CH the command history
	 */
	public Parser(Model m, VariablesHistory VH, CommandHistory CH)
	{
		System.out.println(VH.hashCode());
		myTranslation = new HashMap<>();

		regex = new HashMap<>();
		addResources(REGEX_FILE, regex);
		
		children = new HashMap<>();
		ResourceBundle numChildren = ResourceBundle.getBundle("parsers/numChildren");
		Enumeration<String> keys = numChildren.getKeys();
		while(keys.hasMoreElements())
		{
			String key = keys.nextElement();
			children.put(key, Integer.parseInt(numChildren.getString(key)));	
		}
		
//		nodeMaker = new NodeFactory(t, VH);
		model = m;
		varHistory = VH;
		comHistory = CH;
		lang = "English";
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
	 * @param command The string command from the GUI
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
		System.out.println("hi");
		languageFilePath = "resources.languages/" + lang;
		addResources(languageFilePath, myTranslation);

		if(NEW_COMMAND)
		{
			comHistory.addCommand(command);
		}
		
		NEW_COMMAND = true;

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
					
					if (key.equals("Command"))
					{
						if (varHistory.getCommandKeys().contains(text))
						{
							CustomCommand n = varHistory.getCommand(text);
							nodeList.add(n);
						}
						else if(nodeList.size() > 0 && nodeList.get(i-1) instanceof MakeUserInstruction)
						{
							CustomCommand n = new CustomCommand(text,varHistory);
							nodeList.add(n);
						}
						else
						{
							String commandType = checkLanguage(text);
							try 
							{
								GeneralCommand n = (GeneralCommand) NodeFactory.makeNode(Class.forName(NODE_PACKAGE + commandType), model, children.get(commandType));
								nodeList.add(n);
							}
							catch(ClassNotFoundException e)
							{
								comHistory.addCommand("Error: Could not access constructor for command " + text );
								throw new InvalidEntryException("Error: Could not access Node constructor");
							}
						}
							
					}
					
//					nodeList.add(NodeFactory.makeNode(text, key, varHistory));
					//add these to node factory
					//parser isnt necesarily making nodes
					else if (key.equals("Constant"))
					{
						Constant n = new Constant(Integer.parseInt(text));
						nodeList.add(n);
					}
					else if(key.equals("Variable"))
					{
						Variable n = new Variable(text.substring(1), varHistory);
						nodeList.add(n);
					}
					else if(key.equals("List"))
					{
						ListNode l = new ListNode();
						String noBrackets = text.substring(1,text.length()-1);
						String trimmed = noBrackets.trim();
						NEW_COMMAND = false;
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
						NEW_COMMAND = false;
						List<NodeInterface> listNodes = parseString(trimmed);
						NodeInterface com = listNodes.get(0);
						String firstCommand =  com.getClass().toString().substring(com.getClass().toString().indexOf(".") + 1).trim();
						int numChildren = children.get(firstCommand);
						System.out.println(numChildren);
						UnlimitedCommand l = new UnlimitedCommand(com, numChildren);
						for(int j = 1; j < listNodes.size(); j++)
						{
							l.add(listNodes.get(j));
						}
						nodeList.add(l);
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

	/**
	 * Given a command in any language will return the appropriate command type
	 * Checks for invalid command errors
	 * 
	 * @param command string identified as command syntax in a user input
	 * @return a string indicating the appropriate command type
	 * @throws InvalidEntryException didnt match any of the commands in the given language
	 */
	private String checkLanguage(String command)
	{
		for (String key: myTranslation.keySet())
		{
			if(myTranslation.get(key).matcher(command).matches())
			{
				return key;
			}
		}
		comHistory.addCommand("Error: Cannot recognize language");
		throw new InvalidEntryException("Error: Cannot recognize language");
	}
}
