package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {

	private BlockingQueue<Integer> queue;
	PrintWriter pw;
    
    public Consumer(BlockingQueue<Integer> q) throws FileNotFoundException{
        this.queue=q;
        pw = new PrintWriter(new File("src/consumerOutput.txt"));
    }
 
    @Override
    public void run() {
        try{
            Integer value;
            //consuming messages until exit message is received
            while(true){
            	value = queue.poll();
            	
            	if(value!=null){
            		if(value==200)
            			break;
            		Thread.sleep(10);
            		pw.append(value+"\n");
            		System.out.println("Consumed "+value);
            	}
            	
            }
        }catch(InterruptedException e) {
            e.printStackTrace();
        }
        pw.close();
        System.out.println("Reducer done");
    }
    
}