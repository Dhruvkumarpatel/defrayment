package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class RankAndFile {

	public static void main(String[] args) throws FileNotFoundException{
		
        Scanner scan = new Scanner(new File("B-small-attempt0.in-2.txt"));
		
		int total = Integer.parseInt(scan.nextLine());
		
		
		PrintWriter pw = new PrintWriter("B-small-attempt0.out-2.txt");
		int i=1;
		
		
		
		for(int p=0;p<total;p++){
			int dimen = Integer.parseInt(scan.nextLine());
			
			char[] [] matrix = new char[2*dimen-1][dimen];
		for(int k=0;k<2*dimen-1;k++){	
			
			String tmp = scan.nextLine();
			
			System.out.println(tmp);
			
			String[] tmpString = tmp.split(" ");
			
			for(int l=0;l<tmpString.length;l++){
			matrix[k][l] = tmpString[l].charAt(0);
			}
			
			
		}
		
	
		String finalWord = rankAndFile(matrix);
		System.out.println("final: "+finalWord);
		pw.write("Case #"+i+": "+finalWord+"\n");
		i++;
		}
		scan.close();
		pw.close();
		
	}
	
	
	public static String rankAndFile(char[][] word)
	{
		
		System.out.println("rows " + word.length);
		System.out.println("columns " + word[0].length);
		
		for(int i=0 ; i<word[0].length;i++){
			String tempword="";
		for(int j = word.length -1; j>=word[0].length -1;j--){
			
				
				tempword=tempword+word[j][i];
			}
			
			System.out.println("after " + tempword);
			if(!tempword.equals(new String(word[i]))){
				return tempword;
			}
		}	
		return null;
	}
	
}
