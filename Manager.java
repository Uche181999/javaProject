import java.io.*;
import java.util.ArrayList;

public class Manager<T> {

    private ArrayList<T> resources = new ArrayList<>();

    public Manager(String filePath) {
        String lower = filePath.toLowerCase();
        if (lower.contains("book")) loadBooks(filePath);
        // else if (lower.contains("dvd")) loadDVDs(filePath);
        else if (lower.contains("cd")) loadCDs(filePath);
        else if (lower.contains("loan")) loadLoans(filePath);
    }

    public ArrayList<T> getResources() {
        return resources;
    }

    // ================= BOOK PARSER =================
    private void loadBooks(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            String line;
            Mybook current = null;
            StringBuilder summaryBuilder = null;

            while ((line = br.readLine()) != null) {
                line = line.trim();

                // skip empty lines
                if (line.isEmpty()) continue;

                // entry separator: either end marker or long dashes
                if (line.startsWith("--- END OF ENTRY ---") || line.startsWith("-------------------------------------------------------------------")) {
                    if (current != null) {
                        if (summaryBuilder != null)
                            current.summary = summaryBuilder.toString().trim();
                        resources.add((T) current);
                        current = null;
                        summaryBuilder = null;
                    }
                    continue;
                }

                // create new object if needed
                if (current == null) current = new Mybook();

                // handle fields
                if (line.startsWith("OCLC Number:"))
                    current.oclcNumber = br.readLine().trim();
                else if (line.startsWith("Title:"))
                    current.title = br.readLine().trim();
                else if (line.startsWith("Authors:") || line.startsWith("Author:"))
                    current.authors = br.readLine().trim();
                else if (line.startsWith("Summary:")) {
                    summaryBuilder = new StringBuilder();
                    while ((line = br.readLine()) != null) {
                        line = line.trim();
                        // stop summary if next field or separator
                        if (line.startsWith("Year of publication:") ||
                            line.startsWith("Publisher:") ||
                            line.startsWith("Genre:") ||
                            line.startsWith("Physical Description:") ||
                            line.startsWith("ISBN:") ||
                            line.startsWith("--- END OF ENTRY ---") ||
                            line.startsWith("-------------------------------------------------------------------")) {
                            break;
                        }
                        summaryBuilder.append(line).append("\n");
                    }
                    current.summary = summaryBuilder.toString().trim();
                    if (line == null) break;

                    // continue processing the field that stopped summary
                    if (line.startsWith("Year of publication:"))
                        current.year = br.readLine().trim();
                    else if (line.startsWith("Publisher:"))
                        current.publisher = br.readLine().trim();
                    else if (line.startsWith("Genre:"))
                        current.genre = br.readLine().trim();
                    else if (line.startsWith("Physical Description:"))
                        current.physicalDescription = br.readLine().trim();
                    else if (line.startsWith("ISBN:"))
                        current.isbn = br.readLine().trim();
                    // if it was a separator, next iteration will handle
                    continue;
                }
                else if (line.startsWith("Year of publication:"))
                    current.year = br.readLine().trim();
                else if (line.startsWith("Publisher:"))
                    current.publisher = br.readLine().trim();
                else if (line.startsWith("Genre:"))
                    current.genre = br.readLine().trim();
                else if (line.startsWith("Physical Description:"))
                    current.physicalDescription = br.readLine().trim();
                else if (line.startsWith("ISBN:"))
                    current.isbn = br.readLine().trim();
            }

            // add last object if file didn't end with separator
            if (current != null) {
                resources.add((T) current);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // ================= CD PARSER =================

    private void loadCDs(String filePath) {
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
                    resources.add((T) current);
                    current = null;
                    descriptionBuilder = null;
                }
                continue;
            }

            if (current == null) current = new Mycd();

            // parse fields
            if (line.startsWith("OCLC Number:"))
                current.oclcNumber = br.readLine().trim();
            else if (line.startsWith("Title:"))
                current.title = br.readLine().trim();
            else if (line.startsWith("Performers:"))
                current.performers = br.readLine().trim();
            else if (line.startsWith("Credits:"))
                current.credits = br.readLine().trim();
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
                    current.year = br.readLine().trim();
                else if (line.startsWith("Language:"))
                    current.language = br.readLine().trim();
                else if (line.startsWith("Publisher:"))
                    current.publisher = br.readLine().trim();
                else if (line.startsWith("Genre:"))
                    current.genre = br.readLine().trim();
                else if (line.startsWith("Physical Description:"))
                    current.physicalDescription = br.readLine().trim();
                else if (line.startsWith("ISBN:"))
                    current.isbn = br.readLine().trim();
                continue;
            }
            else if (line.startsWith("Year of release:"))
                current.year = br.readLine().trim();
            else if (line.startsWith("Language:"))
                current.language = br.readLine().trim();
            else if (line.startsWith("Publisher:"))
                current.publisher = br.readLine().trim();
            else if (line.startsWith("Genre:"))
                current.genre = br.readLine().trim();
            else if (line.startsWith("Physical Description:"))
                current.physicalDescription = br.readLine().trim();
            else if (line.startsWith("ISBN:"))
                current.isbn = br.readLine().trim();
        }

        // add last CD if file didn't end with separator
        if (current != null) {
            resources.add((T) current);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
}
    // ================= CD PARSER =================
    private void loadLoans(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            String line;
            Mybook current = null;
            StringBuilder summaryBuilder = null;

            while ((line = br.readLine()) != null) {
                line = line.trim();

                // skip empty lines
                if (line.isEmpty()) continue;

                // entry separator: either end marker or long dashes
                if (line.startsWith("--- END OF ENTRY ---") || line.startsWith("-------------------------------------------------------------------")) {
                    if (current != null) {
                        if (summaryBuilder != null)
                            current.summary = summaryBuilder.toString().trim();
                        resources.add((T) current);
                        current = null;
                        summaryBuilder = null;
                    }
                    continue;
                }

                // create new object if needed
                if (current == null) current = new Mybook();

                // handle fields
                if (line.startsWith("OCLC Number:"))
                    current.oclcNumber = br.readLine().trim();
                else if (line.startsWith("Title:"))
                    current.title = br.readLine().trim();
                else if (line.startsWith("Authors:") || line.startsWith("Author:"))
                    current.authors = br.readLine().trim();
                else if (line.startsWith("Summary:")) {
                    summaryBuilder = new StringBuilder();
                    while ((line = br.readLine()) != null) {
                        line = line.trim();
                        // stop summary if next field or separator
                        if (line.startsWith("Year of publication:") ||
                            line.startsWith("Publisher:") ||
                            line.startsWith("Genre:") ||
                            line.startsWith("Physical Description:") ||
                            line.startsWith("ISBN:") ||
                            line.startsWith("--- END OF ENTRY ---") ||
                            line.startsWith("-------------------------------------------------------------------")) {
                            break;
                        }
                        summaryBuilder.append(line).append("\n");
                    }
                    current.summary = summaryBuilder.toString().trim();
                    if (line == null) break;

                    // continue processing the field that stopped summary
                    if (line.startsWith("Year of publication:"))
                        current.year = br.readLine().trim();
                    else if (line.startsWith("Publisher:"))
                        current.publisher = br.readLine().trim();
                    else if (line.startsWith("Genre:"))
                        current.genre = br.readLine().trim();
                    else if (line.startsWith("Physical Description:"))
                        current.physicalDescription = br.readLine().trim();
                    else if (line.startsWith("ISBN:"))
                        current.isbn = br.readLine().trim();
                    // if it was a separator, next iteration will handle
                    continue;
                }
                else if (line.startsWith("Year of publication:"))
                    current.year = br.readLine().trim();
                else if (line.startsWith("Publisher:"))
                    current.publisher = br.readLine().trim();
                else if (line.startsWith("Genre:"))
                    current.genre = br.readLine().trim();
                else if (line.startsWith("Physical Description:"))
                    current.physicalDescription = br.readLine().trim();
                else if (line.startsWith("ISBN:"))
                    current.isbn = br.readLine().trim();
            }

            // add last object if file didn't end with separator
            if (current != null) {
                resources.add((T) current);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
