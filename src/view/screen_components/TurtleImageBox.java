package view.screen_components;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import controller.ValueModifierDelegate;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.DrawerObservable;
import model.TurtleObservable;
import view.Observer;
import view.constants.PaletteConstants;
import view.factories.ComboBoxFactory;

public class TurtleImageBox extends ScreenComponent implements Observer{
	private static final double TURTLE_ICON_SIDE_LENGTH = 100;
	private static final double BOX_WIDTH = 300;
	private static final double BOX_HEIGHT = 150;
	private DrawerObservable drawerObservable;
	private List<TurtleObservable> displayedTurtles;
	private ValueModifierDelegate imageChanger;
	private VBox imageChangeWrapper;
	private Map<Double, ImageView> idToImageMap;
	
	public TurtleImageBox(ValueModifierDelegate imageChanger){
		this.imageChanger = imageChanger;
		this.displayedTurtles = new ArrayList<>();
		this.idToImageMap = new HashMap<>();
	}
	
	public void setDrawerObservable(DrawerObservable drawerObservable){
		this.drawerObservable = drawerObservable;
	}
	
	@Override
	protected void mapUserActions() {}

	@Override
	protected void generateGUIComponent() {
		BorderPane borderPane = super.getBorderPane();
		imageChangeWrapper = new VBox();
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setMaxWidth(BOX_WIDTH);
		scrollPane.setPrefHeight(BOX_HEIGHT);
		scrollPane.setContent(imageChangeWrapper);
		borderPane.setCenter(scrollPane);
		borderPane.setMaxWidth(BOX_WIDTH);
		borderPane.setMaxHeight(BOX_HEIGHT);
	}
	
	private void addImageChangeOption(HBox hbox, double id){
		ComboBox<Integer> imageChangeOption = ComboBoxFactory.generateIntegerComboBox(PaletteConstants.TURTLE_IMAGES.length);
        imageChangeOption.valueProperty().addListener((observable, oldValue, newValue) -> {
        	imageChanger.changeValue(Double.toString(id), Integer.toString(imageChangeOption.getValue()));
        });
		hbox.getChildren().add(imageChangeOption);
	}
	
	private void addTurtleImage(HBox hbox, TurtleObservable turt){
		ImageView imageView = new ImageView();
        imageView.setImage(new Image(getClass().getClassLoader().getResourceAsStream(turt.getTurtleShape())));
        imageView.setFitWidth(TURTLE_ICON_SIDE_LENGTH);
        imageView.setFitHeight(TURTLE_ICON_SIDE_LENGTH);
        idToImageMap.put(turt.getValue(), imageView);
        hbox.getChildren().add(imageView);
	}
	
	private HBox generateNewTurtle(TurtleObservable turt){
		HBox hbox = new HBox();
		addTurtleImage(hbox, turt);
		addImageChangeOption(hbox, turt.getValue());
		return hbox;
	}
	
	private void addTurtleToDisplay(TurtleObservable turt){
		displayedTurtles.add(turt);
		imageChangeWrapper.getChildren().add(this.generateNewTurtle(turt));
	}

	private void updateDisplayImage(TurtleObservable turt){
		double id = turt.getValue();
        Image image = new Image(getClass().getClassLoader().getResourceAsStream(turt.getTurtleShape()));
		this.idToImageMap.get(id).setImage(image);
	}
	
	public void update(){
        for(TurtleObservable turt : drawerObservable.getTurtleObservables()){
            if(!displayedTurtles.contains(turt)){
                this.addTurtleToDisplay(turt);
            }
            updateDisplayImage(turt);
        }
    }
	
	@Override
	public void notifyOfChanges() {
		update();
		System.out.println("notified");
	}
}
