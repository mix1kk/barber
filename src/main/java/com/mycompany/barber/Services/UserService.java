package com.mycompany.barber.Services;

import com.mycompany.barber.Models.User;
import com.mycompany.barber.Repository.UserRepository;
import com.mycompany.barber.Utils.User.UserNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Integer userId) {
        return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }

    public void save(User user) {
        fillUser(user);
        userRepository.save(user);
    }

    public void update(int userId, User user) {
        String createdAt = userRepository.findById(userId).orElseThrow(UserNotFoundException::new).getCreatedAt();
        fillUser(user);
        user.setCreatedAt(createdAt);
        user.setUserId(userId);
        userRepository.save(user);
    }

    public void delete(int userId) {
        userRepository.deleteById(userId);
    }

    private void fillUser(User user) {
        if (user.getCreatedAt() == null) {
            user.setCreatedAt(String.valueOf(System.currentTimeMillis()));
        }
        user.setUpdatedAt(String.valueOf(System.currentTimeMillis()));
        user.setUpdatedBy(user.getUserName());//TODO: сделать запись имени того, кто изменил поле
    }
}
