import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.stream.Collectors;

import commands.Image;
import strategy.ImageContext;

/**
 * Client class which produces a CLI to the user. The client continues to
 * read input until the user gives an exit command.
 * 
 * @author Hampus Eriksson
 * 
 */

public class Client {
	
	
	/**
	 * Converts a string array to a list of integers.
	 * 
	 * @param values: String array with values that are to be
	 * parsed.
	 * @return The function returns a list of integers. 
	 */
	private static List<Integer> stringArrayToIntList(String[] values) {
		try {
		    List<Integer> valuesAsIntegers = Arrays.stream(values)
		            .map(Integer::parseInt)
		            .collect(Collectors.toList());

		    return valuesAsIntegers;
		    
		} catch(NumberFormatException e) {
			System.out.println("Invalid form. Input is not an integer: " 
					+ e.getMessage());
			
			return Collections.emptyList();
		}
	}
	
	/**
	 * Calculates the total amount of storage needed for a given collection
	 * of images.
	 * 
	 * @param images: A collection of images and their respective storage 
	 * estimation.
	 * @return The function returns the total storage estimation.
	 */
	private static long calculateTotalStorage(Map<Integer, Long> images) {
		
		long totalSize = 0;
		for(Long imageSize : images.values()) {
			totalSize += imageSize;
		}
		
		return totalSize;
	}
	 /**
	  * Main function and entry point for program. Starts a simple CLI
	  * for the image storage estimation program.
	  * 
	  */
	public static void main(String[] args) {
		
		final String header = "Storage calculator by Hampus Eriksson. "
				+ "Enter one line for each image/group on the format \"type width "
				 + "height\", or \"G i, i, ...\". Exit with \"Q\".";
		
		final String DELIMITERS = "\\s*(\\s|,)\\s*";
		
		Map<Integer, Long> images = new HashMap<Integer, Long>();
		
		Scanner reader = new Scanner(System.in);
		
		String newLine;
		
		DecimalFormatSymbols symbols = new DecimalFormatSymbols();
		symbols.setGroupingSeparator(' ');
		DecimalFormat decimalFormat = new DecimalFormat("###,###", symbols);
		
		ImageContext ctx = new ImageContext();
		
		System.out.println(header);
		
		while (!reader.hasNext("\\s*(Q|q)\\s*")) {
			Image imageType = null;
			List<Integer> values = new ArrayList<Integer>(); 
			try {
				newLine = reader.nextLine();			
				String[] imageInfo = newLine.split(DELIMITERS);
				
				imageType = Image.valueOf(imageInfo[0]);
				values = stringArrayToIntList(
						Arrays.copyOfRange(imageInfo, 1, imageInfo.length));

			} catch(NoSuchElementException e) {
				System.err.println("Could not read from line as no line was found: " 
								+ e.getMessage());
			} catch(IllegalStateException e) {
				System.err.println("Could not read from line, scanner is closed: " 
						+ e.getMessage());
			} catch(IllegalArgumentException e) {
				System.out.println("Invalid image/group type given.");
			}
			
			if(values.size() >= 2) {
				ctx.setStrategy(imageType.getStrategy());
				ctx.execute(values, images);

			} else {
				System.out.println("Too few parameters for image/group.");
			}
			
		}
		reader.close();
		long totalSize = calculateTotalStorage(images);
		System.out.printf("Total size: %s bytes", decimalFormat.format(totalSize));
	}

}
