package com.eximius.eximius;

import com.eximius.eximius.Entities.User;
import com.eximius.eximius.Repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindByUserName() {
        User user = new User();
        user.setUserName("testuser");
        user.setPassword("password123");

        userRepository.save(user);

        Optional<User> foundUser = userRepository.findByUserName("testuser");
        assertTrue(foundUser.isPresent());
    }

    @Test
    public void testExistsByUserName() {
        boolean exists = userRepository.existsByUserName("testuser");
        assertTrue(exists);
    }
}