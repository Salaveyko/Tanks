package com.tanks.objects;

import java.io.Serial;
import java.io.Serializable;

public class Coordinates implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private int x;
    private int y;
    private int xCorrector;
    private int yCorrector;

    public Coordinates(){
        this(0, 0, 0, 0);
    }
    public Coordinates(int x, int y, int xCorrector, int yCorrector) {
        this.xCorrector = xCorrector;
        this.yCorrector = yCorrector;
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getXCorrector() {
        return xCorrector;
    }

    public void setXCorrector(int xCorrector) {
        this.xCorrector = xCorrector;
    }

    public int getYCorrector() {
        return yCorrector;
    }

    public void setYCorrector(int yCorrector) {
        this.yCorrector = yCorrector;
    }
}
