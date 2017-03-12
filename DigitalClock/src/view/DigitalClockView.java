package view;

import java.awt.BorderLayout;
import java.awt.Graphics;

import javax.swing.JPanel;

/** 
 * Digital Clock view base class
 *  
 *  This is an abstract class, rather than an interface. We need to do this so that 
 *  we can enforce every view in our software to be a JPanel 
 *	(this will help in our UI methods -- for example
 *  we can add any generic DigitalClockView to a UI because they are a Component).
 */
public abstract class DigitalClockView extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	

	//DD: Obviously, this is not a method that should be included in this
	//interface. It is only there to support this sample code. 
	public void draw(int hour,int minute,int second) {};
	
	public  void drawDate(int month, int day,int year){};

}
