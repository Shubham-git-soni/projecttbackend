package placementorg.projectt.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import placementorg.projectt.Entity.User;
import placementorg.projectt.Service.UserService;




@RestController
public class Controller {

    @Autowired
    private UserService userService ;

    @GetMapping("/home")
    public String homepage()
    {
        return "homepage" ;
    }

    @GetMapping("/admin/login")
    public String handleAdmin()
    {
        return "admin" ;
    }
    @GetMapping("/user/login")
    public String handleUser()
    {
        return "user" ;
    }
    @PostMapping("/register/user")
    public User createUser(@RequestBody User user)
    {
        return userService.createUser(user);
    }

}
