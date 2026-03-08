package core;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.Todo;

/**
 * Handles the reading and writing of task data to the hard drive.
 * Ensures that tasks persist between application sessions using standard array data handling.
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The relative path to the text file where tasks are saved.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the current list of tasks to the hard drive.
     *
     * @param tasks An Arraylist of tasks to be saved.
     * @throws OlafException If writing to the file fails.
     */
    public void save(ArrayList<Task> tasks) throws OlafException {
        try {
            // Create the directory if it doesn't exist
            File file = new File(filePath);
            file.getParentFile().mkdirs();

            FileWriter fw = new FileWriter(file);
            for (Task task : tasks) {
                fw.write(task.toSaveFormat() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            throw new OlafException("I couldn't save my memory!");
        }
    }

    /**
     * Loads tasks from the hard drive into memory upon startup using a two-pass standard array system.
     *
     * @return An Arraylist of Task objects loaded from the save file.
     * @throws OlafException If the file cannot be read or parsed correctly.
     */
    public ArrayList<Task> load() throws OlafException {
        ArrayList<Task> loadedTasks = new ArrayList<>();
        File file = new File(filePath);

        try {
            if (!file.exists()) {
                return loadedTasks; // Return empty list if no file exists yet
            }

            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" \\| "); // Split by " | "

                String type = parts[0];
                boolean isDone = parts[1].equals("1");
                String description = parts[2];

                Task task = null;

                switch (type) {
                case "T":
                    task = new Todo(description);
                    break;
                case "D":
                    LocalDate byDate = LocalDate.parse(parts[3]);
                    task = new Deadline(description, byDate);
                    break;
                case "E":
                    task = new Event(description, parts[3], parts[4]);
                    break;
                }

                if (task != null) {
                    if (isDone) {
                        task.markAsDone();
                    }
                    loadedTasks.add(task);
                }
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println("My memory is a little fuzzy... couldn't read the save file properly.");
        }
        return loadedTasks;
    }
}
