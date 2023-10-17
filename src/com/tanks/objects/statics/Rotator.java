package com.tanks.objects.statics;

import com.tanks.objects.Direction;
import com.tanks.objects.piece.Piece;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Rotator {
    public static Image rotate180(Image img) {
        int width = img.getWidth(null);
        int height = img.getHeight(null);

        BufferedImage rotatedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = rotatedImage.createGraphics();

        AffineTransform at = AffineTransform.getRotateInstance(
                Math.PI,
                width / 2.0,
                height / 2.0);
        g2d.setTransform(at);

        g2d.drawImage(img, 0, 0, null);
        g2d.dispose();

        return rotatedImage;
    }

    public static Image rotateRight(Image img) {
        int width = img.getWidth(null);
        int height = img.getHeight(null);

        BufferedImage rotatedImage = new BufferedImage(height, width, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = rotatedImage.createGraphics();

        AffineTransform at = AffineTransform.getQuadrantRotateInstance(1);
        g2d.setTransform(at);

        g2d.drawImage(img, 0, -height, null);
        g2d.dispose();

        return rotatedImage;
    }

    public static Image rotateLeft(Image img) {
        int width = img.getWidth(null);
        int height = img.getHeight(null);

        BufferedImage rotatedImage = new BufferedImage(height, width, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = rotatedImage.createGraphics();

        AffineTransform at = AffineTransform.getQuadrantRotateInstance(-1);
        g2d.setTransform(at);

        g2d.drawImage(img, -width, 0, null);
        g2d.dispose();

        return rotatedImage;
    }
}
