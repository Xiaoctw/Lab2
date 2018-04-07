package com.company;

import java.util.HashMap;

public class FriendShipVerticesGraph extends ConcreteVerticesGraph<Person> {
    @Override
    public String toString() {
        String result="包含顶点：";
        StringBuilder sb=new StringBuilder();
        for (Vertex vertex:getVertices()
             ) {
            sb.append(vertex.getData().toString()+",");
        }
        result+=sb.toString()+" ";
        result+="\n";
        StringBuilder sb1=new StringBuilder();
        for (Vertex vertex:getVertices()
             ) {
            HashMap<Person,Integer> map= (HashMap<Person, Integer>) vertex.getVertexLinked();
            if (map.isEmpty()){
                continue;
            }
            sb1.append("顶点"+vertex.getData()+":"+map.toString()+" ");
        }
        return result+sb1.toString();
    }
}
