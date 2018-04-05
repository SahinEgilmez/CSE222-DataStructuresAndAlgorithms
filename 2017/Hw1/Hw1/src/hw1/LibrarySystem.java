/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw1;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author segilmez
 */
public class LibrarySystem implements LibraryInterface{

    /*DB Lists*/
    ArrayList<User> userList = new ArrayList<>();
    ArrayList<Staff> staffList = new ArrayList<>();
    ArrayList<Book> bookList = new ArrayList<>();
    Database db = new Database();

    /**
     * default constructor assign to lists
     */
    public LibrarySystem() {
        userList = db.read("Users.csv");
        staffList = db.read("Staffs.csv");
        bookList = db.read("Books.csv");

    }

    /**
     * main method of class. sistem() perform terminal login works and call
     * userSystem() or staffSystem()
     */
    public void system() {
        Scanner scan = new Scanner(System.in);
        String input;
        boolean proceed = true;
        do {
            System.out.println("Are you User or Staff? İf you are user, please enter"
                    + " 'USER' \n or if you are Staff, please enter 'STAFF.\n If you want"
                    + " to exit, please enter 'EXIT'");
            input = scan.nextLine();
            if (input.equals("USER")) {
                proceed = userSystem();
            } else if (input.equals("STAFF")) {
                proceed = staffSystem();
            }
        } while (!input.equals("EXIT") && proceed == true);

        db.write("Users.csv", userList);
        db.write("Staffs.csv", staffList);
        db.write("Books.csv", bookList);

    }

    /**
     * userSystem perform program for USER account
     *
     * @return if it is true, BACK before menu. If it is false, direct EXIT
     * program.
     */
    private boolean userSystem() {
        Scanner scan = new Scanner(System.in);
        String input, tc, password;
        boolean proceed = true;
        do {
            System.out.println("'CONTINUE' or 'BACK' or 'EXIT' ?");
            input = scan.nextLine();
            if (input.equals("BACK")) {
                return true;
            } else if (input.equals("EXIT")) {
                return false;
            }
            System.out.println("Please enter TC Identity Number of 11 digits : ");
            tc = scan.nextLine();
            System.out.println("Please enter your password (if you are not change password, it must be '1'.) : ");
            password = scan.nextLine();
        } while (!checkUser(tc, password));
        int index = 0;
        for (User u : userList) {
            if (u.getTCNo().equals(tc)) {
                index = userList.indexOf(u);
            }
        }
        int i;
        do {
            User user = userList.get(index);
            ArrayList<Book> userBooks = new ArrayList<>();
            for (Book bk : bookList) {
                if (bk.getOwner().equals(user.getTCNo())) {
                    userBooks.add(bk);
                }
            }
            System.out.println("TC NO \t\t Name \t\t Surname ");
            System.out.println(user.getTCNo() + "\t\t" + user.getName() + "\t\t" + user.getSurname() + "/n");
            System.out.println("Your books : ");
            i = 1;
            for (Book bk : userBooks) {
                System.out.println(i + "\t\t" + bk.toString());
                i++;
            }
            System.out.println("What do you want to do? 'BORROW BOOK' or 'GIVE BACK BOOK' or 'CHANGE PASSWORD' or 'BACK' or 'EXIT' ?");
            input = scan.nextLine();
            if (input.equals("BACK")) {
                return true;
            } else if (input.equals("EXIT")) {
                return false;
            } else if (input.equals("BORROW BOOK")) {
                i = 1;
                for (Book bk : bookList) {
                    if (bk.getOwner().equals("00000000000")) {
                        System.out.println(i + "\t\t" + bk.toString());
                    }
                    i++;
                }
                System.out.println("Please enter number of the book of list you want to borrow :");
                input = scan.nextLine();
                userList.get(index).borrow(Integer.parseInt(input), bookList);
            } else if (input.equals("GIVE BACK BOOK")) {
                i = 1;
                for (Book bk : bookList) {
                    if (bk.getOwner().equals(user.getTCNo())) {
                        System.out.println(i + "\t\t" + bk.toString());
                    }
                    i++;
                }
                System.out.println("Please enter number of the book of list you want to give back :");
                input = scan.nextLine();
                userList.get(index).giveBack(Integer.parseInt(input), bookList);
            } else if (input.equals("CHANGE PASSWORD")) {
                System.out.println("Please enter new password :");
                input = scan.nextLine();
                userList.get(index).setPassword(input);
            }
        } while (proceed);

        return true;
    }

    /**
     * check user login
     *
     * @param tc TC Identity number
     * @param password user's password
     * @return user is there or not
     */
    private boolean checkUser(String tc, String password) {
        for (User user : userList) {
            if (user.getTCNo().equals(tc)) {
                if (user.getPassword().equals(password)) {
                    System.out.println("Login is successfully.");
                    return true;
                } else {
                    System.out.println("Password is wrong.");
                    return false;
                }
            }
        }
        System.out.println("User cannot find.TC identity is wrong");
        return false;
    }

    /**
     * perform program for STAFF account
     *
     * @return if it is true, BACK before menu. If it is false, direct EXIT
     * program.
     */
    private boolean staffSystem() {
        Scanner scan = new Scanner(System.in);
        String input, tc, password;
        boolean proceed = true;
        do {
            System.out.println("'CONTINUE' or 'BACK' or 'EXIT' ?");
            input = scan.nextLine();
            if (input.equals("BACK")) {
                return true;
            } else if (input.equals("EXIT")) {
                return false;
            }
            System.out.println("Please enter TC Identity Number of 11 digits : ");
            tc = scan.nextLine();
            System.out.println("Please enter your password (if you are not change password, it must be '1'.) : ");
            password = scan.nextLine();
        } while (!checkStaff(tc, password));
        int index = 0;
        for (Staff s : staffList) {
            if (s.getTCNo().equals(tc)) {
                index = staffList.indexOf(s);
            }
        }
        int i;
        do {
            Staff staff = staffList.get(index);
            System.out.println("TC NO \t\t Name \t\t Surname ");
            System.out.println(staff.getTCNo() + "\t\t" + staff.getName() + "\t\t" + staff.getSurname() + "/n");
            System.out.println("What do you want to do? 'ADD BOOK' or 'DELETE BOOK' "
                    + "or 'ADD USER' or 'DELETE USER' or 'ADD STAFF' or 'DELETE STAFF'"
                    + " or 'CHANGE PASSWORD' or 'BACK' or 'EXIT' ?");
            input = scan.nextLine();
            if (input.equals("BACK")) {
                return true;
            } else if (input.equals("EXIT")) {
                return false;
            } else if (input.equals("ADD BOOK")) {
                System.out.println("Please enter book name : ");
                String name = scan.nextLine();
                System.out.println("Please enter book author : ");
                String author = scan.nextLine();
                System.out.println("Please enter book issue year : ");
                String year = scan.nextLine();
                Book book = new Book(name, author, Integer.parseInt(year));
                bookList.add(book);
            } else if (input.equals("DELETE BOOK")) {
                i = 1;
                for (Book bk : bookList) {
                    System.out.println(i + "\t\t" + bk.toString());
                    i++;
                }
                System.out.println("Please enter number of the book of list you want to delete :");
                input = scan.nextLine();
                bookList.remove(Integer.parseInt(input) - 1);
            } else if (input.equals("ADD USER")) {
                System.out.println("Please enter user name : ");
                String name = scan.nextLine();
                System.out.println("Please enter user surname : ");
                String surname = scan.nextLine();
                System.out.println("Please enter user TC Identity number: ");
                String no = scan.nextLine();
                User user = new User(no, name, surname, "1");
                userList.add(user);
            } else if (input.equals("DELETE USER")) {
                i = 1;
                for (User u : userList) {
                    System.out.println(i + "\t\t" + u.toString());
                    i++;
                }
                System.out.println("Please enter number of the user of list you want to delete :");
                input = scan.nextLine();
                userList.remove(Integer.parseInt(input) - 1);
            } else if (input.equals("ADD STAFF")) {
                System.out.println("Please enter staff name : ");
                String name = scan.nextLine();
                System.out.println("Please enter staff surname : ");
                String surname = scan.nextLine();
                System.out.println("Please enter staff TC Identity number: ");
                String no = scan.nextLine();
                Staff st = new Staff(no, name, surname, "1");
                staffList.add(st);
            } else if (input.equals("DELETE STAFF")) {
                i = 1;
                for (Staff s : staffList) {
                    System.out.println(i + "\t\t" + s.toString());
                    i++;
                }
                System.out.println("Please enter number of the staff of list you want to delete :");
                input = scan.nextLine();
                staffList.remove(Integer.parseInt(input) - 1);
            } else if (input.equals("CHANGE PASSWORD")) {
                System.out.println("Please enter new password :");
                input = scan.nextLine();
                staffList.get(index).setPassword(input);
            }
        } while (proceed);

        return true;
    }

    /**
     * check STAFF
     *
     * @param tc TC Identity number
     * @param password staff's password
     * @return staff is there or not
     */
    private boolean checkStaff(String tc, String password) {
        for (Staff staff : staffList) {
            if (staff.getTCNo().equals(tc)) {
                if (staff.getPassword().equals(password)) {
                    System.out.println("Login is successfully.");
                    return true;
                } else {
                    System.out.println("Password is wrong.");
                    return false;
                }
            }
        }
        System.out.println("Staff cannot find.TC identity is wrong");
        return false;
    }

    /**
     *
     * @throws Throwable
     */
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        db.write("Users.csv", userList);
        db.write("Staffs.csv", staffList);
        db.write("Books.csv", bookList);
    }

}
