package com.tanks.objects.piece;

import com.tanks.objects.Direction;

public class Eagle extends Brick{
    private Tank owner;

    public Eagle(int x, int y, String imgPath, Direction direction, Tank owner) {
        super(x, y, imgPath, direction, false);

        this.owner = owner;
    }

    public Tank getOwner() {
        return owner;
    }

    public void setOwner(Tank owner) {
        this.owner = owner;
    }
}
