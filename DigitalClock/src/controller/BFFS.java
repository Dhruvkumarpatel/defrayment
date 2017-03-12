package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BFFS {

	
	public static void main(String[] args) throws FileNotFoundException{
		
		
Scanner scan = new Scanner(new File("C-small-attempt1.in-2.txt"));
		
		int total = scan.nextInt();
		
		PrintWriter pw = new PrintWriter("C-small-attempt1.out-2.txt");
		int i=1;
		for(int j = 0;j<total;j++){	
			int digit = scan.nextInt();
			int valueArray[] = new int[digit];
			
			for(int k =0;k<digit;k++){
				valueArray[k] = scan.nextInt();
			}
			pw.write("Case #"+i+": "+findBFFS(valueArray)+"\n");
			i++;
		}
		
		scan.close();
		pw.close();
	}
	
	public static int findBFFS(int[] bffs){
		
		List<Integer> set = new ArrayList<Integer>();
		
		System.out.println(bffs.length);
		int extra_values=-1;
		
		set.add(1);
		set.add(bffs[0]);
		
		while(true){
			
			if(bffs.length <set.get(set.size()-1) - 1)
				break;
			if(set.contains(bffs[ set.get(set.size()-1) - 1])){
				break;
			}
			else{
				set.add(bffs[set.get(set.size() -1)-1]);
			}
			
		}
		
		 for(int j=0;j<set.size();j++){
		for(int i=0;i<bffs.length ;i++)
		{
		   
			if(bffs[i]==set.get(j)){
				extra_values++;
			}
		    }
		}
		
		if(!set.contains(1))
			extra_values++;
		
		
		return set.size() +extra_values;
	}
	
}
