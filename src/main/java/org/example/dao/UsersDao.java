package org.example.dao;

import org.example.entity.User;

public interface UsersDao {
    User getUser(int id);
    boolean insertUser(User user);
    boolean updateUser(User user);
    boolean deleteUser(int id);
}
