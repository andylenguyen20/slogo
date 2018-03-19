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
	private VariablesHistory varHistory;
	private CommandHistory comHistory;
	private String lang = "English";

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
		regex = new HashMap<>();
		addResources(REGEX_FILE, regex);
		model = m;
		varHistory = VH;
		comHistory = CH;
	}

	/**
	 * Creates a hashmap given a file path to a properties file and a map to fill. Used to
	 * create key value pairs of syntax to patterns and commands to patterns in the given
	 * language
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

	public void passActionCommand(String command)
	{
		String oldLanguage = lang;
		setLanguage(ResourceBundleManager.retrieveOnScreenCommand("DEFAULT_LANGUAGE"));
		comHistory.addCommand(command);
		List<NodeInterface> fromButton = parseString(command);
		makeTree(fromButton);
		setLanguage(oldLanguage);
	}

	public void passTextCommand(String command){
		comHistory.addCommand(command);
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
					buildNodeList(key,text,nodeList);
				}
			}
			if(!match)
			{
				comHistory.addCommand("Error: Invalid entry, no command " + text );
				throw new InvalidEntryException("Error: Invalid entry, no such command");
			}
		}

	}

	private void buildNodeList(String key, String text, List<NodeInterface> nodeList) 
	{
			try 
			{
				Constructor<?> c = Class.forName(CREATION_PACKAGE + key + "Creation").getConstructor(new Class[] {CommandHistory.class,VariablesHistory.class});
				c.setAccessible(true);
				((NodeCreation) c.newInstance(comHistory, varHistory)).makeNode(text, model, lang, nodeList);
			} 
			catch (Exception e) 
			{
				comHistory.addCommand("Error: Could not evaluate command");
				throw new InvalidEntryException("Error: Could not evaluate command");
			}
	}

}
