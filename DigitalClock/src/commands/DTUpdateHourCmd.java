package commands;

import model.DigitalClockModel;

/**
 * this method executes command to change Hours
 *
 */
public class DTUpdateHourCmd implements DigitalClockCmd{
	private DigitalClockModel digitalClockModel;
	private int value;
	
	public DTUpdateHourCmd(DigitalClockModel digitalClockModel,int value){
		this.digitalClockModel = digitalClockModel;
		this.value = value;
	}
	public void Execute() {
		digitalClockModel.set_hour(value);
	}
	public void UnExecute() {
		digitalClockModel.set_hour(value);
	}
}
