# Disaster Manager

A Java-based disaster management system that coordinates emergency response by matching resource storage locations with disaster-stricken areas.

## Features

- **Disaster Management**: Record and track disasters with IDs, types, locations, and casualty counts
- **Resource Management**: Manage emergency resources with quantities and storage locations  
- **Location Matching**: Automatically match resources from storage in the same city as the disaster
- **Priority System**: Disaster priorities are calculated based on type (Earthquake: 8, Flood: 9, War: 10)

## Planned Improvement

The next major enhancement for this system will be implementing true priority-based resource distribution. Currently, disasters are processed in file order, but the foundation is laid for prioritizing by disaster severity. This will be implemented in the next version to ensure highest-priority disasters receive resources first.

## Project Structure

`Disaster-manager/`  
├── `data/`  
│   ├── `requests.txt`  
│   └── `resources.txt`  
├── `disastermanager/`  
│   ├── `main/`  
│   │   ├── `ConsoleInterface.java`  
│   │   └── `Main.java`  
│   └── `datamanager/`  
│       ├── `disaster/`  
│       │   ├── `DisasterManager.java`  
│       │   ├── `DisasterReader.java`  
│       │   ├── `DisasterWriter.java`  
│       │   └── `Disasters.java`  
│       └── `resource/`  
│           ├── `ResourceManager.java`  
│           ├── `ResourceReader.java`  
│           └── `ResourceWriter.java`  

## Installation

1. Clone the repository:  
`git clone https://github.com/sectep/Disaster-manager.git`  
`cd Disaster-manager`

2. Compile:  
`javac -d bin disastermanager/main/Main.java disastermanager/datamanager/disaster/*.java disastermanager/datamanager/resource/*.java`

3. Run:  
`java -cp bin disastermanager.main.Main`

## Usage

The application provides a console menu with these options:

1. **Manage Disasters** (Add/View)
2. **Manage Resources** (Add/View)  
3. **Distribute Resources**
0. **Exit**

## Data Format

### requests.txt:
```
Id: D001
Disaster: EARTHQUAKE
City: tokyo
Casualty: 150
Priority: 8
```

### resources.txt:
```
Id: R001
Resource: Medical Kits
Quantity: 100
City: tokyo
```

## Contributing

Contributions are welcome! The planned priority distribution system is a great feature for anyone interested in algorithms and emergency management. Feel free to submit pull requests for this or other improvements.

## License

MIT License
