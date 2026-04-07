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
        frame = new Chip8Frame(chip8);
    }
    public static void main( String[] args ) {
        App main = new App();
        main.start();
    }
    public void run(){
        //60hz
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
}
