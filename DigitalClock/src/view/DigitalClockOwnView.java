package view;

import java.awt.GridLayout;
import java.time.Month;

import javax.swing.JLabel;
import javax.swing.JPanel;


/*
 * This class creates view for digital clock 
 */
public class DigitalClockOwnView extends DigitalClockView{

	private  int hour;
	private int minute;
	private int second;
	
	private JPanel mainPanel;
	private JLabel time,date;
	
	
	/**
	 * constructor : create JLable and add it to panel
	 */
	public DigitalClockOwnView() {
	
		time = new JLabel();
		add(time);
	}
	
	/*
	 * (non-Javadoc)
	 * @see view.DigitalClockView#draw(int, int, int)
	 */
	@Override
	public void draw(int hour, int minute, int second) {
		
		time.setText(hour+":"+minute+":"+second);
		
	}
	
	
	
	
	

	
	
	
	
}
