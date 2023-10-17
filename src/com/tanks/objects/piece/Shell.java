package com.tanks.objects.piece;

import com.tanks.objects.Direction;
import com.tanks.objects.constants.Images;
import com.tanks.objects.statics.Rotator;

public class Shell extends Piece {
    private Tank owner;
    private boolean detonate;

    public Shell(Tank owner) {
        this(owner.getX(), owner.getY(), Images.SHELL, owner.getDirection(), owner);
        detonate = false;
    }

    public Shell(int x, int y, String imgPath, Direction direction, Tank owner) {
        super(x, y, imgPath, direction);
        this.owner = owner;
        this.direction = direction;
        setDirection();
    }

    private void setDirection() {
        //correction of start position
        setX(getX() - coordinates.getXCorrector());
        setY(getY() - coordinates.getYCorrector());

        //set shell direction and shooting from muzzle
        switch (owner.getDirection()) {
            case UP -> {
                setY(getY() - owner.getCoordinates().getYCorrector());
            }
            case RIGHT -> {
                setX(getX() + owner.getCoordinates().getXCorrector());
                img = Rotator.rotateRight(img);
            }
            case DOWN -> {
                setY(getY() + owner.getCoordinates().getYCorrector());
                img = Rotator.rotate180(img);
            }
            case LEFT -> {
                setX(getX() - owner.getCoordinates().getXCorrector());
                img = Rotator.rotateLeft(img);
            }
        }
        updateCorrectors();
    }

    public Tank getOwner() {
        return owner;
    }

    public void setOwner(Tank owner) {
        this.owner = owner;
    }

    public boolean isDetonate() {
        return detonate;
    }

    public void setDetonate(boolean detonate) {
        this.detonate = detonate;
    }

    public void boom() {
        detonate = true;
    }
}
