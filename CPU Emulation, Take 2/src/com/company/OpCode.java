package com.company;

@FunctionalInterface
public interface OpCode {
    void run(byte x, byte y);
}
