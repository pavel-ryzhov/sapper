package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EndOfTheGame extends javax.swing.JFrame {


    public EndOfTheGame(int result) {
        initComponents();
        if (result == GAME_LOST){
            jLabel2.setText("GAME LOST");
            jLabel2.setForeground(Color.RED);
        }else {
            jLabel2.setText("GAME WON");
            jLabel2.setForeground(Color.GREEN);
            jLabel1.setText("Your time: " + Playground.result);
        }
    }

    public static final int GAME_LOST = 0, GAME_WON = 1;


    private void initComponents() {


        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jButton2.setText("Exit");
        jButton2.setFont(new java.awt.Font("Tahoma", 0, 15));
        jButton2.setFocusable(false);
        jButton2.addActionListener(e -> Main.exit());

        jButton3.setText("View the field");
        jButton3.setFont(new java.awt.Font("Tahoma", 0, 15));
        jButton3.setFocusable(false);
        jButton3.addActionListener(e -> {
            setVisible(false);
            Playground playground = new Playground();
            playground.setLocation(Main.center.x - playground.getWidth() / 2, Main.center.y - playground.getHeight() / 2);
            playground.jLabel1.setText("Exit");
            playground.jLabel1.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    super.mousePressed(e);
                    Main.exit();
                }
            });
            playground.jLabel2.setText("Restart the game");
            playground.jLabel2.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    super.mousePressed(e);
                    Playground.t.stop();
                    Playground.timer.stop();
                    playground.setVisible(false);
                    Startgame startgame = new Startgame();
                    startgame.setLocation(Main.center.x - startgame.getWidth() / 2, Main.center.y - startgame.getHeight() / 2);
                    startgame.setVisible(true);
                }
            });
            playground.removeMouseListener(Playground.mouseListener);
            playground.setVisible(true);
        });

        jButton4.setText("Restart the game");
        jButton4.setFont(new java.awt.Font("Tahoma", 0, 15));
        jButton4.setFocusable(false);
        jButton4.addActionListener(e -> {
            Playground.t.stop();
            Playground.timer.stop();
            setVisible(false);
            Startgame startgame = new Startgame();
            startgame.setLocation(Main.center.x - startgame.getWidth() / 2, Main.center.y - startgame.getHeight() / 2);
            startgame.setVisible(true);
        });

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setFont(new java.awt.Font("Ink Free", 3, 40));
        jLabel1.setVerticalAlignment(SwingConstants.TOP);
        jLabel1.setForeground(Color.GREEN);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setVerticalAlignment(SwingConstants.BOTTOM);
        jLabel2.setFont(new java.awt.Font("Ink Free", 3, 60));


        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                                        .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                                        .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)))
        );

        pack();
    }


    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
}
