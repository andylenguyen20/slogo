package controller;


import model.VariablesHistory;
import view.GUI;
import view.screen_components.VariableHistoryBox;

/**
 * @author Andy Nguyen
 * The purpose of this class is to allow the VariableHistoryBox front end component to listen as an observer to the VariableHistory
 * in the backend, as well as perform ceratin actions on the VariableHistory backend when user actions are performed.
 */
public class VariableHistoryBoxController extends Controller implements ClearValueDelegate, ValueModifierDelegate {
    private VariablesHistory variablesHistory;
    private VariableHistoryBox variableHistoryBox;

    /**
     * initializes the controller
     * @param gui
     * @param variablesHistory
     */
    public VariableHistoryBoxController(GUI gui, VariablesHistory variablesHistory){
        super(gui);
        this.variablesHistory = variablesHistory;
    }

    /**
     * initializes the variable history box screen component, which takes in two delegates:
     * first, the ValueModifierDelegate allows the variable history box to delegate the act of changing variable value to the controller
     * second, the ClearValueDelegate allows the variable history box to clear the values in variablehistory backend
     */
    @Override
    protected void initializeScreenComponents() {
        variableHistoryBox = new VariableHistoryBox((ValueModifierDelegate)this, (ClearValueDelegate) this);
    }

    /**
     * sets up observer/observable relationships
     */
    @Override
    protected void setUpConnections() {
        variableHistoryBox.setVariableHistory(variablesHistory);
        variablesHistory.addVariableObserver(variableHistoryBox);
    }

    @Override
    protected void addToGUI() {
        super.getGui().addToScreen(variableHistoryBox);
    }

    /**
     * clears the variable history backend
     */
    @Override
    public void clear() {
        variablesHistory.clearVariableHistory();
    }

    /**
     * changes the value of a variable within variablehistory
     * @param value
     * @param variableName
     */
    @Override
    public void changeValue(String value, String variableName) {
        variablesHistory.changeValue(value, variableName);
    }
}
