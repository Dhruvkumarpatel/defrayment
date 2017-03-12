package view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.Month;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.DigitalClockModel;

import commands.CommandStore;
import commands.DTUpdateDayOfWeekCmd;
import commands.DTUpdateHourCmd;
import commands.DTUpdateMinuteCmd;
import commands.DTUpdateMonthCmd;
import commands.DTUpdateSecondCmd;
import commands.DTUpdateYearCmd;

import controller.DigitalClockController;

/**
 * Options UI
 * This is the class that lets a user change the settings of the 
 * clock. Changes in settings will create commands. Commands will 
 * modify the model directly. The controller will then be alerted 
 * that a change occured (just as if one second passed), and update
 * all registered views accordingly.
 */
public class DigitalClockSettingsUI implements ActionListener,ItemListener{
	// TBD
	private JPanel mainPanel;
	private JButton addAnalog, addDigital,changeSeconds,changeMinute,changeHour,
					changeYear, undoCommandButton, redoCommandButton;
	private JTextField changeSecondTF,changeMinuteTF,changeHourTF,changeYearTF;
	private JComboBox changeDayCB, changeMonthCB;
	private JLabel changeDayL, changeMonthL;
	private DigitalClockModel dtModel=null;
	CommandStore cmdStore = CommandStore.getInstance();
	DigitalClockController dtCotroller=null;

    JFrame f1 =null, f2 = null; // frames for two different types of clocks.

    public DigitalClockSettingsUI(){
    	
		 JFrame f = new JFrame("Menus");
	        f.addWindowListener(new WindowAdapter() {
	            public void windowClosing(WindowEvent e) {System.exit(0);}
	          
	        });
	        
	        mainPanel = new JPanel();
	        mainPanel.setLayout(new GridLayout(8,2));
	        addAnalog = new JButton("Add Analog");
	        addDigital =  new JButton("Add Digital");
	        changeSeconds =  new JButton("Change Seconds");
	        changeMinute =  new JButton("Change Minute");
	        changeHour =  new JButton("Change Hour");
			changeYear =  new JButton("Change Year");
			undoCommandButton = new JButton("Undo Command");
			redoCommandButton = new JButton("Redo Command");
			
			changeDayL = new JLabel("Change Day of Month");
			changeMonthL = new JLabel("Change Month");
			
			changeSecondTF = new JTextField();
			changeMinuteTF = new JTextField();
			changeHourTF = new JTextField();
			changeYearTF = new JTextField();
			
			changeDayCB = new JComboBox();
			changeMonthCB =new JComboBox<Month>();
		
			Calendar calendar = Calendar.getInstance();
			fillDayComboBox(calendar.get(Calendar.MONTH)+1,2015);
			fillMonthComboBox();
		
			
			changeDayCB.setSelectedItem(calendar.get(Calendar.DATE));
			changeMonthCB.setSelectedItem(Month.of(calendar.get(Calendar.MONTH)+1));
			
			mainPanel.add(addAnalog);
			mainPanel.add(addDigital);
			mainPanel.add(changeSeconds);
			mainPanel.add(changeSecondTF);
			mainPanel.add(changeMinute);
			mainPanel.add(changeMinuteTF);
			mainPanel.add(changeHour);
			mainPanel.add(changeHourTF);
			mainPanel.add(changeDayL);
			mainPanel.add(changeDayCB);
			mainPanel.add(changeMonthL);
			mainPanel.add(changeMonthCB);
			mainPanel.add(changeYear);
			mainPanel.add(changeYearTF);
			mainPanel.add(undoCommandButton);
			mainPanel.add(redoCommandButton);
	        
			f.getContentPane().add(mainPanel);
			
	        f.pack();
	        f.setSize(new Dimension(400,300));
	        f.show();
	        
	        addAnalog.addActionListener(this);
	        addDigital.addActionListener(this);
	        changeSeconds.addActionListener(this);
	        changeMinute.addActionListener(this);
	        changeHour.addActionListener(this);
	        changeDayCB.addItemListener(this);
	        changeMonthCB.addItemListener(this);
	        changeYear.addActionListener(this);
	        undoCommandButton.addActionListener(this);
	        redoCommandButton.addActionListener(this);
	        
	        
	        initialize3DialsView();
	        initializeOwnView();
	}
	
	/**
	 * this method create combobox for day as per month and year
	 * @param month
	 * @param year
	 */
	public void fillDayComboBox(int month,int year){
		
		
		for(int i=0;i<daysInMonth(month,year);i++){
			changeDayCB.addItem(i+1);
		}
	}
	
	
	/** 
	 * this method fill the value in month combobox
	 */
	public void fillMonthComboBox(){
		
		
		for(int i=1;i<=12;i++){
			changeMonthCB.addItem(Month.of(i));
		}
	}
	
	
	/**
	 * this methods counts how many days are there in perticular month
	 * @param monthNumber
	 * @param year
	 * @return
	 */
	public int daysInMonth(int monthNumber,int year)
	{
		if(monthNumber==2 && year%4==0)
			return 29;
		
	   if (monthNumber < 1 || monthNumber > 12)
	      return 0;
	   switch(monthNumber)
	   {
	      case 2:
	         return 28;
	      case 3:
	      case 4:
	      case 9:
	      case 11:// intentional fall-through
	         return 30;
	      default:
	         return 31;
	   }
	}
	
	
	/** 
	 * this method creates 3Dials View when user clicks it
	 */
	public void initialize3DialsView(){
		 DigitalClockView dtView = new DigitalClock3DialsView();
	       DateView dateView = new DateView();
	       dtCotroller = new DigitalClockController(dtView,dateView);
	         dtModel = new DigitalClockModel(dtCotroller);
	        dtModel.start();
	        
	        f1= new JFrame("Digitial Clock: Design Patterns implementation");
	        f1.setDefaultCloseOperation(f1.DISPOSE_ON_CLOSE);
	        f1.getContentPane().add("North",dateView);
	        f1.getContentPane().add("Center", dtView);
	        f1.pack();
	        f1.setSize(new Dimension(400,300));
	      
	        f1.show();
	        f1.setVisible(false);
	        addAnalog.addActionListener(this);
	}
	
	/**
	 * this method creates its own digital view when user clicks button
	 */
	public void initializeOwnView(){
		 DigitalClockView dtView = new DigitalClockOwnView();
	       DateView dateView = new DateView();
	         dtCotroller.registerView(dtView,dateView);
	      
	        
	        f2= new JFrame("Digitial Clock: Design Patterns implementation");
	        f2.setLayout(new GridLayout(2,1));
	        f2.setDefaultCloseOperation(f2.DISPOSE_ON_CLOSE);
	        f2.getContentPane().add("North",dateView);
	        f2.getContentPane().add("Center", dtView);
	        f2.pack();
	        f2.setSize(new Dimension(400,300));
	        f2.show();
	        f2.setVisible(false);
	}

	/**
	 * events for different button and combobox
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == addAnalog){
			
			f1.setVisible(true);
		}
		else if(e.getSource() == addDigital){
			f2.setVisible(true);
		}
		else if(e.getSource() == changeSeconds){
			DTUpdateSecondCmd updateSecondCmd = new DTUpdateSecondCmd(dtModel,Integer.parseInt(changeMinuteTF.getText()));
			cmdStore.Execute(updateSecondCmd);
		}
		else if(e.getSource() == changeMinute){
			DTUpdateMinuteCmd updateMinuteCmd = new DTUpdateMinuteCmd(dtModel,Integer.parseInt(changeMinuteTF.getText()));
			cmdStore.Execute(updateMinuteCmd);
		}
		else if(e.getSource() == changeHour){
			DTUpdateHourCmd updateHourCmd = new DTUpdateHourCmd(dtModel,Integer.parseInt(changeHourTF.getText()));
			cmdStore.Execute(updateHourCmd);
		}
		else if(e.getSource() == changeYear){
			DTUpdateYearCmd updateYearCmd = new DTUpdateYearCmd(dtModel,Integer.parseInt(changeYearTF.getText()));
			cmdStore.Execute(updateYearCmd);
		}
		else if(e.getSource() == undoCommandButton){
			cmdStore.Undo();
		}
		else if(e.getSource() == redoCommandButton){
			cmdStore.Redo();
		}
		
	}


	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == changeDayCB){
			DTUpdateDayOfWeekCmd updateDayCmd = new DTUpdateDayOfWeekCmd(dtModel, (int) e.getItem());
			cmdStore.Execute(updateDayCmd);
		}
		else if(e.getSource() == changeMonthCB){
			
			Calendar calendar = Calendar.getInstance();
			Month month = (Month) e.getItem();
			fillDayComboBox(month.getValue(),dtModel.get_year());
			
			DTUpdateMonthCmd updateDayCmd = new DTUpdateMonthCmd(dtModel,month.getValue());
			cmdStore.Execute(updateDayCmd);
		}
	}
	
	
}
