package Model;
public class Myloan {

    public String oclcNumber;
    public String loan;
    
    @Override
    public String toString() {
        return 
               "OCLC Number: " + oclcNumber +
               "\n\nLoan: " + loan +
               "\n---------------------\n";
    }
}