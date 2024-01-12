package hr.tvz.cosic.service;

import hr.tvz.cosic.user.UserDTO;

import java.util.Optional;

public interface UserService {

    Optional<UserDTO> findByUsername(String username);
}
