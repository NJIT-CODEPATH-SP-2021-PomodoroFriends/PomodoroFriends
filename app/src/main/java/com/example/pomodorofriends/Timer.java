package com.example.pomodorofriends;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("Timer")
public class Timer extends ParseObject {
    private String name;
    private int lengthA;
    private int lengthB;
    private int repititions;

    public Timer(){}
    public Timer(String name, int lengthA, int lengthB, int repititions) {
        this.name = name;
        this.lengthA = lengthA;
        this.lengthB = lengthB;
        this.repititions = repititions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLengthA() {
        return lengthA;
    }

    public void setLengthA(int lengthA) {
        this.lengthA = lengthA;
    }

    public int getLengthB() {
        return lengthB;
    }

    public void setLengthB(int lengthB) {
        this.lengthB = lengthB;
    }

    public int getRepititions() {
        return repititions;
    }

    public void setRepititions(int repititions) {
        this.repititions = repititions;
    }
}
