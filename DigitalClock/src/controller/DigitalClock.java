package controller;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import model.DigitalClockModel;
import view.DateView;
import view.DigitalClock3DialsView;
import view.DigitalClockOwnView;
import view.DigitalClockSettingsUI;
import view.DigitalClockView;


/**
 * This is our **main** class for a DigitalClock
 */
public class DigitalClock extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public DigitalClock(){
		
		new DigitalClockSettingsUI();
		
		
		
	}
	
	public static void main(String argv[]) {
	        //DD *note -- this really doesn't follow the MVC pattern yet.
			//Here, we are registering a view with a model. We should instead register
			//a controller with the model, and as the user opens views we register
			//views with the controller. 
	      DigitalClock dClock = new DigitalClock();
	}
	
	

	
}
