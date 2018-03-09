package view.screen_components;

import controller.ParserActionDelegate;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class PenControlPanel extends ScreenComponent{
    private static final double SMALL_PEN_SIZE = 50;
    private static final double MED_PEN_SIZE = 50;
    private static final double LARGE_PEN_SIZE = 50;
    private ParserActionDelegate parserActionDelegate;
    private Button penUpButton;
    private Button penDownButton;
    private Button smallPenButton;
    private Button medPenButton;
    private Button largePenButton;

    public PenControlPanel(){
        super();
    }

    public void setController(ParserActionDelegate parserActionDelegate){
        this.parserActionDelegate = parserActionDelegate;
    }

    @Override
    protected void mapUserActions() {
        penUpButton.setOnAction((event -> {
            parserActionDelegate.performParserAction((p)->p.parseString("pu"));
        }));
        penDownButton.setOnAction((event -> {
            parserActionDelegate.performParserAction((p)->p.parseString("pd"));
        }));
        smallPenButton.setOnAction((event -> {
            parserActionDelegate.performParserAction((p)->p.parseString("setps " + SMALL_PEN_SIZE));
        }));
        medPenButton.setOnAction((event -> {
            parserActionDelegate.performParserAction((p)->p.parseString("setps " + MED_PEN_SIZE));
        }));
        largePenButton.setOnAction((event -> {
            parserActionDelegate.performParserAction((p)->p.parseString("setps " + LARGE_PEN_SIZE));
        }));
    }

    @Override
    protected void generateGUIComponent() {
        BorderPane borderPane = super.getBorderPane();
        penUpButton = new Button("Up");
        penDownButton = new Button("Down");
        smallPenButton = new Button("Small");
        medPenButton = new Button("Med");
        largePenButton = new Button("Large");
        VBox vbox = new VBox();
        vbox.getChildren().add(penUpButton);
        vbox.getChildren().add(penDownButton);
        vbox.getChildren().add(smallPenButton);
        vbox.getChildren().add(medPenButton);
        vbox.getChildren().add(largePenButton);
        borderPane.setCenter(vbox);
    }
}
