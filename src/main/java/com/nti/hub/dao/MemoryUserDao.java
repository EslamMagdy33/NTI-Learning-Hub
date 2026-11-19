package  com.nti.hub.dao;

import  com.nti.hub.model.User;

import java.util.ArrayList;
import java.util.List;

public class MemoryUserDao implements UserDao {
    private final List<User> users =new ArrayList<>();

    @Override
    public boolean save(User user) {
        var existingUser=findByEmail(user.getEmail());
        if (existingUser != null ){
            return  false;
        }
        return users.add(user);
    }

    @Override
    public User findByEmail(String email) {
        if (email==null)
            return null;
        for (User user: users){
            if (user.getEmail().equalsIgnoreCase(email)){
                return user;
            }
        }
        return null;
    }

    @Override
    public int count() {
        return users.size();
    }

    @Override
    public boolean deleteByEmail(String email) {
        return users.removeIf(u->u.getEmail().equalsIgnoreCase(email));
    }
}
