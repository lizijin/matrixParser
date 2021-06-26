package com.peter.matrix.parser;

public class Demo {
    void demo(Source<String> strs) {
        Source<? extends Object> objects = strs; // !!! Not allowed in Java

        Source<Integer> integerSource = null;
        objects = integerSource;
        // ...
    }
}
