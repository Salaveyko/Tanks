package com.tanks.main;

import com.tanks.objects.BattleField;
import com.tanks.objects.constants.Const;

import javax.swing.*;

public class TanksGame extends JFrame {

    public TanksGame() {
        startGame();
    }

    private void startGame(){
        setTitle("Tanks");
        setBounds(100, 50, Const.FIELD_WIDTH, Const.FIELD_HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setUndecorated(true);

        add(new BattleField());

        setVisible(true);
    }
}
