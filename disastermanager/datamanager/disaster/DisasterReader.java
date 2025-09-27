package disastermanager.datamanager.disaster;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

// A class that reads requests.txt data.
public class DisasterReader {
    static String line;
    static ArrayList<String> disasterData = DisasterWriter.disasterData;

    private static String path = "src/data/requests.txt";
    

    // Method that shows all disasters.
    public static void showAllDisasters() {
        
        loadDisasterData();

        // Show all disasters.
        System.out.println("\n--- Disasters ---");
        for (String info : disasterData) {
            System.out.println(info); 
        }
    }

    // Method that obtains casualty from ID.
    public static int getCasualty(String id) {

        // Check if ID exists.
        if (idExists(id)) {

            // Iterate through a data array.
            for (int i = 0; i < disasterData.size(); i++) {
                // Make sure that line starts with ID and equales to the given one.
                if (disasterData != null 
                        && disasterData.get(i).startsWith("Id: ") 
                        && disasterData.get(i).substring(4).equals(id)) {
                    
                    // Return the total casualty, which is located in the next three lines.
                    return Integer.parseInt(disasterData.get(i+3).substring(10));
                }
            }
        }

        // If ID doesn't exist, return -1.
        return -1;
    }

    // Method that gets city from ID.
    public static String getCity(String id) {

        for (int i = 0; i < disasterData.size(); i++) {
            // Make sure that line starts with ID and equales to the given one.
            if (disasterData != null 
                    && disasterData.get(i).startsWith("Id: ") 
                    && disasterData.get(i).substring(4).equals(id)) {
                
                // Return the city, which is located in the next two lines,
                return disasterData.get(i+2).substring(6); 
            }
        }

        // If ID doesn't exist, return null.
        return null; 
    }

    // Method that gets disaster type from array.
    public static String getDisaster(String id) {

        loadDisasterData();

        for (int i = 0; i < disasterData.size(); i++) {
            // Make sure that line starts with ID and equales to the given one.
            if (disasterData != null 
                    && disasterData.get(i).startsWith("Id: ")
                    && disasterData.get(i).substring(4).equals(id)) {

                // Return the type of disaster, which is located in the next line.
                return disasterData.get(i+1).substring(10); 
            }
        }

        // If ID doesn't exist, return null.
        return null;
    }

    // Method that gets priority from ID.
    public static int getPriority(String id) {
        
        loadDisasterData();

        for (int i = 0; i < disasterData.size(); i++) {
            if (disasterData != null 
                    && disasterData.get(i).startsWith("Id: ") 
                    && disasterData.get(i).substring(4).equals(id)) {

                // Return the priority, which is located in the next four lines.
                return Integer.parseInt(disasterData.get(i+4).substring(10));
            }
        }
        
        // If ID doesn't exist, return -1.
        return -1; 
    }

    // Method that reads data into array.
    public static void loadDisasterData() {

        // Clear array from last sessions.
        disasterData.clear();

        // Read till the end of file.
        try (BufferedReader in = new BufferedReader(new FileReader(path))) {
            while ((line = in.readLine()) != null) {
                disasterData.add(line);
            }
        } catch (IOException exc) {
            System.out.println("Unexpected behavior has occurred: " + exc);
        }
    }


    // Method that checks if ID exists in array.
    public static boolean idExists(String disasterID) {

        loadDisasterData();

        for (String info : disasterData) {
            // Make sure that line starts with ID and equales to the given one.
            if (info != null && info.startsWith("Id: ") && info.substring(4).equals(disasterID))
                return true;
        }

        System.out.println(disasterID + " doesn't exist. Please enter another ID.");
        return false;
    }
}