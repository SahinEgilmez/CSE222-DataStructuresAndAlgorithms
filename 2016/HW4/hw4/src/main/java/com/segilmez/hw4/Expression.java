/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.segilmez.hw4;

/**
 * ifadeleritemsil eder, sadece adı vardır.
 *
 * @author sahin
 */
public class Expression {

    /**
     * ifadenin ismi
     */
    private String name;//ifadenin ismi

    /**
     * constructor
     *
     * @param str
     */
    public Expression(String str) {
        name = str;
    }

    /**
     * name getter
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * name setter
     *
     * @param str =name
     */
    public void setName(String str) {
        name = str;
    }
}
