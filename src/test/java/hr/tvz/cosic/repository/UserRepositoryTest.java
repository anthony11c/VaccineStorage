package hr.tvz.cosic.repository;

import hr.tvz.cosic.user.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    void findOneByUsername() {
        Optional<User> optionalUser = userRepository.findOneByUsername("admin");

        assertNotNull(optionalUser);
        assertTrue(optionalUser.isPresent());
        assertEquals(optionalUser.get().getUsername(), "admin");
    }

    @Test
    void findOByInvalidUsername() {
        Optional<User> optionalUser = userRepository.findOneByUsername("username");

        assertNotNull(optionalUser);
        assertFalse(optionalUser.isPresent());
    }
}