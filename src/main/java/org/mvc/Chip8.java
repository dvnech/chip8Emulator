package org.mvc;

import java.io.*;
import java.util.Arrays;
import java.util.Stack;

public class Chip8 {

    /*Memory: CHIP-8 has direct access to up to 4 kilobytes of RAM
    Display: 64 x 32 pixels (or 128 x 64 for SUPER-CHIP) monochrome,
     ie. black or white
    A program counter, often called just “PC”, which points at the current instruction in memory
    One 16-bit index register called “I” which is used to point at locations in memory
    A stack for 16-bit addresses, which is used to call subroutines/functions and return from them
    An 8-bit delay timer which is decremented at a rate of 60 Hz (60 times per second) until it reaches 0
    An 8-bit sound timer which functions like the delay timer, but which also gives off a beeping sound
     as long as it’s not 0
    16 8-bit (one byte) general-purpose variable registers numbered 0 through F hexadecimal,
             ie. 0 through 15 in decimal, called V0 through VF
    VF is also used as a flag register; many instructions will set it to either 1 or 0 based on some rule,
     for example using it as a carry flag*/

    char[] memory;
    int[] V;
    int I;
    boolean[] display;
    boolean[] keypad;

    boolean isRun;
    private boolean needsRedraw;
    int pc;
    byte delayTimer;
    byte soundTimer;
    Stack<Character> stack;
    int opcode;


    public Chip8(){
        initialize();
    }

    public void initialize(){
        memory = new char[0x1000];
        for(int i = 0; i < 0x1000; i++){
            memory[i] = 0x0;
        }
        display = new boolean[64*32];

        keypad = new boolean[0xF];
        needsRedraw = false;
        V = new int[0x10];
        I = 0x0;

        delayTimer = 0;
        soundTimer = 0;
        pc = 0x200; //???

        stack = new Stack<>();
        opcode = 0x0;
    }

    public void run(){
        isRun = true;
        opcode = fetch();
        decodeAndExecute();
        //fetch instruction
        //decode
        //execute
        //draw

    }

    public int fetch(){
        if(pc >= memory.length) {
            System.out.println("Memory address out of bounds");
            return -1;
        }
        int instruction = ((memory[pc] & 0xFF) << 8) | (memory[pc + 1] & 0xFF);
        pc += 2;
        return instruction;
    }
    public void decodeAndExecute(){
        /*00E0 (clear screen)
        1NNN (jump)
        6XNN (set register VX)
        7XNN (add value to register VX)
        ANNN (set index register I)
        DXYN (display/draw)*/

        int firstNib = (opcode >> 4*3);
        int X = (opcode >> 4*2 & 15);
        int Y = (opcode >> 4 & 15);
        int N = (opcode & 15);
        int NN = (opcode & 255);
        int NNN = (opcode & 0xFFF);

        switch(firstNib){

            case 0x0:{  //Clear screen
                clearScreen();
                break;
            }
            case 0x1:{ //jump
                pc = NNN;
                break;
            }
            case 0x3:{
                if(V[X] == NN)
                    pc += 2;
                break;
            }
            case 0x6:{  //set register VX
                V[X] = NN;
                break;
            }
            case 0x7:{ //add value to VX
                V[X] += NN;
                break;
            }
            case 0xA:{ //set index register I
                I = NNN;
                break;
            }
            case 0xD:{ //display/draw

                int x = V[X] & 63;
                int y = V[Y] & 31;
                int height = N;

                V[0xF] = 0;
                System.out.println("drawing at " + x + " " + y);
                for(int _y = 0; _y < height; _y++) {
                    int line = memory[I + _y];
                    for(int _x = 0; _x < 8; _x++) {
                        int pixel = line & (0x80 >> _x);
                        if(pixel != 0) {
                            int totalX = (x + _x)%64;
                            int totalY = (y + _y)%32;
                            int index = (totalY * 64) + totalX;

                            if(display[index] == true)
                                V[0xF] = 1;

                            display[index] ^= true;
                        }
                    }
                }
                needsRedraw = true;
                break;
            }
            default:{
                System.err.println("Unknown opcode" + Integer.toHexString(opcode));
            }
        }

    }
    public void updateTimers(){
        if(delayTimer > 0)
            delayTimer--;
        if(soundTimer > 0)
            soundTimer--;
    }
    public void renderGraphics(){

    }
    public void handleInput(){

    }
    public void delay(){

    }
    public void clearScreen(){
        Arrays.fill(display, false);
    }

    public boolean[] getDisplay(){
        return display;
    }

    public void setKeyBuffer(int[] keyBuffer){
        for(int i = 0; i < keypad.length;i++){
            if(keyBuffer[i] <= 0)
                keypad[i] = false;
            else
                keypad[i]  = true;
        }
    }

    public boolean isNeedsRedraw(){
        return needsRedraw;
    }
    public void removeRedrawFlag(){
        needsRedraw = false;
    }

    public void loadProgram(String file) {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(file)) {
            if (is == null) {
                throw new RuntimeException("Файл не найден в resources: " + file);
            }

            int b;
            int offset = 0;
            while ((b = is.read()) != -1) {
                memory[0x200 + offset] = (char) b;
                offset++;
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }


    }

    /**
     * Loads the fontset into the memory
     */
    public void loadFontset() {
        for(int i = 0; i < ChipData.fontset.length; i++) {
            memory[0x50 + i] = (char)(ChipData.fontset[i] & 0xFF);
        }
    }
    public char[] getMemoryDump(){
        return memory;
    }

}
