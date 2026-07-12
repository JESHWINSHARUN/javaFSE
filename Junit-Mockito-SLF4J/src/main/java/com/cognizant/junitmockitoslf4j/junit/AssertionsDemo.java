package com.cognizant.junitmockitoslf4j.junit;

public class AssertionsDemo {

    public int add(int a, int b) {
        return a + b;
    }

    public boolean isEven(int n) {
        return n % 2 == 0;
    }

    public String getName() {
        return "Cognizant";
    }

    public int[] getNumbers() {
        return new int[]{1, 2, 3, 4, 5};
    }

    public Object getObject() {
        return new Object();
    }

    public Object getNullObject() {
        return null;
    }
}