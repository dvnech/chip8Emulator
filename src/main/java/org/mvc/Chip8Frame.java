package org.mvc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;

public class Chip8Frame extends JFrame implements KeyListener {

    private Chip8Panel panel;
    private int[] keyBuffer;
    private int[] keyIdToKey;

    public Chip8Frame(Chip8 c){
        setPreferredSize(new Dimension(640,320));
        pack();
        setPreferredSize(new Dimension(640 + getInsets().left + getInsets().right, 320 + getInsets().top + getInsets().bottom));
        panel = new Chip8Panel(c);
        setLayout(new BorderLayout());
        add(panel,BorderLayout.CENTER);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setVisible(true);

        keyIdToKey = new int[256];
        keyBuffer = new int[16];
    }


    private void fillKeyIds(){
        Arrays.fill(keyIdToKey, -1);

        keyIdToKey['1'] = 0x1;
        keyIdToKey['2'] = 0x2;
        keyIdToKey['3'] = 0x3;
        keyIdToKey['4'] = 0xC;
        keyIdToKey['q'] = 0x4;
        keyIdToKey['w'] = 0x5;
        keyIdToKey['e'] = 0x6;
        keyIdToKey['r'] = 0xD;
        keyIdToKey['a'] = 0x7;
        keyIdToKey['s'] = 0x8;
        keyIdToKey['d'] = 0x9;
        keyIdToKey['f'] = 0xE;
        keyIdToKey['z'] = 0xA;
        keyIdToKey['x'] = 0x0;
        keyIdToKey['c'] = 0xB;
        keyIdToKey['v'] = 0xF;
    }



    @Override
    public void keyPressed(KeyEvent e) {
        if(keyIdToKey[e.getKeyCode()] != -1)
            keyBuffer[keyIdToKey[e.getKeyCode()]] = 1;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(keyIdToKey[e.getKeyCode()] != -1)
            keyBuffer[keyIdToKey[e.getKeyCode()]] = 0;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //not used
    }

    public int[] getKeyBuffer(){
        return keyBuffer;
    }
}
