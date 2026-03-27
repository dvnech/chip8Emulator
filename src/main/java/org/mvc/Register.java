package org.mvc;

public class Register {

    private byte[] registers;
    char I;

    public Register(){
        registers = new byte[0xF];
        for(int i = 0x0; i <= 0xF;i++){
            registers[i] = 0x0;
        }
        I = 0x0;
    }

    public void setV0(byte value){
        registers[0x0] = value;
    }
    public byte getVO(){
        return registers[0x0];
    }


    public void setV1(byte value){
        registers[0x1] = value;
    }
    public byte getV1(){
        return registers[0x1];
    }


    public void setV2(byte value){
        registers[0x2] = value;
    }
    public byte getV2(){
        return registers[0x2];
    }


    public void setV3(byte value){
        registers[0x3] = value;
    }
    public byte getV3(){
        return registers[0x3];
    }


    public void setV4(byte value){
        registers[0x4] = value;
    }
    public byte getV4(){
        return registers[0x4];
    }


    public void setV5(byte value){
        registers[0x5] = value;
    }
    public byte getV5(){
        return registers[0x5];
    }


    public void setV6(byte value){
        registers[0x6] = value;
    }
    public byte getV6(){
        return registers[0x6];
    }


    public void setV7(byte value){
        registers[0x7] = value;
    }
    public byte getV7(){
        return registers[0x7];
    }


    public void setV8(byte value){
        registers[0x8] = value;
    }
    public byte getV8(){
        return registers[0x8];
    }


    public void setV9(byte value){
        registers[0x9] = value;
    }
    public byte getV9(){
        return registers[0x9];
    }


    public void setVA(byte value){
        registers[0xA] = value;
    }
    public byte getVA(){
        return registers[0xA];
    }


    public void setVB(byte value){
        registers[0xB] = value;
    }
    public byte getVB(){
        return registers[0xB];
    }


    public void setVC(byte value){
        registers[0xC] = value;
    }
    public byte getVC(){
        return registers[0xC];
    }


    public void setVD(byte value){
        registers[0xD] = value;
    }
    public byte getVD(){
        return registers[0xD];
    }


    public void setVE(byte value){
        registers[0xE] = value;
    }
    public byte getVE(){
        return registers[0xE];
    }


    public void setVF(byte value){
        registers[0xF] = value;
    }
    public byte getVF(){
        return registers[0xF];
    }

    public void setI(char value){
        I = value;
    }
    public char getI(){
        return I;
    }



}
