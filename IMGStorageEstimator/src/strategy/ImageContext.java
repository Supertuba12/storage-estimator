package strategy;

import java.util.List;
import java.util.Map;

/**
 * Controller class used to execute strategies given a type.
 *
 * @author Hampus Eriksson
 *
 */
public class ImageContext {
	
	private ImageStrategy strategy;
	
	
	public void setStrategy(ImageStrategy strategy) {
		this.strategy = strategy;
	}
	
	
	public void execute(List<Integer> values, Map<Integer, Long> images) {
		this.strategy.estimateStorage(values, images);
	}

}
