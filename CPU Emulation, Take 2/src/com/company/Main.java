package com.company;
import java.util.*;
import java.io.*;
import java.math.*;

public class Main {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String program = in.nextLine();

        List<Instruction> stack = new ArrayList<>();

        for(int i = 0; i < program.length(); i+=4){
            stack.add(new Instruction(program.substring(i, i+4)));
        }

        ALU alu = new ALU(stack);
        alu.run();

        System.out.println(alu);
    }

}
