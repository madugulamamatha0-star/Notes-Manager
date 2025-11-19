import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class NotesManager {

    private static final String FILE_NAME = "notes.txt";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("\n===== NOTES MANAGER =====");
            System.out.println("1. Add Note");
            System.out.println("2. View Notes");
            System.out.println("3. Delete Note");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    addNote();
                    break;
                case 2:
                    viewNotes();
                    break;
                case 3:
                    deleteNote();
                    break;
                case 4:
                    System.out.println("Exiting... Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }

    // Method for writing to the file
    public static void addNote() {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.print("\nEnter your note: ");
            String note = sc.nextLine();

            FileWriter writer = new FileWriter(FILE_NAME, true); 
            writer.write(note + "\n");
            writer.close();

            System.out.println("Note added successfully!");
        } catch (IOException e) {
            System.out.println("Error writing note: " + e.getMessage());
        }
    }

    // Method to read the notes
    public static ArrayList<String> readNotes() {
        ArrayList<String> notes = new ArrayList<>();

        try {
            File file = new File(FILE_NAME);
            if (!file.exists()) {
                return notes; 
            }

            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String line;
            while ((line = br.readLine()) != null) {
                notes.add(line);
            }

            br.close();
            fr.close();
        } catch (IOException e) {
            System.out.println("Error reading notes: " + e.getMessage());
        }

        return notes;
    }

    // Method to display notes
    public static void viewNotes() {
        ArrayList<String> notes = readNotes();

        if (notes.isEmpty()) {
            System.out.println("\nNo notes found!");
            return;
        }

        System.out.println("\n===== SAVED NOTES =====");
        for (int i = 0; i < notes.size(); i++) {
            System.out.println((i + 1) + ". " + notes.get(i));
        }
    }

    // Method to delete a note
    public static void deleteNote() {
        ArrayList<String> notes = readNotes();

        if (notes.isEmpty()) {
            System.out.println("\nNo notes to delete!");
            return;
        }

        viewNotes();
        Scanner sc = new Scanner(System.in);
        System.out.print("\nEnter note number to delete: ");
        int number = sc.nextInt();

        if (number < 1 || number > notes.size()) {
            System.out.println("Invalid note number!");
            return;
        }

        notes.remove(number - 1);

        try {
            FileWriter writer = new FileWriter(FILE_NAME, false); 
            for (String note : notes) {
                writer.write(note + "\n");
            }
            writer.close();

            System.out.println("Note deleted successfully!");
        } catch (IOException e) {
            System.out.println("Error deleting note: " + e.getMessage());
        }
    }
}
