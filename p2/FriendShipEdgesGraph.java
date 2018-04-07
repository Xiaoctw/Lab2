package com.company;

import java.util.*;

public class FriendShipEdgesGraph extends ConcreEdgesGraph<Person> {
    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder();
        for (Person person:vertices()
             ) {
            sb.append(person.toString()+",");
        }
        String result="包含人：";
        result+=sb.toString();
        result+=" \n包含边：";
        StringBuilder sb1=new StringBuilder();
        for (Edge edge:getEdges()) {
            sb1.append(edge.getSource()+"->"+edge.getTarget()+" 权值:"+edge.getWeight()+" ,");
        }
        result+=sb1.toString();
        return result;
    }
    public List<Person> depthFirstSearch(Person person){
        Set<Person> set=getVertices();
        List<Edge> list=getEdges();
        int numberOfPersons=set.size();
        Map<Person,Boolean> isvisited=new HashMap<Person,Boolean>();
        for (Person p:set
             ) {
            isvisited.put(p,false);
        }
        if (!set.contains(person)){
            System.out.println("还没有添加这个人");
            return null;
        }
        ArrayList<Person> persons=new ArrayList<>();//保存最终结果
        Stack<Person> personStack=new Stack<>();
        personStack.push(person);
        isvisited.replace(person,true);
        while (!personStack.empty()){
            Person person1=personStack.pop();
            persons.add(person1);
         //   isvisited.replace(person1,true);
            for (Edge edge:list
                 ) {
                if (edge.getSource().equals(person1)){
                    if(!isvisited.get(edge.getTarget())) {
                        personStack.push((Person) edge.getTarget());
                        isvisited.replace((Person) edge.getTarget(), true);
                    }
                }
            }
        }
       return persons;
    }
    public int findWay(Person p1,Person p2){
        List<Person> list=new ArrayList<Person>();
        Set<Person> personSet=getVertices();//得到全体人员构成的集合
        int numberOfPersons=personSet.size();
        List<Edge> edges=getEdges();// 得到全体边组成的list
        if (!personSet.contains(p1)||!personSet.contains(p2)){
            System.out.println("有人未加入图中");
            return -1;
        }
        Map<Person,Boolean> personBooleanMap=new HashMap<>();
        Map<Person,Integer> personIntegerMap=new HashMap<>();
        for (Person person:personSet
             ) {
            personBooleanMap.put(person,false);
            personIntegerMap.put(person,Integer.MAX_VALUE);
        }
        for (Edge edge :edges
                ) {
            if (edge.getSource().equals(p1)){
              //  personIntegerMap[edge.getTarget()]=edge.getWeight();
                personIntegerMap.replace((Person) edge.getTarget(),edge.getWeight());
            }
        }
        personIntegerMap.replace(p1,0);
        personBooleanMap.replace(p1,true);
        for (int i = 0; i <numberOfPersons ; i++) {
            int min=Integer.MAX_VALUE;
            Person person=null;
            for (Person person1:personSet//对所有人进行遍历，不是对所有边进行遍历
                 ) {
               if (!personBooleanMap.get(person1)){
                   if (personIntegerMap.get(person1)<min){
                       person=person1;
                       min=personIntegerMap.get(person1);
                   }
               }
            }
            if (person==null){
                personBooleanMap.replace(null,true);
            }
            for (Person per:personSet
                 ) {
                if (!personBooleanMap.get(per)){
                    for (Edge edge:edges
                         ) {
                        if (edge.getSource().equals(person)&&edge.getTarget().equals(per)){
                            if (min+edge.getWeight()<personIntegerMap.get(per)){
                                personIntegerMap.replace(per,min+edge.getWeight());
                                break;
                            }
                        }
                    }
                }
            }
        }
        if (personIntegerMap.get(p2)<10000){
            return personIntegerMap.get(p2);
        }else {
            return -1;
        }
    }
}
