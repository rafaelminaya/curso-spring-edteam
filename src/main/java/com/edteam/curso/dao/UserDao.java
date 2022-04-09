package com.edteam.curso.dao;

import com.edteam.curso.models.User;

import java.util.List;

public interface UserDao {
    List<User> getAll();
    User get(long id);
    void register(User user);
    User update(User user);
    void delete(long id);
    User login(User user);

}
