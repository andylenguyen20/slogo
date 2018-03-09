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
	private static final int VERTICAL_INSET = 10;
	private static final int HORIZONTAL_INSET = 20;

	//other turtle images taken from:
	// https://pixabay.com/en/turtle-animal-reptile-water-green-294522/
	// https://pixabay.com/en/sea-turtle-floral-flowers-2952470/

	private DrawerCanvas drawerCanvas;
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
		backgroundColorBox.valueProperty().addListener((observable, oldValue, newValue) -> changeBackgroundColor());
		turtleImageBox.valueProperty().addListener((observable, oldValue, newValue) -> changeTurtleImage());
		penColorBox.valueProperty().addListener((observable, oldValue, newValue) -> changePenColor());
	}

	public void generateGUIComponent(){
		BorderPane borderPane = super.getBorderPane();
		borderPane.setPadding(new Insets(VERTICAL_INSET, HORIZONTAL_INSET, VERTICAL_INSET, HORIZONTAL_INSET));
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
		drawerCanvas.changeBackgroundColor(color);
		update();
	}

	private void changePenColor(){
		controller.setPenColor(Color.valueOf(penColorBox.getValue()));
	}

	private void changeTurtleImage(){
		String imageName = turtleImageBox.getValue();
		drawerCanvas.changeTurtleImage(imageName);
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
