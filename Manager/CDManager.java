package Manager;
import java.io.*;
import java.util.HashMap;

import Model.Mycd;
import Util.Helper;

public class CDManager {

    private HashMap<String,Mycd> resources = new HashMap<>();

    public HashMap<String,Mycd> getResources() {
        return resources;
    }

    public void loadCDs(String filePath) {

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            String line;
            Mycd current = null;
            StringBuilder descriptionBuilder = null;

            while ((line = br.readLine()) != null) {

                line = line.trim();
                if (line.isEmpty()) continue;

                // ===== END OF ENTRY =====
                if (line.startsWith("--- END OF ENTRY ---")
                        || line.startsWith("-------------------------------------------------------------------")) {

                    if (current != null) {
                        resources.put(current.oclcNumber,current);
                        current = null;
                        descriptionBuilder = null;
                    }
                    continue;
                }

                if (current == null)
                    current = new Mycd();

                // ===== SIMPLE FIELDS =====

                if (line.startsWith("OCLC Number:"))
                    current.oclcNumber = Helper.readNextValue(br);

                else if (line.startsWith("Title:"))
                    current.title = Helper.readNextValue(br);

                else if (line.startsWith("Performers:"))
                    current.performers = Helper.readNextValue(br);

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

                // ===== MULTI-LINE DESCRIPTION =====
                else if (line.startsWith("Description:")) {

                    descriptionBuilder = new StringBuilder();

                    while ((line = br.readLine()) != null) {

                        line = line.trim();

                        if (line.startsWith("Year of release:")
                                || line.startsWith("Language:")
                                || line.startsWith("Publisher:")
                                || line.startsWith("Genre:")
                                || line.startsWith("Physical Description:")
                                || line.startsWith("ISBN:")
                                || line.startsWith("--- END OF ENTRY ---")
                                || line.startsWith("-------------------------------------------------------------------")) {
                            break;
                        }

                        descriptionBuilder.append(line).append("\n");
                    }

                    current.description = descriptionBuilder.toString().trim();

                    // Handle the field that stopped the description
                    if (line != null) {

                        if (line.startsWith("Year of release:"))
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
                }
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