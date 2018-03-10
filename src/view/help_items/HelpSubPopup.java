package view.help_items;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * Creates a popup with an image.
 * Used by HelpPopup
 * @author Edward Zhuang
 */
public class HelpSubPopup {
    Group popupRoot;
    Stage popupStage;

    /**
     * Constructor
     * @param image image to be displayed on the popup
     * @param string title of popup
     */
    public HelpSubPopup (Image image, String string){
        popupRoot = new Group();
        popupStage = new Stage();
        popupStage.setTitle(string);
        popupStage.setScene(new Scene(popupRoot, (int)image.getWidth(),(int)image.getHeight()));
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        popupRoot.getChildren().add(imageView);
    }

    /**
     * Shows the popup
     */
    public void show(){
        popupStage.show();
    }
}
