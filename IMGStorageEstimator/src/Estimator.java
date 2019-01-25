import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.HashMap;
import java.util.Map;

import commands.Image;


public class Estimator {
	
	private Map<Image, Long> images = new HashMap<Image, Long>();

	public static void main(String[] args) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String newLine;
		
		DecimalFormatSymbols symbols = new DecimalFormatSymbols();
		symbols.setGroupingSeparator(' ');
		
		String pattern = "###,###";
		DecimalFormat decimalFormat = new DecimalFormat(pattern, symbols);
		
		ImageContext ctx = new ImageContext();
		
		while(true) {
			
			try {
				newLine = reader.readLine();
				if(newLine.equals("q") || newLine.equals("Q")) { 
									
					int width = 2333;
					int height = 1337;
					
					long estimatedStorage = 0;
					do {
						estimatedStorage += width * height; 
						width = width/2;
						height = height/2;
						System.out.println(estimatedStorage);
						System.out.println(width);
						System.out.println(height);
					} while(width >= 128 && height >= 128);
					
					//String output = decimalFormat.format(bytes);
					//System.out.printf("Total size: %s bytes", output);
					
				} else {
					System.out.println(newLine);
				}
			} catch (IOException e) {
				System.err.println("An IOException was caught :"+e.getMessage());
				e.printStackTrace();
			}
		}
	}
}
