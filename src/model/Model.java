package model;

import java.util.*;

import java.util.function.Consumer;

import javafx.scene.paint.Color;
import view.Observer;
import view.constants.PaletteConstants;

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

	private void initializeColors(){
		colorOptions = Arrays.asList(PaletteConstants.COLORS);
	}

	private void initializeShapes(){
		shapeOptions = Arrays.asList(PaletteConstants.TURTLE_IMAGES);
	}
	
	public List<String> getShapeOptions()
	{
		return shapeOptions;
	}

	public Map<Double, Turtle> getAllTurtles() 
	{
		return allTurtles;
	}

	public void addTurtle(double ID) {
		Turtle t = new Turtle (XHome, YHome, Color.BLUE, ID, shapeOptions.get(0));
		allTurtles.put( t.getValue(), t);
		activeTurtles.add(t);
		drawerObserver.notifyOfChanges();
	}
	
	public void addActiveTurtle(Turtle turt) {
		activeTurtles.add(turt);
	}

	@Override
	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(double index)
	{
		this.backgroundColor = colorOptions.get((int) index);
		drawerObserver.notifyOfChanges();
	}
	
	public List<Color> getColorOptions() 
	{
		return colorOptions;
	}
	
	public void setColorOptions(double index, double R, double G, double B) {
		//EXCEPTION FOR IF R,G,B ARE OUT OF BOUNDS
		colorOptions.set((int) index, Color.rgb((int) R, (int) G, (int) B));
		colorIndexObserver.notifyOfChanges();
	}
	
	public Color getColorAtIndex(double index)
	{
		//EXCEPTION FOR IF AN INDEX IS OUT OF BOUNDS
		colorIndexObserver.notifyOfChanges();
		return colorOptions.get((int)index);
	}

	public Turtle getActiveTurtle() 
	{ 
		return activeTurtles.get(currentTurtle); 
	}
	
	public List<Turtle> getActiveTurtles()
	{
		return activeTurtles;
	}

	public void setActiveTurtles(List<Turtle> newActives) {
		activeTurtles = newActives;
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
	public List<Color> getColorList() {
		return colorOptions;
	}

	public void initializePalette(){
		colorIndexObserver.notifyOfChanges();
	}

	@Override
	public List<TurtleObservable> getTurtleObservables() {
		List<TurtleObservable> turtleList = new ArrayList<>();
		for(double key: this.getAllTurtles().keySet()){
			turtleList.add(this.getAllTurtles().get(key));
		}
		return turtleList;
	}
}
