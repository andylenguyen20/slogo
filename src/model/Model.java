package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.function.Consumer;

import javafx.scene.paint.Color;
import view.Observer;

public class Model implements PaletteObservable, ModelObservable{

	private static final String TURTLE_0 = "black_and_white_turtle.PNG";
	private static final String TURTLE_1 = "colorful_turtle.png";
	private static final String TURTLE_2 = "green_turtle.png";
	private static final String TURTLE_3 = "SLogo.PNG";
	private static final String TURTLE_4 = "fd_arrow.png";
	private static final String TURTLE_5 = "lt_arrow.png";
	private static final String TURTLE_6 = "bk_arrow.png";


	private Color backgroundColor;
	private List<Color> colorOptions;
	private List<String> shapeOptions;
	private List<Turtle> activeTurtles;
	private Map<Double,Turtle> allTurtles;
	private Observer colorIndexObserver;

	public Model(double width, double height)
	{
		//needs actual colors
		initializeColors();
		initializeShapes();
		activeTurtles = new ArrayList<>();
		allTurtles = new HashMap<>();

		Turtle initial = new Turtle(width, height, colorOptions.get(0), 1.0, TURTLE_0);

		allTurtles.put(1.0, initial);
		activeTurtles.add(initial);

	}

	private void initializeColors(){
		colorOptions = new ArrayList<>();
		colorOptions.add(Color.RED);
		colorOptions.add(Color.ORANGE);
		colorOptions.add(Color.YELLOW);
		colorOptions.add(Color.GREEN);
		colorOptions.add(Color.BLUE);
		colorOptions.add(Color.INDIGO);
		colorOptions.add(Color.VIOLET);
	}

	private void initializeShapes(){
		shapeOptions = new ArrayList<>();
		shapeOptions.add(TURTLE_0);
		shapeOptions.add(TURTLE_1);
		shapeOptions.add(TURTLE_2);
		shapeOptions.add(TURTLE_3);
		shapeOptions.add(TURTLE_4);
		shapeOptions.add(TURTLE_5);
		shapeOptions.add(TURTLE_6);
	}

	public List<String> getShapeOptions()
	{
		return shapeOptions;
	}

	public Map<Double, Turtle> getAllTurtles()
	{
		return allTurtles;
	}

	public void addTurtle(Turtle turt) {
		allTurtles.put((double) turt.getValue(), turt);
	}

	public void addActiveTurtle(Turtle turt) {
		activeTurtles.add(turt);
	}

	public Color getBackgroundColor()
	{
		return backgroundColor;
	}

	public void setBackgroundColor(double index)
	{
		this.backgroundColor = colorOptions.get((int) index);
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

	public List<Turtle> getActiveTurtles()
	{
		return activeTurtles;
	}

	public void update (Consumer<Turtle> T) {
		for (Turtle t: getActiveTurtles()) {
			T.accept(t);
		}
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


}
/*
package model;

		import java.util.ArrayList;
		import java.util.Arrays;
		import java.util.HashMap;
		import java.util.List;
		import java.util.Map;

		import java.util.function.Consumer;

		import javafx.scene.paint.Color;
		import javafx.scene.shape.Shape;
		import view.Observer;
		import view.screen_components.Palette;

public class Model implements PaletteObservable{

	private static final String TURTLE_0 = "black_and_white_turtle.PNG";
	private static final String TURTLE_1 = "colorful_turtle.png";
	private static final String TURTLE_2 = "green_turtle.png";
	private static final String TURTLE_3 = "SLogo.PNG";
	private static final String TURTLE_4 = "fd_arrow.png";
	private static final String TURTLE_5 = "lt_arrow.png";
	private static final String TURTLE_6 = "bk_arrow.png";

	private Color backgroundColor;
	private List<Color> colorOptions;
	private List<String> shapeOptions;
	private List<Turtle> activeTurtles;
	private Map<Double,Turtle> allTurtles;

	private static double XHome;
	private static double YHome;

	private int currentTurtle = 0;

	private Observer colorIndexObserver;

	public Model(double width, double height)
	{
		//needs actual colors
		initializeColors();
		initializeShapes();
		activeTurtles = new ArrayList<>();
		allTurtles = new HashMap<>();
		XHome = width;
		YHome = height;

		Turtle initial = new Turtle(XHome, YHome, colorOptions.get(0), 1.0, TURTLE_0);

		allTurtles.put(1.0, initial);
		activeTurtles.add(initial);

	}

	private void initializeColors(){
		colorOptions = new ArrayList<>();
		colorOptions.add(Color.RED);
		colorOptions.add(Color.ORANGE);
		colorOptions.add(Color.YELLOW);
		colorOptions.add(Color.GREEN);
		colorOptions.add(Color.BLUE);
		colorOptions.add(Color.INDIGO);
		colorOptions.add(Color.VIOLET);
	}

	private void initializeShapes(){
		shapeOptions = new ArrayList<>();
		shapeOptions.add(TURTLE_0);
		shapeOptions.add(TURTLE_1);
		shapeOptions.add(TURTLE_2);
		shapeOptions.add(TURTLE_3);
		shapeOptions.add(TURTLE_4);
		shapeOptions.add(TURTLE_5);
		shapeOptions.add(TURTLE_6);
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
		Turtle t = new Turtle (XHome, YHome, Color.BLUE, ID, TURTLE_0);
		allTurtles.put((double) t.getValue(), t);
		activeTurtles.add(t);
	}

	public void addActiveTurtle(Turtle turt) {
		activeTurtles.add(turt);
	}

	public Color getBackgroundColor()
	{
		return backgroundColor;
	}

	public void setBackgroundColor(Color c)
	{
		this.backgroundColor = c;
	}


	public void setBackgroundColor(double index)
	{
		this.backgroundColor = colorOptions.get((int) index);
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
	}


	public void addVariableHistoryObserver(Observer colorListObserver) {
		this.colorIndexObserver = colorListObserver;
	}

	@Override
	public List<Color> getColorList() {
		return colorOptions;
	}

	public void initializePalette(){
		colorIndexObserver.notifyOfChanges();
	}


}
*/