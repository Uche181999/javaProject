

public class Book {
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
        return  "OCLC NUMBER : " + oclcNumber +
                "\n\nTITLE : " + title +
                "\n\nAUTHOR : " + authors +
                "\n\nYEAR : " + year +
                "\n\nPUBLISHER : " + publisher +
                "\n\nGENRE : " + genre +
                "\n\nPHYSICAL DESCRIPTION : " + physicalDescription +
                "\n\nISBN : " + isbn +
                "\n\nSUMMARY : " + summary +
                "\n\n---------------------------------------\n";
    }
}