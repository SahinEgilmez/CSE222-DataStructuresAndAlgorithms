/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.segilmez.hw4;

/**
 * her bir integer operand'ın registerı ve değeri ile birlikte temsil eder
 *
 * @author sahin
 */
public class Operand extends Expression {

    private Register register;//operand registırı
    private Integer value;//değeri

    /**
     * Constructor
     *
     * @param str =name (Expressions sınıfından gelir)
     * @param reg =register
     * @param val =value
     */
    public Operand(String str, Register reg, Integer val) {
        super(str);
        register = reg;
        value = val;
    }

    /**
     * register getter
     *
     * @return register
     */
    public Register getRegister() {
        return register;
    }

    /**
     * value getter
     *
     * @return value
     */
    public Integer getValue() {
        return value;
    }
    /**
     * register setter
     *
     * @param reg = register
     */
    public void setRegister(Register reg) {
        register = reg;
    }

    /**
     * value setter
     *
     * @param val = value
     */
    public void setValue(int val) {
        value = val;
    }

}
