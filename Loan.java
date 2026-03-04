
public class Loan {

    public String oclcNumber;
    public String loan;
    
    @Override
    public String toString() {
        return 
               "OCLC NUMBER : " + oclcNumber +
               "\n\nLOAN : " + loan +
               "\n---------------------------------------\n";
    }
}