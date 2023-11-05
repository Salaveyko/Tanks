package com.tanks.objects.piece;

import com.tanks.objects.Coordinates;
import com.tanks.objects.Direction;
import com.tanks.objects.constants.Const;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.*;

public abstract class Piece  implements Serializable {
    protected static final long serialVersionUID = 1L;
    protected Coordinates coordinates;
    protected transient Image img;
    protected Direction direction;

    public Piece(int x, int y, String imgPath, Direction direction) {

        int widthCorrect = 0;
        int heightCorrect = 0;

        if (imgPath != null && !imgPath.isEmpty()) {
            this.img = new ImageIcon(imgPath).getImage();

            //create width and height variables for correction position of piece
            widthCorrect = img.getWidth(null) / 2;
            heightCorrect = img.getHeight(null) / 2;

            //correcting start piece position
            x += widthCorrect;
            y += heightCorrect;
        }

        this.coordinates = new Coordinates(x, y, widthCorrect, heightCorrect);

        this.direction = direction;
    }

    protected void updateCorrectors() {
        coordinates.setXCorrector(img.getWidth(null) / 2);
        coordinates.setYCorrector(img.getHeight(null) / 2);
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public int getX() {
        return coordinates.getX();
    }

    public void setX(int x) {
        this.coordinates.setX(x);
    }

    public int getY() {
        return coordinates.getY();
    }

    public void setY(int y) {
        this.coordinates.setY(y);
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public boolean intersects(Piece piece) {
        if(piece != this) {
            Rectangle thisRect = new Rectangle(
                    getX() - getCoordinates().getXCorrector(),
                    getY() - getCoordinates().getYCorrector(),
                    img.getWidth(null),
                    img.getHeight(null));

            Rectangle someRect = new Rectangle(
                    piece.getX() - piece.getCoordinates().getXCorrector(),
                    piece.getY() - piece.getCoordinates().getYCorrector(),
                    piece.img.getWidth(null),
                    piece.img.getHeight(null));

            return thisRect.intersects(someRect);
        }
        return false;
    }

    public void paint(Graphics g) {
        correctPosition();

        //position object center
        if (img != null) {
            int imageWidth = img.getWidth(null);
            int imageHeight = img.getHeight(null);

            // Calculate the position for drawing the image with its center
            int drawX = getX() - (imageWidth / 2);
            int drawY = getY() - (imageHeight / 2);

            AffineTransform tx = AffineTransform.getTranslateInstance(drawX, drawY);

            // Draw the image with the transformed coordinates
            Graphics2D g2d = (Graphics2D) g;
            g2d.drawImage(img, tx, null);
        }
    }

    private void correctPosition(){
        //positioning the object inside field border
        int xCorr = getCoordinates().getXCorrector();
        int yCorr= getCoordinates().getYCorrector();
        if(getY() - yCorr < 0){
            setY(yCorr);
        }
        if(getX() + xCorr > Const.FIELD_WIDTH){
            setX(Const.FIELD_WIDTH - xCorr);
        }
        if(getY() + yCorr > Const.FIELD_HEIGHT){
            setY(Const.FIELD_HEIGHT - yCorr);
        }
        if(getX() - xCorr < 0){
            setX(xCorr);
        }
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        if(img != null){
            BufferedImage bufImg = new BufferedImage(
                    img.getWidth(null),
                    img.getHeight(null),
                    BufferedImage.TYPE_INT_ARGB
            );
            Graphics2D g2d = bufImg.createGraphics();
            g2d.drawImage(img, 0, 0, null);
            g2d.dispose();
            ImageIO.write(bufImg, "png", out);
        }
    }
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();

        img = ImageIO.read(in);
    }
}
