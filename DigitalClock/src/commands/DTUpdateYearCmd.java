package commands;

import model.DigitalClockModel;

/**
 * this method executes command to change years
 *
 */

public class DTUpdateYearCmd implements DigitalClockCmd {

	private DigitalClockModel digitalClockModel;
	private int value;
	
	public DTUpdateYearCmd(DigitalClockModel digitalClockModel,int value){
		this.digitalClockModel = digitalClockModel;
		this.value = value;
	}
	public void Execute() {
		digitalClockModel.set_year(value);
	}
	public void UnExecute() {
		
	}

}
