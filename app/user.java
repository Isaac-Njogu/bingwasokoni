public class user {
    public String username, email, phone;

    public user() {} // Default constructor required for Firebase

    public user(String username, String email, String phone) {
        this.username = username;
        this.email = email;
        this.phone = phone;
    }
}
