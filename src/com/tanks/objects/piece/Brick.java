package com.tanks.objects.piece;

import com.tanks.objects.Direction;

public class Brick extends Piece {
    private boolean isSolid;

    public Brick(int x, int y, String imgPath, Direction direction, boolean isSolid) {
        super(x, y, imgPath, direction);
        this.isSolid = isSolid;
    }

    public boolean isSolid() {
        return isSolid;
    }

    public void setSolid(boolean solid) {
        isSolid = solid;
    }
}
