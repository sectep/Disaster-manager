package disastermanager.datamanager.disaster;

// An enumerator that holds disaster types and their priority.
public enum Disasters {
    EARTHQUAKE(8), FLOOD(9), WAR(10);

    private int priority; // holds disaster's priority for specific type.
    private static int defaultPriority = 5;

    Disasters(int priority) {
        this.priority = priority;
    }

    // Method that returns the disaster type, based on the current data.
    public static String returnType(String disasterType) {

        for (Disasters disaster : Disasters.values()) {
            // Return disaster's type, if the argument is mathching with disaster constants.
            if (disaster.toString().equalsIgnoreCase(disasterType)) {
                return disasterType.toUpperCase();
            }
        }

        // Return 'UNDEFINED' if the type hasn't been found in the enumirator.
        return "UNDEFINED";
    }

    // Method that derermines priority.
    public static int determinePriority(String disastertype) {

        for(Disasters disaster: Disasters.values()) {
            // Check if given disaster type is in enuminator list.
            if (disaster.toString().equalsIgnoreCase(disastertype)) {
                return disaster.priority;
            }
        }

        // If the disaster hasn't been found, return default priority.
        return defaultPriority;
    }
}