package view.factories;

import javafx.scene.control.ComboBox;

public class ComboBoxFactory {
    public static final String[] LANGUAGE_LIST = {"English", "Chinese"};
    public static final String[] BACKGROUND_COLOR_LIST = {"White", "Blue"};
    public static final String[] PEN_COLOR_LIST = {"Black", "Red", "Green"};
    public static final String[] TURTLE_IMAGE_LIST = {"black_and_white_turtle.PNG", "colorful_turtle.png", "green_turtle.png"};

    public static ComboBox<String> generateComboBox(String[] options){
        ComboBox<String> comboBox = new ComboBox<>();
        for (int x = 0 ; x < options.length; x++) {
            comboBox.getItems().add(options[x]);
        }
        comboBox.getSelectionModel().selectFirst();
        return comboBox;
    }
}
