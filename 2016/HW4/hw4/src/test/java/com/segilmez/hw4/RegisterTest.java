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
public class RegisterTest {

    public RegisterTest() {
    }

    /**
     * Test of getName method, of class Register.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Register instance = new Register("abc");
        String expResult = "abc";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getUsing method, of class Register.
     */
    @Test
    public void testGetUsing() {
        System.out.println("getUsing");
        Register instance = new Register("abc");//ilk değer false zaten
        boolean expResult = false;
        boolean result = instance.getUsing();
        assertEquals(expResult, result);
    }

    /**
     * Test of setName method, of class Register.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String in = "$t0";
        Register instance = new Register("abc");
        instance.setName(in);
        assertEquals(instance.getName(), in);
    }

    /**
     * Test of setUsing method, of class Register.
     */
    @Test
    public void testSetUsing() {
        System.out.println("setUsing");
        boolean bool = true;
        //burada önemli nokta $t ile başlamayan bir isim verilince set yapmaz.
        Register instance = new Register("$t0");//ilk değer false zaten
        boolean expResult = true;
        instance.setUsing(bool);
        assertEquals(expResult, instance.getUsing());
    }

}
