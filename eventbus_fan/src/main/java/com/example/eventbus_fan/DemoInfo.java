package com.example.eventbus_fan;

public class DemoInfo {
    private int a;
    private int b;
    private String name;
    private int age;

    public DemoInfo(int a, int b, String name, int age) {
        this.a = a;
        this.b = b;
        this.name = name;
        this.age = age;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "DemoInfo{" +
                "a=" + a +
                ", b=" + b +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public void add(int a,int b){
        System.out.println(a+b);
    }

    public void item(String name){
        System.out.println(name);
    }

}
