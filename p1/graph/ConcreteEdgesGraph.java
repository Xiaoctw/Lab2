/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import java.util.*;

/**
 * An implementation of Graph.
 * <p>
 * <p>PS2 instructions: you MUST use the provided rep.
 */
public class ConcreteEdgesGraph<L> implements Graph<String> {

    private final Set<String> vertices = new HashSet<>();//保存定点的信息
    private final List<Edge> edges = new ArrayList<>();//保存边的信息

    // Abstraction function:
    //   TODO
    // Representation invariant:
    //   TODO
    // Safety from rep exposure:
    //   TODO

    // TODO constructor

    // TODO checkRep

    @Override
    public boolean add(String vertex) {
        if (vertices.contains(vertex)) {
            return false;
        } else {
            vertices.add(vertex);
            return true;
        }
        // throw new RuntimeException("not implemented");
    }

    public Edge display(Iterator<Edge> iterator, String source, String target) {//就是找到那么一条边，如果没有就返回null
        while (iterator.hasNext()) {
            Edge edge = iterator.next();
            if (edge.getSource() .equals(source)  && edge.getTarget() .equals(target) ) {
                // edges.remove(edge);//删除这条边
                return edge;
            }
        }
        return null;
    }

    @Override
    public int set(String source, String target, int weight) {
        Iterator<Edge> iterator = edges.iterator();
        if (weight != 0) {//权值不为0
            int previous;
            Edge edge = display(iterator, source, target);
            if (edge != null) {//在集合中有这条边
                previous = edge.getWeight();
                edge.setWeight(weight);//注意，java是对对象的引用，如果这里修改了，那么容器中也会发生变化
                return previous;
            }
            Edge edge1 = new Edge(source, target, weight);
            edges.add(edge1);
        } else {
            Edge edge = display(iterator, source, target);//删除这条边
            if (edge != null) {
                edges.remove(edge);
            }
        }
        //    throw new RuntimeException("not implemented");
        return 0;
    }

    @Override
    public boolean remove(String vertex) {
        vertices.remove(vertex);
        int flag = 0;
        for (Edge edge : edges) {
            if (edge.getSource() .equals(vertex) || edge.getTarget().equals(vertex)) {
                edges.remove(edge);
                flag = 1;
            }
        }
        return flag == 1 ? true : false;
        // throw new RuntimeException("not implemented");
    }

    @Override
    public Set<String> vertices() {//可能不会这么简单
        Set<String> set = new HashSet<String>();
        set.addAll(vertices);
        return set;
        //  throw new RuntimeException("not implemented");
    }

    @Override
    public Map<String, Integer> sources(String target) {
        Map<String, Integer> map = new HashMap<>();
        for (Edge edge : edges) {
            if (edge.getTarget().equals(target)) {
                map.put(edge.getSource(), edge.getWeight());
            }
        }
        return map;
        // throw new RuntimeException("not implemented");
    }

    @Override
    public Map<String, Integer> targets(String source) {
        Map<String, Integer> map = new HashMap<>();
        for (Edge edge : edges) {
            if (edge.getSource().equals(source)) {
                map.put(edge.getTarget(), edge.getWeight());
            }
        }
        return map;
        //  throw new RuntimeException("not implemented");
    }

    // TODO toString()

    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder();
        for (String str:vertices
             ) {
            sb.append(str+",");
        }
        String result="包含顶点：";
        result+=sb.toString();
        result+=" \n包含边：";
        StringBuilder sb1=new StringBuilder();
        for (Edge edge:edges
             ) {
            sb1.append(edge.getSource()+"->"+edge.getTarget()+" 权值:"+edge.getWeight()+" ,");
        }
        result+=sb1.toString();
        return result;
    }
}

/**
 * TODO specification
 * Immutable.
 * This class is internal to the rep of ConcreteEdgesGraph.
 * <p>
 * <p>PS2 instructions: the specification and implementation of this class is
 * up to you.
 */
class Edge {

    // TODO fields

    // Abstraction function:
    //   TODO
    // Representation invariant:
    //   TODO
    // Safety from rep exposure:
    //   TODO

    // TODO constructor

    // TODO checkRep

    // TODO methods

    // TODO toString()
    private String source;//起点
    private String target;//终点
    private int weight;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Edge(String source, String target, int weight) {
        this.source = source;
        this.target = target;
        this.weight = weight;
    }
    public Edge(String source,String target){//如果没有设置初始化的权值，默认为0
        this(source,target,0);
    }
    public Edge(){
        this(null,null,0);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
