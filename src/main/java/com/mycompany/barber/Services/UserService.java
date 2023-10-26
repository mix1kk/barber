package com.mycompany.barber.Services;

import com.mycompany.barber.Models.User;
import com.mycompany.barber.Repository.CompanyRepository;
import com.mycompany.barber.Repository.UserRepository;
import com.mycompany.barber.Utils.User.UserNotDeletedException;
import com.mycompany.barber.Utils.User.UserNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;

    @Autowired
    public UserService(UserRepository userRepository, CompanyRepository companyRepository) {
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Integer userId) {
        return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }

    public List<User> findByUserCompany(String userCompany) {
        return userRepository.findByUserCompany(userCompany);
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
        if (!userRepository.existsById(userId)) {
            throw new UserNotDeletedException("Не существует пользователя");
        }
        userRepository.deleteById(userId);
        if (userRepository.existsById(userId)) {
            throw new UserNotDeletedException("Не удалось удалить пользователя");
        }
    }

    private void fillUser(User user) {
        if (user.getCreatedAt() == null) {
            user.setCreatedAt(String.valueOf(System.currentTimeMillis()));
        }
        user.setUpdatedAt(String.valueOf(System.currentTimeMillis()));
        user.setUpdatedBy(user.getUserName());//TODO: сделать запись имени того, кто изменил поле
    }
}
