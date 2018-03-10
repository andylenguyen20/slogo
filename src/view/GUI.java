package view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import propertiesFiles.ResourceBundleManager;
import view.help_items.HelpPopup;
import view.screen_components.*;
import view.view_exceptions.ImproperScreenComponentException;

public class GUI{
    private static final String NAME = "Slogo Programming Environment";
	private static final int WIDTH = 1100;
	private static final int HEIGHT = 650;
    public static final int TITLE_PANEL_X = 670;
    public static final int TITLE_PANEL_Y = 10;

    public static final int DRAWER_X = 165;
    public static final int DRAWER_Y = 15;

    public static final int COMMAND_HISTORY_BOX_X = 670;
    public static final int COMMAND_HISTORY_BOX_Y = 200;

    public static final int VARIABLE_HISTORY_BOX_X = 890;
    public static final int VARIABLE_HISTORY_BOX_Y = 200;

    public static final int COMMAND_BOX_X = 190;
    public static final int COMMAND_BOX_Y = 510;

    public static final int HELP_BUTTON_X = 1040;
    public static final int HELP_BUTTON_Y = 620;

    public static final int TURTLE_CONTROL_PANEL_X = 30;
    public static final int TURTLE_CONTROL_PANEL_Y = 300;

    public static final int CUSTOM_COMMANDS_X = 890;
    public static final int CUSTOM_COMMANDS_Y = 450;

    public static final int PEN_CONTROL_PANEL_X = 65;
    public static final int PEN_CONTROL_PANEL_Y= 400;

    public static final int PALETTE_X = 0;
    public static final int PALETTE_Y= 0;

    private Button helpButton;


	private Group root = new Group();
    public GUI(){

    }
    
    public void start(Stage stage) {
        this.setStage(stage);
    }
    
    /**
     * Initializes beginning JavaFX Stage
     *
     * @param stage window holding simulation
     */
    private void setStage(Stage stage) {
        stage.setTitle(NAME);
        Scene startScene = new Scene(root, WIDTH, HEIGHT);
        startScene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setScene(startScene);
        this.addTitle();
        this.addHelp();
        stage.show();

    }

    public void addToScreen(ScreenComponent screenComponent){
        if(screenComponent instanceof CommandBox){
            this.positionBorderPane(screenComponent.getGUIComponent(), COMMAND_BOX_X, COMMAND_BOX_Y);
        }else if(screenComponent instanceof CustomCommandsBox){
            this.positionBorderPane(screenComponent.getGUIComponent(), CUSTOM_COMMANDS_X, CUSTOM_COMMANDS_Y);
        }else if(screenComponent instanceof CommandHistoryBox){
            this.positionBorderPane(screenComponent.getGUIComponent(), COMMAND_HISTORY_BOX_X, COMMAND_HISTORY_BOX_Y);
        }else if(screenComponent instanceof Drawer){
            this.positionBorderPane(screenComponent.getGUIComponent(), DRAWER_X, DRAWER_Y);
        } else if(screenComponent instanceof Palette){
            this.positionBorderPane(screenComponent.getGUIComponent(), PALETTE_X, PALETTE_Y);
        }else if(screenComponent instanceof PenControlPanel){
            this.positionBorderPane(screenComponent.getGUIComponent(), PEN_CONTROL_PANEL_X, PEN_CONTROL_PANEL_Y);
        }else if(screenComponent instanceof TurtleControlPanel){
            this.positionBorderPane(screenComponent.getGUIComponent(), TURTLE_CONTROL_PANEL_X, TURTLE_CONTROL_PANEL_Y);
        }else if(screenComponent instanceof VariableHistoryBox){
            this.positionBorderPane(screenComponent.getGUIComponent(), VARIABLE_HISTORY_BOX_X, VARIABLE_HISTORY_BOX_Y);
        }else{
            throw new ImproperScreenComponentException("Trying to position an improper screen component onto the GUI");
        }
    }

    private void positionBorderPane(BorderPane borderPane, double x, double y){
        borderPane.setLayoutX(x);
        borderPane.setLayoutY(y);
        root.getChildren().add(borderPane);
    }

    private void addTitle() {
        BorderPane titlePane = new BorderPane();
        titlePane.getStyleClass().add("titlePane");
        titlePane.setLayoutX(TITLE_PANEL_X);
        titlePane.setLayoutY(TITLE_PANEL_Y);
        root.getChildren().add(titlePane);
    }

    private void addHelp(){
        helpButton = new Button(ResourceBundleManager.retrieveButtonLabel("HELP"));
        helpButton.setLayoutX(HELP_BUTTON_X);
        helpButton.setLayoutY(HELP_BUTTON_Y);
        helpButton.setOnAction((event -> {
            HelpPopup popup = new HelpPopup();
            popup.open();
        }));
        root.getChildren().add(helpButton);
    }
}
