package com.peter.matrix.parser;

public class MethodItem {

    public int methodId;
    public int durTime;
    public int depth;
    public int count = 1;
    public MappingMethod mappingMethod;

    public MethodItem(MappingMethod mappingMethod, int durTime, int depth) {
        this.mappingMethod = mappingMethod;
        this.durTime = durTime;
        this.depth = depth;
    }

    @Override
    public String toString() {
        return depth + "," + methodId + "," + count + "," + durTime;
    }

    public void mergeMore(long cost) {
        count++;
        durTime += cost;
    }

    public String print() {
        StringBuffer inner = new StringBuffer();
        for (int i = 0; i < depth; i++) {
            inner.append('.');
        }
        return inner.toString() + mappingMethod + " " + durTime;
    }
}
