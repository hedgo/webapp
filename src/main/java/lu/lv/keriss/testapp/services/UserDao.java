package lu.lv.keriss.testapp.services;

import lu.lv.keriss.testapp.model.User;
import org.springframework.stereotype.Repository;
import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created with IntelliJ IDEA.
 * User: KERISS
 * Date: 04.11.13
 * Time: 6:36
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class UserDao implements IUserDao {
    private TreeMap<Long, User> users = new TreeMap<Long, User>();
    private AtomicLong sequence = new AtomicLong(0);
    private String[] names = {"John","Jack","Steve","Bill"};
    private int maxUsers = 10;


    public UserDao() {
        init();
    }

    private void init(){
        Random rand = new Random();
        for(int i=1;i<=maxUsers;i++){
            addUser(new User(names[rand.nextInt(4)] + "_" + i, rand.nextInt(99), rand.nextBoolean()));
        }
    }

    @Override
    public List<User> getAll() {
        return new ArrayList<User>(users.values());
    }

    @Override
    public void updateUser(User user) {
        if(users.get(user.getId())!=null){
            users.put(user.getId(), user);
        }else{
            addUser(user);
        }
    }

    @Override
    public void deleteUser(Long id) {
        users.remove(id);
    }

    @Override
    public void addUser(User user) {
        if (user.getId() == null) {
            user.setId(sequence.getAndIncrement());
        }
        users.put(user.getId(), user);
    }

    @Override
    public User findUserById(Long id) {
        return users.get(id);
    }
}
