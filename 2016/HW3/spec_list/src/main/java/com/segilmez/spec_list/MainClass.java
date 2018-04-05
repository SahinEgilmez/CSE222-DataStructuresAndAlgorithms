/**
 * GEBZE TECHNİCAL UNIVERSITY CSE-222 HOMEWORK 3
 * Şahin Eğilmez 131044059
 */
package com.segilmez.spec_list;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author segil
 */
public class MainClass {

    /**
     * SpecList sınıfını test etmek için kullanacağımız main class
     *
     * @param args terminal kumutu
     * @throws Exception //sortList in yanlış parametre alması sounucu
     * fırlatılabilir.
     */
    public static void main(String[] args) throws Exception {
        //SPECLİST İN INTEGER İÇİN TESTİ
        System.out.println("SPECLİST İN INTEGER İÇİN TESTİ :\n");
        SpecList intListist1 = new SpecList<Integer>();//1. liste
        intListist1.add(5);
        intListist1.add(8);
        intListist1.add(2);
        System.out.println("list1 : " + intListist1.toString());
        LinkedList intList2 = new LinkedList<Integer>();//2. liste
        intList2.add(36);
        intList2.add(2);
        intList2.add(86);
        System.out.println("list2 : " + intList2.toString());
        intListist1.addAllAtHead(intList2);//list1 in en başına list2 nin eklenmesi
        System.out.println("list1 after add All At Head list2 : " + intListist1.toString());
        List intIntersectList = new LinkedList<Integer>();//ortak elemanların listesi
        intIntersectList = intListist1.getIntersectList(intList2);//list1 ve list2 nin ortak
        //elemanları intersevtList eklenir.
        System.out.println("Common elements of list1 and list2 : " + intIntersectList.toString());
        try {//sortList e parameter olarak increasing veya decreasing dışında parametre
            //yollandıysa exception fırlatılır.
            intListist1.sortList("increasing");
            System.out.println("list1 after sorted : " + intListist1.toString());
        } catch (Exception e) {
            System.err.println("Wrong parameter.You must enter increasing or decreasing");
        }

        //SPECLİT İN DOUBLE İÇİN TESTİ
        
        System.out.println("\nSPECLİST İN DOUBLE İÇİN TESTİ :\n");
        SpecList doubleList1 = new SpecList<Double>();//1. liste
        doubleList1.add(7.8);
        doubleList1.add(15.65);
        doubleList1.add(0.80);
        System.out.println("list1 : " + doubleList1.toString());
        LinkedList doubleList2 = new LinkedList<Double>();//2. liste
        doubleList2.add(8.2);
        doubleList2.add(52.65);
        doubleList2.add(14.80);
        System.out.println("list2 : " + doubleList2.toString());
        doubleList1.addAllAtHead(doubleList2);//list1 in en başına list2 nin eklenmesi
        System.out.println("list1 after add All At Head list2 : " + doubleList1.toString());
        List doubleIntersectList = new LinkedList<Double>();//ortak elemanların listesi
        doubleIntersectList = doubleList1.getIntersectList(doubleList2);//list1 ve list2 nin ortak
        //elemanları intersevtList eklenir.
        System.out.println("Common elements of list1 and list2 : " + doubleIntersectList.toString());
        try {//sortList e parameter olarak increasing veya decreasing dışında parametre
            //yollandıysa exception fırlatılır.
            doubleList1.sortList("increasing");
            System.out.println("list1 after sorted : " + doubleList1.toString());
        } catch (Exception e) {
            System.err.println("Wrong parameter.You must enter increasing or decreasing");
        }
        
        //SPECLİT İN CHAR İÇİN TESTİ
        System.out.println("\nSPECLİST İN CHARACTER İÇİN TESTİ :\n");
        SpecList charList1 = new SpecList<Character>();//1. liste
        charList1.add('c');
        charList1.add('i');
        charList1.add('m');
        System.out.println("list1 : " + charList1.toString());
        LinkedList charList2 = new LinkedList<Character>();//2. liste
        charList2.add('b');
        charList2.add('o');
        charList2.add('m');
        System.out.println("list2 : " + charList2.toString());
        charList1.addAllAtHead(charList2);//list1 in en başına list2 nin eklenmesi
        System.out.println("list1 after add All At Head list2 : " + charList1.toString());
        List charIntersectList = new LinkedList<Character>();//ortak elemanların listesi
        charIntersectList = charList1.getIntersectList(charList2);//list1 ve list2 nin ortak
        //elemanları intersevtList eklenir.
        System.out.println("Common elements of list1 and list2 : " + charIntersectList.toString());
        try {//sortList e parameter olarak increasing veya decreasing dışında parametre
            //yollandıysa exception fırlatılır.
            charList1.sortList("decreasing");
            System.out.println("list1 after sorted : " + charList1.toString());
        } catch (Exception e) {
            System.err.println("Wrong parameter.You must enter increasing or decreasing");
        }
    }

}
