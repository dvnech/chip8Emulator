package org.mvc;

/**
 * Hello world!
 *
 */
public class App extends Thread
{

    private Chip8 chip8;
    private Chip8Frame frame;

    public App(){
        chip8 = new Chip8();
        chip8.initialize();
        chip8.loadProgram("1-chip8-logo.ch8");
        frame = new Chip8Frame(chip8);
    }
    public static void main( String[] args ) {
        App main = new App();
        main.start();
    }
    public void run(){
        //60hz
        //readMemoryDump(chip8.getMemoryDump(),0x200,0x20F);
        while(true){
            chip8.setKeyBuffer(frame.getKeyBuffer());
            chip8.run();
            if(chip8.isNeedsRedraw()){
                frame.repaint();
                chip8.removeRedrawFlag();
            }
            try{
                Thread.sleep(8);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void readMemoryDump(char[] memoryDump,int start,int end){
        for(int i = start; i < end;i+= 2){
            System.out.println(i + " " + Integer.toHexString(memoryDump[i])
                    + " " + Integer.toHexString(memoryDump[i+1]));
        }
    }
}
