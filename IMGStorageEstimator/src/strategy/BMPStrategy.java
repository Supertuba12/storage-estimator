package strategy;

import java.util.List;

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
	public void estimateStorage(List<Integer> dimensions, List<Long> images) {
		int width = dimensions.get(0);
		int height = dimensions.get(1);
		
		long estimatedStorage = 0;
		do {
			estimatedStorage += width * height; 
			width = width/2;
			height = height/2;
		} while(width >= 128 && height >= 128);
		
		images.add(estimatedStorage);
	}

}
