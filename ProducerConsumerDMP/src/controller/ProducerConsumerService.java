package controller;

import java.io.FileNotFoundException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
 
 
public class ProducerConsumerService {
 
    public static void main(String[] args)  {
        //Creating BlockingQueue of size 10
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);
        Producer producer;
		try {
			producer = new Producer(queue);
			Consumer consumer = new Consumer(queue);
		
        
        //starting producer to produce messages in queue
        new Thread(producer).start();
        //starting consumer to consume messages from queue
        new Thread(consumer).start();
        System.out.println("Producer and Consumer has been started");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
 
}