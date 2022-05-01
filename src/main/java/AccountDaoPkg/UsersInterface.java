package AccountDaoPkg;

import AccountModelPkg.User;

public interface UsersInterface {

    User getUser(String username);
    User createUser(User user);
    void updateUser(User user);
    void deleteUser(User user);

}
