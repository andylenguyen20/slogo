package propertiesFiles;

import java.util.ResourceBundle;

public final class ResourceBundleManager {
    public static final String BUTTON_LABELS = "ButtonLabels";
    public static final String HELP_POPUP = "HelpPopup";
    public static final String LABELS = "Labels";
    private static ResourceBundle resources;
    private static final String DEFAULT_RESOURCE_PATH = "propertiesFiles/";

    public static String retrieveButtonLabel(String s){
        resources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PATH + BUTTON_LABELS);
        return resources.getString(s);
    }

    public static String retrieveHelpPopupConstant(String s){
        resources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PATH + HELP_POPUP);
        return resources.getString(s);
    }

    public static String retrieveLabel(String s){
        resources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PATH + LABELS);
        return resources.getString(s);
    }

}
