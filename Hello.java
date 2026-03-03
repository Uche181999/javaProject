import javax.swing.*;
import java.awt.*;
import java.io.*;

public class Hello {

    private JTextArea textArea = new JTextArea(15, 50);
    private File lastLoadedFile;
    Manager<Mybook> books = new Manager<>("resources/book.txt");
        Manager<Mycd> cds = new Manager<>("resources/cd.txt");
        Manager<Myloan> loans = new Manager<>("resources/cd.txt");

    public Hello() {

        JFrame frame = new JFrame("Hello World App");
        frame.setLayout(new BorderLayout());
 
        // Top panel with Load button
        JButton loadButton = new JButton("Load File");
        JRadioButton bookButton = new JRadioButton("books");
        JRadioButton cdButton = new JRadioButton("CDs");
        JRadioButton loanButton = new JRadioButton("loans");
        JPanel top = new JPanel();
        top.add(loadButton);
        top.add(bookButton);
        top.add(cdButton);
        top.add(loanButton);
        frame.add(top,BorderLayout.NORTH);

        ButtonGroup group = new ButtonGroup();
        group.add(bookButton);
        group.add(cdButton);
        group.add(loanButton);
        // Text area in center
        textArea.setText(Helper.displayText(books));
        JScrollPane scroll = new JScrollPane(textArea);
        frame.add(scroll, BorderLayout.CENTER);

        // Bottom panel with Save button
        JButton saveButton = new JButton("Save File");
        JPanel bottom = new JPanel();
        bottom.add(saveButton);
        frame.add(bottom, BorderLayout.SOUTH);

        // Load button action
        loadButton.addActionListener(e -> loadFile(frame));

        // Save button action
        saveButton.addActionListener(e -> saveFile());

        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
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
                textArea.setText(content.toString());
            } catch (Exception ex) {
                textArea.setText("Error loading file");
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

        // ✅ overwrite file (NOT append)
        try (BufferedWriter writer =
                new BufferedWriter(new FileWriter(saveFile,false))) {

            writer.write(textArea.getText());
        }

        JOptionPane.showMessageDialog(null,
                "File saved as resources/" + fileName);

    } catch (Exception ex) {
        JOptionPane.showMessageDialog(null,
                "Error saving file");
    }
}

    public static void main(String[] args) {
        new Hello();
    }
}