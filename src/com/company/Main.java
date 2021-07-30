package com.company;

import java.awt.*;
import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class Main {
    public static Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
    public static Point center = new Point(size.width / 2, size.height / 2);
    public static Dimension playground_size = new Dimension(648, 648);
    public static LinkedHashMap<Integer, String> records = new LinkedHashMap<>();

    public static void main(String[] args) {
        Startgame startgame = new Startgame();
        startgame.setLocation(center.x - startgame.getWidth() / 2, center.y - startgame.getHeight() / 2);
        startgame.setVisible(true);
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(new File("records.txt")));
            String l;
            while ((l = fileReader.readLine()) != null){
                String[] s = l.split(" ");
                records.put(Integer.parseInt(s[0]), s[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void exit(){
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("records.txt"))){
            for (Map.Entry<Integer, String> x: records.entrySet()) {
                bufferedWriter.write(x.getKey() + " " + x.getValue() + '\n');
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        System.exit(0);
    }
}
