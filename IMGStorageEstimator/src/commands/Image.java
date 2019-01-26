package commands;

import strategy.BMPStrategy;
import strategy.ImageStrategy;
import strategy.JPEG2000Strategy;
import strategy.JPEGStrategy;
import strategy.GroupStrategy;

public enum Image {
	J("J" , new JPEGStrategy()),
	JPG("JPG", new JPEGStrategy()),
	JP2("JP2", new JPEG2000Strategy()),
	JPEG2000("JPEG2000", new JPEG2000Strategy()),
	BMP("BMP", new BMPStrategy()),
	G("G", new GroupStrategy());
	
	private String name;
	private ImageStrategy strategy;
	
	private Image(String name, ImageStrategy strategy) {
		this.name = name;
		this.strategy = strategy;
	}
	
	public ImageStrategy getStrategy() {
		return this.strategy;
	}
	
	public String getName() {
		return this.name;
	}
 	
	
}
