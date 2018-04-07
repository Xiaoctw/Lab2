/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package test.graph;

import static org.junit.Assert.*;

import graph.ConcreteEdgesGraph;
import graph.Graph;
import test.graph.GraphInstanceTest;
import org.junit.Test;

import java.util.Map;

/**
 * Tests for ConcreteEdgesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteEdgesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteEdgesGraphTest extends GraphInstanceTest {
    
    /*
     * Provide a ConcreteEdgesGraph for tests in GraphInstanceTest.
     */
    @Override
    public Graph<String> emptyInstance() {
        return new ConcreteEdgesGraph();
    }
    
    /*
     * Testing ConcreteEdgesGraph...
     */
    
    // Testing strategy for ConcreteEdgesGraph.toString()
    //   TODO
    @Test
    public void testConcreteEdgesGraphtoString(){
        ConcreteEdgesGraph graph= (ConcreteEdgesGraph) emptyInstance();
        graph.add("a");
        graph.add("b");
        graph.add("c");
        graph.add("d");
        graph.set("a","b",3);
        graph.set("b","c",5);
        graph.set("a","c",4);
        graph.set("a","d",2);
       // Systemgraph.toString();
        System.out.println(graph.toString());
        assertEquals("包含顶点：a,b,c,d, \n包含边：a->b 权值:3 ,b->c 权值:5 ,a->c 权值:4 ,a->d 权值:2 ,",graph.toString());
    }
    
    // TODO tests for ConcreteEdgesGraph.toString()
    
    /*
     * Testing Edge...
     */
    
    // Testing strategy for Edge
    //   TODO
    @Test
    public void testStrategy(){
        ConcreteEdgesGraph graph= (ConcreteEdgesGraph) emptyInstance();
        graph.add("a");
        graph.add("b");
        graph.add("c");
        graph.add("d");
        graph.set("a","b",3);
        graph.set("b","c",5);
        graph.set("a","c",4);
        graph.set("a","d",2);
        Map<String,Integer> map=graph.sources("d");
        Map<String,Integer> map1=graph.targets("a");
//        System.out.println(map.toString());
//        System.out.println(map1.toString());
        assertEquals("{a=2}",map.toString());
        assertEquals("{b=3, c=4, d=2}",map1.toString());
    }
    // TODO tests for operations of Edge
    @Test
    public void testOperationsEdges(){
        ConcreteEdgesGraph graph= (ConcreteEdgesGraph) emptyInstance();
        graph.add("a");
        graph.add("b");
        graph.add("c");
        graph.add("d");
        graph.set("a","b",3);
        graph.set("b","c",5);
        graph.set("a","c",4);
        graph.set("a","d",2);
        assertEquals(2,graph.set("a","d",4));
        assertEquals(0,graph.set("a","b",0));
        assertEquals("包含顶点：a,b,c,d, \n包含边：b->c 权值:5 ,a->c 权值:4 ,a->d 权值:4 ,",graph.toString());

    }
}
