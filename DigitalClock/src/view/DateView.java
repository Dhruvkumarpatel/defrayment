package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.time.Month;

import javax.swing.JLabel;
import javax.swing.JPanel;

/*
 * Date View creates a lable which shows Date in all the views.
 */
public class DateView extends JLabel{

	public DateView(){
		setText("Snehit");
	}
	
	/**
	 * This method set the text for date
	 * @param month
	 * @param day
	 * @param year
	 */
	public void draw(int month,int day,int year){
		
		setText(Month.of(month)+" "+day+", "+year);
		
	}
	
	
	

	
}
