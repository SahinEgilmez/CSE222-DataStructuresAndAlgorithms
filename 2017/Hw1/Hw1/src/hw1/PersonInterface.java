package hw1;

/**
 *
 * @author segilmez
 */
public interface PersonInterface {

    /**
     * getter of person name
     *
     * @return person name
     */
    public String getName();

    /**
     * getter of person surname
     *
     * @return person surname
     */
    public String getSurname();

    /**
     * getter of TC Identity number
     *
     * @return TC no
     */
    public String getTCNo();

    /**
     * getter of person password
     *
     * @return person password
     */
    public String getPassword();

    /**
     * set to person name
     *
     * @param str
     */
    public void setName(String str);

    /**
     * set surname
     *
     * @param str
     */
    public void setSurname(String str);

    /**
     * set TC Identity number
     *
     * @param no
     */
    public void setTCNo(String no);

    /**
     * set to password
     *
     * @param str
     */
    public void setPassword(String str);
}
