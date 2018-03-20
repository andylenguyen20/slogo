package controller;

/**
 * @author Andy Nguyen
 * The purpose of this interface is to allow a given view component to change the value of some variable within the backend by
 * delegating the responsibility to the controller. This is specifically used in the case where VariableHistoryBox (view component) needs
 * to change the value of a specific variable in the variable history backend.
 */
public interface ValueModifierDelegate {
    /**
     * Changes the value of a given variableName to a given string
     * @param value
     * @param variableName
     */
    void changeValue(String value, String variableName);
}
