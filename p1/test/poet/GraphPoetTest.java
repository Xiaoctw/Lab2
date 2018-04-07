/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package poet;

import static org.junit.Assert.*;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * Tests for GraphPoet.
 */
public class GraphPoetTest {
    
    // Testing strategy
    //   TODO
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    // TODO tests
    @Test
    public void testGraphPoet() throws IOException {
        GraphPoet nimoy = new GraphPoet(new File("src/poet/mugar-omni-theater.txt"));
        final String input = "Test the system.";
        assertEquals("Test of the system.",nimoy.poem(input));
    }
}
