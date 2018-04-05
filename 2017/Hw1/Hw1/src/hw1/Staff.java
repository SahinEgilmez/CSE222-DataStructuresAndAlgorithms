package hw1;

import java.util.ArrayList;

/**
 *
 * @author segilmez
 */
public class Staff extends Person {
    /**
     * contructor use Person constructor
     * @param strNo
     * @param strName
     * @param strSurname
     * @param strPassword 
     */
    public Staff(String strNo, String strName, String strSurname, String strPassword) {
        super(strNo, strName, strSurname, strPassword);
    }
    /**
     * getter type Staff account
     * @return 
     */
    @Override
    public String getType() {
        return "Staff";
    }
    /**
     * add user
     * @param user
     * @param userList
     * @return 
     */
    public boolean addUser(User user, ArrayList<User> userList) {
        for (User u : userList) {
            if (u.equals(user)) {
                return false;
            }
        }
        userList.add(user);
        return true;
    }
    /**
     * delete user
     * @param tc
     * @param userList
     * @return 
     */
    public boolean deleteUser(String tc, ArrayList<User> userList) {
        for (User user : userList) {
            if (user.getTCNo().equals(tc)) {
                userList.remove(user);
                return true;
            }
        }
        return false;
    }
    /**
     * add book 
     * @param book
     * @param bookList
     * @return 
     */
    public boolean addBook(Book book, ArrayList<Book> bookList){
        for (Book bk : bookList) {
            if (bk.equals(book)) {
                return false;
            }
        }
        bookList.add(book);
        return true;
    }
    /**
     * equasl() perform equivilant
     * @param obj
     * @return 
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Staff)) {
            return false;
        }

        Staff staff = (Staff) obj;
        return staff.getTCNo().equals(getTCNo());
    }

}
