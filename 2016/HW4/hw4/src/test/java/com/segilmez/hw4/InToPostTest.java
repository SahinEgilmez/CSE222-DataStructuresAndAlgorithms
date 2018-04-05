/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.segilmez.hw4;

import java.lang.reflect.Field;
import java.util.Stack;
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
public class InToPostTest {

    private Stack<Character> theStack;
    private Object targetClass;

    public InToPostTest() {
    }

    /**
     * Test of translate method, of class InToPost.
     */
    @Test
    public void testTranslate() {
        System.out.println("translate");
        String input = "c = a / 3 * b + 21";
        InToPost instance = new InToPost();
        String expResult = "c a 3 / b * 21 + =";
        String result = instance.translate(input);
        assertEquals(expResult, result);

    }
}
