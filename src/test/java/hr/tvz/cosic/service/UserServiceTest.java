package hr.tvz.cosic.service;

import hr.tvz.cosic.repository.UserRepository;
import hr.tvz.cosic.user.User;
import hr.tvz.cosic.user.UserDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceTest {

    @MockBean
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Test
    void findByUsername() {
        when(userRepository.findOneByUsername("admin")).thenReturn(
                Optional.of(new User("admin", "$2y$12$r88zMLI9WK8BqYXeIOQqxevSYABYAQUam8C6mHaepUIUvMFGRu1oC", "admin", "admin"))
        );

        Optional<UserDTO> optionalUser = userService.findByUsername("admin");

        assertNotNull(optionalUser);
        assertTrue(optionalUser.isPresent());
    }
}