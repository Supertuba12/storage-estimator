package strategy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Strategy that estimates the storage size for grouped/stacked images.
 * 
 * @author Hampus Eriksson
 * 
 */

public class GroupStrategy extends ImageStrategy {

	/**
	 * Validates whether or not a given list of indices contains duplicates
	 * or not.
	 * 
	 * @param indices: A list of indices of images that the user wants to 
	 * group/stack.
	 * @return boolean which is true if a duplicate was found, otherwise 
	 * returns false.
	 */
	private boolean containsDuplicates(List<Integer> indices) {
		Set<Integer> appearedIndices = new HashSet<Integer>();
		for (int index : indices) {
			if(!appearedIndices.add(index)) {
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * Removes image size estimations with help of each image's index.
	 * 
	 * 
	 * @param indices: A list of indices of images.
	 * @param images: A map containing current image size estimations.
	 * @return
	 */
	private void removeImages(List<Integer> indices, List<Long> images){
		
		indices.stream()
	        .sorted(Comparator.reverseOrder())
	        .mapToInt(Integer::intValue)
	        .forEach(images::remove);
	}
	
	
	public void estimateStorage(List<Integer> indices, List<Long> images) {
		if(!containsDuplicates(indices)) {
			double divisor = indices.size() + 3.0;
			long dividend = 0;
			
			int numberOfImages = images.size();
			List<Integer> realIndices = new ArrayList<Integer>();
			
			for(int index: indices) {
				if(index > 0 && index <= numberOfImages) {
					dividend += images.get(index - 1);
					realIndices.add(index - 1);
				} else {
					System.out.println("Invalid input, index is out of range.");
					return;
				}
			}
			removeImages(realIndices, images);
			long estimatedStorage = (long) (dividend / Math.log(divisor));
			images.add(estimatedStorage);
		} else {
			System.out.println("Invalid input, cannot stack the same image.");
			return;
		}
	}
}
