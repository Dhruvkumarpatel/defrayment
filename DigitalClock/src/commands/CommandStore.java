package commands;

import java.util.ArrayList;
import java.util.Stack;

/**
 * For storing a history of all commands that have been made
 */
public class CommandStore {
	private static CommandStore commandStore=null;
	ArrayList<DigitalClockCmd> _cmdList;
	int _indexCurCmd;
	int lastItem;
	Stack<DigitalClockCmd> undo,redo;
	
	private CommandStore() {
		_cmdList = new ArrayList<DigitalClockCmd>();
		_indexCurCmd = -1;
		undo = new Stack<DigitalClockCmd>();
		redo = new Stack<DigitalClockCmd>();
	}
	
	public static CommandStore getInstance(){
		
		if(commandStore==null){
			commandStore = new CommandStore();
		}
		
		return commandStore;
	}
	
	public void Execute(DigitalClockCmd cmd) {
		cmd.Execute(); // execute commnad
		_cmdList.add(cmd); // add command to list
		_indexCurCmd++; // increase current index
		undo.add(cmd); // add command to undo stack
		redo.clear(); // clear redo stack
		
	}
	public void Undo() {
		System.out.println("undo called");
		if(!undo.empty()){
			DigitalClockCmd cmd = undo.pop(); // get the recent command 
			cmd.Execute(); // execute it 
			redo.add(cmd); // put that command into redo stack
		}
	}
	public void Redo() {
		System.out.println("undo called");
		if(!redo.empty()){
			DigitalClockCmd cmd = redo.pop(); // get the recent command added from stack
			cmd.UnExecute(); // execute that command
			undo.add(cmd); // push it back on to undo stack
		}
	}

}
