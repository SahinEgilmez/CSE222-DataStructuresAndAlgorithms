package hw1;

/**
 * Created by segil on 21.02.2017.
 */
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Database implements DBInterface{

    //Delimiter used in CSV file
    private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE = "\n";
    /**
     * default constructor not need anything.
     */
    public Database() {
    }
    /**
     * read() method perform read .csv files and fill arraylists. 
     * @param fileName "Staff.csv" or"User.csv" or "Books.csv" 
     * @return filled arraylist
     */
    @Override
    public ArrayList read(String fileName) {
        ArrayList list = new ArrayList();
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            if (fileName.equals("Users.csv")) {
                while ((line = br.readLine()) != null) {
                    String[] elem = line.split(",");
                    User user = new User(elem[0], elem[1], elem[2], elem[3]);
                    list.add(user);
                }
            } else if (fileName.equals("Staffs.csv")) {
                while ((line = br.readLine()) != null) {
                    String[] elem = line.split(",");
                    Staff staff = new Staff(elem[0], elem[1], elem[2], elem[3]);
                    list.add(staff);
                }
            } else if (fileName.equals("Books.csv")) {
                while ((line = br.readLine()) != null) {
                    String[] elem = line.split(",");
                    Book book = new Book(elem[0], elem[1], Integer.parseInt(elem[2]));
                    book.setOwner(elem[3]);
                    list.add(book);
                }
            }
        } catch (FileNotFoundException e) {
            //Fıle is not already exist
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
    /**
     * method perform writing to file from arraylist
     * @param fileName "Staff.csv" or"User.csv" or "Books.csv" 
     * @param list reading  list 
     */
    @Override
    public void write(String fileName, ArrayList list) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(fileName);
            for (Object obj : list) {
                if (fileName.equals("Users.csv")) {
                    User user = (User) obj;
                    fileWriter.append(user.getTCNo() + COMMA_DELIMITER);
                    fileWriter.append(user.getName() + COMMA_DELIMITER);
                    fileWriter.append(user.getSurname() + COMMA_DELIMITER);
                    fileWriter.append(user.getPassword() + NEW_LINE);
                } else if (fileName.equals("Staffs.csv")) {
                    Staff staff = (Staff) obj;
                    fileWriter.append(staff.getTCNo() + COMMA_DELIMITER);
                    fileWriter.append(staff.getName() + COMMA_DELIMITER);
                    fileWriter.append(staff.getSurname() + COMMA_DELIMITER);
                    fileWriter.append(staff.getPassword() + NEW_LINE);
                } else if (fileName.equals("Books.csv")) {
                    Book book = (Book) obj;
                    fileWriter.append(book.getName() + COMMA_DELIMITER);
                    fileWriter.append(book.getAuthor() + COMMA_DELIMITER);
                    fileWriter.append(book.getDateOfIssue() + COMMA_DELIMITER);
                    fileWriter.append(book.getOwner() + NEW_LINE);
                }
            }
            /*administrator append*/
            if (fileName.equals("Staffs.csv") && list.isEmpty()) {
                Staff staff = new Staff("00000000000", "admin", "admin", "1");
                fileWriter.append(staff.getTCNo() + COMMA_DELIMITER);
                fileWriter.append(staff.getName() + COMMA_DELIMITER);
                fileWriter.append(staff.getSurname() + COMMA_DELIMITER);
                fileWriter.append(staff.getPassword() + NEW_LINE);
            }
        } catch (Exception e) {
            System.out.println("Error  FileWriter !!!");
            e.printStackTrace();
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
