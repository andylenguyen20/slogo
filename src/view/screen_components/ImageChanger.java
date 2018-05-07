package view.screen_components;

import controller.ImageChangerController;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.ImageChangerObservable;
import model.TurtleObservable;
import view.Observer;

import java.util.List;

public class ImageChanger extends ScreenComponent implements Observer{

    private static final int FIT_HEIGHT = 50;
    public static final int PREF_WIDTH = 150;
    public static final int PREF_HEIGHT = 600;
    private List<TurtleObservable> turtleList;
    private ImageChangerObservable model;
    private ScrollPane scrollPane;
    private ImageChangerController controller;
    private VBox vBox;
    private ComboBox<Integer> imageNumber;

    public ImageChanger(ImageChangerController controller){
        super();
        this.controller = controller;

    }

    @Override
    protected void mapUserActions() {
    }

    public void setImageChangerObservable(ImageChangerObservable model){
        this.model = model;
    }

    @Override
    protected void generateGUIComponent() {
        scrollPane = new ScrollPane();
        vBox = new VBox();
        scrollPane.setContent(vBox);
        scrollPane.setPrefSize(PREF_WIDTH, PREF_HEIGHT);
        imageNumber = new ComboBox<>();
        imageNumber.getItems().addAll(0,1,2,3,4,5,6);
        imageNumber.getSelectionModel().select(0);
        getBorderPane().setCenter(scrollPane);
    }

    /**
     * updates list of turtles
     */
    private void updateList(){
        vBox.getChildren().clear();
        vBox.getChildren().add(imageNumber);
        for (TurtleObservable observable: turtleList){
            constructHBox(observable);
        }
        scrollPane.setContent(vBox);
    }

    private void constructHBox(TurtleObservable turtleObservable){
        HBox hBox = new HBox();
        ImageView turtleImage = new ImageView();
        turtleImage.setPreserveRatio(true);
        turtleImage.setFitHeight(FIT_HEIGHT);
        turtleImage.setImage(new Image(getClass().getClassLoader().getResourceAsStream(turtleObservable.getTurtleShape())));
        turtleImage.setOnMouseClicked(e->{
            controller.changeTurtleImage(turtleObservable.getValue(), imageNumber.getValue());
        });
        hBox.getChildren().add(turtleImage);
        vBox.getChildren().add(hBox);
    }


    @Override
    public void notifyOfChanges() {
        turtleList = model.getAllTurtleObservables();
        updateList();
    }
}
