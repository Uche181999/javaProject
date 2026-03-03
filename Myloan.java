public class Myloan {

    public String oclcNumber;
    public String loan;
    
    @Override
    public String toString() {
        return 
               "\nOCLC Number: " + oclcNumber +
               "\nLOan" +loan +
               "\n---------------------\n";
    }
}