package Model;

public class Mybook {
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
        return  "OCLC Number: " + oclcNumber +
                "\n\nTitle: " + title +
                "\n\nAuthors: " + authors +
                "\n\nYear: " + year +
                "\n\nPublisher: " + publisher +
                "\n\nGenre: " + genre +
                "\n\nPhysical Description: " + physicalDescription +
                "\n\nISBN: " + isbn +
                "\n\nSummary: " + summary +
                "\n\n---------------------\n";
    }
}