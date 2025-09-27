package disastermanager.datamanager.resource;

import java.io.*;

// A class that writes resources into the database.
public class ResourceWriter {
    static String lines;
    private static String path = "src/data/resources.txt";

    // Method that writes resource data into our database.
    public static void writeResource(String resourceID, String resourceType, int quantity, String city) {
        try (BufferedWriter out = new BufferedWriter(new FileWriter(path, true))) {

            // First check if ID is not included in our database.
            if (!idExists(resourceID)) {
                
                // Write down resource's data to the file.
                out.write("Id: " + resourceID + "\n");
                out.write("Resource: " + resourceType + "\n");
                out.write("Quantity: " + quantity + "\n");
                out.write("City: " + city + "\n\n");
                
                System.out.println("Resource's information has been saved successfully.");
                return;
            }

        } catch (IOException exc) {
            System.out.println("Unexpected behavior has happened: " + exc);
        }

        // Notify the user that resource's id has been found in our database.
        System.out.println(resourceID + " already exists. Please enter a unique ID that doesn't repeat.");
    }

    // Method which updates the resource database.
    public static void updateResourceFile() {
        try (BufferedWriter out = new BufferedWriter(new FileWriter(path))) {
            for (String info : ResourceManager.resourceData)
                out.write(info + "\n"); // Add newline for each entry.
        } catch (IOException exc) {
            System.out.println("Unexpected behavior has happened: " + exc);
        }
    }

    // Method that checks if a given ID exists in our database.
    private static boolean idExists(String id) {

        try (BufferedReader in = new BufferedReader(new FileReader(path))) {
            // Read the file till the end of the line.
            while ((lines = in.readLine()) != null) {
                // Make sure that lines starts with id and equals to the given one.
                if (lines.startsWith("Id: ") && lines.substring(4).equals(id)) {
                    return true;      
                }
            }
        } catch (IOException exc) {
            System.out.println("Unexpected behavior has happened: " + exc);
        }

        return false;
    }
}