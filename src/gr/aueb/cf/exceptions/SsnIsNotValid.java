package gr.aueb.cf.exceptions;

public class SsnIsNotValid extends Exception {
    private static final long serialVersionUID = 1L;
    public SsnIsNotValid(String ssn) {
        super("Ssn" + ssn + "is not valid");
    }
}
