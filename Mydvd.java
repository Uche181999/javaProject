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

        return "OCLC Number: " + oclcNumber +
                "\nTitle: " + title +
                "\nCast: " + cast +
                "\nCredits: " + credits +
                "\nPlot: " + plot +
                "\nYear: " + year +
                "\nLanguage: " + language +
                "\nPublisher: " + publisher +
                "\nGenre: " + genre +
                "\nPhysical Description: " + physicalDescription +
                "\nISBN: " + isbn +
                "\n--------------------------------------------------\n";
    }
}