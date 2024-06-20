
import java.util.LinkedList;

/**
 * The Directory class represents a directory in the file system.
 * It contains a list of children, which can be either directories or files.
 * The Directory class extends the FileSystemElement class.
 */
public class Directory extends FileSystemElement{

    
    /**
     * The list of children of the directory.
     */
    private LinkedList<FileSystemElement> children;

    
    /**
     * Creates a new directory with the specified name and parent directory.
     * The list of children is initialized as an empty list.
     * @param name the name of the directory
     * @param parent the parent directory
     */
    public Directory(String name, FileSystemElement parent) {
        super(name, parent);
        children = new LinkedList<>();
    }

    
    /**
     * Adds the specified file or directory to the list of children of this directory.
     * @param element the file or directory to add
     */
    public void addElement(FileSystemElement element) {
        children.add(element);
    }

    

    /**
     * Removes the specified file or directory from the list of children of this directory.
     * @param element the file or directory to remove
     */
    public void removeELement(FileSystemElement element) {
        children.remove(element);
    }


    /**
     * Removes the file or directory at the specified index from the list of children of this directory.
     * @param index the index of the file or directory to remove
     */
    public void removeElementwithNoCopying(FileSystemElement element){
        int index = children.indexOf(element);
        children.remove(index);
    }


    /**
     * Returns the list of children of this directory.
     * @return the list of children
     */
    public LinkedList<FileSystemElement> getChildren() {
        return children;
    }

    
    /**
     * Prints the name of the directory followed by a slash ("/").
     * Then, it prints the names of all children of the directory.
     * The names of the children are indented by the specified prefix.
     * @param prefix the prefix to use for indentation
     * @param dir the current directory
     */

    @Override
    public void print(String prefix, Directory dir) {
        System.out.println(prefix + getName() + "/");

    
        for (FileSystemElement element : children) {
            if (element instanceof Directory) {
                // Recursive call for subdirectories until the specified directory (dir) is reached
                if (dir.equals((Directory) element)) {
                    // If the specified directory is reached, stop recursion
                    ((Directory) element).print("  " + prefix, dir);
                    return;
                }
                ((Directory) element).print("  " + prefix, dir);
            } else {

                // Print files with the same indentation as directories
                element.print("  " + prefix.replace("*", "\b"), dir);
            }
        }
    }
    
    


    /**
     * Returns the current path of the directory.
     * The current path is the path from the root directory to the current directory.
     * @return the current path of the directory
     */
    @Override
    public String getCurrentPath(){
        
        if (getParent() == null) {
            return "/";
        } else {
            // If the current directory is the root, return an empty string to avoid duplication
            if (getParent().getParent() == null) {
                return "/" + getName();
            } else {
                return getParent().getCurrentPath() + "/" + getName();
            }
        }
    }
    

}

