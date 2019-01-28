package strategy;

import java.util.List;
import java.util.Map;

/**
 * Strategy for estimating the storage size of a BMP image. The strategy takes
 * into account that a BMP image can be of a pyramid level type when
 * calculating the estimated storage size.
 *
 * @author Hampus Eriksson
 *
 */
public class BMPStrategy extends ImageStrategy {
	
	@Override
	public void estimateStorage(List<Integer> dimensions, Map<Integer, Long> images) {
		int width = dimensions.get(0);
		int height = dimensions.get(1);
		
		if(width > 0 && height > 0) {
			long estimatedStorage = 0;
			do {
				estimatedStorage += width * height; 
				width = width/2;
				height = height/2;
			} while(width >= 128 && height >= 128);
			
			images.put(images.size() + 1, estimatedStorage);
		} else {
			System.out.println("Could not estimate storage for BMP."
					+ " Dimensions must be positive.");
		}
	}

}
