package parsers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import java.lang.reflect.*;

import model.CommandHistory;
import model.VariablesHistory;
import nodes.NodeInterface;

/**
 * @author Belanie Nagiel
 * 
 * A class that was an attempt at refactoring the Parser by making nodes elsewhere but I am still having trouble implementing it.
 * This is not currently being used.
 *
 */
public class NewNodeFactory 
{
	private Map<String,Class[]> commandToParams;
	private Map<String,Class[]> commandToArgs;
	private Map<String,Integer> children;
	private static String PARAMETER_FILE = "parsers/commandToConstructorParameters";
	private static List<String> primitives = new ArrayList<>(Arrays.asList("java.lang.Double", "java.lang.Integer"));
	
	public NewNodeFactory()
	{
		createChildrenMap();
		buildParameterMap();
		buildArgumentMap();
	}
	
	/**
	 * Returns a new Node based on the type and all the possible inputs
	 * 
	 * @param type
	 * @param args
	 * @return new Node
	 */
	public NodeInterface makeNode(String type, Object ... args)
	{
		Object[] realArgs = getArguments(type, args);
		
		NodeInterface n = nodeReflection(type, realArgs);
		
		System.out.print(n.getClass());
		
		return n;
	}
	
	/**
	 * Creates the node based on type and the arguments.
	 * 
	 * @param type
	 * @param realArgs
	 * @return
	 */
	private NodeInterface nodeReflection(String type, Object[] realArgs)
	{
		try
		{
			Class<?> clazz;
			if(type.equals("Command"))
			{
				
				clazz = Class.forName("commandNode." + type);
			}
			else
			{
				clazz = Class.forName("nodes." + type);
			}
			Constructor<?> c = clazz.getConstructor(commandToParams.get(type));
			c.setAccessible(true);
			Object o = c.newInstance(realArgs);
			return (NodeInterface) o;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;	
	}
	
	/**
	 * Creates the list of real arguments for the new instance of a class.
	 * 
	 * @param type
	 * @param args
	 * @return
	 */
	private Object[] getArguments(String type, Object ... args)
	{
		Class[] argTypes = commandToArgs.get(type);
		Object[] arguments = new Object[argTypes.length];
		for(int i = 0; i < arguments.length; i++)
		{
			for(Object possibleArg: args)
			{
//				System.out.println(possibleArg.getClass());
//				System.out.println(argTypes[i].getClass());
				if(argTypes[i].isAssignableFrom(possibleArg.getClass()))
				{
					arguments[i] = possibleArg;
				}
			}
		}
		return arguments;
	}
	
	/**
	 * Creates the map of node types to parameters.
	 * 
	 */
	private void buildParameterMap() 
	{
		commandToParams = new HashMap<>();
		ResourceBundle params = ResourceBundle.getBundle(PARAMETER_FILE);
		Enumeration<String> keys = params.getKeys();
		while(keys.hasMoreElements())
		{
			String key = keys.nextElement();
			String[] classStrings = params.getString(key).split(",");
			Class[] classes = getParameterClasses(classStrings);
			commandToParams.put(key, classes);
		}
	}
	
	/**
	 * Creates the list of classes based on the .property file.
	 * 
	 * @param classStrings
	 * @return
	 */
	private Class[] getParameterClasses(String[] classStrings)
	{
		Class[] parameterClasses = new Class[classStrings.length];
		for (int i = 0; i < classStrings.length; i++)
		{
//			System.out.println(classStrings[i]);
			try
			{
				if(primitives.contains(classStrings[i]))
				{
					Class primitive = Class.forName(classStrings[i]);
					Field fieldPrimitive = primitive.getDeclaredField("TYPE");
					if(fieldPrimitive != null)
					{
						parameterClasses[i] = (Class)fieldPrimitive.get(primitive);
					}
				}
				else
				{
					parameterClasses[i] = Class.forName(classStrings[i]); 
//					System.out.println(parameterClasses[i].toString());
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return parameterClasses;
	}
	
	/**
	 * Creates map of node types to argument types
	 * 
	 */
	private void buildArgumentMap()
	{
		commandToArgs = new HashMap<>();
		ResourceBundle params = ResourceBundle.getBundle(PARAMETER_FILE);
		Enumeration<String> keys = params.getKeys();
		while(keys.hasMoreElements())
		{
			String key = keys.nextElement();
			String[] classStrings = params.getString(key).split(",");
			Class[] classes = getArgumentClasses(classStrings);
			commandToArgs.put(key, classes);
		}
		
	}
	
	/**
	 * Creates the list of classes based on the .property file.
	 * 
	 * @param classStrings
	 * @return
	 */
	private Class[] getArgumentClasses(String[] classStrings)
	{
		Class[] argClasses = new Class[classStrings.length];
		for (int i = 0; i < classStrings.length; i++)
		{
			try 
			{
				argClasses[i] = Class.forName(classStrings[i]);
//				System.out.println("args " + argClasses[i].toString());
			} 
			catch (ClassNotFoundException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
		}
		return argClasses;
	}
	
	/**
	 * Creates a map of commands to the number of children they have.
	 * 
	 */
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
	
//	
//	private String checkLanguage(String command, Map<String,Pattern> myTranslation, CommandHistory comHistory)
//	{
//		for (String key: myTranslation.keySet())
//		{
//			if(myTranslation.get(key).matcher(command).matches())
//			{
//				return key;
//			}
//		}
//		comHistory.addCommand("Error: Cannot recognize language");
//		throw new InvalidEntryException("Error: Cannot recognize language");
//	}
}
