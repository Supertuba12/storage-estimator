/**
 * Strategy for estimating the storage size of a JPEG image. The strategy takes
 * into account that a JPEG can be of a pyramid level type when calculating the
 * estimated storage size.
 *
 * @author Hampus Eriksson
 *
 */
public class JPEGStrategy implements ImageStrategy {
	
	@Override
	public long estimateStorage(int width, int height) {
		
		long estimatedStorage = 0;
		do {
			double imageSize = width * height * 0.2;
			estimatedStorage += (long) Math.ceil(imageSize);
			width = width/2;
			height = height/2;
		} while(width >= 128 && height >= 128);
		
		return estimatedStorage; 
	}

}
