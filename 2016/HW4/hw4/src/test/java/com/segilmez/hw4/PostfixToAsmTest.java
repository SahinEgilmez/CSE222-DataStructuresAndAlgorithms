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
public class PostfixToAsmTest {

    public PostfixToAsmTest() {
    }

    /**
     * Test of convert method, of class PostfixToAsm.
     */
    @Test
    public void testConvert() throws Exception {
        System.out.println("convert");
        PostfixToAsm instance = new PostfixToAsm("a 4 = # b 3 = # c 12 ="
                + " # b a b 3 * - = ");
        String expResult = "li $t0,4\n"
                + "\n"
                + "li $t1,3\n"
                + "\n"
                + "li $t2,12\n"
                + "\n"
                + "li $t3,3\n"
                + "mult $t1,$t3\n"
                + "mflo $t3\n"
                + "sub $t3,$t0,$t3\n"
                + "move $t1,$t3\n"
                + "\n";
        String result = instance.convert();
        assertEquals(expResult, result);

    }

}
