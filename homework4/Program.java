
public class Program {
    // Create a new FileSystem object and a Directory object to represent the current directory.
    private static FileSystem fs = new FileSystem();
    // Create a new Directory object to represent the current directory.
    private static Directory currentDirectory;

    public static void main(String[] args) {

        currentDirectory = fs.getRoot();
        String option = "";
        while(true){
        
        System.out.println("===== File System Management Menu =====");
        System.out.println("Current directory: "+fs.getCurrentPath(currentDirectory));
        System.out.println("1. Change directory");
        System.out.println("2. List directory contents");
        System.out.println("3. Create file/directory");
        System.out.println("4. Delete file/directory");
        System.out.println("5. Move file/directory");
        System.out.println("6. Search file/directory");
        System.out.println("7. Print directory tree");
        System.out.println("8. Sort contents by date created");
        System.out.println("9. Exit");
        System.out.print("Please select an option:");
        option = System.console().readLine();
            switch (option) {
                case "1":
                    changeDirectory();
                    break;
                case "2":
                    listContents();
                    break;
                case "3":
                    System.out.println("Current directory: "+fs.getCurrentPath(currentDirectory));
                    System.out.println("Create file or directory (f/d):");
                    String type = System.console().readLine();
                    if (type.equals("d")) {
                       createDirectory();   
                    } else if (type.equals("f")) {
                        createFile();
                    } else {
                        System.out.println("Invalid option");
                    }
                    break;
                case "4":
                    System.out.println("Delete file or directory (f/d):");
                    String type2 = System.console().readLine();
                    if (type2.equals("d")) {
                        deleteDirectory();
                    } else if (type2.equals("f")) {
                        deleteFile();
                    } else {
                        System.out.println("Invalid option");
                    }
                    
                    break;
                case "5":
                    moveElement();
                    break;
                case "6":
                    search();
                    break;
                case "7":
                    printDirectoryTree();
                    break;
                case "8":
                    sortContentsByDate();   
                    break;
                case "9":
                
                    System.out.println("Exit");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }
    }


    /**
     * Changes the current directory to the specified directory.
     */
    private static void changeDirectory(){
        System.out.println("Current directory: "+fs.getCurrentPath(currentDirectory));
        System.out.print("Enter name of directory to change to:");
        String name = System.console().readLine();
        if(fs.changeDirectory(name) != null) currentDirectory = fs.changeDirectory(name);

    }


    /**
     * Lists the contents of the current directory.
     */
    private static void listContents(){
        fs.listContents(currentDirectory);
    }


    /**
     * Creates a new file in the current directory.
     */
    private static void createFile(){

        System.out.print("Enter name for new file:");
        String name = System.console().readLine();
        fs.addFile(name, currentDirectory);
        System.out.println("File created: " + name);
    }


    /**
     * Creates a new directory in the current directory.
     */
    private static void createDirectory(){
        System.out.print("Enter name for new directory:");
        String name = System.console().readLine();
        fs.addDirectory(name, currentDirectory);
        System.out.println("Directory created: " + name + "/");
    }


    /**
     * Deletes a file in the current directory.
     */
    private static void deleteFile(){
        System.out.print("Enter name of file to delete:");
        String name = System.console().readLine();
        
        if(fs.deleteFile(name, currentDirectory)) System.out.println("File deleted: " + name);
        else System.out.println("File not found.");
    }


    /**
     * Deletes a directory in the current directory.
     */
    private static void deleteDirectory(){
        System.out.println("Enter name of directory to delete:");
        String name = System.console().readLine();
        if(fs.deleteDirectory(name, currentDirectory)) System.out.println("Directory deleted: " + name + "/");
        else System.out.println("Directory not found.");
    }

    
    /**
     * Moves a file or directory to a new directory.
     */
    private static void moveElement(){
        System.out.println("Current directory: "+fs.getCurrentPath(currentDirectory));
        System.out.print("Enter the name of file/directory to move:");
        String name = System.console().readLine();
        System.out.print("Enter new directory path:");
        String path = System.console().readLine();
        Directory newParent = fs.changeDirectory(path);

        if(newParent != null)fs.moveElement(name, newParent);
        else System.out.println("Directory not found.");
        
    }


    /**
     * Searches for a file or directory in the file system.
     */
    private static void search(){
        System.out.println("Search query:");
        String name = System.console().readLine();
        fs.search(name);
        
    }



    /**
     * Prints the directory tree of the file system.
     */
    private static void printDirectoryTree(){
        fs.printDirectoryTree2(currentDirectory);
    }

    
    /**
     * Sorts the contents of the current directory by date created.
     */
    private static void sortContentsByDate(){
        fs.sortDirectoryByDate(currentDirectory);
    }



    
}
