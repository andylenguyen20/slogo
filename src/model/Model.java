package model;

import java.util.*;

import java.util.function.Consumer;

import javafx.scene.paint.Color;
import view.Observer;
import view.constants.PaletteConstants;

/**
 * This class serves as a way to keep track of the background color, the shape of the turtle, and the current active and 
 * non active turtles.
 * 
 * @author Belanie Nagiel, Charlie Dracos, and Andy Nguyen
 *
 */
public class Model implements PaletteObservable, DrawerObservable {
	private Color backgroundColor;
	private List<Color> colorOptions;
	private List<String> shapeOptions;
	private List<Turtle> activeTurtles;
	private Map<Double,Turtle> allTurtles;

	private static double XHome;
	private static double YHome;

	private int currentTurtle = 0;

	private Observer drawerObserver;

	private Observer colorIndexObserver;

	/**
	 * Class Constructor
	 * 
	 * @param width
	 * @param height
	 */
	public Model(double width, double height)
	{
		initializeColors();
		initializeShapes();
		activeTurtles = new ArrayList<>();
		allTurtles = new HashMap<>();
		XHome = width;
		YHome = height;

		Turtle initial = new Turtle(XHome, YHome, colorOptions.get(0), 1.0, shapeOptions.get(0));

		allTurtles.put(1.0, initial);
		activeTurtles.add(initial);

	}

	/**
	 * Initializes the color options
	 * 
	 */
	private void initializeColors(){
		colorOptions = Arrays.asList(PaletteConstants.COLORS);
	}

	/**
	 * Initializes the turtle shape options
	 * 
	 */
	private void initializeShapes(){
		shapeOptions = Arrays.asList(PaletteConstants.TURTLE_IMAGES);
	}

	/**
	 * 
	 * @return the list of image options as strings
	 */
	public List<String> getShapeOptions()
	{
		return shapeOptions;
	}

	/**
	 * @return the map of all turtles whether active or not
	 */
	public Map<Double, Turtle> getAllTurtles()
	{
		return allTurtles;
	}

	/**
	 * adds a turtle to the map of all turtles
	 * 
	 * @param ID
	 */
	public void addTurtle(double ID) {
		Turtle t = new Turtle (XHome, YHome, Color.BLUE, ID, shapeOptions.get(0));
		allTurtles.put( t.getValue(), t);
		activeTurtles.add(t);
		drawerObserver.notifyOfChanges();
	}

	/**
	 * adds a turtle to the list of active turtles
	 * 
	 * @param turt
	 */
	public void addActiveTurtle(Turtle turt) {
		activeTurtles.add(turt);
		turt.setTurtleStatus(true);
	}

	@Override
	/**
	 * @return the current background color
	 */
	public Color getBackgroundColor() {
		return backgroundColor;
	}

	/**
	 * Sets the background color to the color in color options at index
	 * 
	 * @param index
	 */
	public void setBackgroundColor(double index)
	{
		this.backgroundColor = colorOptions.get((int) index);
		drawerObserver.notifyOfChanges();
	}

	/**
	 * returns the list of color options
	 * 
	 * @return
	 */
	public List<Color> getColorOptions()
	{
		return colorOptions;
	}

	/**
	 * Sets the color in color options at index o a new color made from RGB
	 * 
	 * @param index
	 * @param R
	 * @param G
	 * @param B
	 */
	public void setColorOptions(double index, double R, double G, double B) {
		colorOptions.set((int) index, Color.rgb((int) R, (int) G, (int) B));
		colorIndexObserver.notifyOfChanges();
	}

	/** 
	 * @param index
	 * @return the color at a given index in the color options list
	 */
	public Color getColorAtIndex(double index)
	{
		colorIndexObserver.notifyOfChanges();
		return colorOptions.get((int)index);
	}

	/**
	 * @return the current active turtle
	 */
	public Turtle getActiveTurtle()
	{
		return activeTurtles.get(currentTurtle);
	}

	/**
	 * 
	 * @return the list of active turtles
	 */
	public List<Turtle> getActiveTurtles()
	{
		return activeTurtles;
	}

	/**
	 * Sets the list of active turtles to a new list of turtles
	 * @param newActives
	 */
	public void setActiveTurtles(List<Turtle> newActives) {
		activeTurtles = newActives;
		for(Turtle t: newActives)
		{
			t.setTurtleStatus(true);
		}
	}
	
	/**
	 * Sets the turtle of ID to not active
	 * @param turtleID
	 */
	public void setTurtleInactive(double turtleID)
	{
		activeTurtles.remove(allTurtles.get(turtleID));
		allTurtles.get(turtleID).setTurtleStatus(false);
	}
	
	/**
	 * Sets the turtle of ID to active
	 * @param turtleID
	 */
	public void setTurtleActive(double turtleID)
	{
		activeTurtles.add(allTurtles.get(turtleID));
		allTurtles.get(turtleID).setTurtleStatus(true);
	}

	public void update (Consumer<Turtle> T) {
		for (Turtle t: getActiveTurtles()) {
			T.accept(t);
			currentTurtle++;
		}
		currentTurtle = 0;
		drawerObserver.notifyOfChanges();
	}

	public void addDrawerObserver(Observer observer){
		drawerObserver = observer;
	}

	public void addObserver(Observer colorListObserver) {
		this.colorIndexObserver = colorListObserver;
	}

	@Override
	/**
	 * 
	 * @return the list of color options
	 */
	public List<Color> getColorList() {
		return colorOptions;
	}

	/**
	 * notifies the observer of changes to the color palette
	 * 
	 */
	public void initializePalette(){
		colorIndexObserver.notifyOfChanges();
	}

	@Override
	/**
	 * Gets the information for the view from each active turtle 
	 */
	public List<TurtleObservable> getTurtleObservables() {
		List<TurtleObservable> turtleList = new ArrayList<>();
		for(double key: this.getAllTurtles().keySet()){
			turtleList.add(this.getAllTurtles().get(key));
		}
		return turtleList;
	}
}
