package com.company;

public class Edge<L> {
    private L source;
    private L target;
    private int weight;

    public Edge(L source, L target, int weight) {
        this.source = source;
        this.target = target;
        this.weight = weight;
    }
    public Edge(L source,L target){
        this(source,target,1);
    }
    public Edge(){
        this(null,null,1);
    }
    public L getSource() {
        return source;
    }

    public void setSource(L source) {
        this.source = source;
    }

    public L getTarget() {
        return target;
    }

    public void setTarget(L target) {
        this.target = target;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
