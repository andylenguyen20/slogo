package parsers;

import java.lang.reflect.Constructor;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import commandNode.MakeUserInstruction;
import model.CommandHistory;
import model.Model;
import model.VariablesHistory;
import nodes.CustomCommand;
import nodes.GeneralCommand;
import nodes.NodeInterface;

public class CommandCreation extends NodeCreation {
	private CommandHistory commandHistory;
	private VariablesHistory variablesHistory;
	private Map<String,Pattern> myTranslation2;
	private Map<String,Integer> children;
	private static final String NODE_PACKAGE = "commandNode.";
	
	public CommandCreation(CommandHistory comHistory, VariablesHistory varHistory) {
		super(comHistory, varHistory);
		commandHistory = comHistory;
		variablesHistory = varHistory;
		myTranslation2 = new HashMap<>();
		createChildrenMap();
		
	}

	@Override
	public void makeNode(String text, Model model, String language, List<NodeInterface> nodeList) {
		String languageFilePath = "resources.languages/" + language;
		System.out.println(language);
		if(!nodeList.isEmpty() && nodeList.get(nodeList.size()-1) instanceof MakeUserInstruction)
		{
			nodeList.add(new CustomCommand(text,variablesHistory));
		}
		if (variablesHistory.getCommandKeys().contains(text))
		{
			nodeList.add(variablesHistory.getCommand(text));
		}
		else
		{
			createLanguageMap(languageFilePath);
			String commandType = checkLanguage(text);
			try 
			{
				nodeList.add((GeneralCommand) newNode(Class.forName(NODE_PACKAGE + commandType), model, children.get(commandType)));
			}
			catch(Exception e)
			{
				commandHistory.addCommand("Error: Could not access constructor for command " + text );
				throw new InvalidEntryException("Error: Could not access Node constructor");
			}
			
		}
	}
	
	private Object newNode(Class<?> clazz,Model model, int numChildren)
	{
		try
		{
			Constructor<?> c = clazz.getConstructor(new Class[] {Model.class,Integer.TYPE});
			c.setAccessible(true);
			Object o = c.newInstance(model, numChildren);
			return o;
		}
		catch(Exception e) 
		{
			commandHistory.addCommand("Error: Invalid entry, no such command" );
			throw new InvalidEntryException("Error: Invalid entry, no such command");
		}
	}
	
	private String checkLanguage(String command)
	{
		for (String key: myTranslation2.keySet())
		{
			if(myTranslation2.get(key).matcher(command).matches())
			{
				return key;
			}
		}
		commandHistory.addCommand("Error: Cannot recognize language");
		throw new InvalidEntryException("Error: Cannot recognize language");
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
	
	private void createLanguageMap(String filepath)
	{
		ResourceBundle lang = ResourceBundle.getBundle(filepath);
		Enumeration<String> keys = lang.getKeys();
		while(keys.hasMoreElements())
		{
			String key = keys.nextElement();
			myTranslation2.put(key, Pattern.compile(lang.getString(key), Pattern.CASE_INSENSITIVE));
		}
	}

}
