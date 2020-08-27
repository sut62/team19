package com.project.backend.Login.Controller;
import com.project.backend.Login.Entity.Login;
import com.project.backend.Login.Repository.LoginRepository ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;
@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class LoginController {
    @Autowired
    private final LoginRepository loginRepository;
    public LoginController(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    @GetMapping("/login/{username}/{password}")
    public Collection<Login> getLoginByUsername(
        @PathVariable("username") String username,
        @PathVariable("password") String password) {
        return loginRepository.findLoginByUsername(username,password);
    }

    @GetMapping("/login")
    public Collection<Login> Login() {
        return loginRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/login/{name}/{username}/{password}/{email}")
    public Login newLogin(Login newLogin,
    @PathVariable String name, 
    @PathVariable String username, 
    @PathVariable String password, 
    @PathVariable String email
    ){
            newLogin.setName(name);
            newLogin.setUsername(username);
            newLogin.setPassword(password);
            newLogin.setEmail(email);

            return loginRepository.save(newLogin);
    }
}
