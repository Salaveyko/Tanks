package com.tanks.objects;

import com.tanks.objects.piece.Brick;
import com.tanks.objects.piece.Tank;

import java.awt.*;
import java.io.*;
import java.util.List;

public class GameMap implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private List<Brick> bricks;
    private Tank player1;
    private Tank player2;

    public GameMap() {}


    public void loadGameMap(String filePath) {
        GameMap gm;
        try (ObjectInputStream oIn = new ObjectInputStream(
                new FileInputStream(filePath)
        )) {

            gm = (GameMap) oIn.readObject();
            this.bricks = gm.bricks;
            this.player1 = gm.player1;
            this.player2 = gm.player2;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveGameMap(String filePath) {
        try (ObjectOutputStream oOut = new ObjectOutputStream(
                new FileOutputStream(filePath))
        ) {

            oOut.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Brick> getBricks() {
        return bricks;
    }

    public boolean removeBrick(Brick brick) {
        return bricks.remove(brick);
    }

    public void setPlayer1(Tank player1) {
        this.player1 = player1;
    }

    public void setPlayer2(Tank player2) {
        this.player2 = player2;
    }

    public Tank getPlayer1() {
        return player1;
    }

    public Tank getPlayer2() {
        return player2;
    }

    public void paint(Graphics g) {
        for (Brick br : bricks) {
            br.paint(g);
        }
    }
}
