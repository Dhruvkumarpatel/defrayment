package commands;

import model.DigitalClockModel;

/**
 * this method executes command to change Months
 *
 */

public class DTUpdateMonthCmd implements DigitalClockCmd {

	private DigitalClockModel digitalClockModel;
	private int value;
	
	public DTUpdateMonthCmd(DigitalClockModel digitalClockModel,int value){
		this.digitalClockModel = digitalClockModel;
		this.value = value;
	}
	public void Execute() {
		digitalClockModel.set_month(value);
	}
	public void UnExecute() {
		
	}
}
