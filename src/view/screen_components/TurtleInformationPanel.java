package view.screen_components;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.TurtleObservable;


public class TurtleInformationPanel extends ScreenComponent{
    private static final String TITLE = "Turtle Info";
    private static final double WIDTH = 200;
    private static final double HEIGHT = 200;
    private final TurtleObservable turtleObservable;
    private Stage stage;
    private TextArea turtleInfoPanel;
    public TurtleInformationPanel(TurtleObservable turtleObservable){
        super();
        this.turtleObservable = turtleObservable;
        Group root = new Group();
        root.getChildren().add(getBorderPane());
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        stage = new Stage();
        stage.setScene(scene);
    }

    private void createPopup(){
        stage.show();
    }

    public void renderInformation(){
        this.createPopup();
        turtleInfoPanel.clear();
        turtleInfoPanel.appendText("Position: (" + turtleObservable.getXCoordinate() + "," + turtleObservable.getYCoordinate() + ")\n");
        turtleInfoPanel.appendText("Showing: " + turtleObservable.getTurtleShowing() + "\n");
        //turtleInfoPanel.appendText("Active: " + turtleObservable.getTurtleStatus() + "\n");
        turtleInfoPanel.appendText("Direction angle: " + turtleObservable.getDirectionAngle() + "\n");
    }

    @Override
    protected void mapUserActions() {
        //no actions to map yet
    }

    @Override
    protected void generateGUIComponent() {
        BorderPane borderPane = super.getBorderPane();
        turtleInfoPanel = new TextArea();
        borderPane.setCenter(turtleInfoPanel);
    }
}
