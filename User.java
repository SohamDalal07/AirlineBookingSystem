

 
public class User {
    private String username;
    private String password;
 
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
 
    public String getUsername() {
        return username;
    }
 
    public boolean authenticate(String password) {
        return this.password.equals(password);
    }
 
    public String toFileString() {
        return username + "," + password;
    }
 
    public static User fromFileString(String line) {
        String[] parts = line.split(",");
        if (parts.length == 2) {
            return new User(parts[0], parts[1]);
        }
        return null;
    }
}
 
 