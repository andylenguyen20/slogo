package model;

import javafx.scene.paint.Color;

import java.util.List;

/**
 * @author Eddie Zhuang, Andy Nguyen
 * The purpose of this interface is to get relevant information from within the model about the list of colors and list of turtle images so that
 * the Palette screen component can update itself
 */
public interface PaletteObservable {
    List<Color> getColorList();
    List<String> getShapeOptions();
}
