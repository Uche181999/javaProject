package Manager;
import java.util.HashMap;

public class Manager<T> {

    private HashMap<String, T> resources;

    public Manager(String filePath) {
        String lower = filePath.toLowerCase();

        if (lower.contains("book")) {
            BookManager bookManager = new BookManager();
            bookManager.loadBooks(filePath);
            // now getResources returns HashMap<String, Mybook
            resources = (HashMap<String, T>) bookManager.getResources(); 
        } 
        else if (lower.contains("cd")) {
            CDManager cdManager = new CDManager();
            cdManager.loadCDs(filePath);
            resources = (HashMap<String, T>) cdManager.getResources();
        } 
        else if (lower.contains("loan")) {
            LoanManager loanManager = new LoanManager();
            loanManager.loadLoans(filePath);
            resources = (HashMap<String, T>) loanManager.getResources();
        } 
        else if (lower.contains("dvd")) {
            DVDManager dvdManager = new DVDManager();
            dvdManager.loadDVDs(filePath);
            resources = (HashMap<String, T>) dvdManager.getResources();
        }
    }

    public HashMap<String, T> getResources() {
        return resources;
    }

    // optional helper: get by key
    public T get(String key) {
        return resources.get(key);
    }
}