package org.example.userdao;

public interface UsersDao {
    User getUser(int id);
    boolean insertUser(User user);
    boolean updateUser(User user);
    boolean deleteUser(int id);
}
