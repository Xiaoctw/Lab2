package com.company;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Vertex<L> {
    private L data;
    private Map<L,Integer> vertexLinked=new LinkedHashMap<>();

    public Vertex(L data) {
        this.data = data;
    }
    public Vertex(){
        this.data=null;
    }

    public L getData() {
        return data;
    }

    public void setData(L data) {
        this.data = data;
    }
     /*
    如果存在临界结点，那么更新权值，没有的话添加进入
     */
    public void addVertex(L vertex,int weight){
        if (vertexLinked.containsKey(vertex)){
            vertexLinked.replace(vertex,weight);
        }
        vertexLinked.put(vertex,weight);
        return;
    }
    /*
    如果这个定点给入的定点领接，返回true，反之false
     */
    public boolean hasAdioin(L vertex){
        if (vertexLinked.containsKey(vertex)){
            return true;
        }
        return false;
    }
    public void deleteVertex(L vertex){
        if (vertexLinked.containsKey(vertex)){
            vertexLinked.remove(vertex);
        }
        return;
    }
    /*
    如果存在对应节点，那么得到权值，如果没有，返回0
     */
    public int getWeight(L target){
        int weight=0;
        if (vertexLinked.containsKey(target)){
            weight=vertexLinked.get(target);
        }
        return weight;
    }
    public Map<L, Integer> getVertexLinked() {
        return vertexLinked;
    }
}
