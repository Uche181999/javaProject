import java.io.*;
import java.util.ArrayList;



public class LoanManager {

        private ArrayList<Myloan> resources = new ArrayList<>();

        public ArrayList<Myloan> getResources() {
            return resources;
        }

        // ================= BOOK PARSER =================
        public void loadLoans(String filePath) {

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            String line;
            Myloan current = null;

            while ((line = br.readLine()) != null) {

                line = line.trim();

                if (line.isEmpty())
                    continue;

                // ENTRY END
                if (line.startsWith("--- END OF ENTRY ---")
                        || line.startsWith("-------------------------------------------------------------------")) {

                    if (current != null) {
                        resources.add(current);
                        current = null;
                    }
                    continue;
                }

                if (current == null)
                    current = new Myloan ();

                // ========= SIMPLE FIELDS =========

                if (line.startsWith("OCLC Number:")) {
                    current.oclcNumber = Helper.readNextValue(br);
                    continue;
                }

                if (line.startsWith("Title:")) {
                    current.title = Helper.readNextValue(br);
                    continue;
                }

                if (line.startsWith("Authors:")
                        || line.startsWith("Author:")) {
                    current.authors = Helper.readNextValue(br);
                    continue;
                }

                if (line.startsWith("Year of publication:")) {
                    current.year = Helper.readNextValue(br);
                    continue;
                }

                if (line.startsWith("Publisher:")) {
                    current.publisher = Helper.readNextValue(br);
                    continue;
                }

                if (line.startsWith("Genre:")) {
                    current.genre = Helper.readNextValue(br);
                    continue;
                }

                if (line.startsWith("Physical Description:")) {
                    current.physicalDescription = Helper.readNextValue(br);
                    continue;
                }

                if (line.startsWith("ISBN:")) {
                    current.isbn = Helper.readNextValue(br);
                    continue;
                }

                // ========= SUMMARY (SPECIAL CASE) =========

                if (line.startsWith("Summary:")) {

                    StringBuilder summaryBuilder = new StringBuilder();

                    while ((line = br.readLine()) != null) {

                        line = line.trim();

                        if (line.startsWith("Year of publication:")
                                || line.startsWith("Publisher:")
                                || line.startsWith("Genre:")
                                || line.startsWith("Physical Description:")
                                || line.startsWith("ISBN:")
                                || line.startsWith("--- END OF ENTRY ---")
                                || line.startsWith("-------------------------------------------------------------------")) {
                            break;
                        }

                        if (!line.isEmpty())
                            summaryBuilder.append(line).append("\n");
                    }

                    current.summary = summaryBuilder.toString().trim();

                    // IMPORTANT:
                    // We already read the next field line,
                    // so process it in next loop iteration.
                    if (line != null) {
                        // push it back logically by handling it again
                        // easiest way: manually process it
                        if (line.startsWith("Year of publication:"))
                            current.year = Helper.readNextValue(br);
                        else if (line.startsWith("Publisher:"))
                            current.publisher = Helper.readNextValue(br);
                        else if (line.startsWith("Genre:"))
                            current.genre = Helper.readNextValue(br);
                        else if (line.startsWith("Physical Description:"))
                            current.physicalDescription = Helper.readNextValue(br);
                        else if (line.startsWith("ISBN:"))
                            current.isbn = Helper.readNextValue(br);
                    }

                    continue;
                }
            }

            if (current != null)
                resources.add(current);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }}