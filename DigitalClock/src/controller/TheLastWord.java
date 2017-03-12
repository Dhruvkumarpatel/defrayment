	package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class TheLastWord {

	public static void main(String[] args) throws FileNotFoundException{
		
		Scanner scan = new Scanner(new File("A-large.in-2.txt"));
		
		String total = scan.nextLine();
		
		PrintWriter pw = new PrintWriter("A-large2.out.txt");
		int i=1;
		while(scan.hasNext()){	
			String tmp = scan.nextLine();
			String finalWord = theLastWord(tmp);
			System.out.println(finalWord);
			pw.write("Case #"+i+": "+finalWord+"\n");
			i++;
		}
		
		scan.close();
		pw.close();
		
	}
	
	
	public static String theLastWord(String word){
		
		String finalWord="";
		
		finalWord += word.charAt(0);
		for(int i=1; i<word.length();i++){
			
			if(word.charAt(i)>=finalWord.charAt(0)){
				finalWord = word.charAt(i) +finalWord;
			}
			else
			{
				finalWord += word.charAt(i);
			}
		}
		
		return finalWord;
	}
}
