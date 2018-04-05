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
public class InfixToPostfixTest {
    
    public InfixToPostfixTest() {
    }
    

    /**
     * Test of convert method, of class InfixToPostfix.
     */
    @Test
    public void testConvert() throws Exception {
        System.out.println("convert");
        String readFileName = "input.txt";
        InfixToPostfix instance = new InfixToPostfix();
        String expResult = "a 4 = # b 3 = # c 12 = # b a b 3 * - = # "
                + "c a 3 / b * 21 + = # print c # ";
        String result = instance.convert(readFileName);
        assertEquals(expResult, result);
    }
    
}
