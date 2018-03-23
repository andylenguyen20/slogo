package parsers;

import java.lang.reflect.Constructor;
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
 * Masterpiece Comments: I think this version of the Parser is better designed, because it 
 * 
 * @author Belanie Nagiel
 *
 * This class takes in command strings and outputs them as lists of nodes so that the the tree building class
 * evaluate the command. It also throws errors for incorrect syntax and use of words that are not recognized
 * as commands.
 *
 */
public class Parser
{
	private Map<String,Pattern> regex;
	private final String REGEX_FILE = "parsers/regex";
	private final String CREATION_PACKAGE = "parsers.";
	private Model model;
	private VariablesHistory variablesHistory;
	private CommandHistory commandHistory;
	private String lang = "English";

	/**
	 * Class Constructor
	 * Creates the map of the data type regular expressions to the data type and initializes 
	 * private for the model, variable history, and command history.
	 *
	 * @param m the current model
	 * @param VH the variable history
	 * @param CH the command history
	 */
	public Parser(Model m, VariablesHistory VH, CommandHistory CH)
	{
		regex = new HashMap<>();
		addResources(REGEX_FILE, regex);
		model = m;
		variablesHistory = VH;
		commandHistory = CH;
	}

	/**
	 * Creates a hashmap given a file path to a properties file and a map to fill. Used to
	 * create key value pairs of data type syntax to patterns
	 *
	 * @param filepath
	 * @param map
	 */
	private void addResources(String filepath, Map<String,Pattern> map)
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
	 * Sets the language that the parser will be translating commands in.
	 * 
	 * @param language
	 */
	public void setLanguage(String language)
	{
		lang = language;
	}

	/**
	 * Takes in a command string, removes any comments included in the command, and 
	 * sends a split version of the string to be turned into nodes for the tree
	 * 
	 * @param command
	 * @return
	 */
	protected List<NodeInterface> parseString(String command)
	{
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

	/**
	 * Parses a command that is activated by pressing a button on the view. Sets the language for
	 * the command recognition to the default language for buttons and creates the tree based 
	 * on the list of nodes from parse string.
	 * @param command
	 */
	public void passActionCommand(String command)
	{
		String oldLanguage = lang;
		setLanguage(ResourceBundleManager.retrieveOnScreenCommand("DEFAULT_LANGUAGE"));
		commandHistory.addCommand(command);
		List<NodeInterface> fromButton = parseString(command);
		makeTree(fromButton);
		setLanguage(oldLanguage);
	}

	/**
	 * Parses a command that is given by a string of user input and 
	 * creates a tree based on the list of nodes from parseString
	 * 
	 * @param command
	 */
	public void passTextCommand(String command){
		commandHistory.addCommand(command);
		List<NodeInterface> nodeList = parseString(command);
		makeTree(nodeList);
	}


	/**
	 * Takes in the node list created by parsre and creates and evaluates
	 * the appropriate tree in order to process the command
	 * 
	 * @param nodeList
	 */
	public void makeTree(List<NodeInterface> nodeList)
	{
		TreeMaker tm  = new TreeMaker(nodeList);
		ArrayList<HeadInterface> heads = (ArrayList<HeadInterface>) tm.getHeads();
		TreeEvaluator te = new TreeEvaluator();
		te.evaluate(heads);
	}

	/**
	 * Matches string inputs to their appropriate data types and sends information 
	 * to the buildNodeList class in order to create the node list.
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
					buildNodeList(key,text,nodeList);
				}
			}
			if(!match)
			{
				commandHistory.addCommand("Error: Invalid entry, no command " + text );
				throw new InvalidEntryException("Error: Invalid entry, no such command");
			}
		}

	}

	/**
	 * Uses reflection to create the proper node creation object for a specific user input. 
	 * Adds the node created to the node list that will be used to create the tree.
	 * 
	 * @param key
	 * @param text
	 * @param nodeList
	 */
	private void buildNodeList(String key, String text, List<NodeInterface> nodeList) 
	{
			try 
			{
				Constructor<?> c = Class.forName(CREATION_PACKAGE + key + "Creation").getConstructor(new Class[] {CommandHistory.class,VariablesHistory.class});
				c.setAccessible(true);
				((NodeCreation) c.newInstance(commandHistory, variablesHistory)).makeNode(text, model, lang, nodeList);
			} 
			catch (Exception e) 
			{
				commandHistory.addCommand("Error: Could not evaluate command");
				throw new InvalidEntryException("Error: Could not evaluate command");
			}
	}

}
