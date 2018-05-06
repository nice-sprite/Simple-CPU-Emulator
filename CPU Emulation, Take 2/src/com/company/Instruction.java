package com.company;

public class Instruction {
    public byte operand1;
    public byte operand2;
    public byte opcode;

    public Instruction(String operation){
        opcode = Byte.parseByte(operation.substring(0, 1), 16);
        boolean helper = false;
        helper = opcode == 1 || opcode == 7 || opcode == 8;
        operand1 = Byte.parseByte(operation.substring(1, (helper) ? 2:3), 16);
        operand2 = Byte.parseByte(operation.substring((helper) ? 3:2), 16);


    }


    public String toString(){
        return String.format("op:%d p1:%d p2:%d", opcode, operand1, operand2);
    }

}
