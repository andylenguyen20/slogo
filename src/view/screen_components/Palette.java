package view.screen_components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.PaletteObservable;
import view.Observer;

import java.util.List;

/**
 * Palette class with visual representation of back end palette options
 * @author Andy Nguyen
 * @author Edward Zhuang
 */
public class Palette extends ScreenComponent implements Observer{

    public static final int RECT_WIDTH = 60;
    public static final int RECT_HEIGHT = 20;
    public static final String PALETTE_TITLE = "Color and Image Palette";
    public static final int VBOX_SPACING = 10;
    private VBox vBox;

    private PaletteObservable paletteObservable;

    /**
     * @see ScreenComponent
     */
    public Palette(){
        super();
    }

    @Override
    protected void mapUserActions() {
    }

    /**
     * Sets up this class' paletteObservable
     * @param paletteObservable interface with methods to retrieve back end palette values
     */
    public void setPaletteObservable(PaletteObservable paletteObservable){
        this.paletteObservable = paletteObservable;
    }

    /**
     * Creates BorderPane and adds front end items to it
     * @see ScreenComponent
     */
    @Override
    public void generateGUIComponent() {
        BorderPane borderPane = super.getBorderPane();
        generateVBox(borderPane);
    }

    /**
     * GeneratesVBox
     * @param borderPane BorderPane where VBox is added
     */
    private void generateVBox(BorderPane borderPane){
        vBox = new VBox();
        vBox.setPadding(new Insets(VBOX_SPACING, VBOX_SPACING, VBOX_SPACING, VBOX_SPACING));
        vBox.setSpacing(VBOX_SPACING);
        Label paletteTitle = new Label(PALETTE_TITLE);
        borderPane.setTop(paletteTitle);
        BorderPane.setAlignment(paletteTitle, Pos.CENTER);
        borderPane.setCenter(vBox);
    }

    /**
     * Updates the visual representation of color and image palette options
     * @param colorList list of colors to be shown
     * @param shapeList list of images to be shown
     */
    public void updatePalette(List<Color> colorList, List<String> shapeList){

        vBox.getChildren().clear();
        for (int x = 0; x< colorList.size(); x++){
            Image image = new Image(getClass().getClassLoader().getResourceAsStream(shapeList.get(x)));
            ImageView imageView = new ImageView();
            imageView.setImage(image);
            imageView.setPreserveRatio(true);
            imageView.setFitHeight(RECT_HEIGHT);

            HBox temp = new HBox();
            Label number = new Label(Integer.toString(x));
            Rectangle r = new Rectangle();
            r.setWidth(RECT_WIDTH);
            r.setHeight(RECT_HEIGHT);
            r.setFill(colorList.get(x));
            temp.getChildren().addAll(number, r, imageView);
            vBox.getChildren().add(temp);
        }
    }

    /**
     * Updates the palette
     */
    @Override
    public void notifyOfChanges() {
        updatePalette(paletteObservable.getColorList(), paletteObservable.getShapeOptions());
    }
}
