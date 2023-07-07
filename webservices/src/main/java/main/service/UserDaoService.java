package main.service;

import main.entities.User;
import org.springframework.stereotype.Component;

import java.sql.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

@Component
public class UserDaoService {

    private static List<User> users = new ArrayList<>();
    private static Integer usersCount = 0;

    static {
        users.add(new User(++usersCount,"Adam" , LocalDate.now().minusYears(31)));
        users.add(new User(++usersCount,"Eva" , LocalDate.now().minusYears(32)));
        users.add(new User(++usersCount,"John" , LocalDate.now().minusYears(33)));
    }

    public List<User> findAll(){
        return users;
    }

    public User saveUser(User user){
        user.setId(++usersCount);
        users.add(user);
        return user;
    }

    public User findOne(int id) {
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        return users.stream().filter(predicate).findFirst().orElse(null);
    }

    public void deleteById(Integer id) {
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        users.removeIf(predicate);
    }
}
