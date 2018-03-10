package view.factories;

import javafx.scene.control.ComboBox;

/**
 * Factory class which is used to produce ComboBoxes
 * @author Andy Nguyen
 */
public class ComboBoxFactory {

    /**
     * creates and returns a ComboBox with String options
     * @param options array of strings options to be put into ComboBox
     * @return created comboBox
     */
    public static ComboBox<String> generateStringComboBox(String[] options){
        ComboBox<String> comboBox = new ComboBox<>();
        for (int x = 0 ; x < options.length; x++) {
            comboBox.getItems().add(options[x]);
        }
        comboBox.getSelectionModel().selectFirst();
        return comboBox;
    }

    /**
     * creates and returns a ComboBox with int options
     * @param numOptions array of int options to be put into ComboBox
     * @return created comboBox
     */
    public static ComboBox<Integer> generateIntegerComboBox(int numOptions){
        ComboBox<Integer> comboBox = new ComboBox<>();
        for (int x = 0 ; x < numOptions; x++) {
            comboBox.getItems().add(x);
        }
        comboBox.getSelectionModel().selectFirst();
        return comboBox;
    }
}
