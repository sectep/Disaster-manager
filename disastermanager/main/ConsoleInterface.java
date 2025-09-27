package disastermanager.main;

import java.util.Scanner;

import disastermanager.datamanager.disaster.*;
import disastermanager.datamanager.resource.*;

// A class that manages the console.
public class ConsoleInterface {
    Scanner console = new Scanner(System.in); // deaclare a scanner variable.
    
    // Store the information of disaster.
    private String id, disasterType, city, resource;
    private int casualty, priority, quantity;

    private static final int DISASTER = 1; // Constant to hold disaster manager option.
    private static final int RESOURCE = 2; // Constant to hold resource manager option.
    private static final int DISTRIBUTE = 3; // Constant to hold distributing process option.

    private static final int EXIT = 0; // Constant to hold an exit option.
    private static final int ADD = 1; // Constant to hold writing down option.
    private static final int VIEW = 2; // Constant to hold viewing option.

    // Method that launches the application.
    public void launch() {
        display(); // Display the options.

        handleOption(askOption()); // Perform the chosen option.
    }

    // Method that interacts with the option.
    private void handleOption(int what) {

        // Check if the option is not an invalid type.
        if (what != -1) {
            
            switch (what) {
                case EXIT:
                    System.out.println("Terminating the program.\n");
                    break;
                case DISASTER:
                    displayDisastersMenu();
                    doDisasterOption(askOption());
                    break;
                case RESOURCE:
                    displayResourcesMenu();
                    doResourceOption(askOption());
                    break;
                case DISTRIBUTE:
                    DisasterManager.helpDisasters();
                    break;
                default:
                    displayInvalidOption();
                    break;
            }
        } else {
            displayIntegerExc(); // Display invalid type exception.
        }
    }

    // Method that interacts with disaster options.
    private void doDisasterOption(int what) {

        // Check if the option is not an invalid type.
        if (what != -1) {
        
            switch (what) {
                case ADD:
                    addDisasters();
                    break;
                case VIEW:
                    DisasterReader.showAllDisasters();
                    break;
                case EXIT:
                    displayTermination();
                    break;
                default:
                    displayInvalidOption();
                    break;
            }
        } else {
            displayIntegerExc(); // Display invalid type exception.
        }
    }

    // Method that interacts with resource options.
    private void doResourceOption(int what) {

        // Check if the option is not an invalid type.
        if (what != -1) {
            
            switch (what) {
                case ADD:
                    addResources();
                    break;
                case VIEW:
                    ResourceReader.showResources();
                    break;
                case EXIT:
                    displayTermination();
                    break;
                default:
                    displayInvalidOption();
                    break;
            }
        } else {
            displayIntegerExc(); // Display invalid type exception.
        }
    }

    // Method, that writes down disasters utill user enters exit.
    private void addDisasters() {
        
        String input; 
        
        do {
            
            System.out.println("\nEnter 'exit' in the next line " + "to halt this program "
                + "or any other string to continue: ");
            
            // Obtain user's data.
            input = console.nextLine();

            // Check if user doesn't wan't to finish filling the data,
            if (!input.equalsIgnoreCase("exit")) {
                
                // Ask the information of disaster.
                System.out.println("Processing a new disaster record.");
                getDangerData();

                // Write disaster to the file.
                writeDisaster();
            }
        } while (!input.equalsIgnoreCase("exit"));
    }

    // Method, that updates disaster priority regarding to it's input.
    private void writeDisaster() {
        
        // Change the priority corresponding to disaster's type.
        priority = Disasters.determinePriority(disasterType);

        // Write down the the information of disaster.
        DisasterWriter.saveDisaster(id, disasterType, city, casualty, priority);
    }

    // Method, that writes down disasters utill user enters exit.
    private void addResources() {

        String input;

        do {

            System.out.println("\nEnter 'exit' in the next line " + "to halt this program "
                    + "or any other string to continue: ");

            // Obtain user's data.
            input = console.nextLine();

            // Check if user doesn't wan't to finish filling the data,
            if (!input.equalsIgnoreCase("exit")) {

                // Ask the information of disaster.
                System.out.println("Processing a new resource record.");

                // Write disaster to the file.
                getResourceData();
                ResourceWriter.writeResource(id, resource, quantity, city);

            }
        } while (!input.equalsIgnoreCase("exit"));
    }


    // Method, that asks for the option.
    private int askOption() {
        System.out.print("Choose: ");

        // Check if the next input is an integer.
        if (console.hasNextInt()) {
            int opt = console.nextInt();
            console.nextLine();
            return opt;
        }

        System.out.println("Invalid input. Please enter a number.");
        console.nextLine();
        return -1;
    }

    // Method, that gets the danger information by user's input.
    private void getDangerData() {
        id = askID();
        disasterType = askDisaster();
        city = askCity();
        casualty = askCasualty();
    }

    // Method, that gets the resource information by user's input.
    private void getResourceData() {
        id = askID();
        resource = askResource();
        quantity = askQuantity();
        city = askCity();
    }

    // Method that asks for user ID.
    private String askID() {
        System.out.print("Enter the ID: ");
        return console.nextLine();
    }

    // Method, that asks for disaster.
    private String askDisaster() {
        System.out.print("Enter disaster: ");
        disasterType = console.nextLine();
        return Disasters.returnType(disasterType);
    }

    // Method that asks for human casualty.
    private int askCasualty() {
        System.out.print("Enter the casualty count: ");

        // Check if the next input is an integer.
        if (console.hasNextInt()) {
            int c = console.nextInt();
            console.nextLine();
            return c;
        }

        System.out.println("Invalid input. Please enter a number.");
        console.nextLine();
        return -1;
    }


    // Method that asks for resources.
    private String askResource() {
        System.out.print("Enter resources: ");
        return console.nextLine();

    }

    // Method that asks for city.
    private String askCity() {
        System.out.print("Enter the city: ");
        return console.nextLine().toLowerCase();
    }

    // Method that asks for number of resources.
    private int askQuantity() {
        System.out.print("Enter resource quantity: ");

        // Check if the next input is an integer.
        if (console.hasNextInt()) {
            int q = console.nextInt();
            console.nextLine();
            return q;
        }

        System.out.println("Invalid input. Please enter a valid positive number.");
        console.nextLine();
        return -1;
    }

        
        // Method that displays the main options.
    private void display() {
        System.out.println("=== Disaster Management System ===");
        System.out.println("1. Manage Disasters (Add/View)");
        System.out.println("2. Manage Resources (Add/Edit/View)");
        System.out.println("3. Distribute Resources");
        System.out.println("0. Exit");
    }

    // Method that displays disaster options.
    private void displayDisastersMenu() {
        System.out.println("\n=== Disaster Manager ===");
        System.out.println("1. Add Disaster");
        System.out.println("2. View All Disasters");
        System.out.println("0. Exit");
    }

    // Method that displays resource options.
    private void displayResourcesMenu() {
        System.out.println("\n=== Resource Manager ===");
        System.out.println("1. Add Resource");
        System.out.println("2. View All Resources");
        System.out.println("0. Exit");
    }

    // Method, that displays termination method.
    private void displayTermination() {
        System.out.println("Terminating the program.\n");
    }

    // Method, that reports an error.
    private void displayInvalidOption() {
        System.out.println("Invalid option! (0-2)\n");
    }

    // Method, that displays invalid type exception.
    private void displayIntegerExc() {
        System.out.println("Invalid type. Enter integer value!");
    }

    
}