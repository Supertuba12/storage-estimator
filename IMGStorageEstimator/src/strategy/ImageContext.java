package strategy;

import java.util.List;

/**
 * Strategy controller used to execute strategies given a type.
 *
 * @author Hampus Eriksson
 *
 */
public class ImageContext {
	
	private ImageStrategy strategy;
	
	
	public void setStrategy(ImageStrategy strategy) {
		this.strategy = strategy;
	}
	
	
	public void execute(List<Integer> values, List<Long> images) {
		this.strategy.estimateStorage(values, images);
	}

}
