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
import model.Turtle;
import model.VariableHistory;
import nodes.Constant;
import nodes.Node;
import nodes.NodeFactory;
import nodes.Variable;

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
	
	//possibly change to list because order of checking regex matters
	private Map<String,Pattern> regex;
	private Map<String,Integer> children;
	private String languageFilePath;
	private static final String REGEX_FILE = "parsers/regex";
	private static final String NODE_PACKAGE = "commandNode.";
	private Turtle turt;
	private VariableHistory history;
	
	/**
	 * Class Constructor
	 * Creates the two hashmaps for the syntax recognition and command recognition
	 * given the current language.
	 * 
	 * @param t the current turtle
	 * @param language the current language
	 */
	public Parser(Turtle t, String language)
	{
		regex = new HashMap<>();
		addResources(REGEX_FILE, regex);
		
		languageFilePath = "resources.languages/" + language;
		myTranslation = new HashMap<>();
		addResources(languageFilePath, myTranslation);
		
		children = new HashMap<>();
		ResourceBundle numChildren = ResourceBundle.getBundle("parsers/numChildren");
		Enumeration<String> keys = numChildren.getKeys();
		while(keys.hasMoreElements())
		{
			String key = keys.nextElement();
			children.put(key, Integer.parseInt(numChildren.getString(key)));	
		}
		turt = t;
		history = new VariableHistory();
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
	public List<Node> parseString(String command)
	{
		System.out.println("hi");
		String[] commandList = command.trim().split("\\s+(?![^\\[]*\\])");
		for(String s: commandList)
		{
			System.out.println(s);
		}
		List<Node> nodeList = new ArrayList<>();
		
		checkSyntax(commandList, nodeList);
		
		//check this
//		TreeMaker tm  = new TreeMaker(nodeList);
//		ArrayList<Node> heads = (ArrayList<Node>) tm.getHeads();
//		TreeEvaluator te = new TreeEvaluator(heads);
//		System.out.println(te.getValue());
		
		return nodeList;
		
	}
	
	/**
	 * fill the nodeList with the appropriate nodes based on matching string input to node types
	 * also checks for syntax errors
	 * 
	 * @param commandList the list of strings given by the user
	 * @param nodeList the empty nodeList that will be filled
	 * @throws ClassNotFoundException can't find the node class
	 * @throws InvalidEntryException didn't match any of entry types
	 */
	private void checkSyntax(String[] commandList, List<Node> nodeList)
	{
		for (int i = 0; i<commandList.length; i++)
		{
			String text = commandList[i];
			boolean match = false;
			for (String key: regex.keySet())
			{
				if(regex.get(key).matcher(text).matches())
				{
					//System.out.println("matched" + key);
					match = true;
					if (key.equals("Command"))
					{
						String commandType = checkLanguage(text);
						try 
						{
							//System.out.println("got to command");
							//System.out.println(commandType);
							Node n = (Node)NodeFactory.makeNode(Class.forName(NODE_PACKAGE + commandType), turt, children.get(commandType));
							nodeList.add(n);
						}
						catch(ClassNotFoundException e)
						{
							throw new InvalidEntryException("Error: Could not access Node constructor");
						}
						
					}
					else if (key.equals("Constant"))
					{
						//System.out.println("got to constant");
						Node n = new Constant(Integer.parseInt(text));
						nodeList.add(n);
					}
					else if(key.equals("Variable"))
					{
						//System.out.println("got to variable");
						Node n = new Variable(text.substring(1), history);
						nodeList.add(n);
					}
				}
			}
			if(!match)
			{
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
		throw new InvalidEntryException("Error: Cannot recognize language");
	}
}
