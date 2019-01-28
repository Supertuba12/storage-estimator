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
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import strategy.GroupStrategy;

@DisplayName("Baseline Group test class")
class GroupStrategyTest {

	static GroupStrategy instance;
	static Map<Integer, Long> images;
	static List<Integer> indices;
	
	
	private final static ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final static PrintStream originalOut = System.out;
	
	
	@BeforeAll
    static void beforeAll() {
		System.setOut(new PrintStream(outContent));
        instance = new GroupStrategy();
        
    }
 
    @BeforeEach
    void beforeEach() {
        images = new HashMap<Integer, Long>();
        indices = new ArrayList<Integer>();
        outContent.reset();
    }
 
    @AfterAll
    static void afterAll() {
    	System.setOut(originalOut);
    	
        instance = null;
        images = null;
        indices = null;
    }
 
    @Test
    @DisplayName("Duplicate indices given to group.")
    void duplicateTest() {
    	indices.add(1);
    	indices.add(2);
    	indices.add(3);
    	indices.add(1);
    	indices.add(4);
    	
    	images.put(1, (long) 12345);
    	images.put(2, (long) 67890);
    	images.put(3, (long) 9876);
    	images.put(4, (long) 765);
    	
    	instance.estimateStorage(indices, images);
    	
    	assertEquals(4, images.size());
    	assertEquals("Invalid input, cannot stack the same image.\n", outContent.toString());
    
    }
 
    @Test
    @DisplayName("Unique indices given to group")
    void uniqueTest() {
    	indices.add(1);
    	indices.add(2);
    	indices.add(3);
    	
    	images.put(1, (long) 12345);
    	images.put(2, (long) 67890);
    	images.put(3, (long) 9876);
    	images.put(4, (long) 765);
    	
    	instance.estimateStorage(indices, images);
    	
    	assertEquals(2, images.size());
    	assertEquals(765, images.get(4).longValue());
    	assertEquals(50291, images.get(5).longValue());
    
    }
    
    @Test
    @DisplayName("Index out of range test")
    void indexTest() {
    
    	indices.add(-1);
    	indices.add(2);
    	indices.add(3);
    	indices.add(5);
    	
    	images.put(1, (long) 12345);
    	images.put(2, (long) 67890);
    	images.put(3, (long) 9876);
    	images.put(4, (long) 765);
    	
    	instance.estimateStorage(indices, images);
    	
    	assertEquals(4, images.size());
    	assertEquals("Invalid input, index is out of range.\n", outContent.toString());
    	
    }
    
}