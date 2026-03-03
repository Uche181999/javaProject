import java.io.*;
import java.util.ArrayList;

public class CDManager<T> {

        private ArrayList<Mycd> resources = new ArrayList<>();
        


        public ArrayList<Mycd> getResources() {
            return resources;
        }
// ================= CD PARSER =================

        public void loadCDs(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            String line;
            Mycd current = null;
            StringBuilder descriptionBuilder = null;

            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                // separator indicates end of entry
                    if (line.startsWith("--- END OF ENTRY ---") || line.startsWith("-------------------------------------------------------------------")) {
                    if (current != null) {
                        if (descriptionBuilder != null)
                            current.description = descriptionBuilder.toString().trim();
                        resources.add(current);
                        current = null;
                        descriptionBuilder = null;
                    }
                    continue;
                }

                if (current == null) current = new Mycd();

                // parse fields
                if (line.startsWith("OCLC Number:"))
                    current.oclcNumber = Helper.readNextValue(br);
                else if (line.startsWith("Title:"))
                    current.title = Helper.readNextValue(br);
                else if (line.startsWith("Performers:"))
                    current.performers = Helper.readNextValue(br);
                else if (line.startsWith("Credits:"))
                    current.credits = Helper.readNextValue(br);
                else if (line.startsWith("Description:")) {
                    descriptionBuilder = new StringBuilder();
                    while ((line = br.readLine()) != null) {
                        line = line.trim();
                        // stop description if next field or separator
                        if (line.startsWith("Year of release:") ||
                            line.startsWith("Language:") ||
                            line.startsWith("Publisher:") ||
                            line.startsWith("Genre:") ||
                            line.startsWith("Physical Description:") ||
                            line.startsWith("ISBN:") ||
                            line.startsWith("-------------------------------------------------------------------")) {
                            break;
                        }
                        descriptionBuilder.append(line).append("\n");
                    }
                    current.description = descriptionBuilder.toString().trim();

                    // continue processing the field that stopped description
                    if (line == null) break;
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
                    continue;
                }
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

            // add last CD if file didn't end with separator
            if (current != null) {
                resources.add(current);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
