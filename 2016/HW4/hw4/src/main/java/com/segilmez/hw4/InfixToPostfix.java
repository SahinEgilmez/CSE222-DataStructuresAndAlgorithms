/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.segilmez.hw4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * bir dosyadan infix ifadeleri okur ve bunu postfix olarak döndürür(convert)
 * yazar.
 * @author sahin
 */
public class InfixToPostfix {
    /**
     * Constructor
     */
    public InfixToPostfix() {
    }
    /**
     * 
     * @return postfix form daki string. bu string her satırı " # " ile ayırır
     * @throws IOException exceptions
     */
    public String convert(String readFileName) throws IOException {
        InToPost converter = new InToPost();
        String output = "";
        File readFile = new File(readFileName);//okunacak dosya
        FileReader fileReader = new FileReader(readFile);//yazılacak dosya
        String line;//her satır için string   
        BufferedReader br = new BufferedReader(fileReader);
        String str="";//return string
        while ((line = br.readLine()) != null) {
            output = converter.translate(line);//infix satır postfixe çevrildi
            str =str+(output+ " # ");//str daha sonra assembly e çevrilirken 
                //" # " a göre ayrıştırılacak.(Bunu yeniden okuma yapmamak için yaptım)
        }
        br.close();
        return str;//return postfix string
    }

}
