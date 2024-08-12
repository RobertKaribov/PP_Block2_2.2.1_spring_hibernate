package hiber.service;

import hiber.model.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService {
    void addUser(User user);
    List<User> getAllUsers();
    @Transactional
    User findUserByCarModelAndSeries(String model, int series);
}
