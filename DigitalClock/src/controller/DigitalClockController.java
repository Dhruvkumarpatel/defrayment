package controller;

import java.util.ArrayList;

import model.DigitalClockModel;
import view.DateView;
import view.DigitalClockView;


public class DigitalClockController {
	
	ArrayList<DigitalClockView> dtViewList; //stores views registered by main method
	DigitalClockModel dtModel;	// reference of model
	ArrayList<DateView> dateViewList; // dateView list of views
	
	
	
	public DigitalClockController(DigitalClockView dtView, DateView dateView){
		dtViewList = new ArrayList<DigitalClockView>();
		dateViewList = new ArrayList<DateView>();
		dtViewList.add(dtView);
		dateViewList.add(dateView);
	}
	
	/**
	 * View calls this method to register its views to controller
	 * @param dtView
	 * @param dateView
	 */
	public void registerView(DigitalClockView dtView, DateView dateView){
		
		dtViewList.add(dtView);
		dateViewList.add(dateView);
	}
	
	/**
	 * Model calls this method to communicate with view
	 * @param dtModel
	 */
	public void draw(DigitalClockModel dtModel){
		
		for(int i=0;i<dtViewList.size();i++){
			dtViewList.get(i).draw(dtModel.get_hour(), dtModel.get_mininute(), dtModel.get_second());
			dateViewList.get(i).draw( dtModel.get_month(), dtModel.get_day(), dtModel.get_year());
		}

	}
	
	
	
	
}
