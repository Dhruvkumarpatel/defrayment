package control;


import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class GUIPDDiff implements ActionListener{

	/**
	 * @param args
	 */
	static JFrame frame = new JFrame();
	static File input1=null, input2=null;
	JLabel filePath1, filePath2;
	
	public GUIPDDiff(){

		frame.setSize(700, 400);

		JPanel mainPanel = createGUI();
		frame.getContentPane().setLayout(new BorderLayout());
		frame.getContentPane().add(new Label("PDF difference"),"North");
		frame.getContentPane().add(mainPanel,"Center");

		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
	}

	private JPanel createGUI() {

		JPanel mainPanel = new JPanel();
		GridLayout grid = new GridLayout(0,1);
		mainPanel.setLayout(grid);

		JButton file1Btn = new JButton("Select File 1");
		JButton file2Btn = new JButton("Select File 2");

		JButton processBtn = new JButton("Proceed");

		processBtn.addActionListener(this);
		file1Btn.addActionListener(this);
		file2Btn.addActionListener(this);

		JPanel topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(0,1));
		JPanel topLeftFirstPanel = new JPanel();

		topLeftFirstPanel.add(new Label("File 1 : "));		
		topLeftFirstPanel.add(file1Btn);
		topPanel.add(topLeftFirstPanel);

		JPanel topRightFirstPanel = new JPanel();

		topRightFirstPanel.add(new Label("File 2 : "));			
		topRightFirstPanel.add(file2Btn);
		topPanel.add(topRightFirstPanel);

		filePath1 =new JLabel();
		topPanel.add(new JPanel().add(filePath1));

		filePath2=new JLabel();
		topPanel.add(new JPanel().add(filePath2));

		mainPanel.add(topPanel);

		JPanel downPanel = new JPanel();
		downPanel.setLayout(new FlowLayout());
		downPanel.add(processBtn);
		mainPanel.add( downPanel);

		return mainPanel;

	}

	public File selectFile(){

		File file = null;
		JFileChooser fileChooser = new JFileChooser();

		int returnValue = fileChooser.showOpenDialog(frame);

		if(returnValue == fileChooser.APPROVE_OPTION){
			file = fileChooser.getSelectedFile();
		}

		return file;

	}



	public static void main(String[] args) {

		if(args.length>2){
			
			input1 = new File(args[0]);
			input2 = new File(args[1]);
			ProcessImage.startX = Integer.parseInt(args[2]);
			ProcessImage.startY = Integer.parseInt(args[3]);
			ProcessImage.endX = Integer.parseInt(args[4]);
			ProcessImage.endY = Integer.parseInt(args[5]);
			ProcessImage.pageNumber = Integer.parseInt(args[6]);
			try {
				new OverlapPdfs(input1, input2);
			} catch (COSVisitorException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else{
			new GUIPDDiff();
		}
		
		frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		if(e.getActionCommand().equals("Proceed")){

			try {
				new OverlapPdfs(input1, input2);
			} catch (COSVisitorException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(e.getActionCommand().equals("Select File 1")){
			input1 = selectFile();
			filePath1.setText("FILE1: "+input1.getPath());
		}
		else if(e.getActionCommand().equals("Select File 2")){
			input2 = selectFile();
			filePath2.setText("FILE2: "+input2.getPath());
		}
	}

}
