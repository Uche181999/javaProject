
import java.io.*;
import java.util.HashMap;



public class LoanManager {

        private HashMap<String,Loan>resources = new HashMap<>();

        public HashMap<String,Loan> getResources() {
            return resources;
        }

        // ================= BOOK PARSER =================
        public void loadLoans(String filePath) {

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            String line;
            Loan current = null;

            while ((line = br.readLine()) != null) {

                line = line.trim();

                if (line.isEmpty())
                    continue;

                // ENTRY END
                if (line.startsWith("--- END OF ENTRY ---")
                        || line.startsWith("-------------------------------------------------------------------")) {

                    if (current != null) {
                        resources.put(current.oclcNumber,current);
                        current = null;
                    }
                    continue;
                }

                if (current == null)
                    current = new Loan ();

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
                resources.put(current.oclcNumber,current);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }}