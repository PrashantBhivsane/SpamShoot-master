package classify.spam.dexter.firebase_auth_spam_detect;

/**
 * Created by Prashant on 08-10-2017.
 */

public class UserData {

public String name,username,email,bio;

    public UserData(String name, String username, String email, String bio) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.bio = bio;
    }

    public UserData()
    {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", bio='" + bio + '\'' +
                '}';
    }
}
