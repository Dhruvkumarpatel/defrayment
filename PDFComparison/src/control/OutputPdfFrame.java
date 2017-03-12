package control;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDPixelMap;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDXObjectImage;



/** @see http://stackoverflow.com/questions/7823631 */
public class OutputPdfFrame extends JPanel {
	 JFrame frame = null;
	private static  int SIZE = 300;
	private static  int INSET = 64;
	private static final AlphaComposite OVER_HALF =
	AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 0.4f);
	private boolean src;

	//private static String path="C:\\Users\\U452775\\Desktop\\PDFRepo\\output";
	private static ArrayList<BufferedImage> image=null, overlay=null, output=null;

	public OutputPdfFrame(){}
	public OutputPdfFrame(boolean src) {
		this.src = src;
		this.setBackground(Color.WHITE);
	}

	@Override
	public Dimension getPreferredSize() {
		
		return new Dimension(overlay.get(0).getWidth(), overlay.get(0).getHeight());
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
			
			for(int i=0;i<image.size();i++)
			{
				BufferedImage bufferedImage = new BufferedImage(overlay.get(0).getWidth(), overlay.get(0).getHeight(), BufferedImage.TYPE_INT_RGB);

				g2 =bufferedImage.createGraphics();
				
				g2.drawImage(image.get(i), 0, 0, null);
		
				g2.setComposite(OVER_HALF);
	
				g2.drawImage(overlay.get(i), 0, 0, null);
			
				output.add(bufferedImage);

			}
			try {
				exportToPDF(output, "C:\\Users\\U452775\\Desktop\\PDFRepo\\output\\output_final.pdf");
			} catch (COSVisitorException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
	
			
	}
	
	

	
	public void createDifferenceOverlapPDFs(){
		
		Graphics2D g2;
			
		for(int i=0;i<image.size();i++)
		{
	        
			BufferedImage bufferedImage = new BufferedImage(overlay.get(0).getWidth(), overlay.get(0).getHeight(), BufferedImage.TYPE_INT_RGB);

			g2 =bufferedImage.createGraphics();
			
			g2.drawImage(image.get(i), 0, 0, null);
		
			g2.setComposite(OVER_HALF);
	
			g2.drawImage(overlay.get(i), 0, 0, null);
		
			output.add(bufferedImage);

		}
		try {
			exportToPDF(output, "C:\\Users\\U452775\\Desktop\\PDFRepo\\output\\output_final.pdf");
		} catch (COSVisitorException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public void exportToPDF(ArrayList<BufferedImage> image, String filePath) throws COSVisitorException, IOException{
	    PDDocument doc = null;
	    PDPage page = null;
	    PDXObjectImage ximage = null;
	    doc = new PDDocument();
	    
	    for(int i=0;i<image.size();i++){
		    try {
		        
		        page = new PDPage(new PDRectangle(overlay.get(0).getWidth(), overlay.get(0).getHeight()));  
		        doc.addPage(page);
		        ximage = new PDPixelMap(doc, image.get(i)); 	
		        PDPageContentStream content = new PDPageContentStream(doc, page,true,true);
	
		        //create a new outStream
		        ByteArrayOutputStream baos=new ByteArrayOutputStream();
		        ImageIO.write(image.get(i), "png", baos );
		       
		        //create a new inputstream
		        InputStream in = new ByteArrayInputStream(baos.toByteArray());
		       
		       
		        content.drawImage(ximage, 0,0);
		        content.close();
		    }
		    catch (IOException ie){
		        ie.printStackTrace();
		    }
	    
	    }
	    //save and close
	    doc.save(filePath);
	    doc.close();
	}
	

	public void getPDFs(ArrayList<BufferedImage> imageList1,ArrayList<BufferedImage> imageList2) throws IOException{

		frame = new JFrame();
		image = new ArrayList<BufferedImage>();
		overlay = new ArrayList<BufferedImage>();
		output = new ArrayList<BufferedImage>();
		int size = imageList1.size()<imageList2.size() ? imageList1.size() : imageList2.size();

		for(int i=0;i<size;i++){
			image.add(new OverlapsImages().createRedBlueImage(imageList1.get(i),'R'));
			overlay.add(new OverlapsImages().createRedBlueImage(imageList2.get(i),'C'));
		}
		
		createDifferenceOverlapPDFs();
	}



}
