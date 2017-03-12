package model;

import java.util.Calendar;
import java.util.Date;

import controller.DigitalClockController;


/**
 * Digital timer model
 */
public class DigitalClockModel implements Runnable {
	// data members
	private int _second;
	private int _mininute;
	private int _hour;
	private int _dayofweek;
	private int _day;
	private int _month;
	private int _year;
    private Thread _thread;
    DigitalClockController _dtController = null;
    Calendar calendar = null;
    Date date = null;
    
	public DigitalClockModel(DigitalClockController view) {
		calendar = Calendar.getInstance();
		date = new Date();
		_second = calendar.get(Calendar.SECOND);
		_mininute = calendar.get(Calendar.MINUTE);
		_hour = calendar.get(Calendar.HOUR);
		_dayofweek = Calendar.DAY_OF_WEEK;
		_day = calendar.get(Calendar.DATE);
		_month = calendar.get(Calendar.MONTH)+1;
		_year = calendar.get(Calendar.YEAR);
		
		
		//System.out.println(_day + " "+_dayofweek+" "+_month+" "+_year+" "+date.getDate());
		
		
		_dtController = view;
	}
	
	public void start() {
        _thread = new Thread(this);
        _thread.setPriority(Thread.MIN_PRIORITY);
        _thread.start();
    }


    public synchronized void stop() {
        _thread = null;
    }

    /*
     * getters/setters for class variables
     */
    public int get_second() {
		return _second;
	}

	public void set_second(int _second) {
		if(_second==60){
			set_mininute(get_mininute()+1);
			this._second = 0;
		}
		else{
			this._second = _second;
		}
	}

	public int get_mininute() {
		return _mininute;
		
	}

	public void set_mininute(int _mininute) {
		
		
		
		if(_mininute==60){
			set_hour(get_hour()+1);
			this._mininute = 0;
		}
		else{
			this._mininute = _mininute;
		}
	}

	public int get_hour() {
		return _hour;
	}

	public void set_hour(int _hour) {
		if(_hour==12)
			this._hour = 0;
		else
			this._hour = _hour;
	}

	public int get_dayofweek() {
		return _dayofweek;
	}

	public void set_dayofweek(int _dayofweek) {
		this._dayofweek = _dayofweek;
	}

	public int get_day() {
		return _day;
	}

	public void set_day(int _day) {
		this._day = _day;
	}

	public int get_month() {
		return _month;
	}

	public void set_month(int _month) {
		this._month = _month;
	}

	public int get_year() {
		return _year;
	}

	public void set_year(int _year) {
		this._year = _year;
	}

	public Thread get_thread() {
		return _thread;
	}

	public void set_thread(Thread _thread) {
		this._thread = _thread;
	}

	public DigitalClockController get_dtController() {
		return _dtController;
	}

	public void set_dtController(DigitalClockController _dtController) {
		this._dtController = _dtController;
	}
    
    public void run() {
        Thread me = Thread.currentThread();
        while (_thread == me) {
        	// DD: **Note what is happening here: the model is directly telling
        	// the view to redraw every second. This is *wrong* -- every second,
        	// the model should update its internal state. And whenever there is
        	// an internal state change, the controller must be alerted. Each
        	// controller will then tell all views to update itself accordingly.  
        	_dtController.draw(this);
            try {
                _thread.sleep(1000);
                set_second(get_second()+1);
               
            } catch (InterruptedException e) { break; }
        }
        _thread = null;
    }


	
	
	
}
