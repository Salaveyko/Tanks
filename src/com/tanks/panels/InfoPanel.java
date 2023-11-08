package com.tanks.panels;

import com.tanks.constants.Const;
import com.tanks.constants.Resources;

import javax.swing.*;
import java.awt.*;

public class InfoPanel extends CustomPanel {

    private final BattleField battleField;

    public InfoPanel(BattleField battleField){
        setPreferredSize(new Dimension(Const.INFO_PANEL_WIDTH, Const.INFO_PANEL_HEIGHT));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.battleField = battleField;

        setNamePanel();
        setLivesPanel();
    }

    private void setNamePanel(){
        JLabel mapNameLabel = new JLabel(battleField.getMapName());
        mapNameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        mapNameLabel.setForeground(Color.WHITE);

        JPanel mapNamePanel = new CustomPanel(new FlowLayout(FlowLayout.CENTER, 0, 10));
        mapNamePanel.setMaximumSize(new Dimension(Const.INFO_PANEL_WIDTH, 50));
        mapNamePanel.add(mapNameLabel);

        add(mapNamePanel);
    }

    private void setLivesPanel(){
        PlayerPanel p1Lives = new PlayerPanel(Resources.GOLD_TANK_IMG);
        battleField.getPlayer1().addAmAliveListener(p1Lives);

        PlayerPanel p2Lives = new PlayerPanel(Resources.SILVER_TANK_IMG);
        battleField.getPlayer2().addAmAliveListener(p2Lives);

        JPanel livesPanel = new CustomPanel();
        livesPanel.setMaximumSize(new Dimension(Const.INFO_PANEL_WIDTH, 120));
        livesPanel.setLayout(new BoxLayout(livesPanel, BoxLayout.Y_AXIS));
        livesPanel.add(p1Lives);
        livesPanel.add(p2Lives);

        add(livesPanel);
    }
}
