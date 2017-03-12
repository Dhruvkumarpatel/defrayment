package control;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TrianlgeSum {
	
	ArrayList<ArrayList<Integer>> triangle=null;
	
	
	private void findMaxPathSumInTriangle() {
		
		System.out.println("Max path = "+sumTriangle(0, 0, 0));
	}
	
	public int sumTriangle(int i,int j,int sum){
		
		if(i<0 || j<0 || i>triangle.size()-1 || j>triangle.get(i).size()-1)
		{
			return 0;
		}
		
		int leftSum= sum+triangle.get(i).get(j) + sumTriangle(i+1, j-1, sum);
		int rightSum = sum+triangle.get(i).get(j)+sumTriangle(i+1,j+1,sum);
		int bottomSum= sum +triangle.get(i).get(j)+ sumTriangle(i+1, j, sum);
		
		if(leftSum>rightSum && leftSum>bottomSum)
		{
			return leftSum;
		}
		else if(rightSum>leftSum && rightSum>bottomSum){
			return rightSum;
		}
		else {
			return bottomSum;
		}
	}
	
	
	public TrianlgeSum() throws NumberFormatException, IOException{
	/*	ArrayList<Integer> row1 = new ArrayList<Integer>();
		row1.add(1);
		
		ArrayList<Integer> row2 = new ArrayList<Integer>();
		row2.add(1);
		row2.add(3);
		
		ArrayList<Integer> row3 = new ArrayList<Integer>();
		row3.add(3);
		row3.add(2);
		row3.add(1);
		
		ArrayList<Integer> row4 = new ArrayList<Integer>();
		row4.add(2);
		row4.add(1);
		row4.add(3);
		row4.add(4);
		
		ArrayList<Integer> row5 = new ArrayList<Integer>();
		row5.add(3);
		row5.add(5);
		row5.add(2);
		row5.add(4);
		row5.add(1);
		
		
		
		triangle.add(row1);
		triangle.add(row2);
		triangle.add(row3);
		triangle.add(row4);
		triangle.add(row5);
		*/
		
		//System.out.println(sumTriangle(0, 0, 0));
		loadTriangle("src/TriangleData.txt");
	}
	
	public void printTriangle(){
		
		System.out.println("\n");
		
		for(int i=0;i<triangle.size();i++){
			
			for(int j=0;j<triangle.get(i).size();j++){
				
				System.out.print(triangle.get(i).get(j)+" ");
			}
			
			System.out.println();
		}
		
		System.out.println("\n");
		
	}
	
	
	
	public void loadTriangle(String fileName) throws NumberFormatException, IOException{
		
		File file = new File(fileName);
		
		BufferedReader reader = new BufferedReader(new FileReader(file));
		
		String line=null;
		
		while((line=reader.readLine())!=null){
			
			int numberOfTestCases = Integer.parseInt(line);
			
			for(int i=0;i<numberOfTestCases;i++){
				triangle = new ArrayList<ArrayList<Integer>>();
				int numberOfRows = Integer.parseInt(reader.readLine());
				
				for(int j=0;j<numberOfRows;j++){
					
					String row = reader.readLine();
					String[] rowToken = row.split(" ");
					ArrayList<Integer> rowList = new ArrayList<Integer>();
					
					for(int k=0;k<rowToken.length;k++){
						rowList.add(Integer.parseInt(rowToken[k]));						
					}
					
					triangle.add(rowList);
				}
				
				printTriangle();
				findMaxPathSumInTriangle();
				
				
			}
			
			
		}
		
		reader.close();
	
	}
	
	


	public static void main(String[] args){
		
		try {
			new TrianlgeSum();
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

	
	
	
	
	
	
	
}
