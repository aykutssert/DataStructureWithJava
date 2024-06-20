import java.sql.Timestamp;


/**
 * FileSystemElement.java
 * This class represents a file system element, which can be a file or a directory.
 * It contains a name, a date created, and a reference to the parent directory.
 * The print method is abstract and must be implemented by subclasses.
 * The FileSystemElement class is used as a superclass for the File and Directory classes.
 * The FileSystemElement class is used to represent files and directories in the file system.
 */
public abstract class FileSystemElement {
    /**
     * The name of the file system element.
     */
    protected String name;

    /**
     * The date the file system element was created.
     */
    protected Timestamp dateCreated;

    /**
     * The parent directory of the file system element.
     */
    protected FileSystemElement parent;

    /**
     * Creates a new file system element with the specified name and parent directory.
     */
    public FileSystemElement(String name, FileSystemElement parent){
        this.name = name;
        this.parent = parent;
        this.dateCreated = new Timestamp(System.currentTimeMillis());
    }

    /**
     * Returns the name of the file system element.
     * @return the name of the file system element
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the date the file system element was created.
     * @return the date the file system element was created
     */
    public Timestamp getDateCreated() {
        return dateCreated;
    }
    /**
     * Returns the parent directory of the file system element.
     * @return the parent directory of the file system element
     */
    public FileSystemElement getParent() {
        return parent;
    }

    /**
     * Sets the parent directory of the file system element.
     * @param parent the parent directory of the file system element
     */
    public void setParent(FileSystemElement parent) {
        this.parent = parent;
    }
    /**
     * Prints the file system element with the specified prefix.
     * @param prefix the prefix to print before the file system element
     * @param dir the current directory
     */
    public abstract void print(String prefix,Directory dir); // to help in printing the directory size

    /**
     * Returns the current path of the file system element.
     * @return the current path of the file system element
     */
    public String getCurrentPath(){
        
        if (parent == null) {
            if(name.equals("root")){
                return "/";
            }
            return name;
        } else {
            if(parent.getName().equals("root")){
                return "/" + name;
            }

            return parent.getCurrentPath() + "/" + name;
        }
    }
}