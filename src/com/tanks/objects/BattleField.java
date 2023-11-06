package com.tanks.objects;

import com.tanks.objects.constants.Const;
import com.tanks.objects.constants.Resources;
import com.tanks.objects.piece.*;
import com.tanks.objects.statics.Move;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class BattleField extends JPanel implements ActionListener, KeyListener {

    private boolean gameOver;
    private final Timer timer;
    private final GameMap gameMap;
    private final Tank player1;
    private final Tank player2;

    private final Set<Integer> keysPressed;
    private final List<Tank> tanks;
    private final List<Shell> shells;

    public BattleField() {
        gameOver = false;

        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);

        keysPressed = new HashSet<>();
        gameMap = new GameMap();
        //gameMap.saveGameMap(Resources.LVL_ROOT + "1.lvl");
        gameMap.loadGameMap(Resources.LVL_ROOT + "1.lvl");
        shells = new LinkedList<>();
        tanks = new LinkedList<>();

        player1 = gameMap.getPlayer1();
        player2 = gameMap.getPlayer2();
        tanks.add(player1);
        tanks.add(player2);

        timer = new Timer(8, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        //background
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, Const.FIELD_WIDTH, Const.FIELD_HEIGHT);

        //map
        gameMap.paint(g);

        //tanks
        for (Tank t : tanks) {
            if (t.getLives() > 0) {
                t.paint(g);
            }
        }

        //shoots
        for (Shell sh : shells) {
            sh.paint(g);
        }
    }

    private void detonator(Shell shell, Piece piece) {
        shells.remove(shell);

        if (piece instanceof Shell) {
            shells.remove(piece);
        } else if (piece instanceof Brick b) {
            if (!b.isSolid()) {
                if (piece instanceof Eagle) {
                    gameOver = true;
                }
                gameMap.removeBrick(b);
            }
        } else if (piece instanceof Tank t) {
            t.setLives(t.getLives() - 1);

            if (t.getLives() <= 0) {
                if (t == player1 || t == player2) {
                    gameOver = true;
                }
                tanks.remove(t);
            }
        }
    }

    private void movePiece(Piece piece) {
        Move.straight(piece);
        if (intersects(piece)) {
            Move.back(piece);
        }
        if (piece instanceof Shell) {
            int heightCorr = piece.getCoordinates().getYCorrector();
            int widthCorr = piece.getCoordinates().getXCorrector();
            if (piece.getX() <= widthCorr || piece.getX() >= Const.FIELD_WIDTH - widthCorr ||
                    piece.getY() <= heightCorr || piece.getY() >= Const.FIELD_HEIGHT - heightCorr) {
                shells.remove(piece);
            }
        }
    }

    private boolean intersects(Piece piece) {

        // intersections of any bricks
        for (Brick brick : gameMap.getBricks()) {
            if (piece.intersects(brick)) {
                if (piece instanceof Shell sh) {
                    detonator(sh, brick);
                }
                return true;
            }
        }
        // intersections of any tanks
        for (Tank tank : tanks) {
            if (tank != piece) {
                if (piece.intersects(tank)) {
                    if (piece instanceof Shell sh) {
                        if (sh.getOwner() != tank) {
                            detonator(sh, tank);
                        } else {
                            return false;
                        }
                    }
                    return true;
                }
            }
        }
        // intersections of any shells
        for (Shell sh : shells) {
            if (piece.intersects(sh)) {
                if (sh.getOwner() != piece) {
                    detonator(sh, piece);
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    private void updateMoves() {
        //player 1 controls
        if (player1.getLives() > 0) {
            if (keysPressed.contains(KeyEvent.VK_UP) && !player1.turn(Direction.UP)) {
                movePiece(player1);
            }
            if (keysPressed.contains(KeyEvent.VK_RIGHT) && !player1.turn(Direction.RIGHT)) {
                movePiece(player1);
            }
            if (keysPressed.contains(KeyEvent.VK_DOWN) && !player1.turn(Direction.DOWN)) {
                movePiece(player1);
            }
            if (keysPressed.contains(KeyEvent.VK_LEFT) && !player1.turn(Direction.LEFT)) {
                movePiece(player1);
            }
            if (keysPressed.contains(KeyEvent.VK_SPACE)) {
                shotHim(player1);
            }
        }

        //player 2 controls
        if (player2.getLives() > 0) {
            if (keysPressed.contains(KeyEvent.VK_W) && !player2.turn(Direction.UP)) {
                movePiece(player2);
            }
            if (keysPressed.contains(KeyEvent.VK_D) && !player2.turn(Direction.RIGHT)) {
                movePiece(player2);
            }
            if (keysPressed.contains(KeyEvent.VK_S) && !player2.turn(Direction.DOWN)) {
                movePiece(player2);
            }
            if (keysPressed.contains(KeyEvent.VK_A) && !player2.turn(Direction.LEFT)) {
                movePiece(player2);
            }
            if (keysPressed.contains(KeyEvent.VK_SHIFT)) {
                shotHim(player2);
            }
        }

        repaint();
    }

    private void shotHim(Tank t) {
        if (t.CanShot() && !gameOver) {
            shells.add(new Shell(player2));
            t.setCanShot(false);
            t.startCooldown();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
            //update shells positions
            for (int i = 0; i < shells.size(); i++) {
                movePiece(shells.get(i));
            }
            //updating players positions
            updateMoves();

            repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keysPressed.add(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keysPressed.remove(e.getKeyCode());
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

}
