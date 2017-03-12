package control;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.imageio.ImageIO;


public class ProcessImage {

	static int startX,startY,endX,endY,pageNumber;
	
	public static int comparepDF(BufferedImage bi,int pageNumber) throws IOException{


		int totalOther=0;
		

		for(int i=0;i<bi.getHeight();i++){
			
			for(int j=0;j<bi.getWidth();j++){

				Color color = new Color(bi.getRGB(j,i));
						
				if(color.getRed()!=255&&color.getGreen()!=255&& color.getBlue()!=255){
				 
					if(!(j>startX && j<endX && i>startY && i<endY && pageNumber==ProcessImage.pageNumber)){
			
						totalOther++;
			
					}
				}

			}

		}

		

		return totalOther;
	}

}
