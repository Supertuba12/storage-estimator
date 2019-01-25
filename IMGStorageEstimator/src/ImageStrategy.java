/**
 * Interface class for strategies.
 *
 * @author Hampus Eriksson
 *
 */


public interface ImageStrategy {
	
	public long estimateStorage(int width, int height);
	
}
