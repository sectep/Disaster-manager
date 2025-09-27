package disastermanager.datamanager.disaster;

import java.util.ArrayList;

import disastermanager.datamanager.resource.ResourceManager;

// A Class that manages disaster.
public class DisasterManager {
    static ArrayList<String> disasterData = DisasterReader.disasterData;

    
    // Distribute resources for disasters.
    public static void helpDisasters() {

        DisasterReader.loadDisasterData();

        for(int i = 0; i < disasterData.size(); i++) {
            // Find the line that startrs with "id".
            if (disasterData.get(i).startsWith("Id: ")) { 

                // Get id and distribute resources for the disaster.
                String id = disasterData.get(i).substring(4); 
                ResourceManager.helpDisaster(id);
            } 
        }
    }

    // Method that removes a disaster.
    public static void removeDisaster(String disasterID, int casualty) {

        DisasterReader.loadDisasterData();

        // First check if there are no people left to help.
        if (casualty <= 0) {
            for (int i = 0; i < disasterData.size(); i++) {
                // Make sure that line starts with disaster's id and matches.
                if (disasterData.get(i) != null
                        && disasterData.get(i).startsWith("Id: ")
                        && disasterData.get(i).substring(4).equals(disasterID)) {

                    // Remove information of the disaster that contains 5 lines. Update the file.
                    for (int j = 0; j < 6; j++) {
                        disasterData.remove(disasterData.get(i));
                    }

                    DisasterWriter.updateDisasterFile();

                    return;
                }
            }
        }

        // Otherwise notify that there are still people that need help.
        System.out.println("Can't remove ID " + disasterID + " because "
                + casualty + " people still need help.");

    }

    // Method that updates disaster's priotity in array.
    public static void updateCasualty(String id, int casualty) {

        for (int i = 0; i < disasterData.size(); i++) {
            if (disasterData.get(i) != null
                    && disasterData.get(i).startsWith("Id: ")
                    && disasterData.get(i).substring(4).equals(id)) {

                // Update priority's information, which is located in next 3 lines.
                disasterData.set(i+3, "Danger: " + casualty);

                return;
            }
        }
    }
}
