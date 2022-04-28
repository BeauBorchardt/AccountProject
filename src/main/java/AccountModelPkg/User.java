package AccountModelPkg;

public class User {
    public int userId;
    public String username;
    public String password;
    public int accessLevel;

    public User(){

    }

    public void setUsername(String username){
        this.username = username;
    }
    public int getUserId() {
        return this.userId;
    }
    public void setPassword(String password){
        this.password = password;
    }


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
