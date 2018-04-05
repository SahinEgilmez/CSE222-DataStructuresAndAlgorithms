/**
 * GEBZE TECHNİCAL UNİVERSİTY CSE-222 HOMEWORK 3
 * Şahin Eğilmez 131044059
 */
package com.segilmez.spec_list;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * <h1>SpecList</h1>
 * Bu sınıf linkedList sınıfının genişletilmiş halidir. Ekstra 3 method içerir.
 * bunlar : getAllAtHead , getIntersectList ve sortList dir.
 *
 * @author Şahin EĞİLMEZ
 * @param <E> comparable dan türemiş sınıfı (ve ya tipi) gösterir.
 *
 */
public class SpecList<E extends Comparable<E>> extends LinkedList<E> {

    /**
     * Parametresiz constructor
     */
    public SpecList() {
        super();
    }

    /**
     * Başlangıçta c collectionını listeye alan constructor
     *
     * @param c İlk oluştuğunda listin içinde bu c collection' ı bulunacak.
     */
    public SpecList(Collection<? extends E> c) {
        super(c);
    }

    /**
     * Method aldığı c collectionını bizim listin en başına ekler.
     *
     * @param c listemizin başına eklenecek collection
     * @return başarılı ve ya başarısız olmasına göre true ve ya false
     */
    Boolean addAllAtHead(Collection<? extends E> c) {
        return super.addAll(c);
    }

    /**
     * Bu method parametre olarak aldığı c collection ı ve bizim list'imiz de
     * ortak olan elemanları bulur ve bir liste yazıp return eder.
     *
     * @param c ortak elemanların aranacağı collection
     * @return ortak elemanlardan oluşan liste
     */
    List<E> getIntersectList(Collection<? extends E> c) {
        List<E> list = new LinkedList<E>();
        for (int i = 0; i < this.size(); ++i) {
            if (c.contains(this.get(i)) && !(list.contains(this.get(i)))) {
                list.add(this.get(i));
            }
        }
        return list;
    }

    /**
     * Bu method listemiz içindeki elemanları compareTo methoduna göre
     * karşılaştırarak sıralar. Bunu yaparken aldığı sortType parametresine göre
     * artan veya azalan şeklinde sıralar.
     *
     * @param sortType "increasing" olursa artan şekilde, "decreasing olursa
     * azalan" şekilde sıralama yapılır.
     * @return sıralanmış olarak kendi listemiz return edilir.
     * @throws Exception eğer parametre 2 seçenek de değilse neye göre sıralama
     * yapılacağı bilinmediğinden Exception yapılır. Ama sıralama gene
     * varsayılan olarak artan şekilde yapılacaktır.
     */
    List<E> sortList(String sortType) throws Exception {
        boolean swapped;
        int compareType = 1;//artansa 1 ,azalansa -1
        if (sortType == "increasing") {//artan olarak sıralama yapılır.
            compareType = 1;
        } else if (sortType == "decreasing") {//azalan olarak sıralama yapılır.
            compareType = -1;
        } else {
            Exception exp = new Exception("Unacceptable parameter is (String sortType)");
            throw exp;
        }
        //döngü swap edileck değer kalmaya devam ediyorsa devam eder.
        do {
            swapped = false;
            for (int i = 0; i < this.size() - 1; ++i) {
                if (((compareType == 1) && (this.get(i).compareTo(this.get(i + 1)) > 0))
                        || ((compareType == -1) && (this.get(i).compareTo(this.get(i + 1)) < 0))) {
                    E temp = this.get(i);
                    this.set(i, this.get(i + 1));
                    this.set(i + 1, temp);
                    swapped = true;
                }
            }
            if (swapped == false) {
                break;
            }
            swapped = false;
            for (int i = this.size() - 1; i > 0; --i) {
                if (((compareType == 1) && (this.get(i-1).compareTo(this.get(i)) > 0))
                        || ((compareType == -1) && (this.get(i-1).compareTo(this.get(i ))) < 0)) {
                    E temp = this.get(i - 1);
                    this.set(i - 1, this.get(i));
                    this.set(i, temp);
                    swapped = true;
                }
            }

        } while (swapped);
        return this;//kendi listemizi return ediyoruz.
    }
}
