package AccountModelPkg;

public class UsersMod {
    int userId;
    String userName;
    String password;
    int accessLevel;

    @Override
    public String toString() {
        return "UsersMod{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", accessLevel=" + accessLevel +
                '}';
    }
}
