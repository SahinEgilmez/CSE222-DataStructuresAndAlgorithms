/**
 * Created by segil on 21.02.2017.
 */
package hw1;

import java.util.ArrayList;

public interface DBInterface {

    /**
     * read() method perform read .csv files and fill arraylists.
     *
     * @param fileName "Staff.csv" or"User.csv" or "Books.csv"
     * @return filled arraylist
     */
    public ArrayList read(String fileName);

    /**
     * method perform writing to file from arraylist
     *
     * @param fileName "Staff.csv" or"User.csv" or "Books.csv"
     * @param list reading list
     */
    public void write(String fileName, ArrayList list);
}
