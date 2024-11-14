package placementorg.projectt.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import placementorg.projectt.Entity.User;
import placementorg.projectt.Repository.UserRepository;

@Service
public class UserService {

    @Autowired
    PasswordEncoder passwordEncoder ;
    @Autowired
    UserRepository userRepository ;


    public User createUser(@RequestBody  User user)
    {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return  userRepository.save(user) ;
    }
}
