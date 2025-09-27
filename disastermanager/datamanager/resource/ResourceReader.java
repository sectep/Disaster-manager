package disastermanager.datamanager.resource;

import java.io.*;
import java.util.ArrayList;

// Method which reads data from resources.txt
public class ResourceReader {
    static String line, rId;
    public static ArrayList<String> resourceData = new ArrayList<String>();
    private static String path = "src/data/resources.txt";

    // Method which shows resources.
    public static void showResources() {
        
        loadResourceData();

        // Display all avaible resources.
        System.out.println("--- Resources ---");
        for (String info : resourceData) {
            System.out.println(info);
        }
    }


    // Method which gets quantity from ID.
    public static int getQuantity(String id) {
            
        for (int i = 0; i < resourceData.size(); i++) {
            // Make sure that line statrs with "id" and matches.
            if (resourceData.get(i) != null
                    && resourceData.get(i).startsWith("Id: ")
                    && resourceData.get(i).substring(4).equals(id)) {
                
                // Return the quantify, which is located in two next lines.
                return Integer.parseInt(resourceData.get(i+2).substring(10));
            }
        }

        // If ID doesn't exist or quantity not found, return -1.
        return -1; 
    }

    // Method that get the resource of given id.
    public static String getResource(String resourceID) {
        
        for (int i = 0; i < resourceData.size(); i++) {
            // Make sure that the line starts with resource's id and matches.
            if (resourceData.get(i) != null
                    && resourceData.get(i).startsWith("Id: ")
                    && resourceData.get(i).substring(4).equals(resourceID)) {
                
                // Return the resource type, which is located on the next line.
                return resourceData.get(i+1).substring(10);
            }
        }
        
        return null;
    }

    // Method that reads data from the file and writes it to the array.
    public static void loadResourceData() {

        resourceData.clear();

        // Read till the end of file.
        try (BufferedReader in = new BufferedReader(new FileReader(path))) {
            while ((line = in.readLine()) != null) {
                resourceData.add(line);
            }
        } catch (IOException exc) {
            System.out.println("Unexpected behavior has occurred: " + exc);
        }
    }

    // Method which checks if ID exists in array.
    public static boolean idExists(String id) {
        
        loadResourceData();

        for (String info : resourceData) {
            // Check if line is ID and equals to the given argument.
            if (info.startsWith("Id: ") && info.substring(4).equals(id)) {
                return true;
            }
        }

        System.out.println(id + " doesn't exist. ");
        return false;
    }
}