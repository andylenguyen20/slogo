package model;

import view.Observer;

import java.util.ArrayList;
import java.util.List;

public class CommandHistory implements CommandHistoryObservable{
    private Observer commandHistoryObserver;

	private List<String> commands;
	private int index;

	public CommandHistory() {
		commands = new ArrayList<>();
		index = 0;
	}

    public void addObserver(Observer commandHistoryObserver){
	    this.commandHistoryObserver = commandHistoryObserver;
    }

	public String getCommand () {
		return commands.get(index);
	}
	
	public void addCommand (String command) {
		commands.add(command);
		index++;
        commandHistoryObserver.notifyOfChanges();
	}
	
	public List<String> getCommands(){
		return commands;
	}
	
	public void clearHistory(){
		commands.clear();
        commandHistoryObserver.notifyOfChanges();
	}
}
