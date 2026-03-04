import javax.swing.*;

import Manager.Manager;
import Model.Mybook;
import Model.Mycd;
import Model.Mydvd;
import Model.Myloan;
import Util.Helper;

import java.awt.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class LibraryAnalysisDashboard {

    private File lastLoadedFile;
    private Manager<Mybook> books = new Manager<>("resources/book.txt");
    private Manager<Mycd> cds = new Manager<>("resources/cd.txt");
    private Manager<Myloan> loans = new Manager<>("resources/loan.txt");
    private Manager<Mydvd> dvds = new Manager<>("resources/dvd.txt");

    private JTextArea outputArea = new JTextArea("RESULTS ARE DISPLAYED HERE ...");
    private JTextField oclcField = new JTextField();
    private JTextField genreField = new JTextField();

    public LibraryAnalysisDashboard() {

        // Create Frame
        JFrame frame = new JFrame("Library Analytics Dashboard");
        frame.setSize(1000, 650);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout(10, 10));

        // =======================
        // NORTH PANEL (Load Button)
        // =======================
        JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton loadButton = new JButton("Load Library Data Files");
        northPanel.add(loadButton);
        frame.add(northPanel, BorderLayout.NORTH);

        // =======================
        // CENTER PANEL (Output Area)
        // =======================
        outputArea.setEditable(false);
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(outputArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        // =======================
        // WEST PANEL (Analysis Buttons)
        // =======================
        JPanel westPanel = new JPanel();
        westPanel.setLayout(new GridLayout(12, 1, 5, 5));
        westPanel.setBorder(BorderFactory.createTitledBorder("Analysis Buttons Panel"));

        JButton reportButton = new JButton("Generate Catalogue Report");

        JRadioButton booksRadio = new JRadioButton("Books");
        JRadioButton dvdsRadio = new JRadioButton("DVDs");
        JRadioButton cdsRadio = new JRadioButton("CDs");
        JRadioButton loansRadio = new JRadioButton("Loans");

        ButtonGroup group = new ButtonGroup();
        group.add(booksRadio);
        group.add(dvdsRadio);
        group.add(cdsRadio);
        group.add(loansRadio);

        JButton showGenresButton = new JButton("Show Genres");

        JButton topBooksButton = new JButton("Top 10 Loaned Books");
        JButton topDvdsButton = new JButton("Top 10 Loaned DVDs");
        JButton topCdsButton = new JButton("Top 10 Loaned CDs");

        westPanel.add(new JLabel(""));
        westPanel.add(reportButton);
        westPanel.add(booksRadio);
        westPanel.add(dvdsRadio);
        westPanel.add(cdsRadio);
        westPanel.add(loansRadio);
        westPanel.add(showGenresButton);
        westPanel.add(new JLabel(""));
        westPanel.add(topBooksButton);
        westPanel.add(topDvdsButton);
        westPanel.add(topCdsButton);

        frame.add(westPanel, BorderLayout.WEST);

        // =======================
        // EAST PANEL (Search Panel)
        // =======================
        JPanel eastPanel = new JPanel();
        eastPanel.setLayout(new GridLayout(9, 1,5,5));
        eastPanel.setBorder(BorderFactory.createTitledBorder("Search Panel"));

        JButton oclcSearchButton = new JButton("Search");

        
        JButton genreSearchButton = new JButton("Search");

        eastPanel.add(new JLabel(""));
        eastPanel.add(new JLabel("Enter OCLC Number:"));
        eastPanel.add(oclcField);
        eastPanel.add(oclcSearchButton);
        eastPanel.add(new JLabel(""));
        eastPanel.add(new JLabel("Enter Genre:"));
        eastPanel.add(genreField);
        eastPanel.add(genreSearchButton);

        JPanel eastWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
        eastWrapper.add(eastPanel);

        frame.add(eastWrapper, BorderLayout.EAST)   ;

        // =======================
        // SOUTH PANEL (Status Bar)
        // =======================
        JButton saveButton = new JButton("Save File");
        JPanel bottom = new JPanel();
        bottom.add(saveButton);
        frame.add(bottom, BorderLayout.SOUTH);

        // =======================
        // BOTTON ACTIONS
        // =======================

        // Load button action
        loadButton.addActionListener(e -> loadFile(frame));

        // Save button action
        saveButton.addActionListener(e -> saveFile());

        // display books action
        booksRadio.addActionListener(e -> displayBooks());

        // display cds action
        cdsRadio.addActionListener(e -> displayCDs());

         // display dvds action
        dvdsRadio.addActionListener(e -> displayDVDs());

        // display loans action
        loansRadio.addActionListener(e -> displayLoans());

        // display genres action
        showGenresButton.addActionListener(e -> displayGenres());

        // display top loaned book action
        topBooksButton.addActionListener(e -> getTop10Loaned(books,loans, "BOOKs"));

        // display top loaned cd action
        topCdsButton.addActionListener(e -> getTop10Loaned(cds,loans,"CDS"));

        // display top loaned dvd action
        topDvdsButton.addActionListener(e -> getTop10Loaned(dvds,loans,"DVDS"));

        // display by oclc number search
        oclcSearchButton.addActionListener(e -> oclcSearch());

        // display by genre search
        genreSearchButton.addActionListener(e -> genreSearch());

        // display by genre search
        reportButton.addActionListener(e -> generateCatalogueReport());

        // Make Frame Visible
        frame.setVisible(true);
    }
    // ================= LOAD FILE =================
    private void loadFile(JFrame frame) {
        JFileChooser chooser = new JFileChooser();
        if (chooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            lastLoadedFile = file;
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                StringBuilder content = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    content.append(line).append("\n");
                }
                reader.close();
                outputArea.setText(content.toString());
            } catch (Exception ex) {
                outputArea.setText("Error loading file");
            }
        }
    }
       // ================= SAVE FILE =================
    private void saveFile() {

        if (lastLoadedFile == null) {
            JOptionPane.showMessageDialog(null,
                    "Load a file first!");
            return;
        }

        try {
            // create resources folder
            File folder = new File("resources");
            if (!folder.exists()) folder.mkdir();

            String lowerName =
                    lastLoadedFile.getName().toLowerCase();

            String fileName = "saved_file.txt";

            if (lowerName.contains("book"))
                fileName = "book.txt";
            else if (lowerName.contains("dvd"))
                fileName = "dvd.txt";
            else if (lowerName.contains("cd"))
                fileName = "cd.txt";
            else if (lowerName.contains("loan"))
                fileName = "loan.txt";

            File saveFile = new File(folder, fileName);

            //overwrite file (NOT append)
            try (BufferedWriter writer =
                    new BufferedWriter(new FileWriter(saveFile,false))) {

                writer.write(outputArea.getText());
            }

            JOptionPane.showMessageDialog(null,
                    "File saved as resources/" + fileName);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,
                    "Error saving file");
        }
    }
    // ================= DISPLAY BOOKS =================

    private void displayBooks(){
        outputArea.setText(Helper.displayText(books));
    }

    // ================= DISPLAY CDS =================

    private void displayCDs(){
        outputArea.setText(Helper.displayText(cds));
    }

    // ================= DISPLAY DVDS =================

    private void displayDVDs(){
        outputArea.setText(Helper.displayText(dvds));
    }
    // ================= DISPLAY LOANS =================

    private void displayLoans(){

        outputArea.setText(Helper.displayText(loans));
    }
    // ================= DISPLAY GENRES =================

    private void displayGenres(){
        HashSet<String> bookGenres = new HashSet<>();
        HashSet<String> cdGenres = new HashSet<>();
        HashSet<String> dvdGenres = new HashSet<>();
        HashSet<String> genres = new HashSet<>();

        String allGenres = "";

        for(Mybook b : books.getResources().values()){
            bookGenres.add(b.genre);
        }
        for(Mycd c : cds.getResources().values()){
            cdGenres.add(c.genre);
        }
        for(Mydvd d : dvds.getResources().values()){
            dvdGenres.add(d.genre);
        }
        genres.addAll(bookGenres);
        genres.addAll(cdGenres);
        genres.addAll(dvdGenres);

        for (String g : genres){
            allGenres = allGenres + g +"\n\n";
        }

        outputArea.setText(allGenres);
    }
    // ================= DISPLAY TOP LOANED FOR BOOKS, CDS AND DVDS =================
private <T> void getTop10Loaned(
        Manager<T> items,
        Manager<Myloan> loans,
        String typeName) {

    HashMap<String, Integer> countMap = new HashMap<>();

    for (Myloan l : loans.getResources().values()) {
        String id = l.oclcNumber;
        if (items.getResources().containsKey(id)) {
            countMap.put(id, countMap.getOrDefault(id, 0) + 1);
        }
    }

    ArrayList<Map.Entry<String, Integer>> list = new ArrayList<>(countMap.entrySet());
    list.sort((a, b) -> b.getValue().compareTo(a.getValue()));

    StringBuilder sb = new StringBuilder();
    sb.append("TOP 10 LOANED ").append(typeName).append("\n\n");

    int limit = Math.min(10, list.size());

    for (int i = 0; i < limit; i++) {
        String id = list.get(i).getKey();
        int count = list.get(i).getValue();
        T item = items.getResources().get(id);

        sb.append("Loan count: ")
          .append(count)
          .append("\n")
          .append(item.toString())
          .append("\n\n");
    }

    outputArea.setText(sb.toString());
}
    // ================= DISPLAY BY OCLC NUMBER SEARCH=================
    private void oclcSearch(){
        String oclcInput = oclcField.getText().trim();
        if (oclcInput.isEmpty()) {
            outputArea.setText("Please enter a valid OCLC Number.");
            return;
        }

        StringBuilder result = new StringBuilder();

        // Search Books
        if (books.getResources().containsKey(oclcInput)) {
            Mybook book = books.getResources().get(oclcInput);
            result.append("BOOK:\n").append(book.toString()).append("\n\n");
        }

        // Search CDs
        if (cds.getResources().containsKey(oclcInput)) {
            Mycd cd = cds.getResources().get(oclcInput);
            result.append("CD:\n").append(cd.toString()).append("\n\n");
        }

        // Search DVDs
        if (dvds.getResources().containsKey(oclcInput)) {
            Mydvd dvd = dvds.getResources().get(oclcInput);
            result.append("DVD:\n").append(dvd.toString()).append("\n\n");
        }

        // No match
        if (result.isEmpty()) {
            result.append("No item found for OCLC Number: ").append(oclcInput);
        }

        outputArea.setText(result.toString());
    }

// ================= DISPLAY BY GENRE SEARCH=================

    private void genreSearch(){

        String genreInput = genreField.getText().trim().toLowerCase();
        if (genreInput.isEmpty()) {
            outputArea.setText("Please enter a genre.");
            return;
        }

        HashSet<String> matchedItems = new HashSet<>();
        StringBuilder result = new StringBuilder();

        // Search Books
        for (Mybook b : books.getResources().values()) {
            if (b.genre != null && b.genre.toLowerCase().contains(genreInput)) {
                matchedItems.add("BOOK:\n\n" + b.toString());
            }
        }

        // Search CDs
        for (Mycd c : cds.getResources().values()) {
            if (c.genre != null && c.genre.toLowerCase().contains(genreInput)) {
                matchedItems.add("CD:\n\n" + c.toString());
            }
        }

        // Search DVDs
        for (Mydvd d : dvds.getResources().values()) {
            if (d.genre != null && d.genre.toLowerCase().contains(genreInput)) {
                matchedItems.add("DVD:\n\n" + d.toString());
            }
        }

        if (matchedItems.isEmpty()) {
            result.append("No items found for genre: ").append(genreInput);
        } else {
            result.append("Items matching genre '").append(genreInput).append("':\n\n");
            for (String item : matchedItems) {
                result.append(item).append("\n");
            }
        }

        outputArea.setText(result.toString());
    }
// ================= DISPLAY BY GENRE SEARCH=================
    private void generateCatalogueReport() {
        StringBuilder report = new StringBuilder();

        // ======= Total unique library items =======
        int totalItems = books.getResources().size() 
                    + dvds.getResources().size() 
                    + cds.getResources().size();

        report.append("How many unique library items are there in the catalogue? ")
            .append(totalItems).append("\n");

        report.append("How many of these are books? ")
            .append(books.getResources().size()).append("\n");

        report.append("How many of these are DVDs? ")
            .append(dvds.getResources().size()).append("\n");

        report.append("How many of these are CDs? ")
            .append(cds.getResources().size()).append("\n\n");

        // ======= Unique genres =======
        HashSet<String> bookGenres = new HashSet<>();
        HashSet<String> dvdGenres = new HashSet<>();
        HashSet<String> cdGenres = new HashSet<>();

        for (Mybook b : books.getResources().values())
            if (b.genre != null) bookGenres.add(b.genre);

        for (Mydvd d : dvds.getResources().values())
            if (d.genre != null) dvdGenres.add(d.genre);

        for (Mycd c : cds.getResources().values())
            if (c.genre != null) cdGenres.add(c.genre);

        report.append("How many unique different genres are there in the book catalogue? ")
            .append(bookGenres.size()).append("\n");

        report.append("How many unique different genres are there in the DVD catalogue? ")
            .append(dvdGenres.size()).append("\n");

        report.append("How many unique different genres are there in the CD catalogue? ")
            .append(cdGenres.size()).append("\n\n");

        // ======= Items per genre =======
        report.append("How many items are there in each genre of the book catalogue?\n");
        for (String genre : bookGenres) {
            long count = books.getResources().values().stream()
                            .filter(b -> genre.equals(b.genre))
                            .count();
            report.append(genre).append(": ").append(count).append("\n");
        }
        report.append("\n");

        report.append("How many items are there in each genre of the DVD catalogue?\n");
        for (String genre : dvdGenres) {
            long count = dvds.getResources().values().stream()
                            .filter(d -> genre.equals(d.genre))
                            .count();
            report.append(genre).append(": ").append(count).append("\n");
        }
        report.append("\n");

        report.append("How many items are there in each genre of the CD catalogue?\n");
        for (String genre : cdGenres) {
            long count = cds.getResources().values().stream()
                            .filter(c -> genre.equals(c.genre))
                            .count();
            report.append(genre).append(": ").append(count).append("\n");
        }

        // Show in output area
        outputArea.setText(report.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LibraryAnalysisDashboard();
        });
    }
}