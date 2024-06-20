

/*
 * File.java
 * This class represents a file in the file system.
 * It extends the FileSystemElement class.
 * It contains a name and a reference to the parent directory.
 * The print method is implemented to print the file name.
 * The File class is used to represent files in the file system.
 */
public class File extends FileSystemElement{

    /**
     * Creates a new file with the specified name and parent directory.
     * @param name the name of the file
     * @param parent the parent directory
     */
    public File(String name, FileSystemElement parent) {
        super(name, parent);
    }

    /**
     * Prints the name of the file.
     * @param prefix the prefix to print before the name
     * @param dir the current directory
     */
    @Override
    public void print(String prefix, Directory dir) {
        
        System.out.println(prefix  + getName());
    }

    
}
