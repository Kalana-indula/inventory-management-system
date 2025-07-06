package org.inventory.app.service;

import org.inventory.app.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    User createUser(User user);

    List<User> getAllUsers();
}
