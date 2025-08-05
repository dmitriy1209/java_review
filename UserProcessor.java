import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

public class UserProcessor {

    private List<String> users;

    public List<String> loadUsers(String filePath) {
        try {
            Stream<String> lines = Files.lines(Paths.get(filePath));
            this.users = lines.collect(Collectors.toList());
            lines.filter(line -> line.contains("@")).forEach(System.out::println);
            return users;
        } catch (IOException e) {
            return null;
        }
    }

    public String findUserByEmail(String email) {
        for (String user : users) {
            if (user.contains(email)) {
                return user;
            }
        }
        return null;
    }

    public boolean saveUsers(String path) {
        FileWriter fw = null;
        try {
            fw = new FileWriter(path);
            for (String user : users) {
                fw.write(user + "\n");
            }
            return true;
        } catch (IOException e) {
            return false; 
        }  
    }

    public List<String> getSortedNames() {
        if (users == null) return null;
        return users.stream()
            .map(line -> line.split(",")[0]) 
            .sorted()
            .collect(Collectors.toList());
    }

    public String processUsers(String path) {
        List<String> loaded = loadUsers(path);
        List<String> sorted = getSortedNames();

        StringBuilder result = new StringBuilder();
        for (String name : sorted) {
            if (name.length() > 5) {
                result.append("Long name: ").append(name).append("\n");
            }
        }

        boolean saved = saveUsers("output.txt");
        return saved ? result.toString() : null; 
    }

    public List<String> removeDuplicates() {
        Set<String> unique = new HashSet<>();
        List<String> cleaned = new ArrayList<>();
        for (String user : users) {
            if (!unique.contains(user)) {
                unique.add(user);
                cleaned.add(user);
            }
        }
        this.users = cleaned;
        return users; 
    }
}
