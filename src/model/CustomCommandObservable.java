package model;

import java.util.List;

/**
 * @author Andy Nguyen
 * allows the CustomCommandBox to get commands and update itself
 */
public interface CustomCommandObservable {
    /**
     *
     * @return a list of custom commands
     */
    List<String> getCommands();
}
