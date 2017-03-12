
package commands;

import model.DigitalClockModel;

/**
 * this method executes command to change Minutes
 *
 */
public class DTUpdateMinuteCmd implements DigitalClockCmd {

	private DigitalClockModel digitalClockModel;
	private int value;
	
	public DTUpdateMinuteCmd(DigitalClockModel digitalClockModel,int value){
		this.digitalClockModel = digitalClockModel;
		this.value = value;
	}
	public void Execute() {
		digitalClockModel.set_mininute(value);
	}
	public void UnExecute() {
		
	}
}
