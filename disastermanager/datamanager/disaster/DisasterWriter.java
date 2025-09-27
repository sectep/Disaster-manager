package disastermanager.datamanager.disaster;

import java.io.*;
import java.util.ArrayList;

// A class that writes disaster data to requests.txt
public class DisasterWriter {
    static String line;
    static ArrayList<String> disasterData = new ArrayList<String>();
    private static String path = "src/data/requests.txt";

    // Method that writes disaster data into our database.
    public static void saveDisaster(String disasterID, String disasterType, String city, int loses, int priority) {
        
        try (BufferedWriter out = new BufferedWriter(new FileWriter(path, true))) {
            
            if (!idExists(disasterID)) {
                
                // Write down the information of disaster.
                out.write("Id: " + disasterID + "\n");
                out.write("Disaster: " + disasterType + "\n");
                out.write("City: " + city + "\n");
                out.write("Casualty: " + loses + "\n");
                out.write("Priority: " + priority + "\n\n");

                System.out.println("Disaster's information has been successfully saved. ");
                return;
            } else {
                System.out.println(disasterID + " already exists. Please enter a unique ID that doesn't repeat.");
            }
        } catch (IOException exc) {
            System.out.println("Unexpected behavior has happened: " + exc);
        }
    }


    // Method that rewrites data based on its additional priority.
    public static void writeUpdatedDisaster(String disasterId, int priority) {
        
        DisasterReader.loadDisasterData();

        // Update disaster's priority and its file.
        updatePriority(disasterId, priority);
        updateDisasterFile();
        
        System.out.println(disasterId + " has been updated successfully.");
    }

    // Method that updates disaster's priotity in array.
    private static void updatePriority(String disasterId, int priority) {
    
        for (int i = 0; i < disasterData.size(); i++) {
            if (disasterData.get(i) != null 
                && disasterData.get(i).startsWith("Id: ") 
                && disasterData.get(i).substring(4).equals(disasterId)) {
                
                // Update priority's information.
                disasterData.set(i+4, "Danger: " + priority); 
               
                return;
            }
        }
    }

    // Method, that writes down information from array.
    public static void updateDisasterFile() {
        
        try (BufferedWriter out = new BufferedWriter(new FileWriter(path))) {
            // Write down each element of the array to the file.
            for (String info : disasterData) {
                out.write(info + "\n");
            }
        } catch (IOException exc) {
            System.out.println("Unexpected behavior has occurred.");
        }
    }

    // Method that checks if ID exists in array.
    private static boolean idExists(String id) {
        
        DisasterReader.loadDisasterData();
        
        for (String info : disasterData) {
            // Make sure that line starts with id and check if it matches.
            if (info != null && info.startsWith("Id: ") && info.substring(4).equals(id)) {
                return true;
            }
        }
        
        // Return false if ID is not found.
        return false; 
    }
}