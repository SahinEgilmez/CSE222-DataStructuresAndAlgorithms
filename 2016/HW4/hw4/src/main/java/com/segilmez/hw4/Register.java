/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.segilmez.hw4;

/**
 * register ları temsil eder.
 * @author sahin
 */
public class Register {

    private String name;//register adı
    private boolean using;//registerın kullanımda olup olmadığı durumu
    /**
     * constructor
     * @param in  = name
     */
    public Register(String in) {
        this.name = in;
        using = false;//yeni register eklendiğinde kullanımda olmaz doğal olarak
    }
    /**
     * name getter
     * @return name
     */
    public String getName() {
        return name;
    }
    
    /**
     * using getter
     * @return using
     */
    public boolean getUsing() {
        return using;
    }
    /**
     * name setter
     * @param in =name
     */
    public void setName(String in) {
        name = in;
    }
    /**
     * using setter
     * burada sadece sonradan oluşturulmuş "$t" ile başlayan ismi bulunanan 
     * registerların kullanımı değiştirilebilir.
     * @param bool =using 
     */
    public void setUsing(boolean bool) {
        if ('$' == (name.charAt(0))) {
            using = bool;
        }
    }
}
