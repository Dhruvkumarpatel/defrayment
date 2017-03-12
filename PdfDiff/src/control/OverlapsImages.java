package control;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class OverlapsImages {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {

	}

	public void createTwoColorImags(BufferedImage file1, BufferedImage file2) throws IOException{

		createRedBlueImage(file1,'R');
		createRedBlueImage(file2,'C');
	}


	public  BufferedImage createRedBlueImage(BufferedImage bi,char color) throws IOException{

		//BufferedImage bi =ImageIO.read(file);
		BufferedImage newImage = new BufferedImage(bi.getWidth(),bi.getHeight(), BufferedImage.TYPE_INT_RGB);

		int[] pixels = new int[bi.getHeight() * bi.getWidth()];

		if(color=='R'){

			for (int j = 0; j < bi.getHeight(); j++) {
				for (int i = 0; i < bi.getWidth(); i++) {

					if(bi.getRGB(i, j)!=-1){

						pixels[j*bi.getWidth() + i] = 0xFF0000;
					}
					else {
						pixels[j*bi.getWidth() + i] = bi.getRGB(i, j);
					}
				}
			}

			newImage.setRGB(0, 0, bi.getWidth(), bi.getHeight(), pixels, 0, bi.getWidth());
			return newImage;

		}
		else{

			for (int j = 0; j < bi.getHeight(); j++) {
				for (int i = 0; i < bi.getWidth(); i++) {

					if(bi.getRGB(i, j)!=-1){
						pixels[j*bi.getWidth() + i] = 0x00FFFF;
					}
					else {
						pixels[j*bi.getWidth() + i] = bi.getRGB(i, j);
					}
				}
			}

			newImage.setRGB(0, 0, bi.getWidth(), bi.getHeight(), pixels, 0, bi.getWidth());
			return newImage;

		}

	}


	/*public  void overlapsImages(String path) throws IOException{
		BufferedImage image = ImageIO.read(new File(path+"cyanImage.png"));
		BufferedImage overlay = ImageIO.read(new File(path+ "redImage.png"));

		// create the new image, canvas size is the max. of both image sizes
		int w = Math.max(image.getWidth(), overlay.getWidth());
		int h = Math.max(image.getHeight(), overlay.getHeight());
		BufferedImage combined = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

		// paint both images, preserving the alpha channels
		Graphics2D g = combined.createGraphics();

		g.drawImage(image, 0, 0, null);
		g.drawImage(overlay, 0, 0, null);
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 0.75f));
		// Save as new image
		ImageIO.write(combined, "PNG", new File(path, "combined.png"));
	}*/

}
