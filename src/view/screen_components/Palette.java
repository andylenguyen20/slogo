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

public class Palette extends ScreenComponent implements Observer{

    public static final int RECT_WIDTH = 60;
    public static final int RECT_HEIGHT = 20;
    public static final String PALETTE_TITLE = "Color and Image Palette";
    public static final int VBOX_SPACING = 10;
    private VBox vBox;

    private PaletteObservable paletteObservable;

    public Palette(){
        super();
    }

    @Override
    protected void mapUserActions() {

    }

    public void setPaletteObservable(PaletteObservable paletteObservable){
        this.paletteObservable = paletteObservable;
    }

    @Override
    public void generateGUIComponent() {
        BorderPane borderPane = super.getBorderPane();
        generateVBox(borderPane);
    }

    private void generateVBox(BorderPane borderPane){
        vBox = new VBox();
        vBox.setPadding(new Insets(VBOX_SPACING, VBOX_SPACING, VBOX_SPACING, VBOX_SPACING));
        vBox.setSpacing(VBOX_SPACING);
        Label title2 = new Label(PALETTE_TITLE);
        borderPane.setTop(title2);
        BorderPane.setAlignment(title2, Pos.CENTER);
        borderPane.setCenter(vBox);
    }

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


    @Override
    public void notifyOfChanges() {
        updatePalette(paletteObservable.getColorList(), paletteObservable.getShapeOptions());
    }
}
