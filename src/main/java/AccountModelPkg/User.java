package AccountModelPkg;

public class User {
    public int userId;
    public String username;
    public String password;
    public int accessLevel;

    @Override
    public String toString() {
        return "UsersMod{" +
                "userId=" + userId +
                ", userName='" + username + '\'' +
                ", password='" + password + '\'' +
                ", accessLevel=" + accessLevel +
                '}';
    }
}
