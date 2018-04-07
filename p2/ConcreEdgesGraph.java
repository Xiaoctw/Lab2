package com.company;

import java.util.*;

public class ConcreEdgesGraph<L> implements Graph<L> {
    private final Set<L> vertices = new HashSet<>();//保存定点的信息
    private final List<Edge> edges = new ArrayList<>();
    @Override
    public boolean add(L vertex) {
        //vertices.add(vertex);
        if (vertices.contains(vertex)){
            return false;
        }else {
            vertices.add(vertex);
        }
        return true;
    }

    @Override
    public int set(L source, L target, int weight) {
        if (weight!=0){
            int previous;
            for (Edge edge:edges){
                if (edge.getSource().equals(source)&&edge.getTarget().equals(target)){
                        previous=edge.getWeight();
                        edge.setWeight(weight);
                        return previous;
                }
            }
            Edge edge=new Edge(source,target,weight);
            edges.add(edge);
        }else {
            for (Edge edge:edges) {
                if (edge!=null){
                    edges.remove(edge);
                }
            }
        }
        return 0;
    }

    @Override
    public boolean remove(L vertex) {
        vertices.remove(vertex);
        int flag=0;
        for (Edge edge:edges) {
            if(edge.getSource().equals(vertex)||edge.getTarget().equals(vertex)){
                edges.remove(edge);
                flag=1;
            }
        }
        return flag==1?true:false;
    }

    @Override
    public Set<L> vertices() {
        return vertices;
    }

    @Override
    public Map<L, Integer> sources(L target) {
        Map<L,Integer> map=new HashMap<>();
        for (Edge edge:edges
             ) {
            if (edge.getTarget().equals(target)){
                map.put((L) edge.getSource(),edge.getWeight());
            }
        }
        return map;
    }

    @Override
    public Map<L, Integer> targets(L source) {
        Map<L,Integer> map=new HashMap<>();
        for (Edge edge:edges
             ) {
            if (edge.getSource().equals(source)){
                map.put((L) edge.getTarget(),edge.getWeight());
            }
        }
        return map;
    }
    public Set<L> getVertices() {
        return vertices;
    }

    public List<Edge> getEdges() {
        return edges;
    }
}
