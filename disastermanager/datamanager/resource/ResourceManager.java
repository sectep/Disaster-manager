package disastermanager.datamanager.resource;

import java.util.ArrayList;

import disastermanager.datamanager.disaster.DisasterManager;
import disastermanager.datamanager.disaster.DisasterReader;

// A class that manages resources.
public class ResourceManager {
    private static int remaining_casualties;
    static ArrayList<String> resourceData = ResourceReader.resourceData;

    // Method which matches the location of the request.
    public static void helpDisaster(String disasterID) {

        ResourceReader.loadResourceData();

        // Check if disaster's ID exists in the database.
        if (disasterID != null && DisasterReader.idExists(disasterID)) {

            // Obtain the information of the disaster.
            String city = DisasterReader.getCity(disasterID); 
            int casualty = DisasterReader.getCasualty(disasterID); 

            // Obtain avaivbe resources for disaster where storage is in the same city.
            String resourceId = matchResource(city);

            System.out.printf("\nDispatching resources for request ID %s (Location: %s, Casualties: %d).%n",
                    disasterID, city, casualty);

            // Check if city has been matched and if casualty is a valid value.
            if (resourceId != null && casualty != -1) {
                
                // Distribute resources and remove the resource if it is exhausted.
                distributeResource(resourceId, casualty);
                removeResource(resourceId);

                // Update the information and remove the disaster.
                DisasterManager.updateCasualty(disasterID, remaining_casualties);
                DisasterManager.removeDisaster(disasterID, remaining_casualties);

                return;
            }
            System.out.println("No matching cities have been found for ID " + disasterID + ".");
        }
    }

    // Method which updates resource quantity, based on the casualty.
    public static void distributeResource(String resourceID, int casualty) {
        
        System.out.println("Distributing with " + ResourceReader.getResource(resourceID));

        // Iterate through array.
        for (int i = 0; i < resourceData.size(); i++) {
            // Check if resource ID matches with our database.
            if (resourceData.get(i) != null 
                    && resourceData.get(i).startsWith("Id: ") 
                    && resourceData.get(i).substring(4).equals(resourceID)) {

                // Update resource's quantity and remove it.
                updateResource(resourceID, casualty); 
                removeResource(resourceID);

                return;
            }
        }
    }

    // Method which updates the quantity of the resource.
    private static void updateResource(String resourceID, int casualty) {

        for (int i = 0; i < resourceData.size(); i++) {
            // Make sure that the line starts with "id" and has found the id.
            if (resourceData.get(i) != null 
                    && resourceData.get(i).startsWith("Id: ") 
                    && resourceData.get(i).substring(4).equals(resourceID)) {
                
                // Get the total quanity of avaible resources in from the storage id.
                int avaible_resources = ResourceReader.getQuantity(resourceID);

                // Calculate remaining caculaties and distributed resources.
                remaining_casualties = casualty - avaible_resources; 
                
                int distributed = casualty - remaining_casualties;                
                
                // Calculate remaining resources and update the quantity.
                int remaining_resources = avaible_resources - distributed;
                resourceData.set(i+2, "Quantity: " + remaining_resources);
                
                System.out.println("Distribution for ID " + resourceID + " is over. ");
                return;
            }
        }
    }

    // Method that removes resource ID that stored resources.
    public static void removeResource(String resourceID) {
        int quanity = ResourceReader.getQuantity(resourceID);
       
        // Remove ID only if resource is exhausted. 
        if (quanity <= 0) {
            for (int i = 0; i < resourceData.size(); i++) {
                // Make sure that line starts with ID and chek whether it exists.
                if (resourceData.get(i) != null && resourceData.get(i).startsWith("Id: ")
                        && resourceData.get(i).substring(4).equals(resourceID)) {

                    System.out.println("Removing ID " + resourceID +" that is exhausted.");
                    
                    // Remove four lines that contain resource's information.
                    for (int j = 0; j < 4; j++) {
                        resourceData.remove(i);
                    }

                    ResourceWriter.updateResourceFile();

                    return;
                }
            }
        }
    }

    // Method which returns resource ID if it matches with the given city.
    private static String matchResource(String city) {

        for (int i = 0; i < resourceData.size(); i++) {
            // Check if line starts with ID, and compare the city, which starts in the 
            // next three lines with given one.
            if (resourceData.get(i) != null
                    && resourceData.get(i).startsWith("Id: ")
                    && resourceData.get(i+3).substring(6).equalsIgnoreCase(city)) {

                // Return the ID.
                return resourceData.get(i).substring(4);
            }
        }

        return null;
    }
}

