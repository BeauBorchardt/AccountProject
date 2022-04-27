package AccountDaoPkg;

import AccountModelPkg.User;

public interface UsersInterface {

    public User getUser(String username);
    public void createUser(User user);
    public void updateUser(User user);
    public void deleterUser(User user);

}