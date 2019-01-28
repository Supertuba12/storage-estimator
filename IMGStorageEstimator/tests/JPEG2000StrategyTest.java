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

import strategy.JPEG2000Strategy;

@DisplayName("Baseline JPEG2000 test class")
class JPEG2000StrategyTest {

	static JPEG2000Strategy instance;
	static List<Long> images;
	static List<Integer> dimensions;
	
	private final static ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final static PrintStream originalOut = System.out;
	
	
	@BeforeAll
    static void beforeAll() {
		 System.setOut(new PrintStream(outContent));
		
        instance = new JPEG2000Strategy();
        
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
    @DisplayName("JPEG2000 without pyramid-level images.")
    void noPyramidTest() {
    	dimensions.add(128);
    	dimensions.add(128);
        instance.estimateStorage(dimensions, images);
        
        assertEquals(1, images.size());
        assertEquals(2883, images.get(0).longValue());
    }
 
    @Test
    @DisplayName("JPEG2000 with pyramid-level images")
    void withPyramidTest() {
    	dimensions.add(2048);
    	dimensions.add(1024);
        instance.estimateStorage(dimensions, images);
        
        assertEquals(1, images.size());
        assertEquals(313240, images.get(0).longValue());
        
    }
    
    @Test
    @DisplayName("Non-positive dimensions for JPEG2000")
    void nonPositiveTest1() {
    	dimensions.add(-1000);
    	dimensions.add(-2000);
        instance.estimateStorage(dimensions, images);
        
        assertEquals(0, images.size());
        assertEquals("Could not estimate storage for JPEG2000. Dimensions must be positive.\n", 
        		outContent.toString());
    }
    
    @Test
    @DisplayName("Non-positive dimensions for JPEG2000")
    void nonPositiveTest2() {
    	dimensions.add(0);
    	dimensions.add(1000);
        instance.estimateStorage(dimensions, images);
        
        assertEquals(0, images.size());
        assertEquals("Could not estimate storage for JPEG2000. Dimensions must be positive.\n", 
        		outContent.toString());
    }
}
