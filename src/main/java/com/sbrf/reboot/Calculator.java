package com.sbrf.reboot;

public class Calculator {
    public int getAddition(int a, int b) {
        return a + b;
    }

    public int getSubtraction(int a, int b) {
        return a - b;
    }

    public int getMultiplication(int a, int b) {
        return a * b;
    }

    public int getDivision(int a, int b) {
        try {
            return a / b;
        } catch (ArithmeticException exception){
            System.err.println("Division by zero is not allowed");
            return 0;
        }
    }

    public double getSinus(double a) {
        return Math.sin(a);
    }

    public double getCosines(double a){
        return Math.cos(a);
    }

    public double getLogarithm(double a){
        return Math.log(a);
    }
}
