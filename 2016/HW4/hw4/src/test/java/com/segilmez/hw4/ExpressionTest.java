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
public class ExpressionTest {

    public ExpressionTest() {
    }

    /**
     * Test of getName method, of class Expression.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Expression instance = new Expression("exp1");
        String expResult = "exp1";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setName method, of class Expression.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String str = "exp2";
        Expression instance = new Expression("exp1");
        instance.setName(str);
        assertEquals(str, instance.getName());
    }

}
