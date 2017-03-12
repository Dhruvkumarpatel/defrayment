package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.concurrent.Semaphore;

public class Queue {
	int value;
	
	static Semaphore consumerSema = new Semaphore(0);
	static Semaphore producerSema = new Semaphore(1);
	private PrintWriter producerWriter, consumerWriter;
	
	public Queue(){
		try {
			producerWriter = new PrintWriter(new File("src/producerOutput.txt"));
			consumerWriter = new PrintWriter(new File("src/consumerOutput.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void get(){
		try {
			consumerSema.acquire();
		    } catch (InterruptedException e) {
		      System.out.println("InterruptedException caught");
		    }
		    System.out.println("Got: " + value);
		    consumerWriter.append(value+"\n");
		    producerSema.release();
	}
	
	public void put(int n) {
	    try {
	    	producerSema.acquire();
	    } catch (InterruptedException e) {
	      System.out.println("InterruptedException caught");
	    }

	    this.value = n;
	    
	    System.out.println("Put: " + n);
	    producerWriter.append(n+"\n");
	    consumerSema.release();
	  }
	
	
	public void close(){
		producerWriter.close();
		consumerWriter.close();
	}
	
}
