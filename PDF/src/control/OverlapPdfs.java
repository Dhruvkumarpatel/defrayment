package control;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDStream;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.PDExtendedGraphicsState;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDXObjectForm;
import org.apache.pdfbox.util.MapUtil;

public class OverlapPdfs {
	
	static File file1 = null;
	private ArrayList<BufferedImage> imageList1;
	private ArrayList<BufferedImage> imageList2;
	
	public OverlapPdfs(File file1,File file2) throws IOException, COSVisitorException{
		
		imageList1 = new ArrayList<BufferedImage>();
		imageList2 = new ArrayList<BufferedImage>();
		
		File outputFile=new File("C:\\Users\\U452775\\Desktop\\PDFRepo\\output\\output.pdf");
	
		PDDocument watermarkDoc = PDDocument.load(file1);
		PDDocument doc = PDDocument.load(file2);
 
		overlayWithDarkenBlendMode(watermarkDoc, doc);
   
	   	watermarkDoc.save(outputFile);
	   	
	   	this.file1 = file1;
	   	
	   	createImage(file1,false);
	   	createImage(file2,false);
	    createImage(outputFile,true);
	    /*ImageIO.write(imageList1.get(0), "PNG",new File("C:\\Users\\U452775\\Desktop\\PDFRepo\\output",file1.getName()));
	    ImageIO.write(imageList1.get(1), "PNG",new File("C:\\Users\\U452775\\Desktop\\PDFRepo\\output",file2.getName()));
	    ImageIO.write(imageList2.get(0), "PNG",new File("C:\\Users\\U452775\\Desktop\\PDFRepo\\output",outputFile.getName()));
	    */
	    new OutputPdfFrame().getPDFs(imageList1,imageList2);
	    
	    doc.close();
	    watermarkDoc.close();
		
	}
	
	
	
	
	
	public void overlayWithDarkenBlendMode(PDDocument document, PDDocument overlay) throws IOException
	{
	  

	    List<PDPage> pages = document.getDocumentCatalog().getAllPages();
	    int i=0;
	    for (PDPage page: pages)
	    {
	    	PDXObjectForm xobject = importAsXObject(document, (PDPage) overlay.getDocumentCatalog().getAllPages().get(i++));
	  	    PDExtendedGraphicsState darken = new PDExtendedGraphicsState();
	  	       
	  	    darken.getCOSDictionary().setName("BM", "Darken");
	  	    
	        Map<String, PDExtendedGraphicsState> states = page.getResources().getGraphicsStates();
	        
	        if (states == null)
	            states = new HashMap<String, PDExtendedGraphicsState>();
	        String darkenKey = MapUtil.getNextUniqueKey(states, "Dkn");
	        
	        states.put(darkenKey, darken);
	        
	        page.getResources().setGraphicsStates(states);
	       
	        
	        PDPageContentStream stream = new PDPageContentStream(document, page, true, false, true);
	        stream.setStrokingColor(1, 0, 0);
	        stream.appendRawCommands(String.format("/%s gs ", darkenKey));
	        stream.drawXObject(xobject, 0, 0, 1, 1);
	        stream.close();
	       // document.close();
	    }
	}

	PDXObjectForm importAsXObject(PDDocument target, PDPage page) throws IOException
	{
	    final PDStream xobjectStream = new PDStream(target, page.getContents().createInputStream(), false);
	    final PDXObjectForm xobject = new PDXObjectForm(xobjectStream);

	    xobject.setResources(page.findResources());
	    xobject.setBBox(page.findCropBox());

	    COSDictionary group = new COSDictionary();
	  
	    group.setName("S", "Transparency");
	    group.setBoolean(COSName.getPDFName("K"), true);
	    xobject.getCOSStream().setItem(COSName.getPDFName("Group"), group);

	    return xobject;
	}
	

	public  void createImage(File sourceFile,boolean check){
		 try {
			 	double total=0.0;
			 	String msg="";
		        String destinationDir = "C:\\Users\\U452775\\Desktop\\PDFRepo\\output\\"; // converted images from pdf document are saved here

		        if (sourceFile.exists()) {
		            System.out.println("Images copied to Folder: "+ destinationDir);             
		            PDDocument document = PDDocument.load(sourceFile.getAbsolutePath());
		            List<PDPage> list = document.getDocumentCatalog().getAllPages();
		            System.out.println("Total files to be converted -> "+ list.size());

		            String fileName = sourceFile.getName().replace(".pdf", "");             
		            int pageNumber = 1;
		            for (PDPage page : list) {
		                BufferedImage image = page.convertToImage();
		               
		                if(check){
		                	
		                	double d2 = ProcessImage.comparepDF(imageList1.get(pageNumber-1),pageNumber);
				          	double d1 = ProcessImage.comparepDF(image,pageNumber);
		                	
		                	msg+="Page "+pageNumber+" is "+ (d2 - (d1 - d2))*100/d2 +" % similiar.\n";
		                	imageList2.add(image);
		                }
		                else{
		                	imageList1.add(image);
		                	
		                }
		                pageNumber++;
		            }
		            
		            document.close();
		            System.out.println("Converted Images are saved at -> "+ destinationDir);
		            if(check){
			        	System.out.println("Confidence : "+msg);
			        }
		        } else {
		            System.err.println(sourceFile.getName() +" File not exists");
		        }
		        
		       

		    } catch (Exception e) {
		        e.printStackTrace();
		    }
	}
	
	
	

}
