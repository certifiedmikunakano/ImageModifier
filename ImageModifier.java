package hoohoohaha;
import java.util.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ImageModifier extends JFrame {
	
	static double percentImageEncrypted = 0;
	static double percentImageDecrypted = 0;
	static double percentTextEncrypted = 0;
	static double percentTextDecrypted = 0;

	static TheImage toEncrypt = new TheImage("");
	static TheImage toDecrypt = new TheImage(" ");
	static TheText textToEncrypt = new TheText(" ");
	static TheText textToDecrypt = new TheText(" ");
	static TheImage toEmojify = new TheImage(" ");
	static TheImage toRemove = new TheImage(" ");
	
	static JLabel imageDecryptionCodeLabel;
	static JLabel textDecryptionCodeLabel;
	
	static JLabel imageEmojificationWidthLabel;
	static JLabel imageEmojificationHeightLabel;

	static JLabel imageEmojificationEmojiSizeLabel;
	static JLabel imageEmojificationDirectoryLabel;


	static JLabel imageRemovalPercentLabel;
	

	static JPanel imageEncryptionPanel;
	static JPanel imageDecryptionPanel;
	static JPanel imageEmojificationPanel;
	static JPanel imageRemovalPanel;
	static JPanel textEncryptionPanel;
	static JPanel textDecryptionPanel;
	
	static JButton imageEncryptionButton;
	static JButton imageDecryptionButton;
	static JButton imageEmojificationButton;
	static JButton imageRemovalButton;
	static JButton textEncryptionButton;
	static JButton textDecryptionButton;
	
	static JTextArea imageEncryptionInfo;
	static JTextArea imageDecryptionInfo;
	static JTextArea textEncryptionInfo;
	static JTextArea textDecryptionInfo;
	
	static JTextArea imageEmojificationInfo;
	static JTextArea imageRemovalInfo;

	
	static JTextField imageDecryptionCodeField;
	static JTextField textDecryptionCodeField;
	
	static JTextField imageEmojificationWidthField;
	static JTextField imageEmojificationHeightField;

	static JTextField imageEmojificationEmojiSizeField;
	static JTextField imageEmojificationDirectoryField;


	static JTextField imageRemovalPercentField;
	
	static JFileChooser imageEncryptionFileChooser;
	static JFileChooser imageDecryptionFileChooser;
	static JFileChooser imageRemovalFileChooser;
	static JFileChooser imageEmojificationFileChooser;
	static JFileChooser textEncryptionFileChooser;
	static JFileChooser textDecryptionFileChooser;
	
	static FileNameExtensionFilter imageFilter = new FileNameExtensionFilter("images", "png", "jpg");
	
	static FileNameExtensionFilter textFilter = new FileNameExtensionFilter("text docs", "txt");
	
	public ImageModifier () {
		buildImageEncryption();
		buildImageDecryption();
		buildImageEmojification();
		buildImageRemoval();
		buildTextEncryption();
		buildTextDecryption();
		setLayout (new GridLayout (2, 3));
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setSize (1400, 800);
		setTitle ("Image Modifier");
		
		add(imageEncryptionPanel);
		add(imageDecryptionPanel);
		add(imageEmojificationPanel);
		add(imageRemovalPanel);
		add(textEncryptionPanel);
		add(textDecryptionPanel);
		
	}


	
	private void buildImageEncryption() {
		imageEncryptionPanel = new JPanel();
		imageEncryptionPanel.setBorder(BorderFactory.createTitledBorder("Encrypt Image"));
		imageEncryptionPanel.setBackground(Color.WHITE);
		
		imageEncryptionFileChooser = new JFileChooser();
		imageEncryptionFileChooser.setFileFilter(imageFilter);
		imageEncryptionFileChooser.setFileHidingEnabled(true);
		imageEncryptionFileChooser.setPreferredSize( new Dimension(350, 200) );

		
		imageEncryptionButton = new JButton("Encrypt Image");
		imageEncryptionButton.addActionListener(new imageEncryptionListener());
		
		imageEncryptionInfo = new JTextArea (5, 20);
		imageEncryptionInfo.setEditable(false);
		
		imageEncryptionPanel.add(imageEncryptionFileChooser);
		imageEncryptionPanel.add(imageEncryptionButton);
		imageEncryptionPanel.add(imageEncryptionInfo);
		
		//add
		
		
	}
	
	private class imageEncryptionListener implements ActionListener  {
		public void actionPerformed (ActionEvent e) {
			File selectedFile = imageEncryptionFileChooser.getSelectedFile();
			String path = selectedFile.getAbsolutePath();
			toEncrypt = new TheImage (path);
			try {
				toEncrypt.enhancedEncrypt();
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
	}



	private void buildImageDecryption() {
		imageDecryptionPanel = new JPanel();
		imageDecryptionPanel.setBorder(BorderFactory.createTitledBorder("Decrypt Image"));
		imageDecryptionPanel.setBackground(Color.LIGHT_GRAY);		
		
		imageDecryptionFileChooser = new JFileChooser();
		imageDecryptionFileChooser.setFileFilter(imageFilter);
		imageDecryptionFileChooser.setFileHidingEnabled(true);
		imageDecryptionFileChooser.setPreferredSize( new Dimension(350, 200) );
		
		imageDecryptionCodeLabel = new JLabel ("Enter decryption code: ");
		
		imageDecryptionCodeField = new JTextField(15);
		imageDecryptionCodeField.setEditable(true);
		
		imageDecryptionButton = new JButton("Decrypt Image");
		imageDecryptionButton.addActionListener(new imageDecryptionListener());
		
		imageDecryptionInfo = new JTextArea(5, 20);
		imageDecryptionInfo.setEditable(false);
		
		imageDecryptionPanel.add(imageDecryptionFileChooser);
		imageDecryptionPanel.add(imageDecryptionCodeLabel);
		imageDecryptionPanel.add(imageDecryptionCodeField);
		imageDecryptionPanel.add(imageDecryptionButton);
		imageDecryptionPanel.add(imageDecryptionInfo);
		
	}
	
	private class imageDecryptionListener implements ActionListener  {
		public void actionPerformed (ActionEvent e) {
			File selectedFile = imageDecryptionFileChooser.getSelectedFile();
			String path = selectedFile.getAbsolutePath();
			toDecrypt = new TheImage (path);
			String rawCode = imageDecryptionCodeField.getText();
			String[] rootsArray = rawCode.split(" ");
	
		   	 int root1 = Integer.parseInt(rootsArray[0]);
		   	 int root2 = Integer.parseInt(rootsArray[1]);
		   	 int root3 = Integer.parseInt(rootsArray[2]);
		   	 int root4 = Integer.parseInt(rootsArray[3]);
		   	 int root5 = Integer.parseInt(rootsArray[4]);
		   	 int root6 = Integer.parseInt(rootsArray[5]);
			try {
				toDecrypt.enhancedDecryptCustom(root1, root2, root3, root4, root5, root6);
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
	}



	private void buildImageEmojification() {
		imageEmojificationPanel = new JPanel();
		imageEmojificationPanel.setBorder(BorderFactory.createTitledBorder("Emojify Image"));
		imageEmojificationPanel.setBackground(Color.WHITE);
		
		imageEmojificationFileChooser = new JFileChooser();
		imageEmojificationFileChooser.setFileFilter(imageFilter);
		imageEmojificationFileChooser.setFileHidingEnabled(true);
		imageEmojificationFileChooser.setPreferredSize( new Dimension(350, 200) );
		
		imageEmojificationDirectoryLabel = new JLabel("Emoji folder path: ");
		imageEmojificationDirectoryField = new JTextField (25);
		
		imageEmojificationWidthLabel = new JLabel("Width in emojis: ");
		imageEmojificationWidthField = new JTextField (3);
		
		imageEmojificationHeightLabel = new JLabel("Height in emojis: ");
		imageEmojificationHeightField = new JTextField (3);
		
		imageEmojificationEmojiSizeLabel = new JLabel("Emoji size: ");
		imageEmojificationEmojiSizeField = new JTextField (3);
		
		imageEmojificationButton = new JButton("Emojify");
		imageEmojificationButton.addActionListener(new imageEmojificationListener());
		
		imageEmojificationInfo = new JTextArea (5, 20);
		imageEmojificationInfo.setEditable(false);
		
		imageEmojificationPanel.add(imageEmojificationFileChooser);
		imageEmojificationPanel.add(imageEmojificationDirectoryLabel);
		imageEmojificationPanel.add(imageEmojificationDirectoryField);
		imageEmojificationPanel.add(imageEmojificationWidthLabel);
		imageEmojificationPanel.add(imageEmojificationWidthField);
		imageEmojificationPanel.add(imageEmojificationHeightLabel);
		imageEmojificationPanel.add(imageEmojificationHeightField);
		imageEmojificationPanel.add(imageEmojificationEmojiSizeLabel);
		imageEmojificationPanel.add(imageEmojificationEmojiSizeField);
		imageEmojificationPanel.add(imageEmojificationButton);
		imageEmojificationPanel.add(imageEmojificationInfo);
		
	}


	private class imageEmojificationListener implements ActionListener  {
		public void actionPerformed (ActionEvent e) {
			File selectedFile = imageEmojificationFileChooser.getSelectedFile();
			String path = selectedFile.getAbsolutePath();
			String emojiPath = imageEmojificationDirectoryField.getText();
			int width = Integer.parseInt(imageEmojificationWidthField.getText());
			int height = Integer.parseInt(imageEmojificationHeightField.getText());
			int emojiSize = Integer.parseInt(imageEmojificationEmojiSizeField.getText());
			
			toEmojify = new TheImage (path);
			try {
				toEmojify.setApproxImages(emojiPath, emojiSize);
				toEmojify.emojify(width, height, emojiSize);
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			
		}
	}

	private void buildTextEncryption() {
		textEncryptionPanel = new JPanel();
		textEncryptionPanel.setBorder(BorderFactory.createTitledBorder("Encrypt Text Document"));
		textEncryptionPanel.setBackground(Color.WHITE);	
		
		textEncryptionFileChooser = new JFileChooser();
		textEncryptionFileChooser.setFileFilter(textFilter);
		textEncryptionFileChooser.setFileHidingEnabled(true);
		textEncryptionFileChooser.setPreferredSize( new Dimension(350, 200) );

		textEncryptionButton = new JButton ("Encrypt Text");
		textEncryptionButton.addActionListener(new textEncryptionListener());
		
		textEncryptionInfo = new JTextArea (5, 20);
		textEncryptionInfo.setEditable(false);
		
		textEncryptionPanel.add(textEncryptionFileChooser);
		textEncryptionPanel.add(textEncryptionButton);
		textEncryptionPanel.add(textEncryptionInfo);
	}

	private class textEncryptionListener implements ActionListener  {
		public void actionPerformed (ActionEvent e) {
			File selectedFile = textEncryptionFileChooser.getSelectedFile();
			String path = selectedFile.getAbsolutePath();
			textToEncrypt = new TheText (path);
			try {
				textToEncrypt.encipher();
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
	}


	private void buildTextDecryption() {
		textDecryptionPanel = new JPanel();
		textDecryptionPanel.setBorder(BorderFactory.createTitledBorder("Decrypt Text Document"));
		textDecryptionPanel.setBackground(Color.LIGHT_GRAY);
		
		textDecryptionFileChooser = new JFileChooser();
		textDecryptionFileChooser.setFileFilter(textFilter);
		textDecryptionFileChooser.setFileHidingEnabled(true);
		textDecryptionFileChooser.setPreferredSize( new Dimension(350, 200) );
		
		textDecryptionCodeLabel = new JLabel ("Enter decryption code: ");
		
		textDecryptionCodeField = new JTextField(15);
		textDecryptionCodeField.setEditable(true);
		
		textDecryptionButton = new JButton("Decrypt Text");
		textDecryptionButton.addActionListener(new textDecryptionListener());
		
		textDecryptionInfo = new JTextArea(5, 20);
		textDecryptionInfo.setEditable(false);
		
		textDecryptionPanel.add(textDecryptionFileChooser);
		textDecryptionPanel.add(textDecryptionCodeLabel);
		textDecryptionPanel.add(textDecryptionCodeField);
		textDecryptionPanel.add(textDecryptionButton);
		textDecryptionPanel.add(textDecryptionInfo);
		
	}
	
	private class textDecryptionListener implements ActionListener  {
		public void actionPerformed (ActionEvent e) {
			File selectedFile = textDecryptionFileChooser.getSelectedFile();
			String path = selectedFile.getAbsolutePath();
			textToDecrypt = new TheText (path);
			String rawCode = textDecryptionCodeField.getText();
			String[] rootsArray = rawCode.split(" ");
	
		   	 int root1 = Integer.parseInt(rootsArray[0]);
		   	 int root2 = Integer.parseInt(rootsArray[1]);
		   	 int root3 = Integer.parseInt(rootsArray[2]);
		   	 int root4 = Integer.parseInt(rootsArray[3]);
		   	 int root5 = Integer.parseInt(rootsArray[4]);
		   	 int root6 = Integer.parseInt(rootsArray[5]);
			try {
				textToDecrypt.decipherCustom(root1, root2, root3, root4, root5, root6);
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
	}



	private void buildImageRemoval() {
		imageRemovalPanel = new JPanel();
		imageRemovalPanel.setBorder(BorderFactory.createTitledBorder("Remove Pixels from Image"));
		imageRemovalPanel.setBackground(Color.LIGHT_GRAY);	
		
		imageRemovalFileChooser = new JFileChooser();
		imageRemovalFileChooser.setFileFilter(imageFilter);
		imageRemovalFileChooser.setFileHidingEnabled(true);
		imageRemovalFileChooser.setPreferredSize( new Dimension(350, 200) );
		
		imageRemovalPercentLabel = new JLabel ("Percent of Pixels to Remove: ");
		imageRemovalPercentField = new JTextField (3);
		
		imageRemovalButton = new JButton("Remove Pixels");
		imageRemovalButton.addActionListener(new removalListener());
		
		imageRemovalInfo = new JTextArea (5, 20);
		imageRemovalInfo.setEditable(false);
		
		imageRemovalPanel.add(imageRemovalFileChooser);
		imageRemovalPanel.add(imageRemovalPercentLabel);
		imageRemovalPanel.add(imageRemovalPercentField);
		imageRemovalPanel.add(imageRemovalButton);
		imageRemovalPanel.add(imageRemovalInfo);

		
		
	}
	
	private class removalListener implements ActionListener  {
		public void actionPerformed (ActionEvent e) {
			File selectedFile = imageRemovalFileChooser.getSelectedFile();
			String path = selectedFile.getAbsolutePath();
		
			
			toRemove = new TheImage (path);
			double proportion = Double.parseDouble(imageRemovalPercentField.getText()) / 100.0;
			try {
				toRemove.eraseProportion(proportion);
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			
		}
	}

	public static void updateImageEncryptionStatus () {
		double a = toEncrypt.getPercentEncrypted();
		if (a <= 99.9) {
			imageEncryptionInfo.setText("Encrypting... " + String.format("%.1f", a) + "%");
			
		}
		else {
			ArrayList<Integer> roots = toEncrypt.tempRootArray();
			int r1 = roots.get(0);
			int r2 = roots.get(1);
			int r3 = roots.get(2);
			int r4 = roots.get(3);
			int r5 = roots.get(4);
			int r6 = roots.get(5);
			imageEncryptionInfo.setText("Encryption complete. \nYour decryption code is: " + r1 + " " + r2 + " " + r3 + " " + r4 + " " + r5 + " " + r6 + "\nKeep this code somewhere safe but don't lose it!");
		}
	}

	public static void updateImageDecryptionStatus () {
		double a = toDecrypt.getPercentDecrypted();
		if (a <= 99.9) {
			imageDecryptionInfo.setText("Decrypting... " + String.format("%.1f", a) + "%");
		}
		else {
			imageDecryptionInfo.setText("Decryption complete.");
		}
	}
	
	public static void updateImageEmojificationStatus () {
		double a = toEmojify.getPercentEmojified();
		if (a <= 99.9) {
			imageEmojificationInfo.setText("Emojifying... " + String.format("%.1f", a) + "%");
		}
		else {
			imageEmojificationInfo.setText("Image Emojified.");
		}
	}
	
	public static void updateImageRemovalStatus () {
		double a = toRemove.getPercentRemoved();
		if (a <= 99.9) {
			imageRemovalInfo.setText("Revoming pixels... " + String.format("%.1f", a) + "%");
		}
		else {
			imageRemovalInfo.setText("Pixels removed.");
		}
	}
	
	public static void updateTextEncryptionStatus () {
		double a = textToEncrypt.getPercentEncrypted();
		if (a <= 99.9) {
			textEncryptionInfo.setText("Encrypting... " + String.format("%.1f", a) + "%");
			
		}
		else {
			ArrayList<Integer> roots = textToEncrypt.tempRootArray();
			int r1 = roots.get(0);
			int r2 = roots.get(1);
			int r3 = roots.get(2);
			int r4 = roots.get(3);
			int r5 = roots.get(4);
			int r6 = roots.get(5);
			textEncryptionInfo.setText("Encryption complete. \nYour decryption code is: " + r1 + " " + r2 + " " + r3 + " " + r4 + " " + r5 + " " + r6 + "\nKeep this code somewhere safe but don't lose it!");
		}
	}
	
	public static void updateTextDecryptionStatus () {
		double a = textToDecrypt.getPercentDecrypted();
		if (a <= 99.9) {
			textDecryptionInfo.setText("Decrypting... " + String.format("%.1f", a) + "%");
		}
		else {
			textDecryptionInfo.setText("Decryption complete.");
		}
	}

	public static void main(String[] args) throws InterruptedException {
		ImageModifier im = new ImageModifier();
		
		while (true) {
			Thread.sleep(50);
			updateImageEncryptionStatus();
			updateImageDecryptionStatus();
			updateImageEmojificationStatus();
			updateImageRemovalStatus();
			updateTextEncryptionStatus ();
			updateTextDecryptionStatus ();
			
		}
	}
	
}

	 class TheImage {
		private BufferedImage image;
	    private BufferedImage rawImage;
	    private int tempRoot1;
	    private int tempRoot2;
	    private int tempRoot3;
	    private int tempRoot4;
	    private int tempRoot5;
	    private int tempRoot6;
	    
	    private static double percentEncrypted;
	    private static double percentDecrypted;
	    private static double percentEmojified;
	    private static double percentRemoved;
	    
	    
	    public ArrayList<Integer> tempRootArray() {
	    	ArrayList<Integer> result = new ArrayList<Integer>();
	    	result.add(tempRoot1);
	    	result.add(tempRoot2);
	    	result.add(tempRoot3);
	    	result.add(tempRoot4);
	    	result.add(tempRoot5);
	    	result.add(tempRoot6);
	    	return result;

	    	
	    }
	    public double getPercentEncrypted() {
	    	return percentEncrypted;
	    	
	    }
	    
	    public  double getPercentDecrypted() {
	    	return percentDecrypted;
	    	
	    }
	    public  double getPercentEmojified () {
	    	return percentEmojified;
	    	
	    }
	    public  double getPercentRemoved () {
	    	return percentRemoved;
	    	
	    }
	    
	    private ArrayList<BufferedImage> approxImages = new ArrayList<BufferedImage>();
	    
	    public void setApproxImages (String directoryName, int size) throws IOException {
	    	File folder = new File (directoryName);
	    	for (File approxImage : folder.listFiles()) {
	    		BufferedImage bi = ImageIO.read(approxImage);
	    		BufferedImage addedImage = resizeImage(bi, size, size);
	    		approxImages.add(addedImage);
	    	}
	    	
	    }
	    
	    public ArrayList<BufferedImage> getApproxImages () {
	    	return approxImages;
	    }
	    
	    private ArrayList<Color> computeAverages (ArrayList<BufferedImage> input) {
	    	ArrayList<Color> result = new ArrayList<Color>();
	    	for (int i = 0; i < input.size(); i++) {
	    		result.add(computeAverage(input.get(i)));
	    	}
	    	return result;
	    }
	    
	    private Color computeAverage (BufferedImage bi) {
	    	long rSum = 0;
	    	long gSum = 0;
	    	long bSum = 0;
	    	for (int i = 0; i < bi.getWidth(); i++) {
	    		for (int j = 0; j < bi.getHeight(); j++) {
	    			Color c = new Color (bi.getRGB(i, j));
	    			rSum += c.getRed();
	    			gSum += c.getGreen();
	    			bSum += c.getBlue();
	    		}
	    	}
	    	int totalPixels = bi.getWidth() * bi.getHeight();
	    	return new Color((int) (rSum / totalPixels), (int) (gSum / totalPixels), (int) (bSum / totalPixels));
	    }
	    
	    private int determineClosestIndex (Color input) {
	    	ArrayList<Color> colorBank = computeAverages (approxImages);
	    	
	    	int minDistance = 99999999;
	    	int minDistanceIndex = 0;
	    	
	    	for (int i = 0; i < colorBank.size(); i++) {
	    		Color candidate = colorBank.get(i);
	    		double candidateRed = candidate.getRed();
	    		double candidateGreen = candidate.getGreen();
	    		double candidateBlue = candidate.getBlue();
	    		double red = input.getRed();
	    		double green = input.getGreen();
	    		double blue = input.getBlue();
	    		double squared = Math.pow(candidateRed - red, 2) + Math.pow(candidateGreen - green, 2) + Math.pow(candidateBlue - blue, 2);
	    		double distance = Math.sqrt(squared);
	    		
	    		if (distance < minDistance) {
	    			minDistance = (int) distance;
	    			minDistanceIndex = i;
	    		}
	    		
	    	}
	    	
	    	return minDistanceIndex;
	    	
	    	
	    }
	    
	    public void emojify (int width, int height, int emojiSize) throws IOException {
	    	//read image
	    	File f = null;
	      	 try{
	    		 f = new File(fileName); //image file path
	    		 rawImage = new BufferedImage(1018, 1018, BufferedImage.TYPE_INT_ARGB);
	    		 rawImage = ImageIO.read(f);
	    		 image = resizeImage(rawImage, width*emojiSize, height*emojiSize);
	    		 System.out.println("Reading complete.");
	      	 }catch(IOException e){
	    		 System.out.println("Error: "+e);
	      	 }
	      	 
	      	 for (int x = 0; x < width; x++) {
	      		 for (int y = 0; y < height; y++) {
	      			 
	      			 //compute average RGB of cell
	      			 BufferedImage sub = image.getSubimage(x * emojiSize, y*emojiSize, emojiSize, emojiSize);
	      			 
	      			 Color inputColor = computeAverage (sub);
	      			 int index = determineClosestIndex (inputColor);
	      			 BufferedImage replace = approxImages.get(index);
	      			 
	      	
	      			 for (int i = x*emojiSize; i < (x+1)*emojiSize; i++) {
	      				for (int j = y*emojiSize; j < (y+1)*emojiSize; j++) {
	      					int newRGB = replace.getRGB(i % emojiSize, j % emojiSize);
	      					image.setRGB(i, j, newRGB);
	         
	      					
	         			 }
	      			 }
	      			 
	      		 }
	      		 percentEmojified += 100.0 / (width * height);
	      	 }
	      	 System.out.println("Image emojified with " + width + " emojis by " + height + " emojis. ");
	      	ImageIO.write(image, "png", f);
	      	System.out.println("Writing complete.");
	      	 
	    }
	    
	    
	    
	    private String fileName;
	    public TheImage (String fn) {
	   	 fileName = fn;
	   	 percentEncrypted = 0;
	   	 percentDecrypted = 0;
	   	 
	   	percentEmojified = 0;
	   	percentRemoved = 0;
	    }
	    
	    public TheImage (BufferedImage im) {
	   	 rawImage = im;
	   	percentEncrypted = 0;
	  	 percentDecrypted = 0;
	  	 percentEmojified = 0;
	  	 percentRemoved = 0;
	    }
	    
	    public BufferedImage getImage () {
	   	 return image;
	    }
	    
	    
		public void imageEncrypt () throws IOException{
	  	  Random random = new Random();
	   	 int width = 1018;    //width of the image
	   	 int height = 1018;   //height of the image
	   	 //generate the primitive roots mod 1019
	  	 
	   	 ArrayList <Integer> proots = new ArrayList <Integer> ();
	   	 for (int i = 0; i < 1018; i++) {
	  		  proots.add(i);
	   	 }
	   	 for (int i = 0; i < 1019; i++) {
	  		  int j = proots.indexOf((i*i) % 1019);
	  		  if (j != -1) {
	  			  proots.remove(j);
	  		  }
	  		 
	   	 }
	   	 int randomIndex1 = random.nextInt(proots.size());
	   	 int randomIndex2 = random.nextInt(proots.size());
	   	 int proot1 = proots.get(randomIndex1);
	   	 int proot2 = proots.get(randomIndex2);
	  	 
	  	 
	   	 File f = null;
	  	 
	   	 //read image
	   	 try{
	 		 f = new File(fileName); //image file path
	 		 rawImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	 		 rawImage = ImageIO.read(f);
	 		 image = resizeImage(rawImage, width, height);
	 		 System.out.println("Reading complete.");
	   	 }catch(IOException e){
	 		 System.out.println("Error: "+e);
	   	 }
	  	 
	   	 BufferedImage scrambledImage = scramble(image, proot1);

	   	 BufferedImage scrambledImage2 = scramble2(scrambledImage, proot2);
	   	 System.out.println("Image Scrambled.");
	  	 
	   	 
	   	 ImageIO.write(scrambledImage2, "png", f);
	   	 System.out.println("Image writing complete.");
	   	 
	   	 image = scrambledImage2;
	  	 
	   	 int secretCode = 10000 * proot1 + proot2;
	  	 
	   	 System.out.println("Your decryption code is: " + secretCode + ". Keep this code to yourself but don't lose it!");
	  	      
	  	 
	  	}
	    
		public void imageDecrypt () throws IOException{
	   	 int width = 1018;    //width of the image
	   	 int height = 1018;   //height of the image
	   	 BufferedImage image = null;
	   	 File f = null;
	   	 Scanner scan = new Scanner(System.in);
	  	 
	   	 //read image
	   	 try{
	 		 f = new File(fileName); //image file path
	 		 BufferedImage rawImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	 		 rawImage = ImageIO.read(f);
	 		 image = resizeImage(rawImage, width, height);
	 		 System.out.println("Reading complete.");
	   	 }catch(IOException e){
	 		 System.out.println("Error: "+e);
	   	 }
	  	 
	   	 System.out.println("Enter the decryption code for that image: ");
	  	 
	   	 String codestring = scan.nextLine();
	   	 int code = Integer.parseInt(codestring);
	   	 int root1 = code / 10000;
	   	 int root2 = code % 10000;
	  	 
	   	 BufferedImage unscrambledImage1 = unscrambleCols (image, root1);

	   	 BufferedImage unscrambledImage2 = unscrambleRows (unscrambledImage1, root2);
	   	 System.out.println("Image unscrambled.");
	  	 
	  					 
	   	 ImageIO.write(unscrambledImage2, "png", f);
	   	 System.out.println("Writing complete.");
	   	 
	   	 image = unscrambledImage2;
	 		      
	 		 
	     	}
	    
		public void enhancedDecrypt () throws IOException{
	   	 int width = 1018;    //width of the image
	   	 int height = 1018;   //height of the image
	   	 BufferedImage image = null;
	   	 File f = null;
	   	 Scanner scan = new Scanner(System.in);
	  	 
	   	 //read image
	   	 try{
	 		 f = new File(fileName); //image file path
	 		 BufferedImage rawImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	 		 rawImage = ImageIO.read(f);
	 		 image = resizeImage(rawImage, width, height);
	 		 System.out.println("Reading complete.");
	   	 }catch(IOException e){
	 		 System.out.println("Error: "+e);
	   	 }
	  	 
	   	 System.out.println("Enter the decryption code for that image: ");
	  	 
	   	 String codestring = scan.nextLine();
	   	 String[] rootsArray = codestring.split(" ");
	    
	   	 int root1 = Integer.parseInt(rootsArray[0]);
	   	 int root2 = Integer.parseInt(rootsArray[1]);
	   	 int root3 = Integer.parseInt(rootsArray[2]);
	   	 int root4 = Integer.parseInt(rootsArray[3]);
	   	 int root5 = Integer.parseInt(rootsArray[4]);
	   	 int root6 = Integer.parseInt(rootsArray[5]);

	   	 
	   	 
	   
	  	 
	   	 BufferedImage unscrambledImage1 = unscrambleD2 (image, root5, root6);
	   	 BufferedImage unscrambledImage2 = unscrambleD1 (unscrambledImage1, root3, root4);
	   	 BufferedImage unscrambledImage3 = unscrambleRows (unscrambledImage2, root2);
	   	 BufferedImage unscrambledImage4 = unscrambleCols (unscrambledImage3, root1);

	   	 System.out.println("Image unscrambled.");
	  	 
	  					 
	   	 ImageIO.write(unscrambledImage4, "png", f);
	   	 System.out.println("Writing complete.");
	   	 
	   	 image = unscrambledImage4;
	 		      
	 		 
	     	}
	    
		private void enhancedDecryptParameters () throws IOException{
	   	 int width = 1018;    //width of the image
	   	 int height = 1018;   //height of the image
	   	 image = getImage();
	   	 File f = new File (fileName);
	   	 
	   	 
	   
	  	 
	   	 BufferedImage unscrambledImage1 = unscrambleD2 (image, tempRoot5, tempRoot6);
	   	 BufferedImage unscrambledImage2 = unscrambleD1 (unscrambledImage1, tempRoot3, tempRoot4);
	   	 BufferedImage unscrambledImage3 = unscrambleRows (unscrambledImage2, tempRoot2);
	   	 BufferedImage unscrambledImage4 = unscrambleCols (unscrambledImage3, tempRoot1);

	   	 System.out.println("Image unscrambled.");
	  	 
	  					 
	   	 ImageIO.write(unscrambledImage4, "png", f);
	   	 System.out.println("Writing complete.");
	   	 
	   	 image = unscrambledImage4;
	 		      
	 		 
	     	}
		
		public void enhancedDecryptCustom (int r1, int r2, int r3, int r4, int r5, int r6) throws IOException{
		   	 int width = 1018;    //width of the image
		   	 int height = 1018;   //height of the image
		   	 image = getImage();
		   	 File f = null;
		   	 
		   	try{
		 		 f = new File(fileName); //image file path
		 		 BufferedImage rawImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		 		 rawImage = ImageIO.read(f);
		 		 image = resizeImage(rawImage, width, height);
		 		 System.out.println("Reading complete.");
		   	 }catch(IOException e){
		 		 System.out.println("Error: "+e);
		   	 }
		   	 
		   
		  	 
		   	 BufferedImage unscrambledImage1 = unscrambleD2 (image, r5, r6);
		   	 BufferedImage unscrambledImage2 = unscrambleD1 (unscrambledImage1, r3, r4);
		   	 BufferedImage unscrambledImage3 = unscrambleRows (unscrambledImage2, r2);
		   	 BufferedImage unscrambledImage4 = unscrambleCols (unscrambledImage3, r1);

		   	 System.out.println("Image unscrambled.");
		  	 
		  					 
		   	 ImageIO.write(unscrambledImage4, "png", f);
		   	 System.out.println("Writing complete.");
		   	 
		   	 image = unscrambledImage4;
		 		      
		 		 
		     	}
	    
		public void enhancedEncrypt () throws IOException{
	 		  Random random = new Random();
	  		 int width = 1018;    //width of the image
	  		 int height = 1018;   //height of the image
	  		 //generate the primitive roots mod 1019
	 		 
	  		 ArrayList <Integer> proots = new ArrayList <Integer> ();
	  		 for (int i = 0; i < 1018; i++) {
	 			  proots.add(i);
	  		 }
	  		 for (int i = 0; i < 1019; i++) {
	 			  int j = proots.indexOf((i*i) % 1019);
	 			  if (j != -1) {
	 				  proots.remove(j);
	 			  }
	 			 
	  		 }
	  		 int randomIndex1 = random.nextInt(proots.size());
	  		 int randomIndex2 = random.nextInt(proots.size());
	  		 int randomIndex3 = random.nextInt(proots.size());
	  		 int randomIndex4 = random.nextInt(proots.size());
	  		 int randomIndex5 = random.nextInt(proots.size());
	  		 int randomIndex6 = random.nextInt(proots.size());

	  		 int proot1 = proots.get(randomIndex1);
	  		 int proot2 = proots.get(randomIndex2);
	  		 int proot3 = proots.get(randomIndex3);
	  		 int proot4 = proots.get(randomIndex4);
	  		 int proot5 = proots.get(randomIndex5);
	  		 int proot6 = proots.get(randomIndex6);
	  		 
	  		  tempRoot1 = proot1;
	  		  tempRoot2 = proot2;  	 
	  		  tempRoot3 = proot3;
	  		  tempRoot4 = proot4;
	  		  tempRoot5 = proot5;
	  		  tempRoot6 = proot6;


	 		 
	  		 File f = null;
	 		 
	  		 //read image
	  		 try{
	    		 f = new File(fileName); //image file path
	    		 rawImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	    		 rawImage = ImageIO.read(f);
	    		 image = resizeImage(rawImage, width, height);
	    		 System.out.println("Reading complete.");
	  		 }catch(IOException e){
	    		 System.out.println("Error: "+e);
	  		 }
	 		 
	  		 BufferedImage scrambledImage = scramble(image, proot1);

	  		 BufferedImage scrambledImage2 = scramble2(scrambledImage, proot2);
	  		 
	  		 BufferedImage scrambledImage3 = scrambleD1(scrambledImage2, proot3, proot4);

	  		 BufferedImage scrambledImage4 = scrambleD2(scrambledImage3, proot5, proot6);

	  		 System.out.println("Image Scrambled.");
	 		 
	  		 
	  		 ImageIO.write(scrambledImage4, "png", f);
	  		 System.out.println("Image writing complete.");
	  		 
	  		 image = scrambledImage4;
	 		 
	  		 String secretCode = proot1 + " " + proot2 + " " + proot3 + " " + proot4 + " " + proot5 + " " + proot6;
	 		 
	  		 System.out.println("Your decryption code is: \n" + secretCode + ". \nKeep this code to yourself but don't lose it!");
	 		      
	 		 
	     	}
	    
		private static int prToThe (int base, int k) {
	  	  int value = 1;
	  	  for (int i = 0; i < k; i++) {
	  		  value*=base;
	  		  value%=1019;
	  	  }
	  	  return value;
		}
	    
		static BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) throws IOException {
	   	 BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
	   	 Graphics2D graphics2D = resizedImage.createGraphics();
	   	 graphics2D.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
	   	 graphics2D.dispose();
	   	 return resizedImage;
		}
	    
		private static ArrayList<Color> encrypt (ArrayList <Color> original, int pr) {
	  	  ArrayList<Color> newArr = new ArrayList<Color>();
	  	  for (int i = 0; i < original.size(); i++) {
	  		  newArr.add(original.get(prToThe(pr, i) - 1));
	  	  }
	  	  return newArr;
		}
	    
		private static ArrayList<Color> encrypt2 (ArrayList <Color> original, int pr) {
	  	  ArrayList<Color> newArr = new ArrayList<Color>();
	  	  for (int i = 0; i < original.size(); i++) {
	  		  newArr.add(original.get(prToThe(pr, i) - 1));
	  	  }
	  	  return newArr;
		}

	    
		private static BufferedImage scramble (BufferedImage original, int pr) {
	  		  BufferedImage scrambled = new BufferedImage (original.getWidth(), original.getHeight(), BufferedImage.TYPE_INT_ARGB);
	  		 
	  		  //scramble each row
	  		  for (int y = 0; y < original.getHeight(); y++) {
	  			  ArrayList<Color> origRow = new ArrayList <Color> ();
	  			  for (int j = 0; j < original.getWidth(); j++) {
	  				  Color c = new Color(original.getRGB(j, y));
	  				  origRow.add(c);
	  			  }
	  			 
	  			  ArrayList<Color> scrambledRow = encrypt (origRow, pr);
	  			  for (int j = 0; j < original.getWidth(); j++) {
	  				  int newRGB = scrambledRow.get(j).getRGB();
	  				  scrambled.setRGB(j, y, newRGB);
	  			  }
	  			  percentEncrypted += 100.0 / (1018 * 4);
	  			  percentRemoved += 100.0 / (1018 * 8);
	  		  }
	  		  return scrambled;
	  	  }
	    
		private static BufferedImage scramble2 (BufferedImage original, int pr) {
	  	  BufferedImage scrambled = new BufferedImage (original.getWidth(), original.getHeight(), BufferedImage.TYPE_INT_ARGB);
	  	 
	  	  //scramble each column
	  	  for (int x = 0; x < original.getWidth(); x++) {
	  		  ArrayList<Color> origCol = new ArrayList <Color> ();
	  		  for (int j = 0; j < original.getHeight(); j++) {
	  			  Color c = new Color(original.getRGB(x, j));
	  			  origCol.add(c);
	  		  }
	  		 
	  		  ArrayList<Color> scrambledCol = encrypt2 (origCol, pr);
	  		  for (int j = 0; j < original.getHeight(); j++) {
	  			  int newRGB = scrambledCol.get(j).getRGB();
	  			  scrambled.setRGB(x, j, newRGB);
	  		  }
				  percentEncrypted += 100.0 / (1018 * 4);
	  			  percentRemoved += 100.0 / (1018 * 8);


	  	  }
	  	  return scrambled;
		}
	    
		private static BufferedImage scrambleD1 (BufferedImage original, int pr1, int pr2) {
	     	  BufferedImage scrambled = new BufferedImage (original.getWidth(), original.getHeight(), BufferedImage.TYPE_INT_ARGB);
	     	 
	     	  //scramble each row
	     	  for (int y = 0; y < original.getHeight(); y++) {
	     		  ArrayList<Color> origRow = new ArrayList <Color> ();
	     		  for (int j = 0; j < original.getWidth(); j++) {
	     			  int modifiedY = (y + (prToThe(pr1, j))) % 1018;
	     			  Color c = new Color(original.getRGB(j, modifiedY));
	     			  origRow.add(c);
	     		  }
	     		 
	     		  ArrayList<Color> scrambledRow = encrypt (origRow, pr2);
	     		  for (int j = 0; j < original.getWidth(); j++) {
	     			  int modifiedY = (y + (prToThe(pr1, j))) % 1018;
	     			  int newRGB = scrambledRow.get(j).getRGB();
	     			  scrambled.setRGB(j, modifiedY, newRGB);
	     		  }
	  			  percentEncrypted += 100.0 / (1018 * 4);
	  			  percentRemoved += 100.0 / (1018 * 8);


	     	  }
	     	  return scrambled;
	       }
	    
		private static BufferedImage scrambleD2 (BufferedImage original, int pr1, int pr2) {
	 		  BufferedImage scrambled = new BufferedImage (original.getWidth(), original.getHeight(), BufferedImage.TYPE_INT_ARGB);
	 		 
	 		  //scramble each column
	 		  for (int x = 0; x < original.getWidth(); x++) {
	 			  ArrayList<Color> origCol = new ArrayList <Color> ();
	 			  for (int j = 0; j < original.getHeight(); j++) {
	 				  int modifiedX = (x + (prToThe(pr1, j))) % 1018;
	 				  Color c = new Color(original.getRGB(modifiedX, j));
	 				  origCol.add(c);
	 			  }
	 			 
	 			  ArrayList<Color> scrambledCol = encrypt2 (origCol, pr2);
	 			  for (int j = 0; j < original.getHeight(); j++) {
	 				  int modifiedX = (x + (prToThe(pr1, j))) % 1018;
	 				  int newRGB = scrambledCol.get(j).getRGB();
	 				  scrambled.setRGB(modifiedX, j, newRGB);
	 			  }
	  			  percentEncrypted += 100.0 / (1018 * 4);
	  			  percentRemoved += 100.0 / (1018 * 8);


	 		  }
	 		  return scrambled;
	   	}
	    
		public static int discreteLogBasePrMod1019 (int base, int k) {
	 		  int value = 0;
	 		  for (int i = 0; i < 1019; i++) {
	 			  if (prToThe(base, i) % 1019 == k) {
	 				  value = i;
	 				  break;
	 			  }
	 		  }
	 		  return value;
	   	}
	    
		public static int discreteLogBasePrMod1019_ (int base, int k) {
			  return discreteLogarithm(base, k, 1019);
	  	}
	    
		public void eraseProportion (double proportion) throws IOException {
	   	 enhancedEncrypt();
	   	 erase (proportion);
	   	 enhancedDecryptParameters();
	   	 
	   	 
		}
	    
		private void erase (double proportionErased) {
	   	 
	   	 int cutoff = (int) (1018 - 1018 * Math.sqrt(1 - proportionErased));
	   	 System.out.println("CUTOFF: " + cutoff);
	   	 for (int i = 0; i < image.getWidth(); i++) {
	   		 //System.out.println("why the fuck isn't this working???");
	   		 for (int j = 0; j < image.getHeight(); j++) {
	   			 if ((i <= cutoff) || (j <= cutoff)) {
	   				 image.setRGB(i,  j, 0);
	   			 }
	   		 }
	   	 }
	   	 
		}
	    
		static int discreteLogarithm(int a, int b, int m)
		{
	    	int n = (int) (Math.sqrt (m) + 1);
	 
	    	// Calculate a ^ n
	    	int an = 1;
	    	for (int i = 0; i < n; ++i)
	        	an = (an * a) % m;
	 
	    	int[] value=new int[m];
	 
	    	// Store all values of a^(n*i) of LHS
	    	for (int i = 1, cur = an; i <= n; ++i)
	    	{
	        	if (value[ cur ] == 0)
	            	value[ cur ] = i;
	        	cur = (cur * an) % m;
	    	}
	 
	    	for (int i = 0, cur = b; i <= n; ++i)
	    	{
	        	// Calculate (a ^ j) * b and check
	        	// for collision
	        	if (value[cur] > 0)
	        	{
	            	int ans = value[cur] * n - i;
	            	if (ans < m)
	                	return ans;
	        	}
	        	cur = (cur * a) % m;
	    	}
	    	return -1;
		}
	  	 
	   	//use pr1
	   	public static ArrayList<Color> decryptRows (ArrayList <Color> original, int pr) {
	 		  ArrayList<Color> newArr = new ArrayList<Color>();
	 		  for (int i = 0; i < original.size(); i++) {
	 			 
	 			  newArr.add(original.get(discreteLogBasePrMod1019_(pr, i+1) % 1018));
	 		  }
	 		  return newArr;
	   	}
	  	 
	   	//use pr2
	   	public static ArrayList<Color> decryptCols (ArrayList <Color> original, int pr) {
	 		  ArrayList<Color> newArr = new ArrayList<Color>();
	 		  for (int i = 0; i < original.size(); i++) {
	 			  newArr.add(original.get(discreteLogBasePrMod1019_(pr, i+1) % 1018));
	 		  }
	 		  return newArr;
	   	}

	  	 
	   	public static BufferedImage unscrambleCols (BufferedImage original, int pr) {
	 			  BufferedImage unscrambled = new BufferedImage (original.getWidth(), original.getHeight(), BufferedImage.TYPE_INT_ARGB);
	 			 
	 			  //scramble each row
	 			  for (int y = 0; y < original.getHeight(); y++) {
	 				  ArrayList<Color> origRow = new ArrayList <Color> ();
	 				  for (int j = 0; j < original.getWidth(); j++) {
	 					  Color c = new Color(original.getRGB(j, y));
	 					  origRow.add(c);
	 				  }
	 				 
	 				  ArrayList<Color> unscrambledRow = decryptRows (origRow, pr);
	 				  for (int j = 0; j < original.getWidth(); j++) {
	 					  int newRGB = unscrambledRow.get(j).getRGB();
	 					  unscrambled.setRGB(j, y, newRGB);
	 				  }
	 				  System.out.println("Column " + y + " unscrambled");
	 	  			  percentDecrypted += 100.0 / (1018 * 4);
	 	  			  percentRemoved += 100.0 / (1018 * 8);



	 			  }
	 			  return unscrambled;
	 		  }
	  	 
	   	public static BufferedImage unscrambleRows (BufferedImage original, int pr) {
	 		  BufferedImage unscrambled = new BufferedImage (original.getWidth(), original.getHeight(), BufferedImage.TYPE_INT_ARGB);
	 		 
	 		  //scramble each column
	 		  for (int x = 0; x < original.getWidth(); x++) {
	 			  ArrayList<Color> origCol = new ArrayList <Color> ();
	 			  for (int j = 0; j < original.getHeight(); j++) {
	 				  Color c = new Color(original.getRGB(x, j));
	 				  origCol.add(c);
	 			  }
	 			 
	 			  ArrayList<Color> unscrambledCol = decryptCols (origCol, pr);
	 			  for (int j = 0; j < original.getHeight(); j++) {
	 				  int newRGB = unscrambledCol.get(j).getRGB();
	 				  unscrambled.setRGB(x, j, newRGB);
	 			  }
	 			  System.out.println("Row " + x + " unscrambled");
		  			  percentDecrypted += 100.0 / (1018 * 4);
		  			  percentRemoved += 100.0 / (1018 * 8);


	 		  }
	 		  return unscrambled;
	   	}
	  	 
	   	public static BufferedImage unscrambleD1 (BufferedImage original, int pr1, int pr2) {
	   		  BufferedImage unscrambled = new BufferedImage (original.getWidth(), original.getHeight(), BufferedImage.TYPE_INT_ARGB);
	   		 
	   		  //scramble each row
	   		  for (int y = 0; y < original.getHeight(); y++) {
	   			  ArrayList<Color> origRow = new ArrayList <Color> ();
	   			  for (int j = 0; j < original.getWidth(); j++) {
	 					  int modifiedY = (y + (prToThe(pr1, j))) % 1018;
	   				  Color c = new Color(original.getRGB(j, modifiedY));
	   				  origRow.add(c);
	   			  }
	   			 
	   			  ArrayList<Color> unscrambledRow = decryptRows (origRow, pr2);
	   			  for (int j = 0; j < original.getWidth(); j++) {
	 					  int modifiedY = (y + (prToThe(pr1, j))) % 1018;
	   				  int newRGB = unscrambledRow.get(j).getRGB();
	   				  unscrambled.setRGB(j, modifiedY, newRGB);
	   			  }
	   			  System.out.println("Column " + y + " unscrambled");
		  			  percentDecrypted += 100.0 / (1018 * 4);
		  			  percentRemoved += 100.0 / (1018 * 8);


	   		  }
	   		  return unscrambled;
	   	  }
	  	 
	   	public static BufferedImage unscrambleD2 (BufferedImage original, int pr1, int pr2) {
	   		  BufferedImage unscrambled = new BufferedImage (original.getWidth(), original.getHeight(), BufferedImage.TYPE_INT_ARGB);
	   		 
	   		  //scramble each column
	   		  for (int x = 0; x < original.getWidth(); x++) {
	   			  ArrayList<Color> origCol = new ArrayList <Color> ();
	   			  for (int j = 0; j < original.getHeight(); j++) {
	     				  int modifiedX = (x + (prToThe(pr1, j))) % 1018;
	   				  Color c = new Color(original.getRGB(modifiedX, j));
	   				  origCol.add(c);
	   			  }
	   			 
	   			  ArrayList<Color> unscrambledCol = decryptCols (origCol, pr2);
	   			  for (int j = 0; j < original.getHeight(); j++) {
	     				  int modifiedX = (x + (prToThe(pr1, j))) % 1018;
	   				  int newRGB = unscrambledCol.get(j).getRGB();
	   				  unscrambled.setRGB(modifiedX, j, newRGB);
	   			  }
	   			  System.out.println("Row " + x + " unscrambled");
		  			  percentDecrypted += 100.0 / (1018 * 4);
		  			  percentRemoved += 100.0 / (1018 * 8);


	   		  }
	   		  return unscrambled;
	     	}
	}
	
	 class TheText {
		private int tempRoot1;
	    private int tempRoot2;
	    private int tempRoot3;
	    private int tempRoot4;
	    private int tempRoot5;
	    private int tempRoot6;
	    public ArrayList<Integer> tempRootArray() {
	    	ArrayList<Integer> result = new ArrayList<Integer>();
	    	result.add(tempRoot1);
	    	result.add(tempRoot2);
	    	result.add(tempRoot3);
	    	result.add(tempRoot4);
	    	result.add(tempRoot5);
	    	result.add(tempRoot6);
	    	return result;

	    	
	    }
	private int safePrime;
	private int[] safePrimes = {5, 7, 11, 23, 47, 59, 83, 107, 167, 179, 227, 263, 347, 359, 383, 467, 479, 503, 563, 587, 719, 839, 863, 887, 983, 1019, 1187, 1283, 1307, 1319, 1367, 1439};
	private File textFile;
	private String fileName;
	private ArrayList <Character> chars = new ArrayList<Character>();
	private int targetLength;
	
	 private static double percentEncrypted;
	    private static double percentDecrypted;
	    
	    public  double getPercentEncrypted() {
	    	return percentEncrypted;
	    	
	    }
	    
	    public  double getPercentDecrypted() {
	    	return percentDecrypted;
	    	
	    }
	
	public ArrayList<Character> getCharacterArray () throws FileNotFoundException {
		File f = new File (fileName);
		Scanner scan = new Scanner(f);
		ArrayList<Character> baka = new ArrayList<Character>();
		while (scan.hasNext()) {
			String str = scan.nextLine();
			for (int i = 0; i < str.length(); i++) {
				if (!(Character.isWhitespace(str.charAt(i)))) {
					baka.add(str.charAt(i));
				}
			}
		}
		return baka;
	}
	
	public TheText (String fn) {
		fileName = fn;
		percentEncrypted = 0;
		percentDecrypted = 0;
	}
	
	public void encipher () throws IOException {
		File f = new File (fileName);
		Scanner scan = new Scanner(f);
		while (scan.hasNext()) {
			String str = scan.nextLine();
			for (int i = 0; i < str.length(); i++) {
				if (!(Character.isWhitespace(str.charAt(i)))) {
					chars.add(str.charAt(i));
				}
			}
		}
		int currentLength = chars.size();
		for (int i = 0; i < safePrimes.length; i++) {
			if (Math.pow(safePrimes[i] - 1, 2) >= currentLength) {
				safePrime = safePrimes[i];
				targetLength = (int) Math.pow(safePrime - 1, 2);
				break;
			}
		}
		for (int j = 0; j < targetLength - currentLength; j++) {
			Random random = new Random();
			char c = (char) (random.nextInt(26) + 'a');
			chars.add(c);
		}
		
		ArrayList <Integer> proots = new ArrayList <Integer> ();
	   	 for (int i = 0; i < safePrime - 1; i++) {
	  		  proots.add(i);
	   	 }
	   	 for (int i = 0; i < safePrime; i++) {
	  		  int j = proots.indexOf((i*i) % safePrime);
	  		  if (j != -1) {
	  			  proots.remove(j);
	  		  }
	  		 
	   	 }
	   	 Random random = new Random();
	   	int randomIndex1 = random.nextInt(proots.size());
 		 int randomIndex2 = random.nextInt(proots.size());
 		 int randomIndex3 = random.nextInt(proots.size());
 		 int randomIndex4 = random.nextInt(proots.size());
 		 int randomIndex5 = random.nextInt(proots.size());
 		 int randomIndex6 = random.nextInt(proots.size());

 		 int proot1 = proots.get(randomIndex1);
 		 int proot2 = proots.get(randomIndex2);
 		 int proot3 = proots.get(randomIndex3);
 		 int proot4 = proots.get(randomIndex4);
 		 int proot5 = proots.get(randomIndex5);
 		 int proot6 = proots.get(randomIndex6);
 		 tempRoot1 = proot1;
	   	 tempRoot2 = proot2;
	   	 tempRoot3 = proot3;
	   	 tempRoot4 = proot4;
	   	 tempRoot5 = proot5;
	   	 tempRoot6 = proot6;
		ArrayList<Character> scrambled1 = scramble(chars, proot1);
		ArrayList<Character> scrambled2 = scramble2(scrambled1, proot2);
		ArrayList<Character> scrambled3 = scrambleD1(scrambled2, proot3, proot4);
		ArrayList<Character> finalScrambled = scrambleD2(scrambled3, proot5, proot6);

		chars = finalScrambled;
		System.out.println("scrambling is done");
		
		
		PrintWriter pw = new PrintWriter(fileName);
		
		for (int i = 0; i < safePrime - 1; i++) {
			StringBuilder line = new StringBuilder();
			for (int j = 0; j < safePrime - 1; j++) {
				
				line.append(coords(i, j, chars));
			}
			
			pw.println(line.toString());
		}
		pw.close();
		System.out.println("writing done");
		
		String secretCode = proot1 + " " + proot2 + " " + proot3 + " " + proot4 + " " + proot5 + " " + proot6;
		 
 		System.out.println("Your decryption code is: \n" + secretCode + ". \nKeep this code to yourself but don't lose it!");
		
 	
		
	}
	
	public void decipher() throws IOException {
		File f = new File (fileName);
		Scanner scan = new Scanner(f);
		Scanner saulGoodman = new Scanner(System.in);
		while (scan.hasNext()) {
			String str = scan.nextLine();
			for (int i = 0; i < str.length(); i++) {
				if (!(Character.isWhitespace(str.charAt(i)))) {
					chars.add(str.charAt(i));
				}
			}
		}
		safePrime = (int) (Math.sqrt(chars.size()) + 1);
		System.out.println("Enter the decryption code for that image: ");
	  	 
	   	 String codestring = saulGoodman.nextLine();
	   	 String[] rootsArray = codestring.split(" ");
	    
	   	 int root1 = Integer.parseInt(rootsArray[0]);
	   	 int root2 = Integer.parseInt(rootsArray[1]);
	   	 int root3 = Integer.parseInt(rootsArray[2]);
	   	 int root4 = Integer.parseInt(rootsArray[3]);
	   	 int root5 = Integer.parseInt(rootsArray[4]);
	   	 int root6 = Integer.parseInt(rootsArray[5]);
	   	 
	   	
	   	ArrayList<Character> unscrambled1 = unscrambleD2 (chars, root5, root6);
	   	ArrayList<Character> unscrambled2 = unscrambleD1 (unscrambled1, root3, root4);
	   	ArrayList<Character> unscrambled3 = unscrambleRows (unscrambled2, root2);
	   	ArrayList<Character> finalUnscrambled = unscrambleCols (unscrambled3, root1);

	   	chars = finalUnscrambled;
	   	 System.out.println("unscrambling is done");
		
		PrintWriter pw = new PrintWriter(fileName);
		
		for (int i = 0; i < safePrime - 1; i++) {
			StringBuilder line = new StringBuilder();
			for (int j = 0; j < safePrime - 1; j++) {
				
				line.append(coords(i, j, chars));
			}
			
			pw.println(line.toString());
		}
		pw.close();
		System.out.println("writing done");
	}
	
	public void decipherCustom (int r1, int r2, int r3, int r4, int r5, int r6) throws IOException {
		File f = new File (fileName);
		Scanner scan = new Scanner(f);
		Scanner saulGoodman = new Scanner(System.in);
		while (scan.hasNext()) {
			String str = scan.nextLine();
			for (int i = 0; i < str.length(); i++) {
				if (!(Character.isWhitespace(str.charAt(i)))) {
					chars.add(str.charAt(i));
				}
			}
		}
		safePrime = (int) (Math.sqrt(chars.size()) + 1);
	   	 
	   	ArrayList<Character> unscrambled1 = unscrambleD2 (chars, r5, r6);
	   	ArrayList<Character> unscrambled2 = unscrambleD1 (unscrambled1, r3, r4);
	   	ArrayList<Character> unscrambled3 = unscrambleRows (unscrambled2, r2);
	   	ArrayList<Character> finalUnscrambled = unscrambleCols (unscrambled3, r1);

	   	chars = finalUnscrambled;
	   	 System.out.println("unscrambling is done");
		
		PrintWriter pw = new PrintWriter(fileName);
		
		for (int i = 0; i < safePrime - 1; i++) {
			StringBuilder line = new StringBuilder();
			for (int j = 0; j < safePrime - 1; j++) {
				
				line.append(coords(i, j, chars));
			}
			
			pw.println(line.toString());
		}
		pw.close();
		System.out.println("writing done");
	}
	
	private  ArrayList<Character> encrypt (ArrayList <Character> original, int pr) {
	  	  ArrayList<Character> newArr = new ArrayList<Character>();
	  	  for (int i = 0; i < original.size(); i++) {
	  		  newArr.add(original.get(prToThe(pr, i) - 1));
	  	  }
	  	  return newArr;
		}
	    
		private  ArrayList<Character> encrypt2 (ArrayList <Character> original, int pr) {
	  	  ArrayList<Character> newArr = new ArrayList<Character>();
	  	  for (int i = 0; i < original.size(); i++) {
	  		  newArr.add(original.get(prToThe(pr, i) - 1));
	  	  }
	  	  return newArr;
		}
	
	
		private ArrayList<Character> scramble (ArrayList<Character> original, int pr) {
	  		  ArrayList<Character> scrambled = new ArrayList<Character>();
	  		 for (int i = 0; i < Math.pow(safePrime - 1, 2); i++) {
	  			 scrambled.add('a');
	  		 }
	  		  
	  		  //scramble each row
	  		  for (int y = 0; y < safePrime - 1 ; y++) {
	  			  ArrayList<Character> origRow = new ArrayList <Character> ();
	  			  for (int j = 0; j < safePrime - 1; j++) {
	  				  
	  				  char c = coords (j, y, original);
	  				  origRow.add(c);
	  			  }
	  			 
	  			  ArrayList<Character> scrambledRow = encrypt (origRow, pr);
	  			  for (int j = 0; j < safePrime - 1; j++) {
	  				  
	  				  char newChar = scrambledRow.get(j);
	  				  scrambled.set(index(j, y), newChar);
	  			  }
	  			  percentEncrypted += 100.0 / (4 * (safePrime - 1));
	  		  }
	  		  return scrambled;
	  	  }
		
		
		
		private ArrayList<Character> scramble2 (ArrayList<Character> original, int pr) {
	  		  ArrayList<Character> scrambled = new ArrayList<Character>();
	  		for (int i = 0; i < Math.pow(safePrime - 1, 2); i++) {
	  			 scrambled.add('a');
	  		 }
	  		//scramble each column
		  	  for (int x = 0; x < safePrime - 1; x++) {
		  		  ArrayList<Character> origCol = new ArrayList <Character> ();
		  		  for (int j = 0; j < safePrime - 1; j++) {
		  			  char c = coords (x, j, original);
		  			  origCol.add(c);
		  		  }
		  		 
		  		  ArrayList<Character> scrambledCol = encrypt2 (origCol, pr);
		  		  for (int j = 0; j < safePrime - 1; j++) {
		  			  char newChar = scrambledCol.get(j);
		  			  scrambled.set(index(x, j), newChar);
		  		  }
	  			  percentEncrypted += 100.0 / (4 * (safePrime - 1));

		  	  }
		  	  return scrambled;
	  	  }
		
		private char coords (int x, int y, ArrayList <Character> al) {
			int s = safePrime - 1;
			return al.get(s * x + y);
			
		}
	    
		private int index (int x, int y) {
			int s = safePrime - 1;
			return s * x + y;
		}
		
		private ArrayList<Character> scrambleD1 (ArrayList<Character> original, int pr1, int pr2) {
	  		  ArrayList<Character> scrambled = new ArrayList<Character>();
	  		for (int i = 0; i < Math.pow(safePrime - 1, 2); i++) {
	  			 scrambled.add('a');
	  		 }
	     	  //scramble each row
	     	  for (int y = 0; y < safePrime - 1; y++) {
	     		  ArrayList<Character> origRow = new ArrayList <Character> ();
	     		  for (int j = 0; j < safePrime - 1; j++) {
	     			  int modifiedY = (y + (prToThe(pr1, j))) % (safePrime - 1);
	     			  
	     			  char c = coords(j, modifiedY, original);
	     			  origRow.add(c);
	     		  }
	     		 
	     		  ArrayList<Character> scrambledRow = encrypt (origRow, pr2);
	     		  for (int j = 0; j < safePrime - 1; j++) {
	     			  int modifiedY = (y + (prToThe(pr1, j))) % (safePrime - 1);
	     			  char newChar = scrambledRow.get(j);
	     			  scrambled.set(index(j, modifiedY), newChar);
	     		  }
	  			  percentEncrypted += 100.0 / (4 * (safePrime - 1));

	     	  }
	     	  return scrambled;
	       }
		
		private ArrayList<Character> scrambleD2 (ArrayList<Character> original, int pr1, int pr2) {
	  		  ArrayList<Character> scrambled = new ArrayList<Character>();
	  		for (int i = 0; i < Math.pow(safePrime - 1, 2); i++) {
	  			 scrambled.add('a');
	  		 }
	 		  //scramble each column
	 		  for (int x = 0; x < safePrime - 1; x++) {
	 			  ArrayList<Character> origCol = new ArrayList <Character> ();
	 			  for (int j = 0; j < safePrime - 1; j++) {
	 				  int modifiedX = (x + (prToThe(pr1, j))) % (safePrime - 1);
	 				  char c = coords(modifiedX, j, original);
	 				  origCol.add(c);
	 			  }
	 			 
	 			  ArrayList<Character> scrambledCol = encrypt2 (origCol, pr2);
	 			  for (int j = 0; j < safePrime - 1; j++) {
	 				  int modifiedX = (x + (prToThe(pr1, j))) % (safePrime - 1);
	 				  char newChar = scrambledCol.get(j);
	 				  scrambled.set(index(modifiedX, j), newChar);
	 			  }
	  			  percentEncrypted += 100.0 / (4 * (safePrime - 1));

	 		  }
	 		  return scrambled;
	   	}

		
	
	
	
	
	private int prToThe (int base, int k) {
	  	  int value = 1;
	  	  for (int i = 0; i < k; i++) {
	  		  value*=base;
	  		  value%=safePrime;
	  	  }
	  	  return value;
		}
	
	private int discreteLogBasePrMod1019_ (int base, int k) {
		  return discreteLogarithm(base, k, safePrime);
	}
	
	static int discreteLogarithm(int a, int b, int m)
	{
    	int n = (int) (Math.sqrt (m) + 1);
 
    	// Calculate a ^ n
    	int an = 1;
    	for (int i = 0; i < n; ++i)
        	an = (an * a) % m;
 
    	int[] value=new int[m];
 
    	// Store all values of a^(n*i) of LHS
    	for (int i = 1, cur = an; i <= n; ++i)
    	{
        	if (value[ cur ] == 0)
            	value[ cur ] = i;
        	cur = (cur * an) % m;
    	}
 
    	for (int i = 0, cur = b; i <= n; ++i)
    	{
        	// Calculate (a ^ j) * b and check
        	// for collision
        	if (value[cur] > 0)
        	{
            	int ans = value[cur] * n - i;
            	if (ans < m)
                	return ans;
        	}
        	cur = (cur * a) % m;
    	}
    	return -1;
	}
	
	//use pr1
   	private  ArrayList<Character> decryptRows (ArrayList <Character> original, int pr) {
 		  ArrayList<Character> newArr = new ArrayList<Character>();
 		  for (int i = 0; i < original.size(); i++) {
 			 
 			  newArr.add(original.get(discreteLogBasePrMod1019_(pr, i+1) % (safePrime - 1)));
 		  }
 		  return newArr;
   	}
  	 
   	//use pr2
   	private  ArrayList<Character> decryptCols (ArrayList <Character> original, int pr) {
 		  ArrayList<Character> newArr = new ArrayList<Character>();
 		  for (int i = 0; i < original.size(); i++) {
 			  newArr.add(original.get(discreteLogBasePrMod1019_(pr, i+1) % (safePrime - 1)));
 		  }
 		  return newArr;
   	}
   	
   	private ArrayList<Character> unscrambleCols (ArrayList<Character> original, int pr) {
		  ArrayList<Character> unscrambled = new ArrayList<Character>();
		  for (int i = 0; i < Math.pow(safePrime - 1, 2); i++) {
	  			 unscrambled.add('a');
	  		 }
		  //scramble each row
		  for (int y = 0; y < safePrime - 1; y++) {
			  ArrayList<Character> origRow = new ArrayList <Character> ();
			  for (int j = 0; j < safePrime - 1; j++) {
				  char c = coords(j, y, original);
				  origRow.add(c);
			  }
			 
			  ArrayList<Character> unscrambledRow = decryptRows (origRow, pr);
			  for (int j = 0; j < safePrime - 1; j++) {
				  char newChar = unscrambledRow.get(j);
				  unscrambled.set(index(j, y), newChar);
			  }
			  System.out.println("Column " + y + " unscrambled");
  			  percentDecrypted += 100.0 / (4 * (safePrime - 1));


		  }
		  return unscrambled;
	  }
 
	private ArrayList<Character> unscrambleRows (ArrayList<Character> original, int pr) {
	  ArrayList<Character> unscrambled = new ArrayList<Character>();
	  for (int i = 0; i < Math.pow(safePrime - 1, 2); i++) {
			 unscrambled.add('a');
		 }
	  //scramble each column
	  for (int x = 0; x < safePrime - 1; x++) {
		  ArrayList<Character> origCol = new ArrayList <Character> ();
		  for (int j = 0; j < safePrime - 1; j++) {
			  
			  char c = coords(x, j, original);
			  origCol.add(c);
		  }
		 
		  ArrayList<Character> unscrambledCol = decryptCols (origCol, pr);
		  for (int j = 0; j < safePrime - 1; j++) {
			  char newChar = unscrambledCol.get(j);
			  unscrambled.set(index(x, j), newChar);
		  }
		  System.out.println("Row " + x + " unscrambled");
			  percentDecrypted += 100.0 / (4 * (safePrime - 1));


	  }
	  return unscrambled;
	}
	
	private ArrayList<Character> unscrambleD1 (ArrayList<Character> original, int pr1, int pr2) {
		ArrayList<Character> unscrambled = new ArrayList<Character>();
		for (int i = 0; i < Math.pow(safePrime - 1, 2); i++) {
 			 unscrambled.add('a');
 		 }
 		  //scramble each row
 		  for (int y = 0; y < safePrime - 1; y++) {
 			  ArrayList<Character> origRow = new ArrayList <Character> ();
 			  for (int j = 0; j < safePrime - 1; j++) {
					  int modifiedY = (y + (prToThe(pr1, j))) % (safePrime - 1);
					  
 				  char c = coords(j, modifiedY, original);
 				  origRow.add(c);
 			  }
 			 
 			  ArrayList<Character> unscrambledRow = decryptRows (origRow, pr2);
 			  for (int j = 0; j < safePrime - 1; j++) {
					  int modifiedY = (y + (prToThe(pr1, j))) % (safePrime - 1);
 				  char newChar = unscrambledRow.get(j);
 				  unscrambled.set(index(j, modifiedY), newChar);
 			  }
 			  System.out.println("Column " + y + " unscrambled");
  			  percentDecrypted += 100.0 / (4 * (safePrime - 1));


 		  }
 		  return unscrambled;
 	  }
	 
 	private ArrayList<Character> unscrambleD2 (ArrayList<Character> original, int pr1, int pr2) {
		ArrayList<Character> unscrambled = new ArrayList<Character>();
		for (int i = 0; i < Math.pow(safePrime - 1, 2); i++) {
 			 unscrambled.add('a');
 		 }
 		  //scramble each column
 		  for (int x = 0; x < safePrime - 1; x++) {
 			  ArrayList<Character> origCol = new ArrayList <Character> ();
 			  for (int j = 0; j < safePrime - 1; j++) {
   				  int modifiedX = (x + (prToThe(pr1, j))) % (safePrime - 1);
   				  
 				  char c = coords(modifiedX, j, original);
 				  origCol.add(c);
 			  }
 			 
 			  ArrayList<Character> unscrambledCol = decryptCols (origCol, pr2);
 			  for (int j = 0; j < safePrime - 1; j++) {
   				  int modifiedX = (x + (prToThe(pr1, j))) % (safePrime - 1);
 				  char newChar = unscrambledCol.get(j);
 				  unscrambled.set(index(modifiedX, j), newChar);
 			  }
 			  System.out.println("Row " + x + " unscrambled");
  			  percentDecrypted += 100.0 / (4 * (safePrime - 1));


 		  }
 		  return unscrambled;
   	}
	}


