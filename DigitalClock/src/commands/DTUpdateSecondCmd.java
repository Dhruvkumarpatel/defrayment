package commands;

import model.DigitalClockModel;

/**
 * this method executes command to change seconds
 *
 */

public class DTUpdateSecondCmd implements DigitalClockCmd {

	private DigitalClockModel digitalClockModel;
	public DigitalClockModel getDigitalClockModel() {
		return digitalClockModel;
	}



	public int getValue() {
		return value;
	}
	private int value;
	
	public DTUpdateSecondCmd(DigitalClockModel digitalClockModel,int value){
		this.digitalClockModel = digitalClockModel;
		this.value = value;
	}
	
	
	
	public void Execute() {
		digitalClockModel.set_second(value);
	}
	public void UnExecute() {
		
	}
}
