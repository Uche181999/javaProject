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

                if (line.startsWith("ON LOAN:")) {
                    current.loan = Helper.readNextValue(br);
                    continue;
                }
            }

            if (current != null)
                resources.add(current);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }}