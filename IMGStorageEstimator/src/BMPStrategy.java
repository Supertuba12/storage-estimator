/**
 * Strategy for estimating the storage size of a BMP image. The strategy takes
 * into account that a BMP image can be of a pyramid level type when
 * calculating the estimated storage size.
 *
 * @author Hampus Eriksson
 *
 */
public class BMPStrategy implements ImageStrategy {
	
	@Override
	public long estimateStorage(int width, int height) {
		long estimatedStorage = 0;
		do {
			estimatedStorage += width * height; 
			width = width/2;
			height = height/2;
		} while(width >= 128 && height >= 128);
		
		return estimatedStorage;
	}

}
