
public class Dvd {

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

        return "\nOCLC NUMBER : " + oclcNumber +
                "\n\nTITLE : " + title +
                "\n\nCAST : " + cast +
                "\n\nCREDITS : " + credits +
                "\n\nPLOT : " + plot +
                "\n\nYEAR : " + year +
                "\n\nLANGUAGE : " + language +
                "\n\nPUBLISHER : " + publisher +
                "\n\nGENRE : " + genre +
                "\n\nPHYSICAL DESCRIPTION : " + physicalDescription +
                "\n\nISBN : " + isbn +
                "\n\n---------------------------------------\n";
    }
}