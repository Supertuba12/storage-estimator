import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.stream.Collectors;

import commands.Image;
import strategy.ImageContext;

/*
 * 
 * Client class which produces a CLI to the user. The client continues to
 * read input until the user gives an exit command.
 * 
 * @author Hampus Eriksson
 * 
 */

public class Client {
		
	private static boolean validateValues(List<Integer> values) {
		for(Integer value : values) {
			if(value <= 0) {
				return false;
			}
		}
		return true;
	}
	
	private static List<Integer> stringArrayToIntList(String[] values) {
		
	    List<Integer> valuesAsIntegers = Arrays.stream(values)
	            .map(Integer::parseInt)
	            .collect(Collectors.toList());
	    
	    return valuesAsIntegers;
	}
	
	private static long calculateTotalStorage(List<Long> images) {
		
		long totalSize = 0;
		for(Long imageSize : images) {
			totalSize += imageSize;
		}
		return totalSize;
	}
	
	public static void main(String[] args) {
		
		final String header = "Storage calculator by Hampus Eriksson. "
				+ "Enter one line for each image/group on the format \"type width "
				 + "height\", or \"G i, i, ...\". Exit with \"Q\".";
		
		final String splitCondition = "\\s*(\\s|,)\\s*";
		
		List<Long> images = new ArrayList<Long>();
		
		Scanner reader = new Scanner(System.in);
		
		String newLine;
		
		DecimalFormatSymbols symbols = new DecimalFormatSymbols();
		symbols.setGroupingSeparator(' ');
		DecimalFormat decimalFormat = new DecimalFormat("###,###", symbols);
		
		ImageContext ctx = new ImageContext();
		
		System.out.println(header);
		
		while (!reader.hasNext("\\s*(Q|q)\\s*")) {
			Image imageType = null;
			String[] imageInfo = null;
			try {
			newLine = reader.nextLine();
			
			imageInfo = newLine.split(splitCondition);
			imageType = Image.valueOf(imageInfo[0]);
			
			} catch(NoSuchElementException e) {
				System.err.println("Could not read from line as no line was found: " 
								+ e.getMessage());
			} catch(IllegalStateException e) {
				System.err.println("Could not read from line, scanner is closed: " 
						+ e.getMessage());
			} catch(IllegalArgumentException e) {
				System.out.println("Invalid image/group type given.");
			}
			
			if(imageType != null && imageInfo != null) {
				List<Integer> values = stringArrayToIntList(
						Arrays.copyOfRange(imageInfo, 1, imageInfo.length));
				
				if(validateValues(values)) {
					ctx.setStrategy(imageType.getStrategy());
					ctx.execute(values, images);
					System.out.printf("Current images in list: %s\n", 
							images.toString());
				}
			}
			
		}
		long totalSize = calculateTotalStorage(images);
		System.out.printf("Total size: %s bytes", decimalFormat.format(totalSize));
		   			
	}

}
