package org.mvc;

import javax.swing.*;
import java.awt.*;

public class Chip8Panel extends JPanel {

    Chip8 chip;

    public Chip8Panel(Chip8 chip){
        this.chip = chip;
    }

    public void paint(Graphics g){
        boolean[] display = chip.getDisplay();
        for(int i = 0; i < display.length;i++){
            if(display[i])
                g.setColor(Color.WHITE);
            else
                g.setColor(Color.BLACK);

            int y = (int)Math.floor(i/64);  //check that y coordinate is correct
            int x = i%64;

            g.fillRect(x*10,y*10,10,10);


        }
    }
}
