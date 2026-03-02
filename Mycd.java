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

        return "OCLC Number: " + oclcNumber +
                "\nTitle: " + title +
                "\nPerformers: " + performers +
                "\nCredits: " + credits +
                "\nDescription: " + description +
                "\nYear: " + year +
                "\nLanguage: " + language +
                "\nPublisher: " + publisher +
                "\nGenre: " + genre +
                "\nPhysical Description: " + physicalDescription +
                "\nISBN: " + isbn;
    }
}