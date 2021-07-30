package com.company;

import java.awt.*;

public class Cell {

    private final Point location;
    private int condition;
    private boolean closed;
    private boolean selected;

    public static final int EMPTY = 9, WITH_BOMB = 10, WITH_NUMBER = 11, SELECTED = 12, BORDERS_WITH_0 = 0, BORDERS_WITH_1 = 1, BORDERS_WITH_2 = 2, BORDERS_WITH_3 = 3, BORDERS_WITH_4 = 4, BORDERS_WITH_5 = 5, BORDERS_WITH_6 = 6, BORDERS_WITH_7 = 7, BORDERS_WITH_8 = 8;

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public void setCondition(int condition) {
        this.condition = condition;
    }

    public int getCondition() {
        return condition;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public Cell(Point location){
        this.location = location;
        selected = false;
        closed = true;
        condition = EMPTY;
    }

    public Point getLocation() {
        return location;
    }

    public boolean thiscell(Point x){
        return x.x > location.x && x.x < location.x + 30 && x.y > location.y && x.y < location.y + 30;
    }
}
