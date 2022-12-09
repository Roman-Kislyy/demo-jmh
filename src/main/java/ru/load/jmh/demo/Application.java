package ru.load.jmh.demo;


import ru.load.jmh.demo.jackson.JacksonExample;

public class Application {

    public final static void main(String[] args) {
        System.out.println("Hello!");
        JacksonExample jacksonExample = new JacksonExample();
        jacksonExample.getUser();
    }
}