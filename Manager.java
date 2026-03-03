import java.util.ArrayList;

public class Manager<T> {

    private ArrayList<T> resources;

    public Manager(String filePath) {
        String lower = filePath.toLowerCase();
        if (lower.contains("book")) {
            BookManager bookManager = new BookManager();
            bookManager.loadBooks(filePath);
            resources = (ArrayList<T>) bookManager.getResources(); // safe if T=Mybook
        }
        else if (lower.contains("cd")) {
            CDManager CDManager = new CDManager();
            CDManager.loadCDs(filePath);
            resources = (ArrayList<T>) CDManager.getResources(); // safe if T=Mybook
        }
        else if (lower.contains("loan")) {
            LoanManager LoanManager = new LoanManager();
            LoanManager.loadLoans(filePath);
            resources = (ArrayList<T>) LoanManager.getResources(); // safe if T=Mybook
        }

            else if (lower.contains("dvd")) {
        DVDManager DVDManager = new DVDManager();
        DVDManager.loadDVDs(filePath);
        resources = (ArrayList<T>) DVDManager.getResources(); // safe if T=Mybook
    }
        // handle CDs, Loans similarly
    }

    public ArrayList<T> getResources() {
        return resources;
    }
}