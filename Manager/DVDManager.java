package Manager;
import java.io.*;
import java.util.HashMap;

import Model.Mydvd;
import Util.Helper;

public class DVDManager {

    private HashMap<String,Mydvd> resources = new HashMap<>();

    public HashMap<String,Mydvd> getResources() {
        return resources;
    }

    public void loadDVDs(String filePath) {

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            String line;
            Mydvd current = null;

            while ((line = br.readLine()) != null) {

                line = line.trim();
                if (line.isEmpty()) continue;

                // ===== END OF ENTRY =====
                if (line.startsWith("--- END OF ENTRY ---")
                        || line.startsWith("-------------------------------------------------------------------")) {

                    if (current != null) {
                        resources.put(current.oclcNumber,current);
                        current = null;
                    }
                    continue;
                }

                if (current == null)
                    current = new Mydvd();

                // ===== SIMPLE FIELDS =====

                if (line.startsWith("OCLC Number:"))
                    current.oclcNumber = Helper.readNextValue(br);

                else if (line.startsWith("Title:"))
                    current.title = Helper.readNextValue(br);

                else if (line.startsWith("Cast:"))
                    current.cast = Helper.readNextValue(br);
                
                else if (line.startsWith("Plot:"))
                    current.plot = Helper.readNextValue(br);

                else if (line.startsWith("Credits:"))
                    current.credits = Helper.readNextValue(br);

                else if (line.startsWith("Year of release:"))
                    current.year = Helper.readNextValue(br);

                else if (line.startsWith("Language:"))
                    current.language = Helper.readNextValue(br);

                else if (line.startsWith("Publisher:"))
                    current.publisher = Helper.readNextValue(br);

                else if (line.startsWith("Genre:"))
                    current.genre = Helper.readNextValue(br);

                else if (line.startsWith("Physical Description:"))
                    current.physicalDescription = Helper.readNextValue(br);

                else if (line.startsWith("ISBN:"))
                    current.isbn = Helper.readNextValue(br);


            }
            // Add last CD if file doesn't end with separator
            if (current != null) {
                resources.put(current.oclcNumber,current);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}