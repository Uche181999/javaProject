package Model;
public class Mydvd {

    public String oclcNumber;
    public String title;
    public String cast;
    public String credits;
    public String plot;
    public String language;
    public String year;
    public String publisher;
    public String genre;
    public String physicalDescription;
    public String isbn;
    @Override
    public String toString() {

        return "\nOCLC Number: " + oclcNumber +
                "\n\nTitle: " + title +
                "\n\nCast: " + cast +
                "\n\nCredits: " + credits +
                "\n\nPlot: " + plot +
                "\n\nYear: " + year +
                "\n\nLanguage: " + language +
                "\n\nPublisher: " + publisher +
                "\n\nGenre: " + genre +
                "\n\nPhysical Description: " + physicalDescription +
                "\n\nISBN: " + isbn +
                "\n--------------------------------------------------\n";
    }
}