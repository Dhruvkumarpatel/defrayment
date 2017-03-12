package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {

    private BlockingQueue<Integer> queue;
    private Random rand;
    PrintWriter pw;
     
    
    public Producer(BlockingQueue<Integer> q) throws FileNotFoundException{
        this.queue=q;
        rand = new Random();
        pw = new PrintWriter(new File("src/producerOutput.txt"));
       
    }
    @Override
    public void run() {
        //produce messages
        for(int i=0; i<100; i++){
        	int randomNumber = rand.nextInt(100);
           
            try {
                Thread.sleep(i);
                queue.put(randomNumber);
                pw.append(randomNumber+"\n");
                System.out.println("Produced "+randomNumber);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
			queue.put(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        pw.close();
       System.out.println("Producer done");
    }
}
