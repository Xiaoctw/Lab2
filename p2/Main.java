package com.company;

public class Main {

    public static void main(String[] args) {
        FriendShipEdgesGraph graph1=new FriendShipEdgesGraph();
        Person xiao=new Person("xiao");
        Person li=new Person("li");
        Person tom=new Person("tom");
        Person han=new Person("han");
        Person zhang=new Person("zhang");
        Person yang=new Person("yang");
        Person tim=new Person("tim");
        graph1.add(xiao);
        graph1.add(li);
        graph1.add(tom);
        graph1.add(han);
        graph1.add(zhang);
        graph1.add(yang);
        graph1.add(tim);
        graph1.set(xiao,li,1);
        graph1.set(li,tom,2);
        graph1.set(tom,han,3);
        graph1.set(xiao,tom,2);
        graph1.set(zhang,han,2);
        graph1.set(tim,yang,3);
        graph1.set(yang,zhang,2);
        graph1.set(han,tim,3);
        System.out.println(graph1.toString());
        return;
    }
}
