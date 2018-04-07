package com.company;

import java.util.*;

public class ConcreteVerticesGraph<L> implements Graph<L> {
    private final List<Vertex> vertices=new ArrayList<>();
    @Override
    public boolean add(L vertex) {
        for (Vertex ignored :vertices
             ) {
            if (ignored.getData().equals(vertex)){
                return false;
            }
        }
        Vertex vertex1=new Vertex(vertex);
        vertices.add(vertex1);
        return true;
    }

    @Override
    public int set(L source, L target, int weight) {
        L delete=null;
        if (weight == 0) {
            for (Vertex vertex:vertices
                 ) {
                if (vertex.getData().equals(source)){
                    delete=source;
                    vertices.remove(vertex);
                    break;
                }
            }
            for (Vertex vertex:vertices){
                vertex.deleteVertex(delete);
            }
            return 0;
        }
        int result=0;
        for (Vertex vertex:vertices){
            if (vertex.getData().equals(source)){
                vertex.addVertex(target,weight);
            }
        }
        return result;
    }

    @Override
    public boolean remove(L vertex) {
        Boolean result=false;
        for (Vertex vertex1:vertices
             ) {
            if (vertex1.getData().equals(vertex)){
                vertices.remove(vertex1);
                result=true;
            }
        }
        for (Vertex vertex1:vertices){
            vertex1.deleteVertex(vertex);
        }
        return result;
    }

    @Override
    public Set<L> vertices() {
        Set<L> result=new HashSet<>();
        for (Vertex vertex:vertices
             ) {
            result.add((L) vertex.getData());
        }
        return result;
    }

    @Override
    public Map<L, Integer> sources(L target) {
        Map<L,Integer> map=new LinkedHashMap<>();
        for (Vertex vertex:vertices
             ) {
            if (vertex.hasAdioin(target)&&!vertex.getData().equals(target)){
                map.put((L) vertex.getData(),vertex.getWeight(target));
            }
        }
        return map;
    }

    @Override
    public Map<L, Integer> targets(L source) {
        Vertex findVertex=null;
        for (Vertex ve :vertices) {
            if (ve.getData().equals(source)){
                findVertex=ve;
            }
        }
        Map<L,Integer> map=new LinkedHashMap<>();
        Map<L,Integer> map1=new LinkedHashMap<>();
        for (L l:map1.keySet()){
            map.put(l,map1.get(l));
        }
        return (findVertex==null?null:map);
    }

    public List<Vertex> getVertices() {
        return vertices;
    }
}
