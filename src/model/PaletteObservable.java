package model;

import javafx.scene.paint.Color;

import java.util.List;

public interface PaletteObservable {
    public List<Color> getColorList();
    public List<String> getShapeOptions();
}
