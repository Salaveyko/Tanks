package com.tanks.objects.piece;

import com.tanks.objects.Direction;
import com.tanks.objects.constants.Const;
import com.tanks.objects.constants.Images;
import com.tanks.objects.statics.Rotator;

public class Tank extends Piece {
    private int lives;
    private int hits;
    private boolean canShot;

    public Tank(int x, int y, String imgPath, Direction direction, int lives, int hits) {
        super(
                x, y,
                (imgPath.isEmpty() || imgPath == null) ? Images.GREEN_TANK_IMG : imgPath,
                Direction.UP);
        this.lives = lives;
        this.hits = hits;
        this.canShot = true;

        turn(direction);
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    public boolean CanShot() {
        return canShot;
    }

    public void setCanShot(boolean canShot) {
        this.canShot = canShot;
    }

    public boolean turn(Direction direction) {

        if (this.direction != direction) {
            // find rotation value.
            // the difference between opposite sides is |2|.
            // the difference between UP and LEFT sides is |3|.
            // the difference between all another sides is |1|.
            int tankDirOrdinal = this.direction.ordinal() + 1;
            int dirOrdinal = direction.ordinal() + 1;
            int rotValue = tankDirOrdinal - dirOrdinal;

            if (rotValue % 2 == 0) {
                img = Rotator.rotate180(img);
            } else {
                if (this.direction == Direction.LEFT && rotValue == 3) {
                    img = Rotator.rotateRight(img);
                } else if (this.direction == Direction.UP && rotValue == -3) {
                    img = Rotator.rotateLeft(img);
                } else {
                    if (rotValue == -1) {
                        img = Rotator.rotateRight(img);
                    } else if (rotValue == 1) {
                        img = Rotator.rotateLeft(img);
                    }
                }
            }

            this.direction = direction;
            updateCorrectors();

            return true;
        }

        return false;
    }

    public void startCooldown() {
        new Thread(() ->
        {
            try {
                Thread.sleep(Const.SHOT_COOLDOWN);
                canShot = true;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

}
