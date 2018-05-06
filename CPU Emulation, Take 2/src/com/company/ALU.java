package com.company;

import java.util.List;

public class ALU {

    private List<Instruction> stack;
    private int sp;
    private byte[] V = new byte[3]; // registers

    private OpCode[] functionMap = {
            this::exit, this::load, this::add, this::sub, this::or, this::and,
            this::xor, this::se_rK, this::sne_rK, this::se, this::sne
    };// 11 different opcodes

    private void exit(byte o1, byte o2){
        sp = stack.size();
    }

    private void load(byte x, byte y){
        V[x] = y;
    }

    private void sub(byte x, byte y){
        byte result = (byte)(V[x] - V[y]);
        V[x] = result;
        V[2] = ((result >> 7) == 1) ? (byte)1:0;
    }

    private void add(byte x, byte y){
        int result = V[x] + V[y];
        V[2] = (result > Byte.MAX_VALUE)? (byte)1:0;
        V[x] = (byte)(result);
    }

    private void or(byte x, byte y){
        V[x] |= V[y];
    }

    private void xor(byte x, byte y){
        V[x] ^= V[y];
    }

    private void and(byte x, byte y){
        V[x] &= V[y];
    }

    //skip
    private void se_rK(byte x, byte y){
        boolean skip = V[x] == y;
        if(skip) sp++;
    }

    private void sne_rK(byte x, byte y){
        boolean skip = V[x] != y;
        if(skip) sp++;
    }

    private void se(byte x, byte y){
        boolean skip = V[x] == V[y];
        if(skip) sp++;

    }

    private void sne(byte x, byte y){
        boolean skip = V[x] != V[y];
        if(skip) sp++;
    }


    public ALU(List<Instruction> stack){
        this.stack = stack;
        sp = 0;
    }

    public void run(){
        for(; sp < stack.size(); sp++){
            Instruction command = stack.get(sp);
            functionMap[command.opcode].run(command.operand1, command.operand2);
        }
    }

    @Override
    public String toString(){
        return String.format("%d %d %d", V[0], V[1], V[2]);
    }

}
