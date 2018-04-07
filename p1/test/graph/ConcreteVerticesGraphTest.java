/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package test.graph;

import static org.junit.Assert.*;

import graph.ConcreteVerticesGraph;
import graph.Graph;
import test.graph.GraphInstanceTest;
import org.junit.Test;

import java.util.Map;

/**
 * Tests for ConcreteVerticesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteVerticesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteVerticesGraphTest extends GraphInstanceTest {
    
    /*
     * Provide a ConcreteVerticesGraph for tests in GraphInstanceTest.
     */
    @Override public Graph<String> emptyInstance() {
        return new ConcreteVerticesGraph();
    }
    
    /*
     * Testing ConcreteVerticesGraph...
     */
    
    // Testing strategy for ConcreteVerticesGraph.toString()
    //   TODO
    @Test
    public void testConcreteVerticesGraphtoString(){
        ConcreteVerticesGraph graph=new ConcreteVerticesGraph();
        graph.add("a");
        graph.add("b");
        graph.add("c");
        graph.add("d");
        graph.set("a","b",3);
        graph.set("b","c",5);
        graph.set("a","c",4);
        graph.set("a","d",2);
      //  System.out.println(graph.toString());
        assertEquals("包含顶点:a,b,c,d, \n顶点a:{b=3, c=4, d=2} 顶点b:{c=5} ",graph.toString());
//        Map<String,Integer> map=graph.sources("d");
//        Map<String,Integer> map1=graph.targets("a");
    }
    // TODO tests for ConcreteVerticesGraph.toString()
    
    /*
     * Testing Vertex...
     */
    
    // Testing strategy for Vertex
    //   TODO
    
    // TODO tests for operations of Vertex
    @Test
    public void testVertex(){
        ConcreteVerticesGraph graph=new ConcreteVerticesGraph();
        graph.add("a");
        graph.add("b");
        graph.add("c");
        graph.set("a","b",3);
        graph.set("b","c",5);
        graph.set("a","c",4);
        graph.set("a","d",2);
        Map<String,Integer> map=graph.targets("a");
        assertEquals("{b=3, c=4, d=2}",map.toString());
        return;
    }
    
}
