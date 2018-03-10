package propertiesFiles;

import java.util.ResourceBundle;

/**
 * Class responsible for accessing items from properties files
 * @author Andy Nguyen
 * @author Edward Zhuang
 */
public final class ResourceBundleManager {
    public static final String BUTTON_LABELS = "ButtonLabels";
    public static final String HELP_POPUP = "HelpPopup";
    public static final String LABELS = "Labels";
    public static final String ON_SCREEN_COMMANDS = "OnScreenCommands";
    private static ResourceBundle resources;
    private static final String DEFAULT_RESOURCE_PATH = "propertiesFiles/";

    /**
     * retrieves string from ButtonLabels.properties
     * @param s string key
     * @return resource bundle property value
     */
    public static String retrieveButtonLabel(String s){
        resources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PATH + BUTTON_LABELS);
        return resources.getString(s);
    }

    /**
     * retrieves string from HelpPopup.properties
     * @param s string key
     * @return resource bundle property value
     */
    public static String retrieveHelpPopupConstant(String s){
        resources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PATH + HELP_POPUP);
        return resources.getString(s);
    }

    /**
     * retrieves string from Labels.properties
     * @param s string key
     * @return resource bundle property value
     */
    public static String retrieveLabel(String s){
        resources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PATH + LABELS);
        return resources.getString(s);
    }

    /**
     * retrieves string from OnScreenCommands.properties
     * @param s string key
     * @return resource bundle property value
     */
    public static String retrieveOnScreenCommand(String s){
        resources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PATH + ON_SCREEN_COMMANDS);
        return resources.getString(s);
    }
}
