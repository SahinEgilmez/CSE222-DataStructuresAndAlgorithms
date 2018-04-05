package hw1;

/**
 *
 * @author segilmez
 */
class Book {

    private String name;
    private String author;
    private Integer dateOfIssue;
    private String ownerNo;
    /**
     * default constructor
     */
    public Book() {
    }
    /**
     * constructor assign name, date, author
     * @param strName
     * @param strAuthor
     * @param intDateOfIssue 
     */
    public Book(String strName, String strAuthor, Integer intDateOfIssue) {
        name = strName;
        author = strAuthor;
        dateOfIssue = intDateOfIssue;
        ownerNo = "00000000000"; //Owner is Library
    }
    /**
     * getter owner pf TC ID
     * @return 
     */
    public String getOwner() {
        return ownerNo;
    }
    /**
     * set to owner TC
     * @param strOwnerNo 
     */
    public void setOwner(String strOwnerNo) {
        ownerNo = strOwnerNo;
    }
    /**
     * getter name
     * @return 
     */
    public String getName() {
        return name;
    }
    /**
     * getter author
     * @return 
     */
    public String getAuthor() {
        return author;
    }
    /**
     * getter date of issue
     * @return 
     */
    public int getDateOfIssue() {
        return dateOfIssue;
    }
    /**
     * ovirridee equals
     * @param obj
     * @return 
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Book)) {
            return false;
        }
        final Book other = (Book) obj;
        if (name.equals(other.name) && author.equals(other.author)
                && dateOfIssue.equals(other.dateOfIssue)
                && ownerNo.equals(other.getOwner())) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * to String
     * @return 
     */
    @Override
    public String toString() {
        return "" + getName() + "\t\t" + getAuthor() + "\t\t" + getDateOfIssue();
    }
}
