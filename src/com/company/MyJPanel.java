package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

public class MyJPanel extends JPanel {
    @Override
    public void paint(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.lightGray);
        g.fillRect(0, 0, 5, Main.playground_size.height);
        g.fillRect(Main.playground_size.width - 5, 0, 5, Main.playground_size.height);
        g.fillRect(0, 0, Main.playground_size.width, 5);
        g.fillRect(0, Main.playground_size.height - 5, Main.playground_size.width, 5);
        for (int i = 0; i < 19; i++) {
            g.fillRect(35 + i * 32, 0, 2, Main.playground_size.height);
        }
        for (int i = 0; i < 19; i++) {
            g.fillRect(0, 35 + i * 32, Main.playground_size.width, 2);
        }
        g.setColor(Color.RED);
        g.setFont(new java.awt.Font("Tahoma", 0, 26));

        for (int i = 0; i < Playground.cells.size(); i++) {
            if (!Playground.cells.get(i).isClosed()) {
                g.drawImage(new ImageIcon("opened_cell.png").getImage(), Playground.cells.get(i).getLocation().x, Playground.cells.get(i).getLocation().y, 30, 30, null);

                switch (Playground.cells.get(i).getCondition()) {
                    case Cell.BORDERS_WITH_1:
                        g.setColor(Color.BLUE);
                        g.drawString("1", Playground.cells.get(i).getLocation().x + 8, Playground.cells.get(i).getLocation().y + 24);
                        break;
                    case Cell.BORDERS_WITH_2:
                        g.setColor(new Color(0, 150, 0));
                        g.drawString("2", Playground.cells.get(i).getLocation().x + 8, Playground.cells.get(i).getLocation().y + 24);
                        break;
                    case Cell.BORDERS_WITH_3:
                        g.setColor(Color.RED);
                        g.drawString("3", Playground.cells.get(i).getLocation().x + 8, Playground.cells.get(i).getLocation().y + 24);
                        break;
                    case Cell.BORDERS_WITH_4:
                        g.setColor(Color.YELLOW);
                        g.drawString("4", Playground.cells.get(i).getLocation().x + 8, Playground.cells.get(i).getLocation().y + 24);
                        break;
                    case Cell.BORDERS_WITH_5:
                        g.setColor(Color.BLUE);
                        g.drawString("5", Playground.cells.get(i).getLocation().x + 8, Playground.cells.get(i).getLocation().y + 24);
                        break;
                    case Cell.BORDERS_WITH_6:
                        g.setColor(new Color(0, 150, 0));
                        g.drawString("6", Playground.cells.get(i).getLocation().x + 8, Playground.cells.get(i).getLocation().y + 24);
                        break;
                    case Cell.BORDERS_WITH_7:
                        g.setColor(Color.RED);
                        g.drawString("7", Playground.cells.get(i).getLocation().x + 8, Playground.cells.get(i).getLocation().y + 24);
                        break;
                    case Cell.BORDERS_WITH_8:
                        g.setColor(Color.YELLOW);
                        g.drawString("8", Playground.cells.get(i).getLocation().x + 8, Playground.cells.get(i).getLocation().y + 24);
                        break;
                    case Cell.WITH_BOMB:
                        g.drawImage(new ImageIcon("bomb.png").getImage(), Playground.cells.get(i).getLocation().x, Playground.cells.get(i).getLocation().y, 30, 30, null);
                        break;
                }






            }


            if (Playground.cells.get(i).isSelected()) g.drawImage(new ImageIcon("bomb_label.png").getImage(), Playground.cells.get(i).getLocation().x, Playground.cells.get(i).getLocation().y, 30, 30, null);
        }


    }
}
