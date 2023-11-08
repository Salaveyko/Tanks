package com.tanks.panels;

import com.tanks.constants.Const;

import javax.swing.*;
import java.awt.*;

public class CustomPanel extends JPanel {
    public CustomPanel(LayoutManager layout, boolean isDoubleBuffered) {
        super(layout, isDoubleBuffered);
    }

    public CustomPanel(LayoutManager layout) {
        super(layout);
    }

    public CustomPanel(boolean isDoubleBuffered) {
        super(isDoubleBuffered);
    }

    public CustomPanel() {
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Const.INFO_PANEL_BACKGROUND);
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}
