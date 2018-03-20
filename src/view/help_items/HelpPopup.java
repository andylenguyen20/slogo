package view.help_items;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import propertiesFiles.ResourceBundleManager;

/**
 * CLass which provides a popup box with various buttons which provide images of Slogo commands
 * @author Edward Zhuang
 */
public class HelpPopup {
    public static final int STAGE_WIDTH = 600;
    public static final int STAGE_HEIGHT = 250;
    public static final int HBOX_OFFSET = 10;
    public static final int HBOX1_Y = 150;
    public static final int HBOX2_Y = 200;
    public static final int TITLE_OFFSET = 75;
    private Stage helpStage;
    private Group helpRoot;
    private Button basicSyntaxButton;
    private Button turtleCommandsButton;
    private Button turtleQueriesButton;
    private Button mathOperationsButton;
    private Button booleanOperationsButton;
    private Button variablesButton;
    private Button displayCommandsButton;
    private Button multipleTurtlesButton;
    private HBox hBox1;
    private HBox hBox2;

    /**
     * Constructor
     * Sets up HelpPopup window, but does not show it
     */
    public HelpPopup(){
        helpRoot = new Group();
        helpStage = new Stage();
        helpStage.setTitle("Help for SLogo");
        Scene startScene = new Scene(helpRoot, STAGE_WIDTH, STAGE_HEIGHT);
        startScene.getStylesheets().add(getClass().getResource("popupStyle.css").toExternalForm());
        helpStage.setScene(startScene);
        addFirstButtonRow();
        addSecondButtonRow();
        addTitle();
        setButtonActions();
    }

    /**
     * Shows the popup
     */
    public void open(){
        helpStage.show();
    }

    /**
     * Adds first row of buttons to helpRoot
     */
    private void addFirstButtonRow(){
        hBox1 = new HBox();
        basicSyntaxButton = new Button(ResourceBundleManager.retrieveHelpPopupConstant("BASIC_SYNTAX"));
        turtleCommandsButton = new Button(ResourceBundleManager.retrieveHelpPopupConstant("TURTLE_COMMANDS"));
        turtleQueriesButton = new Button(ResourceBundleManager.retrieveHelpPopupConstant("TURTLE_QUERIES"));
        displayCommandsButton = new Button(ResourceBundleManager.retrieveHelpPopupConstant("DISPLAY_COMMANDS"));
        hBox1.getChildren().add(basicSyntaxButton);
        hBox1.getChildren().add(turtleCommandsButton);
        hBox1.getChildren().add(turtleQueriesButton);
        hBox1.getChildren().add(displayCommandsButton);
        hBox1.setLayoutX(HBOX_OFFSET);
        hBox1.setLayoutY(HBOX1_Y);
        helpRoot.getChildren().add(hBox1);
    }

    /**
     * Adds second row of buttons to helpRoot
     */
    private void addSecondButtonRow(){
        hBox2 = new HBox();
        mathOperationsButton = new Button(ResourceBundleManager.retrieveHelpPopupConstant("MATH_OPERATIONS"));
        booleanOperationsButton = new Button(ResourceBundleManager.retrieveHelpPopupConstant("BOOLEAN_OPERATIONS"));
        variablesButton = new Button(ResourceBundleManager.retrieveHelpPopupConstant("VARIABLES"));
        multipleTurtlesButton = new Button(ResourceBundleManager.retrieveHelpPopupConstant("MULTIPLE_TURTLES"));
        hBox2.getChildren().add(mathOperationsButton);
        hBox2.getChildren().add(booleanOperationsButton);
        hBox2.getChildren().add(variablesButton);
        hBox2.getChildren().add(multipleTurtlesButton);
        hBox2.setLayoutX(HBOX_OFFSET);
        hBox2.setLayoutY(HBOX2_Y);
        helpRoot.getChildren().add(hBox2);
    }

    /**
     * Sets up actions for buttons. Each button generates a popup with an image containing specific commands
     */
    private void setButtonActions(){
        basicSyntaxButton.setOnAction((event -> {
            generatePopup("syntaxImage", "BASIC_SYNTAX");
        }));
        turtleCommandsButton.setOnAction((event -> {
            generatePopup("commandsImage", "TURTLE_COMMANDS");
        }));
        turtleQueriesButton.setOnAction((event -> {
            generatePopup("queriesImage", "TURTLE_QUERIES");
        }));
        mathOperationsButton.setOnAction((event -> {
            generatePopup("mathImage", "MATH_OPERATIONS");
        }));
        booleanOperationsButton.setOnAction((event -> {
            generatePopup("booleanImage", "BOOLEAN_OPERATIONS");
        }));
        variablesButton.setOnAction((event -> {
            generatePopup("variablesImage", "VARIABLES");
        }));
        multipleTurtlesButton.setOnAction((event -> {
            generatePopup("multipleImage", "MULTIPLE_TURTLES");
        }));
        displayCommandsButton.setOnAction((event -> {
            generatePopup("displayImage", "DISPLAY_COMMANDS");
        }));
    }

    /**
     * Adds title to helpRoot
     */
    private void addTitle(){
        Image image = new Image(getClass().getClassLoader().getResourceAsStream(ResourceBundleManager.retrieveHelpPopupConstant("helpTitle")));
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setLayoutX(TITLE_OFFSET);
        helpRoot.getChildren().add(imageView);
    }

    /**
     *
     * @param stringImage name of image
     * @param string name of subPopup window
     */
    private void generatePopup(String stringImage, String string){
        Image image = new Image(getClass().getClassLoader().getResourceAsStream(ResourceBundleManager.retrieveHelpPopupConstant(stringImage)));
        HelpSubPopup subPopup = new HelpSubPopup(image, ResourceBundleManager.retrieveHelpPopupConstant(string));
        subPopup.show();
    }

}
