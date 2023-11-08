package com.tanks.panels;

import com.tanks.constants.Const;
import com.tanks.constants.Resources;
import com.tanks.events.AmAliveEvent;
import com.tanks.events.AmAliveListener;
import com.tanks.objects.piece.Tank;

import javax.swing.*;
import java.awt.*;

public class PlayerPanel extends CustomPanel implements AmAliveListener {
    private final JPanel heartsPanel;

    public PlayerPanel(String tankIcon) {
        setLayout(new FlowLayout(FlowLayout.LEFT, 5, 15));

        JLabel tankIconLabel = new JLabel(scaledIcon(tankIcon));
        add(tankIconLabel);

        heartsPanel = new JPanel(){
            @Override
            public void paintComponent(Graphics g){
                super.paintComponent(g);

                g.setColor(Const.INFO_PANEL_BACKGROUND);
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        add(heartsPanel);
    }

    private Icon scaledIcon(String path){
        ImageIcon originalIcon = new ImageIcon(path);
        Image scaledImage = originalIcon.getImage().getScaledInstance(
                Const.ICON_SIZE, Const.ICON_SIZE, Image.SCALE_DEFAULT
        );

        return new ImageIcon(scaledImage);
    }

    public void updateLives(int lives) {
        heartsPanel.removeAll();

        for (int i = 0; i < lives; i++) {
            heartsPanel.add(new JLabel(new ImageIcon(Resources.HEART)));
        }

        revalidate();
        repaint();
    }

    @Override
    public void amAlive(AmAliveEvent e) {
        if(e.getSource() instanceof Tank){
            updateLives(((Tank)e.getSource()).getLives());
        }
    }
}
