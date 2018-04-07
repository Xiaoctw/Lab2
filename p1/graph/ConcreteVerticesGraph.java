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
public class ConcreteVerticesGraph implements Graph<String> {

    private final List<Vertex> vertices = new ArrayList<>();//存储结点信息

    // Abstraction function:
    //   TODO
    // Representation invariant:
    //   TODO
    // Safety from rep exposure:
    //   TODO

    // TODO constructor

    // TODO checkRep

    /**
     * Add a vertex to this graph.
     *
     * @param vertex label for the new vertex
     * @return true if this graph did not already include a vertex with the
     * given label; otherwise false (and this graph is not modified（修改）)
     */
    @Override
    public boolean add(String vertex) {
        for (Vertex vertice:vertices) {
            if (vertice.getData().equals(vertex)){
                return false;
            }
        }
        Vertex vertex1=new Vertex(vertex);
        vertices.add(vertex1);
        return true;
    }

    @Override
    public int set(String source, String target, int weight) {
        String delete = null;
        if (weight == 0) {
            for (Vertex vertex :
                    vertices) {
                if (vertex.getData() .equals(source)) {
                    delete=source;
                    vertices.remove(vertex);//必须通过遍历的方式才能找到对应的结点
                    break;
                }
            }
            for (Vertex vertex: vertices) {
                vertex.deleteVertex(delete);//如果找到了要删除的顶点，与之临界的边也要删掉与之的临接
            }
            return 0;
        }
        int result=0;
        for (Vertex vertex : vertices
                ) {
            if (vertex.getData() .equals(source) ) {
//                result=vertex.getWeight(target);
               vertex.addVertex(target, weight);//这一步自动有判断功能，如果存在临界，那么改变权值，不存在则加上。
            }
        }
//        Vertex vertex = new Vertex(source);
//        vertex.addVertex(target, weight);
//        vertices.add(vertex);
      return result;
        // throw new RuntimeException("not implemented");
    }

    @Override
    public boolean remove(String vertex) {
        Boolean result=false;
        for (Vertex vertex1:vertices
             ) {
            if (vertex1.getData().equals(vertex)){
                vertices.remove(vertex1);
                result=true;
            }
        }
        for (Vertex vertex1:vertices
             ) {
            vertex1.deleteVertex(vertex);
        }
        return result;
        //throw new RuntimeException("not implemented");
    }

    @Override
    public Set<String> vertices() {
        Set<String> strings=new HashSet<String>();
        for (Vertex vertex:vertices
             ) {
            strings.add(vertex.getData());
        }
        return strings;
       // throw new RuntimeException("not implemented");
    }
    @Override
    public Map<String, Integer> sources(String target) {
        Map<String,Integer> stringIntegerMap=new LinkedHashMap<>();
        for (Vertex vertex:vertices
             ) {
            if (vertex.hasAdioin(target)&&!vertex.getData().equals(target)){
                stringIntegerMap.put(vertex.getData(),vertex.getWeight(target));
            }
        }
       // throw new RuntimeException("not implemented");
        return stringIntegerMap;
    }
    //这个函数暂时写成一个拷贝的函数
    @Override
    public Map<String, Integer> targets(String source) {
        Vertex findvertex=null;
        for (Vertex vertex:vertices
             ) {
            if (vertex.getData().equals(source)){
                findvertex=vertex;
            }
        }
        Map<String,Integer> stringIntegerMap=new LinkedHashMap<String, Integer>();
        Map<String,Integer> stringIntegerMap1=findvertex.getVertexsLinked();
        for (String string :stringIntegerMap1.keySet()
             ) {
            stringIntegerMap.put(string,stringIntegerMap1.get(string));
        }
        return (findvertex==null?null:stringIntegerMap);
     //   throw new RuntimeException("not implemented");
    }

    // TODO toString()

    @Override
    public String toString() {
        String result="包含顶点:";
        StringBuilder sb=new StringBuilder();
        for (Vertex ve:vertices
             ) {
            sb.append(ve.getData()+",");
        }
        result+=sb.toString()+" ";
        result+="\n";
        StringBuilder sb1=new StringBuilder();
        for (Vertex ve:vertices
             ) {
            HashMap<String,Integer> map=ve.getVertexsLinked();
            if (map.isEmpty()){
                continue;
            }
            sb1.append("顶点"+ve.getData()+":"+map.toString()+" ");
        }
        return result+sb1.toString();
    }

}

/**
 * TODO specification
 * Mutable.
 * This class is internal to the rep of ConcreteVerticesGraph.
 * <p>
 * <p>PS2 instructions: the specification and implementation of this class is
 * up to you.
 */
class Vertex {

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
    private String data;
    private Map<String, Integer> vertexsLinked = new LinkedHashMap<>();//保存以这个节点为起点的相邻的顶点。

    public Vertex(String data) {//其中的data为字符串数据
        this.data = data;
    }

    public Vertex() {
        this.data = null;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
    /*
    如果存在临界结点，那么更新权值，没有的话添加进入
     */
    public void addVertex(String vertex, int weight) {
        if (vertexsLinked.containsKey(vertex)) {
            //   vertexs.get(vertex)=weight;
            vertexsLinked.replace(vertex, weight);//更改
        }
        vertexsLinked.put(vertex, weight);
        return;
    }
    /*
    如果这个定点给入的定点领接，返回true，反之false
     */
    public boolean hasAdioin(String vertex){
        if (vertexsLinked.containsKey(vertex)){
            return true;
        }
        return false;
    }
    public void deleteVertex(String vertex) {
        // vertexs.remove(vertex);
//        try {
//            vertexsLinked.remove(vertex);
//        } catch (Exception e) {
//            //打印错误信息
//            System.out.println(e.toString());
//        }
        if(vertexsLinked.containsKey(vertex)){
            vertexsLinked.remove(vertex);
        }
        return;
    }
    public int getWeight(String target){
        int weight=0;
        if(vertexsLinked.containsKey(target)){
            weight=vertexsLinked.get(target);
        }
        return weight;
    }

    public LinkedHashMap<String, Integer> getVertexsLinked() {
       return (LinkedHashMap<String, Integer>) vertexsLinked;
    }
}
