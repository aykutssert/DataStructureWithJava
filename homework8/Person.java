import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * The {@code Person} class represents a person in the social network.
 * It contains the person's name, age, hobbies, and the timestamp of when they were added to the network.
 */
public class Person {

    private String name; // Name of the person
    private int age; // Age of the person
    private List<String> hobbies; // List of hobbies of the person
    private LocalDateTime timestamp; // Timestamp of when the person was added to the network
    private String timestampFormatted;

    /**
     * Constructs a new {@code Person} with the specified name, age, hobbies, and timestamp.
     *
     * @param name      the name of the person
     * @param age       the age of the person
     * @param hobbies   the list of hobbies of the person
     * @param timestamp the timestamp of when the person was added to the network
     */
    public Person(String name, int age, List<String> hobbies,LocalDateTime timestamp) {
        this.name = name;
        this.age = age;
        this.hobbies = hobbies;
        this.timestamp = timestamp;
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = timestamp.format(formatter);
        this.timestampFormatted = formattedDateTime.trim();
    }

    /**
     * Returns the name of the person.
     *
     * @return the name of the person
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the age of the person.
     *
     * @return the age of the person
     */
    public int getAge() {
        return age;
    }

    /**
     * Returns the list of hobbies of the person.
     *
     * @return the list of hobbies of the person
     */
    public List<String> getHobbies() {
        return hobbies;
    }

    /**
     * Returns the timestamp of when the person was added to the network.
     *
     * @return the timestamp of when the person was added to the network
     */
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    /**
     * Returns the formatted timestamp of when the person was added to the network.
     * @return the formatted timestamp of when the person was added to the network
     */
    public String getTimestampFormatted() {
        return timestampFormatted;
    }
    /**
     * Returns a string representation of the person.
     * The string representation consists of the person's name, age, and hobbies.
     *
     * @return a string representation of the person
     */
    @Override
    public String toString() {
        return name + " (Age: " + age + ", Hobbies: " + hobbies + ")";
    }
}
