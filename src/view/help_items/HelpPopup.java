package view.help_items;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import propertiesFiles.ResourceBundleManager;

public class HelpPopup {
    public static final int STAGE_WIDTH = 450;
    public static final int STAGE_HEIGHT = 250;
    public static final int HBOX_OFFSET = 10;
    public static final int HBOX1_Y = 150;
    public static final int HBOX2_Y = 200;
    private Stage helpStage;
    private Group helpRoot;
    private Button basicSyntaxButton;
    private Button turtleCommandsButton;
    private Button turtleQueriesButton;
    private Button mathOperationsButton;
    private Button booleanOperationsButton;
    private Button variablesButton;
    private HBox hBox1;
    private HBox hBox2;

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

    public void open(){
        helpStage.show();
    }


    private void addFirstButtonRow(){
        hBox1 = new HBox();
        basicSyntaxButton = new Button(ResourceBundleManager.retrieveHelpPopupConstant("BASIC_SYNTAX"));
        turtleCommandsButton = new Button(ResourceBundleManager.retrieveHelpPopupConstant("TURTLE_COMMANDS"));
        turtleQueriesButton = new Button(ResourceBundleManager.retrieveHelpPopupConstant("TURTLE_QUERIES"));
        hBox1.getChildren().add(basicSyntaxButton);
        hBox1.getChildren().add(turtleCommandsButton);
        hBox1.getChildren().add(turtleQueriesButton);
        hBox1.setLayoutX(HBOX_OFFSET);
        hBox1.setLayoutY(HBOX1_Y);
        helpRoot.getChildren().add(hBox1);
    }
    private void addSecondButtonRow(){
        hBox2 = new HBox();
        mathOperationsButton = new Button(ResourceBundleManager.retrieveHelpPopupConstant("MATH_OPERATIONS"));
        booleanOperationsButton = new Button(ResourceBundleManager.retrieveHelpPopupConstant("BOOLEAN_OPERATIONS"));
        variablesButton = new Button(ResourceBundleManager.retrieveHelpPopupConstant("VARIABLES"));
        hBox2.getChildren().add(mathOperationsButton);
        hBox2.getChildren().add(booleanOperationsButton);
        hBox2.getChildren().add(variablesButton);
        hBox2.setLayoutX(HBOX_OFFSET);
        hBox2.setLayoutY(HBOX2_Y);
        helpRoot.getChildren().add(hBox2);
    }

    private void setButtonActions(){
        basicSyntaxButton.setOnAction((event -> {
            Image image = new Image(getClass().getClassLoader().getResourceAsStream(ResourceBundleManager.retrieveHelpPopupConstant("syntaxImage")));
            generatePopup(image, ResourceBundleManager.retrieveHelpPopupConstant("BASIC_SYNTAX"));
        }));
        turtleCommandsButton.setOnAction((event -> {
            Image image = new Image(getClass().getClassLoader().getResourceAsStream(ResourceBundleManager.retrieveHelpPopupConstant("commandsImage")));
            generatePopup(image, ResourceBundleManager.retrieveHelpPopupConstant("TURTLE_COMMANDS"));
        }));
        turtleQueriesButton.setOnAction((event -> {
            Image image = new Image(getClass().getClassLoader().getResourceAsStream(ResourceBundleManager.retrieveHelpPopupConstant("queriesImage")));
            generatePopup(image, ResourceBundleManager.retrieveHelpPopupConstant("TURTLE_QUERIES"));
        }));
        mathOperationsButton.setOnAction((event -> {
            Image image = new Image(getClass().getClassLoader().getResourceAsStream(ResourceBundleManager.retrieveHelpPopupConstant("mathImage")));
            generatePopup(image, ResourceBundleManager.retrieveHelpPopupConstant("MATH_OPERATIONS"));
        }));
        booleanOperationsButton.setOnAction((event -> {
            Image image = new Image(getClass().getClassLoader().getResourceAsStream(ResourceBundleManager.retrieveHelpPopupConstant("booleanImage")));
            generatePopup(image, ResourceBundleManager.retrieveHelpPopupConstant("BOOLEAN_OPERATIONS"));
        }));
        variablesButton.setOnAction((event -> {
            Image image = new Image(getClass().getClassLoader().getResourceAsStream(ResourceBundleManager.retrieveHelpPopupConstant("variablesImage")));
            generatePopup(image, ResourceBundleManager.retrieveHelpPopupConstant("VARIABLES"));
        }));
    }
    private void addTitle(){
        Image image = new Image(getClass().getClassLoader().getResourceAsStream(ResourceBundleManager.retrieveHelpPopupConstant("helpTitle")));
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        helpRoot.getChildren().add(imageView);
    }

    private void generatePopup(Image image, String string){
        HelpSubPopup subPopup = new HelpSubPopup(image, string);
    }

}
