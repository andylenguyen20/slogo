package parsers;

import java.lang.reflect.*;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import commandNode.MakeUserInstruction;
import model.CommandHistory;
import model.Model;
import model.Turtle;
import model.VariablesHistory;
import nodes.Constant;
import nodes.CustomCommand;
import nodes.GeneralCommand;
import nodes.NodeInterface;
import nodes.Variable;

/**
 * @author Belanie Nagiel
 * 
 * A rather messy class that tried to take some of the burden of node making off of Parser.
 * Handles command, variable, and constant nodes, and uses reflection to make command nodes.
 * Also handles language checking for nodes.
 *
 */
public class NodeFactory 
{	
	//private Map<String,Class[]> commandToParams;
	//private static String PARAMETER_FILE = "parsers/commandToConstructorParameters";
	private static final String NODE_PACKAGE = "commandNode.";
	
	public NodeFactory() 
	{
		
	}
	
	/**
	 * A messy method that tries to take some of the burden off of parser in terms of creating nodes.
	 * Handles the creation of command, variable, and constant nodes.
	 * 
	 * @param key
	 * @param text
	 * @param varHistory
	 * @param model
	 * @param myTranslation
	 * @param comHistory
	 * @param children
	 * 
	 * @return a node to add to node list
	 */
	public static NodeInterface createNode(String key, String text, VariablesHistory varHistory, Model model, Map<String, Pattern> myTranslation, CommandHistory comHistory, Map<String, Integer> children) 
	{
		// TODO Auto-generated method stub
		if (varHistory.getCommandKeys().contains(text))
		{
			return varHistory.getCommand(text);
		}
		else if (key.equals("Command"))
		{
			String commandType = checkLanguage(text, myTranslation, comHistory);
			try 
			{
				return (GeneralCommand) NodeFactory.makeNode(Class.forName(NODE_PACKAGE + commandType), model, children.get(commandType), comHistory);
			}
			catch(Exception e)
			{
				comHistory.addCommand("Error: Could not access constructor for command " + text );
				throw new InvalidEntryException("Error: Could not access Node constructor");
			}
				
		}
		else if (key.equals("Constant"))
		{
			return new Constant(Integer.parseInt(text));
		}
		else if(key.equals("Variable"))
		{
			return new Variable(text.substring(1), varHistory);
		}
		
		return null;	
	}
	
	/**
	 * Given a command in any language will return the appropriate command type
	 * Checks for invalid command errors
	 * 
	 * @param command string identified as command syntax in a user input
	 * @param comHistory 
	 * @return a string indicating the appropriate command type
	 * @throws InvalidEntryException didnt match any of the commands in the given language
	 */
	private static String checkLanguage(String command, Map<String,Pattern> myTranslation, CommandHistory comHistory)
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

	/**
	 * Uses reflection to create the correct type of command node from the many command nodes in the command node package.
	 * 
	 * @param clazz
	 * @param model
	 * @param numChildren
	 * @param comHistory 
	 * 
	 * @return a new instance of the correct command node
	 */
	private static Object makeNode(Class<?> clazz,Model model, int numChildren, CommandHistory comHistory)
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
			comHistory.addCommand("Error: Invalid entry, no such command" );
			throw new InvalidEntryException("Error: Invalid entry, no such command");
		}
	}

	
	
	
//	private void buildParameterMap() 
//	{
//		commandToParams = new HashMap<>();
//		ResourceBundle params = ResourceBundle.getBundle(PARAMETER_FILE);
//		Enumeration<String> keys = params.getKeys();
//		while(keys.hasMoreElements())
//		{
//			String key = keys.nextElement();
//			String[] classStrings = params.getString(key).split(",");
//			Class[] classes = new Class[classStrings.length];
//			for (int i = 0; i < classStrings.length; i++)
//			{
//				System.out.println(classStrings[i]);
//				try
//				{
//					if(classStrings[i].contains("Double") || classStrings[i].contains("Integer"))
//					{
//						Class primitive = Class.forName(classStrings[i]);
//						Field fieldPrimitive = primitive.getDeclaredField("TYPE");
//						if(fieldPrimitive != null)
//						{
//							classes[i] = (Class)fieldPrimitive.get(primitive);
//						}
//					}
//					else
//					{
//						Class nonPrimitive = Class.forName(classStrings[i]);
//						classes[i] = nonPrimitive;
//						System.out.println(classes[i].toString());
//					}
//				}
//				catch(Exception e)
//				{
//					e.printStackTrace();
//				}
//			}
//			commandToParams.put(key, classes);
//		}
//	}
//
//	public void makeToken(String type, Object ... args) {
//		// TODO Auto-generated method stub
//		// get list of parameter classes for type
//		// getting the correct constructor for the correct type
//		// make array of type Object of size the same as parameters given to constructor
//		// match using isAssignableFrom the arg.getClass and the constructor class from the file
//		   // fill object array with matched values
//		// create using newInstance and passing array of objects
//		//x = new Object[para]
//				
//				//c.newInstance(x)
//		
//	}
//	public NodeInterface makeConstant(String type, String text)
//	{
//		try
//		{
//			Class<?> clazz = Class.forName("nodes." + type);
//			Constructor<?> c = clazz.getConstructor(commandToParams.get(type));
//			c.setAccessible(true);
//			Object o = c.newInstance(Integer.parseInt(text));
//			return (NodeInterface) o;
//		}
//		catch(Exception e)
//		{
//			e.printStackTrace();
//		}
//		return null;
//	}
//	
//	public NodeInterface makeVariable(String type, String text, VariablesHistory vh)
//	{
//		try
//		{
//			Class<?> clazz = Class.forName("nodes." + type);
//			Constructor<?> c = clazz.getConstructor(commandToParams.get("Variable"));
//			c.setAccessible(true);
//			Object o = c.newInstance(text, vh);
//			return (NodeInterface) o;
//		}
//		catch(Exception e)
//		{
//			e.printStackTrace();
//		}
//		return null;
//	}
}
