package AccountDaoPkg;

import AccountModelPkg.User;

public interface UsersInterface {

    public User getUser(String username);
    public User createUser(User user);
    public void updateUser(User user);
    public void deleteUser(User user);

}
