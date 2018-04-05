/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.segilmez.hw4;

import java.util.Stack;

/**
 * Bu sınıf postfix bir işlemi assembly koda çevirmek için kullanılır.
 *
 * @author sahin
 */
public class PostfixToAsm {

    private String postfix;//postfix ifade
    private String output = "";//assembly formatında string
    private Stack<Expression> lineStack;//her bir satırdaki ifadeler için
    private Stack<Operand> registerStack;//tüm registerlar için
    private Register[] myRegisters;//[$t0-$t7] kullanılabilir registerlar.

    /**
     * Constructor stackleri ilklendirir ve myRegisters arrayini doldurur.
     *
     * @param in postfix ifade
     */
    public PostfixToAsm(String in) {
        postfix = in;
        lineStack = new Stack<>();
        registerStack = new Stack<>();
        myRegisters = new Register[8];
        String str;
        for (int i = 0; i < 8; i++) {
            str = "$t" + i;
            myRegisters[i] = new Register(str);
        }
    }

    /**
     * registerStack'in girilen string isminde register ı var mı diye kontrol
     * eder. Varsa indexi yoksa -1 return eder.
     *
     * @param str
     * @return register index
     */
    private int contain(String str) {
        for (int i = 0; i < registerStack.size(); i++) {
            if ((registerStack.get(i).getName() != null) && registerStack.get(i).getName().equals(str)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * aldığı satırı boşluklarına göre ayırır(ki işlemler basit olsun) ve bu
     * ifadeleri lineStack e doldurur.
     *
     * @param line satır
     */
    private void fillStack(String line) {
        String[] arr = line.split(" ");
        for (int i = 0; i < arr.length; i++) {
            if (checkOperator((arr[i].charAt(0)))) {
                lineStack.push(new Operator(arr[i]));
            } else {
                try {
                    //parse edilebiliyorsa isimsiz olarak integer tutulur.
                    int num = Integer.parseInt(arr[i]);
                    Register temp = new Register("");
                    lineStack.push(new Operand(null, temp, num));
                } catch (Exception e) {
                    //parse edilemezse ismiyle birlikte 0 değeri tutulur.
                    Register temp = new Register("");
                    lineStack.push(new Operand(arr[i], temp, 0));
                }

            }
        }
    }

    /**
     * aldığı karakterin "+-/*" operatörlerinden biri mi diye kontrol eder.
     *
     * @param ch
     * @return operatör mü değil mi?(true-false)
     */
    private boolean checkOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '=';
    }

    /**
     * kullanılmayan register var ise onu döndürür.
     *
     * @return yeni register
     * @throws MyException tüm registerlar dolu ise
     */
    private Register addRegister() throws MyException {
        for (int i = 0; i < 8; i++) {
            if (myRegisters[i].getUsing() == false) {
                myRegisters[i].setUsing(true);
                return myRegisters[i];
            }
        }
        throw (new MyException("Tüm registerlar dolu. En fazla 8 register kulanılabilir"));
    }

    /**
     * atama işlemini yapar
     *
     * @param op operatör
     * @param indexReg1 operand1 in indexi
     * @param indexReg2 operand2 nin indexi
     * @return atama işleminin assembly karşılığı
     * @throws MyException yanlış assignment
     */
    private String assignment(String op, int indexReg1, int indexReg2) throws MyException {
        String strOp = "";
        /**
         * alttaki iki contain değeri satırdaki ifadeye eşdeğer registerın zaten
         * stackimizde olup olmadığını belirler.Bu sayede yeni bir register
         * gerekip gerekmediği belli olur ve ona göre işlem yapılır
         */
        int c1 = contain(lineStack.get(indexReg1).getName());
        int c2 = contain(lineStack.get(indexReg2).getName());
        /**
         * Buradan sonraki her if için sadece 1 açıklama yapıyorum. İf blokları
         * birbirine çok benzemekte ama herbiri için registerların stack imizde
         * zaten bulunup bulunmamama durumuna göre çalışması için tasarlandı.
         * Duruma göre assembly kod üretildekten sonra gerekiyorsa gerekli
         * registerlar kullanımdışı kalırlar(daha sonra kullanmak için).
         */
        if (c1 >= 0 && c2 >= 0) {
            registerStack.get(c1).setValue(registerStack.get(c2).getValue());
            strOp = strOp + "move " + registerStack.get(c1).getRegister().getName() + ","
                    + registerStack.get(c2).getRegister().getName() + "\n";
            registerStack.get(c2).getRegister().setUsing(false);
            registerStack.remove(c2);
            lineStack.remove(indexReg2);
            lineStack.remove(indexReg1);
            lineStack.remove(indexReg1);
        } else if (c1 >= 0 && c2 < 0) {
            Register temp = addRegister();
            ((Operand) lineStack.get(indexReg1)).setRegister(temp);
            registerStack.get(c1).setValue(((Operand) lineStack.get(indexReg2)).getValue());
            strOp = strOp + "li " + registerStack.get(c1).getRegister().getName() + ","
                    + ((Operand) lineStack.get(indexReg2)).getValue() + "\n";
            lineStack.remove(indexReg2);
            lineStack.remove(indexReg1);
            lineStack.remove(indexReg1);
        } else if (c1 < 0 && c2 < 0) {
            Register reg = addRegister();
            ((Operand) lineStack.get(indexReg1)).setRegister(reg);
            registerStack.add(new Operand(((Operand) lineStack.get(indexReg1)).getName(), reg, ((Operand) lineStack.get(indexReg2)).getValue()));
            strOp = strOp + "li " + ((Operand) lineStack.get(indexReg1)).getRegister().getName() + ","
                    + ((Operand) lineStack.get(indexReg2)).getValue() + "\n";
            lineStack.remove(indexReg2);
            lineStack.remove(indexReg1);
            lineStack.remove(indexReg1);

        } else {
            //bir integera bir register'ı atayamazsın.
            throw (new MyException("Bir integera bir register atanılamaz!"));
        }
        return strOp;
    }

    /**
     * çarpma işlemini yapar
     *
     * @param op operatör
     * @param indexReg1 operand1 in indexi
     * @param indexReg2 operand2 nin indexi
     * @return çarpma işleminin assembly karşılığı
     * @throws MyException yanlış işlem
     */
    private String mult(String op, int indexReg1, int indexReg2) throws MyException {
        String strOp = "";
        /**
         * alttaki iki contain değeri satırdaki ifadeye eşdeğer registerın zaten
         * stackimizde olup olmadığını belirler.Bu sayede yeni bir register
         * gerekip gerekmediği belli olur ve ona göre işlem yapılır
         */
        int c1 = contain(lineStack.get(indexReg1).getName());
        int c2 = contain(lineStack.get(indexReg2).getName());
        int valMult;
        /**
         * Buradan sonraki her if için sadece 1 açıklama yapıyorum. İf blokları
         * birbirine çok benzemekte ama herbiri için registerların stack imizde
         * zaten bulunup bulunmamama durumuna göre çalışması için tasarlandı.
         * Duruma göre assembly kod üretildekten sonra gerekiyorsa gerekli
         * registerlar kullanımdışı kalırlar(daha sonra kullanmak için).
         */
        if (c1 >= 0 && c2 >= 0) {
            valMult = (registerStack.get(c1).getValue() * registerStack.get(c2).getValue());
            Register mult = addRegister();
            Operand temp = new Operand(mult.getName(), mult, valMult);
            registerStack.add(temp);
            strOp = strOp + "mult " + registerStack.get(c1).getRegister().getName() + ","
                    + registerStack.get(c2).getRegister().getName() + "\n";
            strOp = strOp + "mflo " + registerStack.peek().getRegister().getName() + "\n";
            lineStack.remove(indexReg2);
            lineStack.remove(indexReg1);
            lineStack.setElementAt(temp, indexReg1);
        } else if (c1 >= 0 && c2 < 0) {
            Register mult = addRegister();
            strOp = strOp + "li " + mult.getName() + ","
                    + ((Operand) lineStack.get(indexReg2)).getValue() + "\n";
            valMult = (registerStack.get(c1).getValue() * ((Operand) lineStack.get(indexReg2)).getValue());
            Operand temp = new Operand(mult.getName(), mult, valMult);
            registerStack.add(temp);
            strOp = strOp + "mult " + registerStack.get(c1).getRegister().getName() + "," + mult.getName() + "\n";
            strOp = strOp + "mflo " + registerStack.peek().getRegister().getName() + "\n";
            temp.getRegister().setUsing(false);
            lineStack.remove(indexReg2);
            lineStack.remove(indexReg1);
            lineStack.setElementAt(temp, indexReg1);
        } else if (c1 < 0 && c2 >= 0) {
            Register mult = addRegister();
            strOp = strOp + "li " + mult.getName() + ","
                    + ((Operand) lineStack.get(indexReg1)).getValue() + "\n";
            valMult = (registerStack.get(c2).getValue() * ((Operand) lineStack.get(indexReg1)).getValue());
            Operand temp = new Operand(mult.getName(), mult, valMult);
            registerStack.add(temp);
            strOp = strOp + "mult " + mult.getName() + "," + registerStack.get(c2).getRegister().getName() + "\n";
            strOp = strOp + "mflo " + registerStack.peek().getRegister().getName() + "\n";
            temp.getRegister().setUsing(false);
            lineStack.remove(indexReg2);
            lineStack.remove(indexReg1);
            lineStack.setElementAt(temp, indexReg1);
        } else if (c1 < 0 && c2 < 0) {
            Register mult = addRegister();
            Register tempReg = addRegister();
            strOp = strOp + "li " + mult.getName() + ","
                    + ((Operand) lineStack.get(indexReg1)).getValue() + "\n";
            strOp = strOp + "li " + tempReg.getName() + ","
                    + ((Operand) lineStack.get(indexReg2)).getValue() + "\n";
            valMult = (((Operand) lineStack.get(indexReg1)).getValue() * ((Operand) lineStack.get(indexReg2)).getValue());
            Operand temp = new Operand(mult.getName(), mult, valMult);
            registerStack.add(temp);
            strOp = strOp + "mult " + mult.getName() + "," + tempReg.getName() + "\n";
            strOp = strOp + "mflo " + registerStack.peek().getRegister().getName() + "\n";
            tempReg.setUsing(false);
            lineStack.remove(indexReg2);
            lineStack.remove(indexReg1);
            lineStack.setElementAt(temp, indexReg1);
        }
        return strOp;
    }

    /**
     * bölme işlemini yapar
     *
     * @param op operatör
     * @param indexReg1 operand1 in indexi
     * @param indexReg2 operand2 nin indexi
     * @return bölme işleminin assembly karşılığı
     * @throws MyException yanlış işlem
     */
    private String div(String op, int indexReg1, int indexReg2) throws MyException {
        String strOp = "";
        /**
         * alttaki iki contain değeri satırdaki ifadeye eşdeğer registerın zaten
         * stackimizde olup olmadığını belirler.Bu sayede yeni bir register
         * gerekip gerekmediği belli olur ve ona göre işlem yapılır
         */
        int c1 = contain(lineStack.get(indexReg1).getName());
        int c2 = contain(lineStack.get(indexReg2).getName());
        int valDiv = 0;
        /**
         * Buradan sonraki her if için sadece 1 açıklama yapıyorum. İf blokları
         * birbirine çok benzemekte ama herbiri için registerların stack imizde
         * zaten bulunup bulunmamama durumuna göre çalışması için tasarlandı.
         * Duruma göre assembly kod üretildekten sonra gerekiyorsa gerekli
         * registerlar kullanımdışı kalırlar(daha sonra kullanmak için).
         */
        if (c1 >= 0 && c2 >= 0) {
            try {
                valDiv = (registerStack.get(c1).getValue() / registerStack.get(c2).getValue());
            } catch (Exception e) {
                throw (new MyException("O'a bölmeye çalıştınız. İnput hatalı!"));
            }
            Register div = addRegister();
            Operand temp = new Operand(div.getName(), div, valDiv);
            registerStack.add(temp);
            strOp = strOp + "div " + registerStack.get(c1).getRegister().getName() + ","
                    + registerStack.get(c2).getRegister().getName() + "\n";
            strOp = strOp + "mflo " + registerStack.peek().getRegister().getName() + "\n";
            lineStack.remove(indexReg2);
            lineStack.remove(indexReg1);
            lineStack.setElementAt(temp, indexReg1);
        } else if (c1 >= 0 && c2 < 0) {
            Register div = addRegister();
            strOp = strOp + "li " + div.getName() + ","
                    + ((Operand) lineStack.get(indexReg2)).getValue() + "\n";
            try {
                valDiv = (registerStack.get(c1).getValue() / ((Operand) lineStack.get(indexReg2)).getValue());
            } catch (Exception e) {
                throw (new MyException("O'a bölmeye çalıştınız. İnput hatalı!"));
            }
            Operand temp = new Operand(div.getName(), div, valDiv);
            registerStack.add(temp);
            strOp = strOp + "div " + registerStack.get(c1).getRegister().getName() + "," + div.getName() + "\n";
            strOp = strOp + "mflo " + registerStack.peek().getRegister().getName() + "\n";
            temp.getRegister().setUsing(false);
            lineStack.remove(indexReg2);
            lineStack.remove(indexReg1);
            lineStack.setElementAt(temp, indexReg1);
        } else if (c1 < 0 && c2 >= 0) {
            Register div = addRegister();
            strOp = strOp + "li " + div.getName() + ","
                    + ((Operand) lineStack.get(indexReg1)).getValue() + "\n";
            try {
                valDiv = (registerStack.get(c2).getValue() / ((Operand) lineStack.get(indexReg1)).getValue());
            } catch (Exception e) {
                throw (new MyException("O'a bölmeye çalıştınız. İnput hatalı!"));
            }
            Operand temp = new Operand(div.getName(), div, valDiv);
            registerStack.add(temp);
            strOp = strOp + "div " + div.getName() + "," + registerStack.get(c2).getRegister().getName() + "\n";
            strOp = strOp + "mflo " + registerStack.peek().getRegister().getName() + "\n";
            temp.getRegister().setUsing(false);
            lineStack.remove(indexReg2);
            lineStack.remove(indexReg1);
            lineStack.setElementAt(temp, indexReg1);
        } else if (c1 < 0 && c2 < 0) {
            Register div = addRegister();
            Register tempReg = addRegister();
            strOp = strOp + "li " + div.getName() + ","
                    + ((Operand) lineStack.get(indexReg1)).getValue() + "\n";
            strOp = strOp + "li " + tempReg.getName() + ","
                    + ((Operand) lineStack.get(indexReg2)).getValue() + "\n";
            try {
                valDiv = (((Operand) lineStack.get(indexReg1)).getValue() / ((Operand) lineStack.get(indexReg2)).getValue());
            } catch (Exception e) {
                throw (new MyException("O'a bölmeye çalıştınız. İnput hatalı!"));
            }
            Operand temp = new Operand(div.getName(), div, valDiv);
            registerStack.add(temp);
            strOp = strOp + "div " + div.getName() + "," + tempReg.getName() + "\n";
            strOp = strOp + "mflo " + registerStack.peek().getRegister().getName() + "\n";
            tempReg.setUsing(false);
            lineStack.remove(indexReg2);
            lineStack.remove(indexReg1);
            lineStack.setElementAt(temp, indexReg1);

        }
        return strOp;
    }

    /**
     * toplama işlemini yapar
     *
     * @param op operatör
     * @param indexReg1 operand1 in indexi
     * @param indexReg2 operand2 nin indexi
     * @return toplama işleminin assembly karşılığı
     * @throws MyException yanlış işlem
     */
    private String add(String op, int indexReg1, int indexReg2) throws MyException {
        String strOp = "";
        /**
         * alttaki iki contain değeri satırdaki ifadeye eşdeğer registerın zaten
         * stackimizde olup olmadığını belirler.Bu sayede yeni bir register
         * gerekip gerekmediği belli olur ve ona göre işlem yapılır
         */
        int c1 = contain(lineStack.get(indexReg1).getName());
        int c2 = contain(lineStack.get(indexReg2).getName());
        int valAdd;
        /**
         * Buradan sonraki her if için sadece 1 açıklama yapıyorum. İf blokları
         * birbirine çok benzemekte ama herbiri için registerların stack imizde
         * zaten bulunup bulunmamama durumuna göre çalışması için tasarlandı.
         * Duruma göre assembly kod üretildekten sonra gerekiyorsa gerekli
         * registerlar kullanımdışı kalırlar(daha sonra kullanmak için).
         */
        if (c1 >= 0 && c2 >= 0) {
            valAdd = (registerStack.get(c1).getValue() + registerStack.get(c2).getValue());
            Register add = addRegister();
            Operand temp = new Operand(add.getName(), add, valAdd);
            registerStack.add(temp);
            strOp = strOp + "add " + temp.getName() + "," + registerStack.get(c1).getRegister().getName() + ","
                    + registerStack.get(c2).getRegister().getName() + "\n";
            lineStack.remove(indexReg2);
            lineStack.remove(indexReg1);
            lineStack.setElementAt(temp, indexReg1);
        } else if (c1 >= 0 && c2 < 0) {
            Register tempReg1 = addRegister();
            Register add = addRegister();
            strOp = strOp + "li " + tempReg1.getName() + ","
                    + ((Operand) lineStack.get(indexReg2)).getValue() + "\n";
            valAdd = (registerStack.get(c1).getValue() + ((Operand) lineStack.get(indexReg2)).getValue());
            Operand temp = new Operand(add.getName(), add, valAdd);
            registerStack.add(temp);
            strOp = strOp + "add " + temp.getName() + "," + registerStack.get(c1).getRegister().getName() + ","
                    + tempReg1.getName() + "\n";
            tempReg1.setUsing(false);
            lineStack.remove(indexReg2);
            lineStack.remove(indexReg1);
            lineStack.setElementAt(temp, indexReg1);
        } else if (c1 < 0 && c2 >= 0) {
            Register tempReg1 = addRegister();

            Register add = addRegister();
            strOp = strOp + "li " + tempReg1.getName() + ","
                    + ((Operand) lineStack.get(indexReg1)).getValue() + "\n";

            valAdd = (((Operand) lineStack.get(indexReg1)).getValue() + ((Operand) lineStack.get(indexReg2)).getValue());
            Operand temp = new Operand(add.getName(), add, valAdd);
            registerStack.add(temp);
            strOp = strOp + "add " + temp.getName() + "," + tempReg1.getName() + ","
                    + registerStack.get(c2).getRegister().getName() + "\n";
            tempReg1.setUsing(false);
            lineStack.remove(indexReg2);
            lineStack.remove(indexReg1);
            lineStack.setElementAt(temp, indexReg1);
        } else if (c1 < 0 && c2 < 0) {
            Register tempReg1 = addRegister();
            Register tempReg2 = addRegister();
            Register add = addRegister();
            strOp = strOp + "li " + tempReg1.getName() + ","
                    + ((Operand) lineStack.get(indexReg1)).getValue() + "\n";
            strOp = strOp + "li " + tempReg2.getName() + ","
                    + ((Operand) lineStack.get(indexReg2)).getValue() + "\n";
            valAdd = (((Operand) lineStack.get(indexReg1)).getValue() + ((Operand) lineStack.get(indexReg2)).getValue());
            Operand temp = new Operand(add.getName(), add, valAdd);
            registerStack.add(temp);
            strOp = strOp + "add " + temp.getName() + "," + tempReg1.getName() + ","
                    + tempReg2.getName() + "\n";
            tempReg1.setUsing(false);
            tempReg2.setUsing(false);
            lineStack.remove(indexReg2);
            lineStack.remove(indexReg1);
            lineStack.setElementAt(temp, indexReg1);
        }
        return strOp;
    }

    /**
     * çıkarma işlemini yapar
     *
     * @param op operatör
     * @param indexReg1 operand1 in indexi
     * @param indexReg2 operand2 nin indexi
     * @return çıkarma işleminin assembly karşılığı
     * @throws MyException yanlış işlem
     */
    private String sub(String op, int indexReg1, int indexReg2) throws MyException {
        String strOp = "";
        /**
         * alttaki iki contain değeri satırdaki ifadeye eşdeğer registerın zaten
         * stackimizde olup olmadığını belirler.Bu sayede yeni bir register
         * gerekip gerekmediği belli olur ve ona göre işlem yapılır
         */
        int c1 = contain(lineStack.get(indexReg1).getName());
        int c2 = contain(lineStack.get(indexReg2).getName());
        int valSub;
        /**
         * Buradan sonraki her if için sadece 1 açıklama yapıyorum. İf blokları
         * birbirine çok benzemekte ama herbiri için registerların stack imizde
         * zaten bulunup bulunmamama durumuna göre çalışması için tasarlandı.
         * Duruma göre assembly kod üretildekten sonra gerekiyorsa gerekli
         * registerlar kullanımdışı kalırlar(daha sonra kullanmak için).
         */
        if (c1 >= 0 && c2 >= 0) {
            valSub = (registerStack.get(c1).getValue() - registerStack.get(c2).getValue());
            Register sub = addRegister();
            Operand temp = new Operand(sub.getName(), sub, valSub);
            registerStack.add(temp);
            strOp = strOp + "sub " + temp.getName() + "," + registerStack.get(c1).getRegister().getName() + ","
                    + registerStack.get(c2).getRegister().getName() + "\n";
            registerStack.get(c2).getRegister().setUsing(false);
            lineStack.remove(indexReg2);
            lineStack.remove(indexReg1);
            lineStack.setElementAt(temp, indexReg1);
        } else if (c1 >= 0 && c2 < 0) {
            Register tempReg1 = addRegister();
            Register sub = addRegister();
            strOp = strOp + "li " + tempReg1.getName() + ","
                    + ((Operand) lineStack.get(indexReg2)).getValue() + "\n";
            valSub = (registerStack.get(c1).getValue() - ((Operand) lineStack.get(indexReg2)).getValue());
            Operand temp = new Operand(sub.getName(), sub, valSub);
            registerStack.add(temp);
            strOp = strOp + "sub " + temp.getName() + "," + registerStack.get(c1).getRegister().getName() + ","
                    + tempReg1.getName() + "\n";
            tempReg1.setUsing(false);
            lineStack.remove(indexReg2);
            lineStack.remove(indexReg1);
            lineStack.setElementAt(temp, indexReg1);
        } else if (c1 < 0 && c2 >= 0) {
            Register tempReg1 = addRegister();

            Register sub = addRegister();
            strOp = strOp + "li " + tempReg1.getName() + ","
                    + ((Operand) lineStack.get(indexReg1)).getValue() + "\n";

            valSub = (((Operand) lineStack.get(indexReg1)).getValue() - ((Operand) lineStack.get(indexReg2)).getValue());
            Operand temp = new Operand(sub.getName(), sub, valSub);
            registerStack.add(temp);
            strOp = strOp + "sub " + temp.getName() + "," + tempReg1.getName() + ","
                    + registerStack.get(c2).getRegister().getName() + "\n";
            tempReg1.setUsing(false);
            lineStack.remove(indexReg2);
            lineStack.remove(indexReg1);
            lineStack.setElementAt(temp, indexReg1);
        } else if (c1 < 0 && c2 < 0) {
            Register tempReg1 = addRegister();
            Register tempReg2 = addRegister();
            Register sub = addRegister();
            strOp = strOp + "li " + tempReg1.getName() + ","
                    + ((Operand) lineStack.get(indexReg1)).getValue() + "\n";
            strOp = strOp + "li " + tempReg2.getName() + ","
                    + ((Operand) lineStack.get(indexReg2)).getValue() + "\n";
            valSub = (((Operand) lineStack.get(indexReg1)).getValue() - ((Operand) lineStack.get(indexReg2)).getValue());
            Operand temp = new Operand(sub.getName(), sub, valSub);
            registerStack.add(temp);
            strOp = strOp + "sub " + temp.getName() + "," + tempReg1.getName() + ","
                    + tempReg2.getName() + "\n";
            tempReg1.setUsing(false);
            tempReg2.setUsing(false);
            lineStack.remove(indexReg2);
            lineStack.remove(indexReg1);
            lineStack.setElementAt(temp, indexReg1);

        }
        return strOp;
    }

    /**
     * operasyonları operatöre göre yapar.(alt methodları
     * çağırarak-(add,sub,div,mult,assignment)).
     *
     * @param op operatör
     * @param indexReg1 operand1 in indexi
     * @param indexReg2 operand2 nin indexi
     * @return tek bir opersayonun assembly karşılığı
     * @throws MyException alt methodlardan(add,sub,div,mult,assignment) gelen
     * hatalar
     */
    private String operation(String op, int indexReg1, int indexReg2) throws MyException {
        String strOp = "";
        int c1 = -1;
        int c2 = -1;
        if ("=".equals(op)) {
            strOp = assignment(op, indexReg1, indexReg2);
        } else if ("*".equals(op)) {
            strOp = mult(op, indexReg1, indexReg2);
        } else if ("/".equals(op)) {
            strOp = div(op, indexReg1, indexReg2);
        } else if ("-".equals(op)) {
            strOp = sub(op, indexReg1, indexReg2);
        } else if ("+".equals(op)) {
            strOp = add(op, indexReg1, indexReg2);
        }

        return strOp;
    }

    /**
     * print postfix olmadığından ayrı olarak yazıldı. bu method registerı print
     * eder ve systemcall ile programı sonlandırır.
     *
     * @param index registerın RegisterStack deki indexi
     * @return assembly kod
     * @throws MyException eğer yazılan register yok ise.
     */
    private String print(int index) throws MyException {
        String strOp = "";
        int c = -1;
        c = contain(lineStack.get(index).getName());//registerın varlığını kontrol et
        if (c >= 0) {
            strOp = "move $a0" + "," + registerStack.get(c).getRegister().getName() + "\n"
                    + "li $v0,1\nsyscall";
            return strOp;
        } else {
            throw (new MyException("Böyle bir register yok!"));
        }
    }

    /**
     * çevirme işlemini yapan methodumuzdur. postfix ifadeyi " # " a gör bölerek
     * lineStack e doldurur. lineStack her defasında yeniden doldurulur.
     * Sonrasında bu stack üzerinden değerler operatör işlemine alınır. Sonuç ,
     * bir assembly kodu döndürür.
     *
     * @return assembly kodu
     * @throws Exception operasyonlar sırasında gelen Exceptionlar
     */
    public String convert() throws Exception {
        String[] strArr = postfix.split(" # ");
        for (int i = 0; i < strArr.length; i++) {
            fillStack(strArr[i]);//lineStack dolduruldu
            for (int j = 0; j < lineStack.size(); j++) {
                if ("print".equals(lineStack.get(j).getName())) {
                    output = output + print(j + 1);
                }
                if (lineStack.get(j) instanceof Operator) {
                    output = output + operation(lineStack.get(j).getName(), j - 2, j - 1);
                    j = -1;
                }
            }
            output = output + "\n";//assembly koda eklendi.
            lineStack.clear();//temizlendi
        }
        return output;
    }

}
