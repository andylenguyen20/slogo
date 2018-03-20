package model;

import java.util.Map;

/**
 * @author Andy Nguyen
 * The purpose of this interface is to allow screen components (in this program, just VariableHistoryBox) to listen and update itself
 * based off of information from this VariableHistoryObservable
 */
public interface VariableHistoryObservable {
    /**
     *
     * @return a copy of the variables in the variable history
     */
    Map<String,Double> getVariableMapCopy();
}
