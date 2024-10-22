package com.eximius.eximius.Controllers;

import java.util.List;
import org.springframework.web.bind.annotation.RestController;

import com.eximius.eximius.Repositories.UserRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import com.eximius.eximius.Entities.User;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/{id}")

    //public User user(@RequestParam(value = "name", defaultValue = "World") Long id, int dni){
    //    return new Greeting(1, "Hello " + name + "!" );
    //}
    public User getById(@PathVariable Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("The user with the ID was not found: " + id));
    }

    @PostMapping
    public User createUser(@RequestBody User user){
        return userRepository.save(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User detailsUser){
        User user =  userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("The user with the ID was not found: " + id));
        //user.setUsername(detailsUser.getUsername());
        user.setPassword(detailsUser.getPassword());
        return userRepository.save(user);
    }

    @DeleteMapping("{id}")
    public String deleteUser(@PathVariable Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("The user with the ID was not found: " + id));

        userRepository.delete(user);
        return "The user with the ID was" + id + " was deleted";
    }
}