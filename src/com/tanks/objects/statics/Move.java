package com.tanks.objects.statics;

import com.tanks.objects.constants.Const;
import com.tanks.objects.piece.Piece;
import com.tanks.objects.piece.Shell;

public class Move {
    public static void straight(Piece piece) {
        int heightCorrect = piece.getCoordinates().getXCorrector();
        int widthCorrect = piece.getCoordinates().getYCorrector();
        int speed = Const.SPEED;

        if (piece instanceof Shell){
            speed *= 3;
        }

        switch (piece.getDirection()) {
            case UP -> piece.setY(Math.max(piece.getY() - speed, heightCorrect));
            case RIGHT -> piece.setX(Math.min(piece.getX() + speed, Const.FIELD_WIDTH - widthCorrect));
            case DOWN -> piece.setY(Math.min(piece.getY() + speed, Const.FIELD_HEIGHT - heightCorrect));
            case LEFT -> piece.setX(Math.max(piece.getX() - speed, widthCorrect));
        }
    }

    public static void back(Piece piece) {
        switch (piece.getDirection()) {
            case UP -> piece.setY(piece.getY() + Const.SPEED);
            case RIGHT -> piece.setX(piece.getX() - Const.SPEED);
            case DOWN -> piece.setY(piece.getY() - Const.SPEED);
            case LEFT -> piece.setX(piece.getX() + Const.SPEED);
        }
    }
}
