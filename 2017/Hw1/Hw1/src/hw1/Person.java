package hw1;

/**
 * Person is subclass User and Staff
 * @author segilmez
 */
public abstract class Person implements PersonInterface {

    protected String name;
    protected String surname;
    protected String password;
    protected String TCNo;

    public Person(String strNo,String strName, String strSurname, String strPassword){
        name=strName;
        surname=strSurname;
        TCNo=strNo;
        password=strPassword;
    }

    /**
     * getter of person name
     * @return person name
     */
    @Override
    public String getName() {
        return name;
    }
    /**
     * getter of person surname
     * @return person surname
     */
    @Override
    public String getSurname() {
        return surname;
    }
    /**
     * getter of TC Identity number
     * @return TC no
     */
    @Override
    public String getTCNo() {
        return TCNo;
    }
    /**
     * getter of person password
     * @return person password
     */
    @Override
    public String getPassword() {
        return password;
    }
    /**
     * set to person name
     * @param strName 
     */
    @Override
    public void setName(String strName) {
        name = strName;
    }
    /**
     * set surname
     * @param strSurname 
     */
    @Override
    public void setSurname(String strSurname) {
        surname =strSurname;
    }
    /**
     * set TC Identity number
     * @param no 
     */
    @Override
    public void setTCNo(String no) {
        TCNo = no;
    }
    /**
     * set to password
     * @param strPassword 
     */
    @Override
    public void setPassword(String strPassword) {
        password = strPassword;
    }
    /**
     * type of person 
     * @return 
     */
    public abstract String getType();

}
