/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.segilmez.hw4;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sahin
 */
public class OperandTest {

    public OperandTest() {
    }

    /**
     * Test of getRegister method, of class Operand.
     */
    @Test
    public void testGetRegister() {
        System.out.println("getRegister");
        Register reg = new Register("a");
        Operand instance = new Operand("op", new Register("$t0"), 15);
        instance.setRegister(reg);
        Register result = instance.getRegister();
        assertEquals(instance.getRegister(), result);
    }

    /**
     * Test of getValue method, of class Operand.
     */
    @Test
    public void testGetValue() {
        System.out.println("getValue");
        Operand instance = new Operand("op", new Register("$t0"), 10);
        Integer expResult = 10;
        Integer result = instance.getValue();
        assertEquals(expResult, result);
    }

    /**
     * Test of setRegister method, of class Operand.
     */
    @Test
    public void testSetRegister() {
        System.out.println("setRegister");
        Register reg = new Register("a");
        Operand instance = new Operand("op", new Register("$t0"), 15);
        instance.setRegister(reg);
        assertEquals(instance.getRegister(), reg);

    }

    /**
     * Test of setValue method, of class Operand.
     */
    @Test
    public void testSetValue() {
        System.out.println("setValue");
        int val = 5;
        Operand instance = new Operand("a", new Register("$t0"), 0);
        instance.setValue(val);
        assertEquals((long) instance.getValue(), (long) val);
    }

}
