package strategy;

import java.util.List;
import java.util.Map;


/**
 * Strategy for estimating the storage size of a JPEG 2000 image.
 *
 * @author Hampus Eriksson
 *
 */
public class JPEG2000Strategy extends ImageStrategy {

	@Override
	public void estimateStorage(List<Integer> dimensions, Map<Integer, Long> images) {
		int width = dimensions.get(0);
		int height = dimensions.get(1);
		
		if(width > 0 && height > 0) {
			double imageSize = 
					(width * height * 0.4) / Math.log(Math.log(width * height + 16));
			long estimatedStorage = (long) imageSize;
			
			images.put(images.size() + 1, estimatedStorage);
		} else {
			System.out.println("Could not estimate storage for JPEG2000."
				+ " Dimensions must be positive.");
		}
	}

}
