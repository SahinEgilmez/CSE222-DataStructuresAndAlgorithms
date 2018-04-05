/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.segilmez.hw4;

import java.io.IOException;
import java.util.Stack;

/**
 *
 * @author sahin
 * Bu sınıf infix satırı postfix satıra çevirir.
 * Burada sadece "+,-,*,/" operatörleri postfix yapılacaktır.
 */
public class InToPost {

    private Stack<Character> theStack;//operatörleri sona atmak için
    private String output = "";//postfix satır.
    /**
     * constructor sadece stack ilklendirir.
     */
    public InToPost() {
        theStack = new Stack<>();
    }
    /**
     * infix inputu postfix olarak output a ekler.
     * @param input infix satır
     * @return //postfix satır
     */ 
    public String translate(String input) {
        output = "";
        for (int j = 0; j < input.length(); j++) {
            char ch = input.charAt(j);
            switch (ch) {//operatörün önceliğine göre gotOper methodunu çağırır.
                        //operatör değilse aynen durur(default).
                case '=':
                    gotOper(ch, 0);
                    break;
                case '+':
                case '-':
                    gotOper(ch, 1);
                    break;
                case '*':
                case '/':
                    gotOper(ch, 2);
                    break;
                case ' ':
                    output = output + " ";
                    break;
                default:
                    output = output + ch;
                    break;
            }
        }
        //postfix form için stack e atılmış operatörleri en sona ekler.
        while (!theStack.isEmpty()) {
            output = output + " " + theStack.pop();
        }
        //output da her ifade arası tek boşluk olsun.
        output = output.replaceAll("( )+", " ");
        return output;
    }
    /**
     * operatörü önceliğine göre stacke atar ya da output a postfix olarak ekler.
     * @param opThis operatör
     * @param prec1 operatörün önceliği
     */
    private void gotOper(char opThis, int prec1) {
        while (!theStack.isEmpty()) {
            char opTop = theStack.pop();
            int prec2;
            if (opTop == '=') {
                prec2 = 0;
            } else if (opTop == '+' || opTop == '-') {
                prec2 = 1;
            } else if (opTop == '*' || opTop == '/') {
                prec2 = 2;
            } else {
                prec2 = -1;
            }
            if (prec2 != -1) {
                if (prec1 > prec2) {
                    theStack.push(opTop);
                    break;
                } else {
                    output = output + opTop;
                }
            }
        }
        theStack.push(opThis);
    }

}
