package org.example.service;

import org.example.entity.User;

public interface UserService {
    User getUser(int id);
    boolean createUser(User user);
    boolean deleteUser(int id);
}
