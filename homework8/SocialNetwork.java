import java.time.LocalDateTime;
import java.util.*;

/**
 * Represents a social network with people and friendships.
 */
public class SocialNetwork {
    private Map<String, Person> people; // Stores the name of a person as the key and the Person object as the value.

    private Map<Person, List<Person>> graph; // Stores a Person object as the key and a list of Person objects as the value.

    /**
     * Initializes a new instance of the SocialNetwork class.
     * The people map stores the name of a person as the key and the Person object as the value.
     * The graph map stores a Person object as the key and a list of Person objects as the value.
     */
    public SocialNetwork() {
        this.people = new HashMap<>();
        this.graph = new HashMap<>();
    }

    /**
     * Adds a new person to the social network.
     * @param name The name of the person.
     * @param age The age of the person.
     * @param hobbies The list of hobbies of the person.
     */
    public void addPerson(String name, int age, List<String> hobbies) {
        if (name == null || name.isEmpty() || age <= 0 || hobbies == null) {
            System.out.println("Invalid input. Please provide valid name, age, and hobbies.");
            return;
        }
 
        LocalDateTime now = LocalDateTime.now();
        Person newPerson = new Person(name, age, hobbies,now);
        people.put(name, newPerson);
        graph.put(newPerson, new ArrayList<>());
        System.out.println("Person added: "+name+" (Timestamp: "+newPerson.getTimestampFormatted()+")");
    }

    /**
     * Removes a person from the social network.
     * @param name The name of the person to remove.
     */
    public void removePerson(String name, String timestamp) {
        if (name == null || name.isEmpty()) {
            System.out.println("Invalid input. Please provide a valid name.");
            return;
        }
    
        Person person = people.remove(name);
        
        if (person != null) {
            graph.remove(person);
            for (List<Person> friends : graph.values()) {
                friends.remove(person);
            }
            System.out.println("Person removed: " + name);
        } else {
            System.out.println("Person " + name + " not found.");
        }
    }

    /**
     * Adds a friendship between two people in the social network.
     * @param name1 The name of the first person.
     * @param name2 The name of the second person.
     */
    public void addFriendship(String name1, String name2) {
        if (name1 == null || name1.isEmpty() || name2 == null || name2.isEmpty()) {
            System.out.println("Invalid input. Please provide valid names.");
            return;
        }
        Person person1 = people.get(name1);
        Person person2 = people.get(name2);
        if (person1 != null && person2 != null) {
            graph.get(person1).add(person2);
            graph.get(person2).add(person1);
            System.out.println("Friendship added between " + name1 + " and " + name2);
        } else {
            System.out.println("One or both people not found: " + name1 + ", " + name2);
        }
    }

    /**
     * Removes a friendship between two people in the social network.
     * @param name1 The name of the first person.
     * @param name2 The name of the second person.
     */
    public void removeFriendship(String name1, String name2) {
        if (name1 == null || name1.isEmpty() || name2 == null || name2.isEmpty()) {
            System.out.println("Invalid input. Please provide valid names.");
            return;
        }
        Person person1 = people.get(name1);
        Person person2 = people.get(name2);
        if (person1 != null && person2 != null) {
            graph.get(person1).remove(person2);
            graph.get(person2).remove(person1);
            System.out.println("Friendship removed between " + name1 + " and " + name2);
        } else {
            System.out.println("One or both people not found: " + name1 + ", " + name2);
        }
    }
    

    /**
     * Finds the shortest path between two people in the social network.
     * @param name1 The name of the first person.
     * @param name2 The name of the second person.
     */
    public void findShortestPath(String name1, String name2) {
        Person start = people.get(name1);
        Person end = people.get(name2);
        if (start != null && end != null) {
            List<Person> path = bfsForPath(start, end);
            if (path != null) {
                System.out.println("Shortest path: " +
                        String.join(" -> ", path.stream().map(Person::getName).toArray(String[]::new)));
            } else {
                System.out.println("No connection found between " + name1 + " and " + name2);
            }
        } else {
            System.out.println("One or both people not found: " + name1 + ", " + name2);
        }
    }
    
    /**
     * Suggests friends for a person based on mutual friends and common hobbies.
     * @param name The name of the person.
     * @param maxSuggestions The maximum number of suggestions to display.
     */
    public void suggestFriends(String name, int maxSuggestions) {
        Person person = people.get(name);
        if (person == null) {
            System.out.println("Person " + name + " not found.");
            return;
        }

        List<Suggestion> suggestions = new ArrayList<>();

        for (Person candidate : graph.keySet()) {
            if (!candidate.equals(person) && !graph.get(person).contains(candidate)) {
                int mutualFriends = (int) graph.get(person).stream().filter(graph.get(candidate)::contains).count();
                long commonHobbies = person.getHobbies().stream().filter(candidate.getHobbies()::contains).count();
                double score = mutualFriends + 0.5 * commonHobbies;

                if (score > 0) {
                    suggestions.add(new Suggestion(candidate, score, mutualFriends, commonHobbies));
                }
            }
        }

        suggestions.sort(Comparator.comparingDouble(Suggestion::getScore).reversed());
        
        for (int i = 0; i < Math.min(maxSuggestions, suggestions.size()); i++) {
            Suggestion suggestion = suggestions.get(i);
            System.out.printf("%s (Score: %.1f, %d mutual friends, %d common hobbies)\n", 
                suggestion.getPerson().getName(), suggestion.getScore(), suggestion.getMutualFriends(), suggestion.getCommonHobbies());
        }
    }


    /**
     * Counts the number of clusters in the social network.
     */
    public void countClusters() {
        Set<Person> visited = new HashSet<>();
        List<List<Person>> clusters = new ArrayList<>();

        for (Person person : graph.keySet()) {
            if (!visited.contains(person)) {
                List<Person> cluster = new ArrayList<>();
                bfs(person, visited, cluster);
                clusters.add(cluster);
            }
        }

        // Handling empty clusters: if a person has no friends, they are still included as a cluster.
        // This approach ensures that every person is accounted for in the network.
        System.out.println("Number of clusters found: " + clusters.size());
        for (int i = 0; i < clusters.size(); i++) {
            System.out.println("Cluster " + (i + 1) + ":");
            for (Person person : clusters.get(i)) {
                System.out.println("  " + person.getName());
            }
        }
    }
    
    /**
     * Performs a breadth-first search (BFS) traversal of the social network graph.
     * @param start The starting person.
     * @param visited The set of visited people.
     * @param cluster The list of people in the cluster.
     */
    private void bfs(Person start, Set<Person> visited, List<Person> cluster) {
        Queue<Person> queue = new LinkedList<>();
        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            Person current = queue.poll();
            cluster.add(current);

            for (Person neighbor : graph.get(current)) {
                if (!visited.contains(neighbor)) {
                    queue.add(neighbor);
                    visited.add(neighbor);
                }
            }
        }
    }

    /**
     * Performs a breadth-first search (BFS) traversal of the social network graph to find the shortest path between two people.
     * @param start The starting person.
     * @param end The ending person.
     * @return The shortest path between the two people.
     */
    private List<Person> bfsForPath(Person start, Person end) {
        Queue<List<Person>> queue = new LinkedList<>();
        Set<Person> visited = new HashSet<>();
        queue.add(Collections.singletonList(start));
        visited.add(start);

        while (!queue.isEmpty()) {
            List<Person> path = queue.poll();
            Person current = path.get(path.size() - 1);

            if (current.equals(end)) {
                return path;
            }

            for (Person neighbor : graph.get(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    List<Person> newPath = new ArrayList<>(path);
                    newPath.add(neighbor);
                    queue.add(newPath);
                }
            }
        }
        return null;
    }

    /**
     * Represents a suggestion for a friend based on mutual friends and common hobbies.
     */
    private class Suggestion {
        private Person person; // The suggested person.
        private double score; // The score of the suggestion.
        private int mutualFriends; // The number of mutual friends.
        private long commonHobbies; // The number of common hobbies.

        /**
         * Initializes a new instance of the Suggestion class.
         * @param person The suggested person.
         * @param score The score of the suggestion.
         * @param mutualFriends The number of mutual friends.
         * @param commonHobbies The number of common hobbies.
         */
        public Suggestion(Person person, double score, int mutualFriends, long commonHobbies) {
            this.person = person;
            this.score = score;
            this.mutualFriends = mutualFriends;
            this.commonHobbies = commonHobbies;
        }

        /**
         * Gets the suggested person.
         * @return The suggested person.
         */
        public Person getPerson() {
            return person;
        }

        /**
         * Gets the score of the suggestion.
         * @return The score of the suggestion.
         */
        public double getScore() {
            return score;
        }

        /**
         * Gets the number of mutual friends.
         * @return The number of mutual friends.
         */
        public int getMutualFriends() {
            return mutualFriends;
        }

        /**
         * Gets the number of common hobbies.
         * @return The number of common hobbies.
         */
        public long getCommonHobbies() {
            return commonHobbies;
        }
    }
}
