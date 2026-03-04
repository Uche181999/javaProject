
import java.util.HashMap;

public class Manager<T> {

    private HashMap<String, T> resources;

    public Manager(String filePath) {
        String lower = filePath.toLowerCase();

        if (lower.contains("book")) {
            BookManager bookManager = new BookManager();
            bookManager.loadBooks(filePath);

            // now getResources returns HashMap<String, Mybook
            @SuppressWarnings("unchecked")
            HashMap<String, T> tmp = (HashMap<String, T>) bookManager.getResources(); 
            resources =tmp;
        } 
        else if (lower.contains("cd")) {
            CDManager cdManager = new CDManager();
            cdManager.loadCDs(filePath);
            @SuppressWarnings("unchecked")
            HashMap<String, T> tmp = (HashMap<String, T>) cdManager.getResources(); 
            resources =tmp;
        } 
        else if (lower.contains("loan")) {
            LoanManager loanManager = new LoanManager();
            loanManager.loadLoans(filePath);
            @SuppressWarnings("unchecked")
            HashMap<String, T> tmp = (HashMap<String, T>) loanManager.getResources(); 
            resources =tmp;
        } 
        else if (lower.contains("dvd")) {
            DVDManager dvdManager = new DVDManager();
            dvdManager.loadDVDs(filePath);
            @SuppressWarnings("unchecked")
            HashMap<String, T> tmp = (HashMap<String, T>) dvdManager.getResources(); 
            resources =tmp;
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