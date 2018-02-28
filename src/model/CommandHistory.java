package model;

import view.CommandHistoryObserver;

import java.util.ArrayList;
import java.util.List;

public class CommandHistory implements CommandHistoryObservable{
    //Andy's change
    private CommandHistoryObserver commandHistoryObserver;

	private List<String> commands;
	private int index;
	
	public CommandHistory() {
		commands = new ArrayList<String>();
		index = 0;
	}

	//Andy's change
    public void setCommandHistoryObserver(CommandHistoryObserver commandHistoryObserver){
	    this.commandHistoryObserver = commandHistoryObserver;
    }

	public String getCommand () {
		return commands.get(index);
	}
	
	public void addCommand (String command) {
		commands.add(command);
		index++;
        commandHistoryObserver.notifyCommandHistoryObserver();
	}
	
	public List<String> getCommands(){
		return commands;
	}
	
	public void clearHistory(){
		commands.clear();
        commandHistoryObserver.notifyCommandHistoryObserver();
	}
}
