package com.tanks.main;

import com.tanks.constants.Const;
import com.tanks.panels.BattleField;
import com.tanks.panels.InfoPanel;

import javax.swing.*;

public class TanksGame extends JFrame {

    public TanksGame() {
        startGame();
    }

    private void startGame(){
        setTitle("Tanks");
        setBounds(0, 50, Const.FIELD_WIDTH + Const.INFO_PANEL_WIDTH, Const.FIELD_HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setUndecorated(true);

        BattleField battleField = new BattleField();
        InfoPanel infoPanel = new InfoPanel(battleField);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, battleField, infoPanel);
        splitPane.setDividerLocation(Const.FIELD_WIDTH);
        splitPane.setDividerSize(0);
        splitPane.setBorder(null);

        add(splitPane);

        setVisible(true);
    }
}
