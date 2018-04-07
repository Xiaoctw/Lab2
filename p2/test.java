package com.company;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.List;

public class test {
    FriendShipEdgesGraph graph=new FriendShipEdgesGraph();
    FriendShipVerticesGraph graph1=new FriendShipVerticesGraph();
    @Test
    public void test1(){
        Person xiao=new Person("xiao");
        Person li=new Person("li");
        Person tom=new Person("tom");
        Person han=new Person("han");
        Person zhang=new Person("zhang");
        Person yang=new Person("yang");
        Person tim=new Person("tim");
        graph.add(xiao);
        graph.add(li);
        graph.add(tom);
        graph.add(zhang);
        graph.add(yang);
        graph.add(tim);
        graph.add(han);
        graph.set(xiao,li,1);
        graph.set(li,tom,2);
        graph.set(tom,han,3);
        graph.set(xiao,tom,2);
        graph.set(zhang,han,2);
        graph.set(tim,yang,3);
        graph.set(yang,zhang,2);
        graph.set(han,tim,3);
        System.out.println(graph.toString());
     //   Assert.assertEquals(graph.toString(),"包含人：xiao,li,yang,tim,tom,han,zhang, \n包含边：xiao->li 权值:1 ,li->tom 权值:2 ,tom->han 权值:3 ,xiao->tom 权值:2 ,zhang->han 权值:2 ,tim->yang 权值:3 ,yang->zhang 权值:2 ,han->tim 权值:3 ,");
        List<Person> personList= graph.depthFirstSearch(xiao);
     //   Assert.assertEquals("[xiao, tom, han, tim, yang, zhang, li]",personList.toString());
        int Way=graph.findWay(xiao,han);
        System.out.println(Way);
    }
    @Test
    public void test2(){
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
     //   System.out.println(graph1.toString());
        Assert.assertEquals("包含顶点：xiao,li,tom,han,zhang,yang,tim, \n顶点xiao:{li=1, tom=2} 顶点li:{tom=2} 顶点tom:{han=3} 顶点han:{tim=3} 顶点zhang:{han=2} 顶点yang:{zhang=2} 顶点tim:{yang=3} ",graph1.toString());
    }
}
