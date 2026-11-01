import java.util.regex.Pattern;

public class User {
    private static final Pattern NAME_PATTERN = Pattern.compile("[\\p{L}]+(?:[ '\\-][\\p{L}]+)*");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9]+(?:[.-][A-Za-z0-9]+)*\\.[A-Za-z]{2,}");

    private int userID;
    private String firstName;
    private String lastName;
    private String email;

    public User(int userID, String firstName, String lastName, String email) {
        if (userID <= 0) {
            throw new IllegalArgumentException("User ID must be positive");
        }
        validateProfile(firstName, lastName, email);
        this.userID = userID;
        this.firstName = firstName.trim();
        this.lastName = lastName.trim();
        this.email = email.trim();
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    // Update the profile; UserID stays unchanged
    public void updateProfile(String firstName, String lastName, String email) {
        validateProfile(firstName, lastName, email);
        this.firstName = firstName.trim();
        this.lastName = lastName.trim();
        this.email = email.trim();
    }

    private static void validateProfile(String firstName, String lastName, String email) {
        if (firstName == null || !NAME_PATTERN.matcher(firstName.trim()).matches()) {
            throw new IllegalArgumentException("Invalid first name");
        }
        if (lastName == null || !NAME_PATTERN.matcher(lastName.trim()).matches()) {
            throw new IllegalArgumentException("Invalid last name");
        }
        if (email == null || !EMAIL_PATTERN.matcher(email.trim()).matches()) {
            throw new IllegalArgumentException("Invalid email address");
        }
    }

    public int getUserID() { return userID; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
}
