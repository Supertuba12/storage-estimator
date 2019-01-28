package commands;

import strategy.BMPStrategy;
import strategy.ImageStrategy;
import strategy.JPEG2000Strategy;
import strategy.JPEGStrategy;
import strategy.GroupStrategy;


/**
 * Enumeration class with enum values for each type of image.
 * Each enum has a name as well as a corresponding strategy 
 * class used to estimate the image's storage size.
 * 
 * @author Hampus Eriksson
 *
 */
public enum Image {
	J(new JPEGStrategy()),
	JPG(new JPEGStrategy()),
	JP2(new JPEG2000Strategy()),
	JPEG2000(new JPEG2000Strategy()),
	BMP(new BMPStrategy()),
	G(new GroupStrategy());
	
	private ImageStrategy strategy;
	
	private Image(ImageStrategy strategy) {
		this.strategy = strategy;
	}
	
	public ImageStrategy getStrategy() {
		return this.strategy;
	}
	
}
