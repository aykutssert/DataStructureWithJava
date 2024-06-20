//Manages the root directory and contains methods for user interactions such as navigating the directory tree, and managing files and directories.


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * FileSystem.java
 * This class represents the file system.
 * It contains the root directory and methods for managing the file system.
 * The FileSystem class is used to manage the file system, including creating, deleting, and moving files and directories.
 */
public class FileSystem {
    
    /**
     * The root directory of the file system.
     */
    private Directory root;
   
    /**
     * Creates a new file system with a root directory.
     * The root directory is named "root" and has no parent directory.
     */
    public FileSystem() {
        root = new Directory("root", null);
        
    }

    /**
     * Returns the root directory of the file system.
     * @return the root directory of the file system
     */
    public Directory getRoot() {
        return root;
    }


    /**
     * Adds a new directory with the specified name to the parent directory.
     * @param name the name of the new directory
     * @param parent the parent directory
     */
    public void addDirectory(String name,Directory parent) {
        Directory newDir = new Directory(name, parent);
        parent.addElement(newDir);
    }


    /**
     * Adds a new file with the specified name to the parent directory.
     * @param name the name of the new file
     * @param parent the parent directory
     */
    public void addFile(String name, Directory parent) {
        File newFile = new File(name, parent);
        parent.addElement(newFile);
    }


    /**
     * Deletes the file with the specified name from the parent directory.
     * @param name the name of the file to delete
     * @param parent the parent directory
     * @return true if the file is deleted, false otherwise
     */
    public boolean deleteFile(String name , Directory parent){
        
        for (FileSystemElement element : parent.getChildren()) {
            if (element instanceof File && element.getName().equals(name)) {
                parent.removeELement((File) element);
                return true;
            }
        }
        
        return false;
    }


    /**
     * Deletes the directory with the specified name from the parent directory.
     * @param name the name of the directory to delete
     * @param parent the parent directory
     * @return true if the directory is deleted, false otherwise
     */
    public boolean deleteDirectory(String name , Directory parent){

        for (FileSystemElement element : parent.getChildren()) {
            if (element instanceof Directory && element.getName().equals(name)) {
                parent.removeELement((Directory) element);
                return true;
            }
        }
        return false;
    }

    
    /**
     * Moves the file or directory with the specified name to the new parent directory.
     * @param name the name of the file or directory to move
     * @param newParent the new parent directory
     */
    public void moveElement(String name, Directory newParent){
        moveElementHelper(name, newParent, root);

    }

    /**
     * Helper method to move the file or directory with the specified name to the new parent directory.
     * @param name the name of the file or directory to move
     * @param newParent the new parent directory
     * @param dir the current directory being searched
     */
     public void moveElementHelper(String name, Directory newParent, Directory dir) {
        List<FileSystemElement> childrenCopy = new ArrayList<>(dir.getChildren());
    
        for (FileSystemElement element : childrenCopy) {
            if (element.getName().equals(name)) {
                if (element instanceof File) {
                    newParent.addElement(new File(name, newParent));
                    dir.removeELement((File) element);
                    System.out.println("File Moved: " + name + " to " + newParent.getCurrentPath());
                } else if (element instanceof Directory) {
                    newParent.addElement(new Directory(name, newParent));
                    dir.removeELement((Directory) element);
                    System.out.println("Directory Moved: " + name + " to " + newParent.getCurrentPath());
                }
                return;
            }
            if (element instanceof Directory) {
                moveElementHelper(name, newParent, (Directory) element);
            }
        }
    }
    


    /**
     * Searches for the file or directory with the specified name in the file system.
     * @param name the name of the file or directory to search for
     */
    public void search(String name){
        //searching from root
        System.out.println("Searching from root...");
        searchHelper(name, root);
    }


    /**
     * Helper method to search for the file or directory with the specified name in the file system.
     * @param name the name of the file or directory to search for
     * @param dir the current directory being searched
     * @return true if the file or directory is found, false otherwise
     */

     public void searchHelper(String name, Directory dir){
        for (FileSystemElement element : dir.getChildren()) {
            if (element.getName().equals(name)) {
                System.out.println("Found: " + element.getCurrentPath());
            }
            if (element instanceof Directory) {
                searchHelper(name, (Directory) element);
            }
        }
    }
    
    
    

    /**
     * Prints the directory tree starting from the specified directory.
     * @param dir the directory to start printing from
     */
    public void printDirectoryTree2(Directory dir) {
        root.print("* ",dir);
    }
    

    
    /**
     * Lists the contents of the specified directory.
     * @param dir the directory to list the contents of
     */
    public void listContents(Directory dir) {
        //if no children
        
        if(dir.getChildren().isEmpty()){
            System.out.println("Directory is empty.");
            return;
        }
        for (FileSystemElement element : dir.getChildren()) {
            if (element instanceof Directory) {
                System.out.println("* " + element.getName()+"/");
            } else {
                System.out.println(element.getName());
            }
        }
    }


    /**
     * Sorts the contents of the specified directory by date created.
     * @param dir the directory to sort
     */
    public void sortDirectoryByDate(Directory dir) {
        // Sort the directory's children by date
        dir.getChildren().sort((element1, element2) -> {
            Timestamp date1 = element1.getDateCreated();
            Timestamp date2 = element2.getDateCreated();
            return date1.compareTo(date2);
        });

        System.out.println("Sorted contents of " + dir.getCurrentPath() + " by date created:");
        for (FileSystemElement element : dir.getChildren()) {
            if (element instanceof Directory) {
                System.out.println("* " + element.getName() + " (" + element.getDateCreated() + ")");
            } else {
                System.out.println(element.getName() + " (" + element.getDateCreated() + ")");
            }
            
        }
    } 
    
    
    /**
     * Returns the current path of the specified directory.
     * The current path is the path from the root directory to the specified directory.
     * @param dir the directory to get the current path of
     * @return the current path of the directory
     */
    public String getCurrentPath(Directory dir){
        return dir.getCurrentPath();
    }


    /**
     * Changes the current directory to the directory at the specified path.
     * @param path the path of the directory to change to
     * @return the directory at the end of the path, or null if the directory is not found
     */
    public Directory changeDirectory(String path){
        String[] directories = path.split("/");
        Directory currentDirectory  = root;
        // Traverse each directory in the path
        for (String dirName : directories) {
            // Ignore empty directory names
            if (dirName.isEmpty()) {
                continue;
            }
            
            // Search for the directory with the given name in the current directory's children
            boolean found = false;
            for (FileSystemElement element : currentDirectory.getChildren()) {
                if (element instanceof Directory && element.getName().equals(dirName)) {
                    currentDirectory = (Directory) element;
                    found = true;
                    break;
                }
            }
            
            // If the directory is not found, return null
            if (!found) {
                System.out.println("Directory '" + path + "' not found.");
                return null;
            }
        }
        
        // Return the directory at the end of the path
        return currentDirectory;
        
    }
    

    
}
