package model;

import java.util.List;

/**
 * @author Andy Nguyen
 * The purpose of the interface is to allow a view component to get the Commands and update itself based off the value of the commands in this
 * CommandHistoryObservable
 */
public interface CommandHistoryObservable {
    /**
     *
     * @return the list of commands within the CommandHistoryObservable
     */
    List<String> getCommands();
}
