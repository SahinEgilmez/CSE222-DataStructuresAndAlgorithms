package hw1;

import java.util.ArrayList;

/**
 * User account class
 *
 * @author segilmez
 */
public class User extends Person {
    /**
     * constructor use Person contructor
     * @param strNo
     * @param strName
     * @param strSurname
     * @param strPassword 
     */
    public User(String strNo, String strName, String strSurname, String strPassword) {
        super(strNo, strName, strSurname, strPassword);
    }
    /**
     * getter type 
     * @return 
     */
    @Override
    public String getType() {
        return "User";
    }

    /**
     * method perform borrowing book
     * @param i list index - 1 
     * @param list book list
     */
    public void borrow(int i, ArrayList<Book> list) {
        list.get(i - 1).setOwner(getTCNo());
    }

    /**
     * method perform give back book
     * @param i 
     * @param list
     */
    public void giveBack(int i, ArrayList<Book> list) {
        list.get(i - 1).setOwner("00000000000");
    }
    /**
     * equals method check equivilant
     * @return 
    */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof User)) {
            return false;
        }
        User user = (User) obj;
        return user.getTCNo().equals(getTCNo());
    }
}
