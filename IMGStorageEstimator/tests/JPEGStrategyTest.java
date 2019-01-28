import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import strategy.JPEGStrategy;

@DisplayName("Baseline JPEG test class")
class JPEGStrategyTest {

	static JPEGStrategy instance;
	static Map<Integer, Long> images;
	static List<Integer> dimensions;
	
	private final static ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final static PrintStream originalOut = System.out;
	
	
	@BeforeAll
    static void beforeAll() {
		 System.setOut(new PrintStream(outContent));
		
        instance = new JPEGStrategy();
        
    }
 
    @BeforeEach
    void beforeEach() {
        images = new HashMap<Integer, Long>();
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
    @DisplayName("JPEG without pyramid-level images.")
    void noPyramidTest() {
    	dimensions.add(128);
    	dimensions.add(128);
        instance.estimateStorage(dimensions, images);
        
        assertEquals(1, images.size());
        assertEquals(3276, images.get(1).longValue());
    }
 
    @Test
    @DisplayName("JPEG with pyramid-level images")
    void withPyramidTest() {
    	dimensions.add(2048);
    	dimensions.add(1024);
        instance.estimateStorage(dimensions, images);
        
        assertEquals(1, images.size());
        assertEquals(557054, images.get(1).longValue());
        
    }
    
    @Test
    @DisplayName("Non-positive dimensions for JPEG")
    void nonPositiveTest1() {
    	dimensions.add(-1000);
    	dimensions.add(-2000);
        instance.estimateStorage(dimensions, images);
        
        assertEquals(0, images.size());
        assertEquals("Could not estimate storage for JPEG. Dimensions must be positive.\n", 
        		outContent.toString());
    }
    
    @Test
    @DisplayName("Non-positive dimensions for JPEG")
    void nonPositiveTest2() {
    	dimensions.add(0);
    	dimensions.add(1000);
        instance.estimateStorage(dimensions, images);
        
        assertEquals(0, images.size());
        assertEquals("Could not estimate storage for JPEG. Dimensions must be positive.\n", 
        		outContent.toString());
    }
}
