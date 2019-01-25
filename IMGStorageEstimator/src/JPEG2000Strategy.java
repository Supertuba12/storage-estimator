/**
 * Strategy for estimating the storage size of a JPEG 200 image.
 *
 * @author Hampus Eriksson
 *
 */
public class JPEG2000Strategy implements ImageStrategy {

	@Override
	public long estimateStorage(int width, int height) {
	
		double imageSize = 
				(width * height * 0.4) / Math.log(Math.log(width * height + 16));
		long estimatedStorage = (long) Math.ceil(imageSize);
		
		return estimatedStorage;
	}

}
