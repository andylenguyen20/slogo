package controller;

import javafx.scene.paint.Color;

public interface PaletteDelegate {
    void changeBackgroundColor(Color color);
    void changePenColor(Color color);
    void changeTurtleImage(String image);
}
