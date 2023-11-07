package com.tanks.panels;

import com.tanks.constants.Const;
import com.tanks.constants.Resources;

import javax.swing.*;
import java.awt.*;

public class InfoPanel extends JPanel {

    private final BattleField battleField;

    public InfoPanel(BattleField battleField){
        setPreferredSize(new Dimension(Const.INFO_PANEL_WIDTH, Const.INFO_PANEL_HEIGHT));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.battleField = battleField;

        setLivesPanel();
    }

    private void setLivesPanel(){
        PlayerPanel p1Lives = new PlayerPanel(Resources.GOLD_TANK_IMG);
        battleField.getPlayer1().addAmAliveListener(p1Lives);

        PlayerPanel p2Lives = new PlayerPanel(Resources.SILVER_TANK_IMG);
        battleField.getPlayer2().addAmAliveListener(p2Lives);

        JPanel livesPanel = new JPanel(){
            @Override
            public void paintComponent(Graphics g){
                super.paintComponent(g);

                g.setColor(Const.INFO_PANEL_BACKGROUND);
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        livesPanel.setMaximumSize(new Dimension(Const.INFO_PANEL_WIDTH, 120));
        livesPanel.setLayout(new BoxLayout(livesPanel, BoxLayout.Y_AXIS));
        livesPanel.add(p1Lives);
        livesPanel.add(p2Lives);

        add(livesPanel);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        g.setColor(Const.INFO_PANEL_BACKGROUND);
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}
