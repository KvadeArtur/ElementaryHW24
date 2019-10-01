package com.kvart;

import java.util.ArrayList;
import java.util.List;

public class UsersDao {

    private static UsersDao instance;
    private List<User> users = new ArrayList<>();
    private int id = 0;

    public static synchronized UsersDao getInstance() {
        if (instance == null) {
            instance = new UsersDao();
        }
        return instance;
    }

    public UsersDao() {
    }

    public int genereteId () {
        id++;
        return id;
    }

    public void addUser (User user) {
        users.add(user);
    }

    public List<User> getAllUsers () {
        return users;
    }

    public void deleteUser (int id) {
        for (User user: users) {
            if (user.getId() == id) {
                users.remove(user);
            }
        }
    }

    public void updateUser (User userIn) {
        for (User user: users) {
            if (user.getId() == userIn.getId()) {
                users.get(userIn.getId()).setName(userIn.getName());
                users.get(userIn.getId()).setAge(userIn.getAge());
            }
        }
    }
}
