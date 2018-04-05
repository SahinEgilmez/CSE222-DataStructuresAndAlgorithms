/**
 * HW4 - ŞAHİN EĞİLMEZ - 131044059 - MART 2016
 */
package com.segilmez.hw4;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Bu main sınıfı bir dosyadan (input.txt) infix formdaki işlemleri alır ve
 * başka bir dosyaya(output.asm) assembly kod olarak yazar.
 *
 * @author sahin
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            String postfix;
            InfixToPostfix translater = new InfixToPostfix();
            //inputdaki değerleri postfix stringine çevirir(satırlar # ile ayrılır)
            postfix = translater.convert("input.txt");
            PostfixToAsm asm = new PostfixToAsm(postfix);
            //postfix form assembly koda çevrilir
            String strAsm = asm.convert();
            //assembly file'a yazılır.
            File file = new File("output.asm");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file, false);
            BufferedWriter bWriter = new BufferedWriter(fileWriter);
            bWriter.write(strAsm);
            bWriter.close();
        } catch (Exception e) {//MyException gelebilir
            System.out.println(e.getMessage());
        }

    }

}
