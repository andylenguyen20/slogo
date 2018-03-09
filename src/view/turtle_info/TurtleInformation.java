package view.turtle_info;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Turtle;
import model.TurtleObservable;


public class TurtleInformation {
    public static final int STAGE_WIDTH = 350;
    public static final int STAGE_HEIGHT = 200;
    public static final int COORDINATE_OFFSET = 225;
    private Stage helpStage;
    private Group helpRoot;
    private TableView table;
    private TurtleObservable turtle;
    private int turtleX;
    private int turtleY;
    private VBox vBox;

    public TurtleInformation(TurtleObservable turtle1){
        this.turtle = turtle1;
        turtleX = (int)turtle1.getXCoordinate()- COORDINATE_OFFSET;
        turtleY = (int)(turtle1.getYCoordinate()-COORDINATE_OFFSET) * -1;
        helpRoot = new Group();
        helpStage = new Stage();
        helpStage.setTitle("Turtle Information");
        Scene startScene = new Scene(helpRoot, STAGE_WIDTH, STAGE_HEIGHT);
        startScene.getStylesheets().add(getClass().getResource("turtleInformationStyle.css").toExternalForm());
        addInfo();
        helpStage.setScene(startScene);
        helpStage.show();
    };

    public void showTurtleInformation(){
        helpStage.show();
    }

    private void addInfo(){
        vBox = new VBox();
        Label idLabel = new Label("Turtle ID: ");
        Label coordinateLabel = new Label("Turtle coordinates: (" + turtleX + ", " + turtleY + ")");
        Label directionLabel = new Label("Turtle direction (degrees counter-clockwise from east): " + turtle.getDirectionAngle());
        Label penLabel = new Label("Pen values: "); //TODO update this stuff!
        vBox.getChildren().addAll(idLabel, penLabel, coordinateLabel, directionLabel);
        helpRoot.getChildren().add(vBox);
        addActiveRadioButton();
    }

    private void addActiveRadioButton(){
        HBox hBox = new HBox();
        ToggleGroup group = new ToggleGroup();
        RadioButton button1 = new RadioButton("Active");
        button1.setToggleGroup(group);
        button1.setSelected(true);
        RadioButton button2 = new RadioButton("Inactive");
        button2.setToggleGroup(group);
        hBox.getChildren().addAll(button1, button2);
        vBox.getChildren().add(hBox);
    }
}
