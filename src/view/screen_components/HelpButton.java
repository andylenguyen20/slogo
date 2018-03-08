package view.screen_components;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import propertiesFiles.ResourceBundleManager;
import view.constants.ButtonConstants;
import view.help_items.HelpPopup;

public class HelpButton extends ScreenComponent{
    private Button commandHelpButton;


    public HelpButton() {
        super();
    }

    @Override
    protected void mapUserActions() {
        commandHelpButton.setOnAction((event -> {
            displayHelpPopup();
        }));
    }

    public void generateGUIComponent() {
        BorderPane borderPane = super.getBorderPane();
        this.addHelpButton(borderPane);
    }

    private void addHelpButton(BorderPane borderPane){
        commandHelpButton = new Button(ResourceBundleManager.retrieveButtonLabel("COMMAND_HELP_BUTTON_LABEL"));
        borderPane.setCenter(commandHelpButton);
    }

    private void displayHelpPopup(){
        HelpPopup helpPopup = new HelpPopup();
    }
}
