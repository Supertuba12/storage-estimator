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

import strategy.GroupStrategy;

@DisplayName("Baseline Group test class")
class GroupStrategyTest {

	static GroupStrategy instance;
	static List<Long> images;
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
    	images = new ArrayList<Long>();
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
    	
    	images.add((long) 12345);
    	images.add((long) 67890);
    	images.add((long) 9876);
    	images.add((long) 765);
    	
    	instance.estimateStorage(indices, images);
    	
    	assertEquals(4, images.size());
    	assertEquals("Invalid input, cannot stack the same image.\n", outContent.toString());
    
    }
    
    @Test
    @DisplayName("Index out of range test")
    void indexTest() {
    	indices.add(-1);
    	indices.add(2);
    	indices.add(3);
    	indices.add(5);
    	
    	images.add((long) 12345);
    	images.add((long) 67890);
    	images.add((long) 9876);
    	images.add((long) 765);
    	
    	instance.estimateStorage(indices, images);
    	
    	assertEquals(4, images.size());
    	assertEquals("Invalid input, index is out of range.\n", outContent.toString());
    }
 
    @Test
    @DisplayName("Unique indices given to group with edge element left")
    void uniqueTest1() {
    	indices.add(1);
    	indices.add(2);
    	indices.add(3);
    	
    	images.add((long) 12345);
    	images.add((long) 67890);
    	images.add((long) 9876);
    	images.add((long) 765);
    	
    	instance.estimateStorage(indices, images);
    	
    	assertEquals(2, images.size());
    	assertEquals(765, images.get(0).longValue());
    	assertEquals(50291, images.get(1).longValue());
    }
    
    @Test
    @DisplayName("Unique indices given to group with both edge elements left")
    void uniqueTest2() {
    	indices.add(2);
    	indices.add(3);
    	
    	images.add((long) 12345);
    	images.add((long) 67890);
    	images.add((long) 9876);
    	images.add((long) 999);
    	
    	instance.estimateStorage(indices, images);
    	
    	assertEquals(3, images.size());
    	assertEquals(12345, images.get(0).longValue());
    	assertEquals(999, images.get(1).longValue());
    	assertEquals(48318, images.get(2).longValue());
    }
    
    @Test
    @DisplayName("Unique indices given to group with middle elements left")
    void uniqueTest3() {
    	indices.add(1);
    	indices.add(4);
    	
    	images.add((long) 12345);
    	images.add((long) 67890);
    	images.add((long) 9876);
    	images.add((long) 765);
    	
    	instance.estimateStorage(indices, images);
    	
    	assertEquals(3, images.size());
    	assertEquals(67890, images.get(0).longValue());
    	assertEquals(9876, images.get(1).longValue());
    	assertEquals(8145, images.get(2).longValue());
    }
 
}