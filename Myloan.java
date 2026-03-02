public class Myloan {

    public String oclcNumber;
    public String title;
    public String authors;
    public String summary;
    public String year;
    public String publisher;
    public String genre;
    public String physicalDescription;
    public String isbn;
    
    @Override
    public String toString() {
        return "Title: " + title +
               "\nAuthors: " + authors +
               "\nYear: " + year +
               "\nPublisher: " + publisher +
               "\nGenre: " + genre +
               "\nPhysical Description: " + physicalDescription +
               "\nISBN: " + isbn +
               "\nOCLC Number: " + oclcNumber +
               "\nSummary: " + summary +
               "\n---------------------\n";
    }
}