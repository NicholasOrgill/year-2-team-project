package engine;

import java.awt.Graphics;

public class Sprite {

    private int x = 0;
    private int y = 0;
    private int width = 0;
    private int height = 0;
    private boolean finished;

    private int screenWidth;
    private int screenHeight;

    public Sprite(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    public Sprite(int x, int y) {
    	this.x = x;
    	this.y = y;
    }

    public void setScreenSize(int d, int e) {
        this.screenWidth = d;
        this.screenHeight = e;
    }

    public int getScreenWidth() {
        return this.screenWidth;
    }

    public int getScreenHeight() {
        return this.screenHeight;
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

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void update() {

    }

    public void draw(Graphics context) {

    }

    public boolean isFinished() {
        return finished;
    }

    public void finish() {
        this.finished = false;
    }


}
