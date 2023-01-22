package api.implementation;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Class with the responsibility for export JSON files
 */
public class ExporterJson {

    /**
     * Directory that the JSON is gonna be saved
     */
    private static final String directory = "docs/outputs/json";

    /**
     * Exports a json file with the content of @param jsonFile,
     * with the name @param name
     *
     * @param stringJSON string representation of json file.
     * @param name       name of the file without the extension (.json).
     * @throws IOException if some error occurs when writing the file
     * @implNote Files are saved by default in "docs/outputs/json"
     */
    public static void exportJSON(String stringJSON, String name) throws IOException {
        if (stringJSON == null || stringJSON.equals("") || name == null || name.equals("")) {
            throw new IllegalArgumentException("Cannot send parameters null or empty!");
        }
        try (FileWriter file = new FileWriter(directory + "/" + name + ".json")) {
            file.write(stringJSON);
        } catch (IOException exception) {
            throw new IOException("Error trying to write the file!");
        }
    }

}
