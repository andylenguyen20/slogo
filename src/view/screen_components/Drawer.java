package view.screen_components;

import controller.PaletteDelegate;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import model.ModelObservable;
import model.TurtleObservable;
import view.Observer;
import view.constants.CanvasConstants;
import view.constants.ComboBoxConstants;

public class Drawer extends ScreenComponent implements Observer{
    public static final double CANVAS_WIDTH = CanvasConstants.CANVAS_WIDTH;
    public static final double CANVAS_HEIGHT = CanvasConstants.CANVAS_HEIGHT;
    public static final Color INITIAL_PEN_COLOR = Color.BLACK;
	public static final double TURTLE_START_X = CanvasConstants.CANVAS_WIDTH/2;
	public static final double TURTLE_START_Y = CanvasConstants.CANVAS_HEIGHT/2;

	//other turtle images taken from:
	// https://pixabay.com/en/turtle-animal-reptile-water-green-294522/
	// https://pixabay.com/en/sea-turtle-floral-flowers-2952470/

	private DrawerCanvas drawerCanvas;
	private GraphicsContext gc;
	private ComboBox<String> backgroundColorBox;
	private ComboBox<String> penColorBox;
	private ComboBox<String> turtleImageBox;

	private PaletteDelegate paletteDelegate;
	private ModelObservable model;
	private TurtleObservable turtle;
	private HBox hbox;
	public Drawer(){
		super();
	}

	public void setModelObservable(ModelObservable model){
		this.model = model;
	}

	public void setPaletteDelegate(PaletteDelegate paletteDelegate){
		this.paletteDelegate = paletteDelegate;
	}

	public void setTurtle(TurtleObservable turtle){
		this.turtle = turtle;
	}

	@Override
	protected void mapUserActions() {
		backgroundColorBox.valueProperty().addListener(new ChangeListener<Object>() {
			@Override
			public void changed(ObservableValue<?> observable, Object oldValue, Object newValue) {
				changeBackgroundColor();
			}
		});
		turtleImageBox.valueProperty().addListener(new ChangeListener<Object>() {
			@Override
			public void changed(ObservableValue<?> observable, Object oldValue, Object newValue) {
				changeTurtleImage();
			}
		});
		penColorBox.valueProperty().addListener(new ChangeListener<Object>() {
			@Override
			public void changed(ObservableValue<?> observable, Object oldValue, Object newValue) {
				changePenColor();
			}
		});
	}

	public void generateGUIComponent(){
		BorderPane borderPane = super.getBorderPane();
		borderPane.setPadding(new Insets(10,20,10,20));
		drawerCanvas = new DrawerCanvas(borderPane, turtle);
		generateBorderPaneBottom(borderPane);
	}

	private void generateBorderPaneBottom(BorderPane borderPane){
		hbox = new HBox();
		backgroundColorBox = new ComboBox<>();
		penColorBox = new ComboBox<>();
		turtleImageBox = new ComboBox<>();
		addComboBoxOptions(hbox, backgroundColorBox, ComboBoxConstants.BACKGROUND_COLOR_LIST);
		addComboBoxOptions(hbox, penColorBox, ComboBoxConstants.PEN_COLOR_LIST);
		addComboBoxOptions(hbox, turtleImageBox, ComboBoxConstants.TURTLE_IMAGE_LIST);
		borderPane.setBottom(hbox);
	}

	private void addComboBoxOptions(HBox hbox, ComboBox<String> comboBox, String[] options){
		for (int x = 0 ; x < options.length; x++) {
			comboBox.getItems().add(options[x]);
		}
		comboBox.getSelectionModel().selectFirst();
		hbox.getChildren().add(comboBox);
	}

	private void changeBackgroundColor(){
		String color = backgroundColorBox.getValue();
		switch (color){
			case "White": paletteDelegate.changeBackgroundColor(Color.WHITE);
			case "Blue": paletteDelegate.changeBackgroundColor(Color.BLUE);
		}
		update();
	}


	private void changePenColor(){
		String color = (String) penColorBox.getValue();
		Color penColor = null;
		if (color.equals("Red")) {
			penColor = Color.RED;
		}
		if (color.equals("Green")){
			penColor = Color.GREEN;
		}
		if(color.equals("Black")){
			penColor = Color.BLACK;
		}
		paletteDelegate.changePenColor(penColor);
	}

	private void changeTurtleImage(){
		String imageName = (String) turtleImageBox.getValue();
		paletteDelegate.changeTurtleImage(imageName);
		update();
	}


	public void update(){
		drawerCanvas.update();
	}


	@Override
	public void notifyOfChanges() {
		update();
	}
}
