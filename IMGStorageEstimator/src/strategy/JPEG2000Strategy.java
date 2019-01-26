package strategy;

import java.util.List;


/**
 * Strategy for estimating the storage size of a JPEG 2000 image.
 *
 * @author Hampus Eriksson
 *
 */
public class JPEG2000Strategy extends ImageStrategy {

	@Override
	public void estimateStorage(List<Integer> dimensions, List<Long> images) {
		int width = dimensions.get(0);
		int height = dimensions.get(1);
		
		double imageSize = 
				(width * height * 0.4) / Math.log(Math.log(width * height + 16));
		long estimatedStorage = (long) Math.ceil(imageSize);
		
		images.add(estimatedStorage);
	}

}
