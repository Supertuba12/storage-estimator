package strategy;

import java.util.List;

/**
 * Interface class for strategies.
 *
 * @author Hampus Eriksson
 *
 */

public abstract class ImageStrategy {
	
	/**
	 * Estimates the storage size of an image and adds it to a common collection
	 * of estimations.
	 * 
	 * @param values: A list containing image information values.
	 * @param images: A collection of image size estimations.
	 */
	public void estimateStorage(List<Integer> values, List<Long> images) {}
}
