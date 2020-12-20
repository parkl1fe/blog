package lt.sebpra.demoblog.servicies;

import lt.sebpra.demoblog.models.User;
import lt.sebpra.demoblog.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserByUsername(String username) {
        return userRepository.getUserByUserName(username);
    }

}
