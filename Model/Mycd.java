package Model;
public class Mycd {

    public String oclcNumber;
    public String title;
    public String performers;
    public String credits;
    public String description;
    public String year;
    public String language;
    public String publisher;
    public String genre;
    public String physicalDescription;
    public String isbn;

    @Override
    public String toString() {

        return "\nOCLC Number: " + oclcNumber +
                "\n\nTitle: " + title +
                "\n\nPerformers: " + performers +
                "\n\nCredits: " + credits +
                "\n\nbDescription: " + description +
                "\n\nYear: " + year +
                "\n\nLanguage: " + language +
                "\n\nPublisher: " + publisher +
                "\n\nGenre: " + genre +
                "\n\nPhysical Description: " + physicalDescription +
                "\n\nISBN: " + isbn +
                "\n\n---------------------\n";
    }
}