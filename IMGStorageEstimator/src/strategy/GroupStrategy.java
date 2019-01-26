package strategy;

import java.util.List;

/*
 * 
 * Strategy that estimates the storage size for grouped/stacked images.
 * 
 * @author Hampus Eriksson
 * 
 * 
 * (total storage size of images) / ln(number of base level images in stack + 3)"
 * 
 */

public class GroupStrategy extends ImageStrategy {

	public void estimateStorage(List<Integer> indices, List<Long> images) {
		for(int index : indices) {
			
		}
	}
	
}
