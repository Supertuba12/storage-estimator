import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import strategy.BMPStrategy;

@DisplayName("Baseline BMP test class")
class BMPStrategyTest {

	static BMPStrategy instance;
	static List<Long> images;
	static List<Integer> dimensions;
	
	private final static ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final static PrintStream originalOut = System.out;
	
	
	@BeforeAll
    static void beforeAll() {
		 System.setOut(new PrintStream(outContent));
		
        instance = new BMPStrategy();
        
    }
 
    @BeforeEach
    void beforeEach() {
        images = new ArrayList<Long>();
        dimensions = new ArrayList<Integer>();
        outContent.reset();
    }
 
    @AfterAll
    static void afterAll() {
    	System.setOut(originalOut);
    	
        instance = null;
        images = null;
        dimensions = null;
    }
 
    @Test
    @DisplayName("BMP without pyramid-level images.")
    void noPyramidTest() {
    	dimensions.add(128);
    	dimensions.add(128);
        instance.estimateStorage(dimensions, images);
        
        assertEquals(1, images.size());
        assertEquals(16384, images.get(0).longValue());
    }
 
    @Test
    @DisplayName("BMP with pyramid-level images")
    void withPyramidTest() {
    	dimensions.add(2048);
    	dimensions.add(1024);
        instance.estimateStorage(dimensions, images);
        
        assertEquals(1, images.size());
        assertEquals(2785280, images.get(0).longValue());
        
    }
    
    @Test
    @DisplayName("Non-positive dimensions for BMP")
    void nonPositiveTest1() {
    	dimensions.add(-1000);
    	dimensions.add(-2000);
        instance.estimateStorage(dimensions, images);
        
        assertEquals(0, images.size());
        assertEquals("Could not estimate storage for BMP. Dimensions must be positive.\n", 
        		outContent.toString());
    }
    
    @Test
    @DisplayName("Non-positive dimensions for BMP")
    void nonPositiveTest2() {
    	dimensions.add(0);
    	dimensions.add(1000);
        instance.estimateStorage(dimensions, images);
        
        assertEquals(0, images.size());
        assertEquals("Could not estimate storage for BMP. Dimensions must be positive.\n", 
        		outContent.toString());
    }
}
