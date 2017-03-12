package commands;

import java.util.LinkedList;
import java.util.Queue;

import model.DigitalClockModel;

/**
 * An example command. 
 * Note that to UnExecute the day of the week, we have to know what
 * day to roll it back to.. 
 * Do not be afraid to define whatever instance variables and methods you need here.
 */
public class DTUpdateDayOfWeekCmd implements DigitalClockCmd {
	
	private DigitalClockModel digitalClockModel;
	private int value;
	
	public DTUpdateDayOfWeekCmd(DigitalClockModel digitalClockModel,int value){
		this.digitalClockModel = digitalClockModel;
		this.value = value;
	}

	public void Execute() {
		digitalClockModel.set_day(value);
	}
	public void UnExecute() {
		digitalClockModel.set_day(value);
	}
}
// And of course, you will define a number of different DigitalClockCmd Objects.