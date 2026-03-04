
public class Cd {

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

        return "\nOCLC NUMBER : " + oclcNumber +
                "\n\nTITLE : " + title +
                "\n\nPERFORMERS : " + performers +
                "\n\nCREDITS : " + credits +
                "\n\nDESCRIPTION : " + description +
                "\n\nYEAR : " + year +
                "\n\nLANGUAGE : " + language +
                "\n\nPUBLISHER : " + publisher +
                "\n\nGENRE : " + genre +
                "\n\nPHYSICAL DESCRIPTION : " + physicalDescription +
                "\n\nISBN: " + isbn +
                "\n\n---------------------------------------\n";
    }
}