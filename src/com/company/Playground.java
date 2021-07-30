package com.company;

import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class Playground extends javax.swing.JFrame {
    public javax.swing.JLabel jLabel1;
    public javax.swing.JLabel jLabel2;
    public javax.swing.JPanel jPanel1;
    public static boolean gameisstarted = false;
    public static ArrayList<Cell> cells = new ArrayList<>();
    public static int numberofbombs;
    public static Timer t;
    public static MouseListener mouseListener;
    public static Timer timer;
    public static int secs = 0, mins = 0, hours = 0;
    public static String result;

    public Playground() {
        initComponents();
    }


    private void initComponents() {
        if (!gameisstarted) {
            cells.clear();
            for (int i = 0; i < 20; i++) {
                for (int j = 0; j < 20; j++) {
                    cells.add(new Cell(new Point(5 + j * 32, 5 + i * 32)));
                }
            }
        }

        jPanel1 = new MyJPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(648, 684));
        setResizable(false);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) Main.exit();
            }
        });
        mouseListener = new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getButton() == MouseEvent.BUTTON1) {
                    try {
                        if (!gameisstarted) {
                            timer.start();
                            int a = idbylocation(new Point(e.getX(), e.getY() - 36));
                            if (a == -1) throw new ArrayIndexOutOfBoundsException();
                            ArrayList<Integer> c = new ArrayList<>();
                            c.add(a);
                            if (a % 20 != 0) c.add(a - 1);//!left
                            if (a % 20 != 19) c.add(a + 1);//!right
                            if (a > 19) c.add(a - 20);//!top
                            if (a < 380) c.add(a + 20);//!bottom
                            if (a % 20 != 0 && a > 19) c.add(a - 21);
                            if (a % 20 != 0 && a < 380) c.add(a + 19);
                            if (a % 20 != 19 && a > 19) c.add(a - 19);
                            if (a % 20 != 19 && a < 380) c.add(a + 21);
                            for (int i = 0; i < numberofbombs; i++) {
                                int b = random(400, c);
                                c.add(b);
                                cells.get(b).setCondition(Cell.WITH_BOMB);
                            }
                            for (int i = 0; i < cells.size(); i++) {
                                if (cells.get(i).getCondition() != Cell.WITH_BOMB) {
                                    int y = 0;
                                    if (i % 20 != 0) if (cells.get(i - 1).getCondition() == Cell.WITH_BOMB) y++;
                                    if (i % 20 != 19)
                                        if (cells.get(i + 1).getCondition() == Cell.WITH_BOMB) y++;//!right
                                    if (i > 19) if (cells.get(i - 20).getCondition() == Cell.WITH_BOMB) y++;//!top
                                    if (i < 380) if (cells.get(i + 20).getCondition() == Cell.WITH_BOMB) y++;//!bottom
                                    if (i % 20 != 0 && i > 19)
                                        if (cells.get(i - 21).getCondition() == Cell.WITH_BOMB) y++;
                                    if (i % 20 != 0 && i < 380)
                                        if (cells.get(i + 19).getCondition() == Cell.WITH_BOMB) y++;
                                    if (i % 20 != 19 && i > 19)
                                        if (cells.get(i - 19).getCondition() == Cell.WITH_BOMB) y++;
                                    if (i % 20 != 19 && i < 380)
                                        if (cells.get(i + 21).getCondition() == Cell.WITH_BOMB) y++;
                                    cells.get(i).setCondition(y);
                                }
                            }


                            gameisstarted = true;
                        }
                        if (cells.get(idbylocation(new Point(e.getX(), e.getY() - 36))).getCondition() == Cell.BORDERS_WITH_0) opening(idbylocation(new Point(e.getX(), e.getY() - 36)));
                        else if (cells.get(idbylocation(new Point(e.getX(), e.getY() - 36))).getCondition() == Cell.WITH_BOMB && !cells.get(idbylocation(new Point(e.getX(), e.getY() - 36))).isSelected()) gamelost();
                        else if (!cells.get(idbylocation(new Point(e.getX(), e.getY() - 36))).isSelected()) cells.get(idbylocation(new Point(e.getX(), e.getY() - 36))).setClosed(false);
                    }catch (ArrayIndexOutOfBoundsException ignored){}
                }
                if (e.getButton() == MouseEvent.BUTTON3 && cells.get(idbylocation(new Point(e.getX(), e.getY() - 36))).isClosed()) cells.get(idbylocation(new Point(e.getX(), e.getY() - 36))).setSelected(!cells.get(idbylocation(new Point(e.getX(), e.getY() - 36))).isSelected());
            }
        };
        addMouseListener(mouseListener);

        jPanel1.setPreferredSize(new java.awt.Dimension(648, 648));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 648, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 648, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 20));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 20));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        if (Main.records.get(numberofbombs) != null) jLabel2.setText("Your record: " + Main.records.get(numberofbombs));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        if (!gameisstarted) {
            t = new javax.swing.Timer(50, e -> {
                jPanel1.repaint();
                String sec = secs + "", min = mins + "", hour = hours + "";
                if (sec.length() < 2) sec = "0" + sec;
                if (min.length() < 2) min = "0" + min;
                if (hour.length() < 2) hour = "0" + hour;
                jLabel1.setText(hour + ":" + min + ":" + sec);
                gamewon();
            });
            t.start();
            timer = new Timer(1000, e -> {
                secs++;
                if (secs == 60){
                    secs = 0;
                    mins++;
                }
                if (mins == 60){
                    mins = 0;
                    hours++;
                }
            });
        }
        pack();
    }
    public static int idbylocation(Point x){
        for(int i = 0; i < cells.size(); i++){
            if (cells.get(i).thiscell(x)) return i;
        }
        return -1;
    }
    public static int random(int bound, List<Integer> c){
        int a = new Random().nextInt(bound);
        if (c.contains(a)) return random(bound, c);
        else return a;
    }
    public void gamelost(){
        setVisible(false);
        EndOfTheGame endOfTheGame = new EndOfTheGame(EndOfTheGame.GAME_LOST);
        endOfTheGame.setLocation(Main.center.x - endOfTheGame.getWidth() / 2, Main.center.y - endOfTheGame.getHeight() / 2);
        endOfTheGame.setVisible(true);
        for(Cell x: cells){
            x.setClosed(false);
            x.setSelected(false);
        }
    }
    public static void opening(int id){
        cells.get(id).setClosed(false);
        if (id % 20 != 0) {
            if (cells.get(id - 1).isClosed()) {
                cells.get(id - 1).setClosed(false);
                if (cells.get(id - 1).getCondition() == Cell.BORDERS_WITH_0) {
                    opening(id - 1);
                }
            }
        }
        if (id % 20 != 19) {
            if (cells.get(id + 1).isClosed()) {
                cells.get(id + 1).setClosed(false);
                if (cells.get(id + 1).getCondition() == Cell.BORDERS_WITH_0) {
                    opening(id + 1);
                }
            }
        }
        if (id > 19) {
            if (cells.get(id - 20).isClosed()) {
                cells.get(id - 20).setClosed(false);
                if (cells.get(id - 20).getCondition() == Cell.BORDERS_WITH_0) {
                    opening(id - 20);
                }
            }
        }
        if (id < 380) {
            if (cells.get(id + 20).isClosed()) {
                cells.get(id + 20).setClosed(false);
                if (cells.get(id + 20).getCondition() == Cell.BORDERS_WITH_0) {
                    opening(id + 20);
                }
            }
        }
        if (id % 20 != 0 && id > 19) {
            if (cells.get(id - 21).isClosed()) {
                cells.get(id - 21).setClosed(false);
                if (cells.get(id - 21).getCondition() == Cell.BORDERS_WITH_0) {
                    opening(id - 21);
                }
            }
        }
        if (id % 20 != 0 && id < 380) {
            if (cells.get(id + 19).isClosed()) {
                cells.get(id + 19).setClosed(false);
                if (cells.get(id + 19).getCondition() == Cell.BORDERS_WITH_0) {
                    opening(id + 19);
                }
            }
        }
        if (id % 20 != 19 && id > 19) {
            if (cells.get(id - 19).isClosed()) {
                cells.get(id - 19).setClosed(false);
                if (cells.get(id - 19).getCondition() == Cell.BORDERS_WITH_0) {
                    opening(id - 19);
                }
            }
        }
        if (id % 20 != 19 && id < 380) {
            if (cells.get(id + 21).isClosed()) {
                cells.get(id + 21).setClosed(false);
                if (cells.get(id + 21).getCondition() == Cell.BORDERS_WITH_0) {
                    opening(id + 21);
                }
            }
        }
    }
    public void gamewon(){
        int y = 0;
        for(Cell x: cells){
            if (!x.isClosed() && x.getCondition() != Cell.WITH_BOMB || (x.isClosed() && x.isSelected() && x.getCondition() == Cell.WITH_BOMB)) y++;
        }
        if (y == 400){
            result = jLabel1.getText();
            if (Main.records.get(numberofbombs) == null){
                Main.records.put(numberofbombs, result);
            }else {
                String[] s = result.split(":");
                String[] s1 = Main.records.get(numberofbombs).split(":");
                if (Integer.parseInt(s[0] + s[1] + s[2]) < Integer.parseInt(s1[0] + s1[1] + s1[2])){
                    Main.records.put(numberofbombs, result);
                }
            }
            setVisible(false);
            EndOfTheGame endOfTheGame = new EndOfTheGame(EndOfTheGame.GAME_WON);
            endOfTheGame.setLocation(Main.center.x - endOfTheGame.getWidth() / 2, Main.center.y - endOfTheGame.getHeight() / 2);
            endOfTheGame.setVisible(true);
            t.stop();
        }
    }
}