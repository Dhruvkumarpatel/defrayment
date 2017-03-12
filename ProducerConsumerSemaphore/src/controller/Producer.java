package controller;

import java.util.Random;

class Producer implements Runnable {
	  Queue q;
	  Random rand = new Random();
	  Producer(Queue q) {
	    this.q = q;
	    new Thread(this, "Producer").start();
	  }

	  public void run() {
	    for (int i = 0; i < 100; i++)
	    {	
	      q.put(rand.nextInt(100));
	    }
	  }
	}